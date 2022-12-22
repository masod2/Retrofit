
package com.example.Retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 public class PhotoOrderHome {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("photo")
    @Expose
    private String photo;

     public PhotoOrderHome(String imgs ,int id , int orderId ) {
         this.id = id;
         this.orderId=orderId;
         this.photo =imgs ;
     }

     public PhotoOrderHome(String imgs) {
         this.photo= imgs;
     }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
