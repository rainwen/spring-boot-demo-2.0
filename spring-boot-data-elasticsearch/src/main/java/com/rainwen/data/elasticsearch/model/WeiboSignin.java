package com.rainwen.data.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author rain.wen
 * @since 2018/7/23 18:50
 */
@Document(indexName = "weibo", type = "weibo_signin", shards = 2)
public class WeiboSignin implements Serializable {

    /**
     * POI序号
     */
    @Id
    private String poiId;
    /**
     * 地点名
     */
    private String title;
    /**
     * 地址
     */
    private String address;
    /**
     * 经度
     */
    private String lon;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 城市代码
     */
    private String city;
    /**
     * POI类别代码
     */
    private String categoryName;
    /**
     * 签到次数
     */
    private Integer checkinNum;
    /**
     * 照片次数
     */
    private Integer photoNum;

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(Integer checkinNum) {
        this.checkinNum = checkinNum;
    }

    public Integer getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(Integer photoNum) {
        this.photoNum = photoNum;
    }

    @Override
    public String toString() {
        return "WeiboSignin{" +
                "poiId='" + poiId + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", city='" + city + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", checkinNum=" + checkinNum +
                ", photoNum=" + photoNum +
                '}';
    }
}
