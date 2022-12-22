
package com.example.Retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 public class HomeDeliverReq {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("details_address")
    @Expose
    private String detailsAddress;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("work_id")
    @Expose
    private Integer workId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("work")
    @Expose
    private Work work;
    @SerializedName("photo_order_home")
    @Expose
    private PhotoOrderHome photoOrderHome;
    @SerializedName("user")
    @Expose
    private User user;

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

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public PhotoOrderHome getPhotoOrderHome() {
        return photoOrderHome;
    }

    public void setPhotoOrderHome(PhotoOrderHome photoOrderHome) {
        this.photoOrderHome = photoOrderHome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
