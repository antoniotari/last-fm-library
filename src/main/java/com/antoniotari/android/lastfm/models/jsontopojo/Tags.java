
package com.antoniotari.android.lastfm.models.jsontopojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("tag")
    @Expose
    public List<Tag> tag = new ArrayList<Tag>();

}
