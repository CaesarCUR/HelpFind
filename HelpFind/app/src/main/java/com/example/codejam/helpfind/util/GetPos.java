package com.example.codejam.helpfind.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zhouming on 2017/6/21.
 */

public class GetPos {
    private LocationManager _locationManager;
    private String provider;
    private Context mContext;

    public GetPos(Context mContext) {
        _locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        this.mContext = mContext;

    }

    public void getLocation() {
        List<String> providerlist = _locationManager.getProviders(true);
        if (providerlist.contains(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerlist.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
            Toast.makeText(mContext, "建议您打开GPS获取更好定位效果",Toast.LENGTH_SHORT ).show();
        }
        else {
            Toast.makeText(mContext, "您未打开网络或GPS，无法定位",Toast.LENGTH_SHORT ).show();
            return;
        }
        Location location = _locationManager.getLastKnownLocation(provider);
        if (location != null) {
            String currentPosition = "latitude is " + location.getLatitude() + "\n"
                    + "longitude is " + location.getLongitude();

            storelocation(location);
        //从sharedpreferenced中获取地址信息
            SharedPreferences pref= mContext.getSharedPreferences("location",MODE_PRIVATE );
            String loc=pref.getString("location", "meiyoua");
            Toast.makeText(MainActivity.this,"已定位当前位置："+loc , Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this,"已定位当前位置："+currentPosition , Toast.LENGTH_SHORT).show();

        }

        locationManager.requestLocationUpdates(provider, 5000, 5, locationListener);
    }
}
