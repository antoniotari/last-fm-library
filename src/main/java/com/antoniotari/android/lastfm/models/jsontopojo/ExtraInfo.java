
package com.antoniotari.android.lastfm.models.jsontopojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtraInfo implements Parcelable {

    @SerializedName("links")
    @Expose
    public Links links;
    @SerializedName("published")
    @Expose
    public String published;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("content")
    @Expose
    public String content;

    protected ExtraInfo(Parcel in) {
        links = (Links) in.readValue(Links.class.getClassLoader());
        published = in.readString();
        summary = in.readString();
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(links);
        dest.writeString(published);
        dest.writeString(summary);
        dest.writeString(content);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExtraInfo> CREATOR = new Parcelable.Creator<ExtraInfo>() {
        @Override
        public ExtraInfo createFromParcel(Parcel in) {
            return new ExtraInfo(in);
        }

        @Override
        public ExtraInfo[] newArray(int size) {
            return new ExtraInfo[size];
        }
    };
}
