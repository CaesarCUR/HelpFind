package com.codejam.helpfind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Thing implements Serializable {
    private static final long serialVersionUID = -1010711775392052966L;
    private int img;
    private double lantitude;
    private  double longitude;
    private String name;
    private String address;
    private String time;
    public static List<Thing> things = new ArrayList<Thing>();

   static {things.add(new Thing(R.drawable.phone, 30.78373, 103.858854, "手机", "成都市", "2017/08/10"));
       things.add(new Thing(R.drawable.wallet, 30.78373, 103.858854, "wallet", "chengdujia", "2017/08/10"));
       things.add(new Thing(R.drawable.child, 30.60373, 103.798854, "孩子", "chengdujia", "2017/08/10"));
       things.add(new Thing(R.drawable.card, 30.86373, 103.678854, "卡包", "chengdujia", "2017/08/10"));
   }
    public Thing(int img, double lantitude, double longitude, String name, String address, String time) {
        this.img = img;
        this.lantitude = lantitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
        this.time = time;
    }


    public double getLantitude() {
        return lantitude;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
