package com.example.codejam.helpfind.util;

import android.Manifest;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.codejam.helpfind.entity.StaticMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhouming on 2017/6/21.
 */

public class GetPos {

    private static final String BASE_URL = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

    private LocationManager _locationManager;
    private String provider;
    private Context mContext;

    /**
     * LocationListener object
     *
     * build for ...
     */
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            storeLocation(location);
        }
    };

    public GetPos(Context mContext) {
        _locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        this.mContext = mContext;

    }

    public void getLocation() {
        List<String> providerList = _locationManager.getProviders(true);

        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(mContext, "您未打开网络或GPS，无法定位", Toast.LENGTH_SHORT).show();
            return;
        }

        Location location = _locationManager.getLastKnownLocation(provider);

        if (location != null) {
            String currentPosition = "latitude is " + location.getLatitude() + "\n"
                    + "longitude is " + location.getLongitude();

            storeLocation(location);    // store in storage

            // 从sharedpreferenced中获取地址信息
            SharedPreferences pref = mContext.getSharedPreferences("location", MODE_PRIVATE);
            String loc = pref.getString("location", "Unknown");
            Toast.makeText(mContext, "已定位当前位置：" + loc, Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "已定位当前位置：" + currentPosition, Toast.LENGTH_SHORT).show();

            //TODO: post location data to BroadcastSquareActivity
        }

        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(mContext, "Consider check your internet premission", Toast.LENGTH_SHORT).show();
            return;
        }

        _locationManager.requestLocationUpdates(provider, 5000, 5, locationListener);
    }

    /**
     * StoreLocation method execute http request action and store action
     *
     * this method accept a final Location object, then get its location to store.
     * @param location a final Location object
     */
    public void storeLocation(final Location location) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: request for location's name
                HttpURLConnection conn = null;
                String url = BASE_URL + location.getLatitude() + ","
                        + location.getLongitude() + "&sensor=false";

                try {
                    // make request
                    URL mUrl = new URL(url);
                    conn = (HttpURLConnection) mUrl.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);
                    conn.setRequestProperty("Accept-Language", "zh-CN");

                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        String response = StaticMethods.getStringFromInputStream(is);

                        JSONObject resJson = new JSONObject(response);
                        JSONArray resArray = resJson.getJSONArray("results");

                        if (resArray.length() > 0) {
                            JSONObject subObject = resArray.getJSONObject(0);
                            String address = subObject.getString("formatted_address");
                            _store(address);    // 将获取的位置信息用store函数存储在sharedpreference中

                        }
                    } else {
                        throw new NetworkErrorException("Response status is " + conn.getResponseCode());
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void _store(final String address) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("location",MODE_PRIVATE ).edit();
        editor.putString("location", address);
        editor.commit();
        Toast.makeText(mContext, address, Toast.LENGTH_SHORT).show();
    }
}
