package com.rainwen.data.elasticsearch.model;

import java.io.Serializable;

/**
 * @author rain.wen
 * @since 2018/7/31 17:54
 */
public class Location implements Serializable {

    private Double lat;

    private Double lon;

    public Location(){

    }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
