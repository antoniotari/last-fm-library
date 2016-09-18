package com.antoniotari.android.lastfm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.antoniotari.android.lastfm.models.jsontopojo.ExtraInfo;
import com.antoniotari.android.lastfm.models.jsontopojo.LastFmAlbumResponse;
import com.antoniotari.android.lastfm.models.jsontopojo.Tag;

/**
 * Created by antoniotari on 2016-09-11.
 */
public class LastFmAlbum extends LastFmBase {

    private String mArtist;

    public LastFmAlbum(final LastFmAlbumResponse lastFmAlbumResponse) {
        mImages = new Images(lastFmAlbumResponse.album.image);
        mName = lastFmAlbumResponse.album.name;
        mMbid = lastFmAlbumResponse.album.mbid;
        mUrl = lastFmAlbumResponse.album.url;
        mTags = lastFmAlbumResponse.album.tags.tag;
        mBio = lastFmAlbumResponse.album.wiki;
        mArtist = lastFmAlbumResponse.album.artist;
    }

    protected LastFmAlbum(Parcel in) {
        super(in);
        mArtist = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mArtist);
    }

    @SuppressWarnings ("unused")
    public static final Parcelable.Creator<LastFmAlbum> CREATOR = new Parcelable.Creator<LastFmAlbum>() {
        @Override
        public LastFmAlbum createFromParcel(Parcel in) {
            return new LastFmAlbum(in);
        }

        @Override
        public LastFmAlbum[] newArray(int size) {
            return new LastFmAlbum[size];
        }
    };
}