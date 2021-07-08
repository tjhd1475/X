package com.qiashe.domain;

public class FI_Avg_Result {
    private Integer facilityItemId;
    private String title;
    private Float mark;
    private Float price;

    public FI_Avg_Result(Integer facilityItemId, String title, Float mark, Float price) {
        this.facilityItemId = facilityItemId;
        this.title = title;
        this.mark = mark;
        this.price = price;
    }

    public FI_Avg_Result() {
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

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
