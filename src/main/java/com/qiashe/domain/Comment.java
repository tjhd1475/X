package com.qiashe.domain;

public class Comment {
    private Integer id;
    private Integer userId;
    private Integer canteenItemId;
    private String content;
    private Float mark;
    private Integer isLike;

    public Comment(Integer id, Integer userId, Integer canteenItemId, String content, Float mark, Integer isLike) {
        this.id = id;
        this.userId = userId;
        this.canteenItemId = canteenItemId;
        this.content = content;
        this.mark = mark;
        this.isLike = isLike;
    }

    public Comment(Integer userId, Integer canteenItemId, String content, Float mark, Integer isLike) {
        this.userId = userId;
        this.canteenItemId = canteenItemId;
        this.content = content;
        this.mark = mark;
        this.isLike = isLike;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCanteenItemId() {
        return canteenItemId;
    }

    public void setCanteenItemId(Integer canteenItemId) {
        this.canteenItemId = canteenItemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }
}
