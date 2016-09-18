
package com.antoniotari.android.lastfm.models.jsontopojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist extends BaseLastFm {

    @SerializedName("streamable")
    @Expose
    public String streamable;
    @SerializedName("ontour")
    @Expose
    public String ontour;
    @SerializedName("stats")
    @Expose
    public Stats stats;
    @SerializedName("similar")
    @Expose
    public Similar similar;
    @SerializedName("bio")
    @Expose
    public ExtraInfo bio;

}
