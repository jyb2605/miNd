package com.mind.user;

import com.google.android.gms.maps.model.LatLng;

public class Point{

    String name;
    String address;
    LatLng latlng;

    Point(String name, String address, LatLng latlng) {
        this.name = name;
        this.address = address;
        this.latlng = latlng;
    }
}