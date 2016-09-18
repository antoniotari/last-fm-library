package com.antoniotari.android.lastfm;

import java.io.IOException;
import java.lang.ref.WeakReference;

import com.google.gson.Gson;

import com.antoniotari.android.lastfm.models.jsontopojo.LastFmAlbumResponse;
import com.antoniotari.android.lastfm.models.jsontopojo.LastFmArtistResponse;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by antoniotari on 2016-05-22.
 */
public enum LastFm {
    INSTANCE;

    // private static final String LASTFM_API_KEY="0047e9a1eac2a9482a32e30b86c81e23";
    private static final String LASTFM_BASE_URL="http://ws.audioscrobbler.com/2.0/?";
    private static final String LASTFM_ARTIST_URL="method=artist.getInfo&artist=";
    private static final String LASTFM_ALBUM_URL="method=album.getInfo&artist=%s&album=%s";

    private String mApiKey;
    private WeakReference<Object> weakTag;

    public void init(final String apiKey){
        mApiKey = apiKey;
    }

    public void setTag(Object tag) {
        weakTag=new WeakReference<Object>(tag);
    }

    public Object getTag() {
        return weakTag.get();
    }

    public LastFmArtist getArtistImage(String artist) throws IOException {
        String url= LASTFM_BASE_URL + LASTFM_ARTIST_URL +
                sanitizeName(artist) +
                "&api_key=" +
                mApiKey +
                "&format=json";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        String responseString = response.body().string();
        LastFmArtistResponse lastFmArtistResponse = new Gson().fromJson(responseString,LastFmArtistResponse.class);
        LastFmArtist lastFmArtist = null;
        try {
            lastFmArtist= new LastFmArtist(lastFmArtistResponse);
        }catch (NullPointerException e){

        }

        if(lastFmArtist == null || (lastFmArtist.getImages().getSmall().isEmpty() &&
                lastFmArtist.getImages().getMedium().isEmpty() &&
                lastFmArtist.getImages().getLarge().isEmpty() &&
                lastFmArtist.getImages().getExtralarge().isEmpty() &&
                lastFmArtist.getImages().getMega().isEmpty())) {
            lastFmArtist = getArtistImage(splitName(artist));
        }

        return lastFmArtist;

        //JSONObject jsonObject = new JSONObject(responseString);
        //return parseResponse(jsonObject);
    }

    public LastFmAlbum getAlbumInfo(String artist, String albumName) throws IOException {
        String url= LASTFM_BASE_URL +
                String.format(LASTFM_ALBUM_URL, sanitizeName(artist), sanitizeName(albumName)) +
                "&api_key=" +
                mApiKey +
                "&format=json";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        String responseString = response.body().string();
        LastFmAlbumResponse lastFmAlbumResponse = new Gson().fromJson(responseString,LastFmAlbumResponse.class);
        LastFmAlbum lastFmAlbum = null;
        try {
            lastFmAlbum= new LastFmAlbum(lastFmAlbumResponse);
        }catch (NullPointerException e){

        }

//        if(lastFmAlbum == null || (lastFmAlbum.getImages().getSmall().isEmpty() &&
//                lastFmAlbum.getImages().getMedium().isEmpty() &&
//                lastFmAlbum.getImages().getLarge().isEmpty() &&
//                lastFmAlbum.getImages().getExtralarge().isEmpty() &&
//                lastFmAlbum.getImages().getMega().isEmpty())) {
//            lastFmAlbum = getAlbumInfo(splitName(artist), albumName);
//        }

        return lastFmAlbum;

        //JSONObject jsonObject = new JSONObject(responseString);
        //return parseResponse(jsonObject);
    }

    private String sanitizeName(String artist){
        return artist.trim().replace(" ","%20");
    }

    private String splitName(String name){
        if(splitName(name,"&")!=null){
            return splitName(name,"&");
        } else if(splitName(name,"/")!=null){
            return splitName(name,"/");
        } else if(splitName(name,"-")!=null){
            return splitName(name,"-");
        } else if(splitName(name,",")!=null){
            return splitName(name,",");
        } else {
            return "artist";
        }
    }

    private String splitName(String name, String splitChar){
        String[] newArtist = name.split(splitChar);
        if(newArtist.length>1){
            return newArtist[0];
        } else {
            return null;
        }
    }
}