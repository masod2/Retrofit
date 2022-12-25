package com.example.Retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponseobj<Model> {
    @SerializedName("code")
    @Expose
    public int code;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Model data;
    public int getCode() {
        return code;
    }
    public Boolean getSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public Model getObject() {
        return data;
    }

}
