
package com.antoniotari.android.lastfm.models.jsontopojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    public List<Artist_> artist = new ArrayList<Artist_>();

}
