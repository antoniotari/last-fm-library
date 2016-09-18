
package com.antoniotari.android.lastfm.models.jsontopojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastFmArtistResponse {

    @SerializedName("artist")
    @Expose
    public Artist artist;

}
