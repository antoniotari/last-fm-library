package com.antoniotari.android.lastfm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.antoniotari.android.lastfm.models.jsontopojo.ExtraInfo;
import com.antoniotari.android.lastfm.models.jsontopojo.LastFmArtistResponse;
import com.antoniotari.android.lastfm.models.jsontopojo.Tag;

/**
 * Created by antonio tari on 2016-05-22.
 */
public class LastFmArtist extends LastFmBase {

    public LastFmArtist(final LastFmArtistResponse lastFmArtistResponse) {
        mImages = new Images(lastFmArtistResponse.artist.image);
        mName = lastFmArtistResponse.artist.name;
        mMbid = lastFmArtistResponse.artist.mbid;
        mUrl = lastFmArtistResponse.artist.url;
        mTags = lastFmArtistResponse.artist.tags.tag;
        mBio = lastFmArtistResponse.artist.bio;
    }

    protected LastFmArtist(Parcel in) {
        super(in);
    }

    @SuppressWarnings ("unused")
    public static final Parcelable.Creator<LastFmArtist> CREATOR = new Parcelable.Creator<LastFmArtist>() {
        @Override
        public LastFmArtist createFromParcel(Parcel in) {
            return new LastFmArtist(in);
        }

        @Override
        public LastFmArtist[] newArray(int size) {
            return new LastFmArtist[size];
        }
    };
}
