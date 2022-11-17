package com.dungc.ltc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notify {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("time_created")
    @Expose
    public String timeCreated;
    @SerializedName("status")
    @Expose
    public int status;
}
