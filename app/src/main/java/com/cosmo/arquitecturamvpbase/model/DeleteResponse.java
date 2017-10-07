package com.cosmo.arquitecturamvpbase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ana.marrugo on 23/09/2017.
 */

public class DeleteResponse {
    @SerializedName("status")
    @Expose
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
