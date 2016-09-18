package com.antoniotari.android.lastfm.models.jsontopojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoniotari on 2016-09-11.
 */
public class LastFmAlbumResponse {

    @SerializedName ("album")
    @Expose
    public Album album;

}
