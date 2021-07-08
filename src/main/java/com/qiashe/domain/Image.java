package com.qiashe.domain;

public class Image {
    private Integer imageId;
    private String imgLoc;
    private Integer canteenItemId;
    private Integer isMainImg;

    public Image() {
    }

    public Image(Integer imageId, String imgLoc, Integer canteenItemId, Integer isMainImg) {
        this.imageId = imageId;
        this.imgLoc = imgLoc;
        this.canteenItemId = canteenItemId;
        this.isMainImg = isMainImg;
    }

    public Image(String imgLoc, Integer canteenItemId, Integer isMainImg) {
        this.imgLoc = imgLoc;
        this.canteenItemId = canteenItemId;
        this.isMainImg = isMainImg;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }

    public Integer getCanteenItemId() {
        return canteenItemId;
    }

    public void setCanteenItemId(Integer canteenItemId) {
        this.canteenItemId = canteenItemId;
    }

    public Integer getIsMainImg() {
        return isMainImg;
    }

    public void setIsMainImg(Integer isMainImg) {
        this.isMainImg = isMainImg;
    }
}
