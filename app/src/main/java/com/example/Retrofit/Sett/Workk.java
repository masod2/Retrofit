package com.example.Retrofit.Sett;

public class Workk {

    private int _id;
    private String _name;

    public Workk() {
        this._id = 0;
        this._name = "";
    }

    public Workk(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }
}
