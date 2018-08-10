package com.rainwen.data.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

/**
 * @author rain.wen
 * @since 2018/7/23 18:50
 */

/**
 * 索引初始化配置
 */
@Setting(settingPath = "/es/settings.json")
@Mapping(mappingPath = "/es/mappings.json")
@Document(indexName = "weibo", type = "weibo_checkin")
public class WeiboCheckin implements Serializable {

    /**
     * POI序号
     */
    @Id
    private String poiId;
    /**
     * 地点名
     */
    private String placeName;

    /**
     * 地址
     */
    private String address;
    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * POI类别
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

    /**
     * 地理位置经纬度
     * lat纬度，lon经度 "40.715,-74.011"
     */
    @GeoPointField
    private Location location;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Long)
    private Long createTime;

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WeiboSignin{" +
                "poiId='" + poiId + '\'' +
                ", placeName='" + placeName + '\'' +
                ", address='" + address + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", checkinNum=" + checkinNum +
                ", photoNum=" + photoNum +
                ", location=" + location +
                '}';
    }
}
