package com.example.myapplication.model;

/**
 * Created by Nitish on 25-05-2015.
 */

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Content implements Parcelable {

    private static final String PAGE_NO = "pageNo";
    private static final String CONTENT = "pageContent";

    public int pageNo;
    public String content;

    public Content(JSONObject obj) {
        try {
            this.pageNo = obj.getInt(PAGE_NO);
            this.content = obj.getString(CONTENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Content(Parcel in) {
        pageNo = in.readInt();
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pageNo);
        dest.writeString(content);
    }

//    public static final Creator<Content> CREATOR = new Creator<Content>() {
//        @Override
//        public Content createFromParcel(Parcel in) {
//            return new Content(in);
//        }
//
//        @Override
//        public Content[] newArray(int size) {
//            return new Content[][size];
//        }
//    };
}
