package com.antoniotari.android.lastfm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.antoniotari.android.lastfm.models.jsontopojo.ExtraInfo;
import com.antoniotari.android.lastfm.models.jsontopojo.Image;
import com.antoniotari.android.lastfm.models.jsontopojo.Tag;

/**
 * Created by antonio tari on 2016-09-11.
 */
public abstract class LastFmBase implements Parcelable {

    protected Images mImages;
    protected String mName;
    protected String mMbid;
    protected String mUrl;
    protected List<Tag> mTags;
    protected ExtraInfo mBio;

    public LastFmBase() {

    }

    public Images getImages() {
        return mImages;
    }

    public String getName() {
        return mName;
    }

    public String getMbid() {
        return mMbid;
    }

    public String getUrl() {
        return mUrl;
    }

    public List<Tag> getTags() {
        return mTags;
    }

    public ExtraInfo getBio() {
        return mBio;
    }

    protected LastFmBase(Parcel in) {
        mImages = (Images) in.readValue(Images.class.getClassLoader());
        mName = in.readString();
        mMbid = in.readString();
        mUrl = in.readString();
        if (in.readByte() == 0x01) {
            mTags = new ArrayList<Tag>();
            in.readList(mTags, Tag.class.getClassLoader());
        } else {
            mTags = null;
        }
        mBio = (ExtraInfo) in.readValue(ExtraInfo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mImages);
        dest.writeString(mName);
        dest.writeString(mMbid);
        dest.writeString(mUrl);
        if (mTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mTags);
        }
        dest.writeValue(mBio);
    }

    public static class Images implements Parcelable {
        private String small;
        private String medium;
        private String large;
        private String extralarge;
        private String mega;
        private String mDefault;

        public Images(List<Image> images) {
            for (Image image : images) {
                String size = image.size;
                if (size.equalsIgnoreCase("small")) {
                    small = image.Text;
                } else if (size.equalsIgnoreCase("medium")) {
                    medium = image.Text;
                } else if (size.equalsIgnoreCase("large")) {
                    large = image.Text;
                } else if (size.equalsIgnoreCase("extralarge")) {
                    extralarge = image.Text;
                } else if (size.equalsIgnoreCase("mega")) {
                    mega = image.Text;
                } else if (size.isEmpty()) {
                    mDefault = image.Text;
                }
            }
        }

        public String getSmall() {
            return small;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }

        public String getExtralarge() {
            return extralarge;
        }

        public String getMega() {
            return mega;
        }

        public String getDefault() {
            return mDefault;
        }

        public String getBiggestAvailable() {
            if (mega != null) {
                return mega;
            }
            if (extralarge != null) {
                return extralarge;
            }
            if (large != null) {
                return large;
            }
            if (medium != null) {
                return medium;
            }
            return small;
        }

        public String getSmallestAvailable() {
            if (small != null) {
                return small;
            }
            if (medium != null) {
                return medium;
            }
            if (large != null) {
                return large;
            }
            if (extralarge != null) {
                return extralarge;
            }
            return mega;
        }

        public String getAverageAvailable() {
            if (large != null) {
                return large;
            }
            if (extralarge != null) {
                return extralarge;
            }
            return mega;
        }

        protected Images(Parcel in) {
            small = in.readString();
            medium = in.readString();
            large = in.readString();
            extralarge = in.readString();
            mega = in.readString();
            mDefault = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(small);
            dest.writeString(medium);
            dest.writeString(large);
            dest.writeString(extralarge);
            dest.writeString(mega);
            dest.writeString(mDefault);
        }

        @SuppressWarnings ("unused")
        public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
            @Override
            public Images createFromParcel(Parcel in) {
                return new Images(in);
            }

            @Override
            public Images[] newArray(int size) {
                return new Images[size];
            }
        };
    }
}
