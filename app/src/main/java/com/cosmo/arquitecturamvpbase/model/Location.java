package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ana.marrugo on 03/10/2017.
 */

public class Location implements Serializable {


    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("coodinates")
    @Expose
    private Double[] coodinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Double[] getCoodinates() {
        return coodinates;
    }

    public void setCoodinates(Double[] coodinates) {
        this.coodinates = coodinates;
    }
}