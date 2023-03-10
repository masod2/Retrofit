package com.example.Retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse<Model> {
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
    public List<Model> data = null;
    @SerializedName("object")
    @Expose
    public Model object;

    public int getCode() {
        return code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Model> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }

    public void setObject(Model object) {
        this.object = object;
    }

    public Model getObject() {
        return object;
    }

}
