package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public class Customer implements Serializable {



    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("phoneList")
    @Expose
    private ArrayList<Phone> phoneList;

    @SerializedName("_id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Phone> getPhoneList() {
        return phoneList;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneList(ArrayList<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public void setId(String id) {
        this.id = id;
    }
}