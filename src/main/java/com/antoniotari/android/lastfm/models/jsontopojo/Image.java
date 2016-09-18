
package com.antoniotari.android.lastfm.models.jsontopojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    @Expose
    public String Text;
    @SerializedName("size")
    @Expose
    public String size;

}
