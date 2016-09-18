package com.antoniotari.android.lastfm.models.jsontopojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoniotari on 2016-09-11.
 */
public abstract class BaseLastFm {

    @SerializedName ("name")
    @Expose
    public String name;

    @SerializedName("mbid")
    @Expose
    public String mbid;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("image")
    @Expose
    public List<Image> image = new ArrayList<Image>();

    @SerializedName("tags")
    @Expose
    public Tags tags;
}
