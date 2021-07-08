package com.qiashe.domain;

public class FacilityItem {
    private Integer facilityItemId;
    private String title;
    private String info;
    private Float positionX;
    private Float positionY;
    private Float price;
    private String time;

    public FacilityItem() {
    }

    public FacilityItem(Integer facilityItemId, String title, String info, Float positionX, Float positionY, Float price, String time) {
        this.facilityItemId = facilityItemId;
        this.title = title;
        this.info = info;
        this.positionX = positionX;
        this.positionY = positionY;
        this.price = price;
        this.time = time;
    }

    public FacilityItem(String title, String info, Float positionX, Float positionY, Float price, String time) {
        this.title = title;
        this.info = info;
        this.positionX = positionX;
        this.positionY = positionY;
        this.price = price;
        this.time = time;
    }

    public Integer getFacilityItemId() {
        return facilityItemId;
    }

    public void setFacilityItemId(Integer facilityItemId) {
        this.facilityItemId = facilityItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Float getPositionX() {
        return positionX;
    }

    public void setPositionX(Float positionX) {
        this.positionX = positionX;
    }

    public Float getPositionY() {
        return positionY;
    }

    public void setPositionY(Float positionY) {
        this.positionY = positionY;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
