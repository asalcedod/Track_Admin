package com.uninorte.carlos.track_admin;

/**
 * Created by carlos on 14/05/17.
 */

public class Track {
    String lat;
    String lon;

    public Track(String lat, String lon) {

        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}