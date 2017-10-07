package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by ana.marrugo on 03/10/2017.
 */

public class Phone implements Serializable {



    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("location")
    @Expose
    private Location location;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumber() {
        return number;
    }

    public Location getLocation() {
        return location;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}