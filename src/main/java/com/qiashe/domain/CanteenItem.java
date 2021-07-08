package com.qiashe.domain;

public class CanteenItem {
    private Integer canteenItemId;
    private String name;
    private Integer facilityItemId;
    private String info;
    private Float price;
    private Float spicyRate;
    private Float sweetRate;
    private Float mark;

    public CanteenItem(Integer canteenItemId, String name, Integer facilityItemId, String info, Float price, Float spicyRate, Float sweetRate, Float mark) {
        this.canteenItemId = canteenItemId;
        this.name = name;
        this.facilityItemId = facilityItemId;
        this.info = info;
        this.price = price;
        this.spicyRate = spicyRate;
        this.sweetRate = sweetRate;
        this.mark = mark;
    }

    public CanteenItem() {
    }

    public CanteenItem(String name, Integer facilityItemId, String info, Float price, Float spicyRate, Float sweetRate, Float mark) {
        this.name = name;
        this.facilityItemId = facilityItemId;
        this.info = info;
        this.price = price;
        this.spicyRate = spicyRate;
        this.sweetRate = sweetRate;
        this.mark = mark;
    }

    public Integer getCanteenItemId() {
        return canteenItemId;
    }

    public void setCanteenItemId(Integer canteenItemId) {
        this.canteenItemId = canteenItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFacilityItemId() {
        return facilityItemId;
    }

    public void setFacilityItemId(Integer facilityItemId) {
        this.facilityItemId = facilityItemId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSpicyRate() {
        return spicyRate;
    }

    public void setSpicyRate(Float spicyRate) {
        this.spicyRate = spicyRate;
    }

    public Float getSweetRate() {
        return sweetRate;
    }

    public void setSweetRate(Float sweetRate) {
        this.sweetRate = sweetRate;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }
}
