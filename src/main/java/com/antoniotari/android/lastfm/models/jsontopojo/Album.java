package com.antoniotari.android.lastfm.models.jsontopojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antonio tari on 2016-09-11.
 */
public class Album extends BaseLastFm {

    @SerializedName("wiki")
    @Expose
    public ExtraInfo wiki;

    @SerializedName ("artist")
    @Expose
    public String artist;

    @SerializedName("listeners")
    @Expose
    public int listeners;

    @SerializedName("playcount")
    @Expose
    public int playcount;

    //TODO
    /**
     * tracks: {
     track: [
     {
     name: "Fuel",
     url: "https://www.last.fm/music/Metallica/_/Fuel",
     duration: "269",
     @attr: {
     rank: "1"
     },
     streamable: {
     #text: "0",
     fulltrack: "0"
     },
     artist: {
     name: "Metallica",
     mbid: "65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab",
     url: "https://www.last.fm/music/Metallica"
     }
     },
     {
     */
}