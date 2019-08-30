package com.example.image_display;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private String id, title, url, des, keyword, email, date;
    private Boolean isShared;
    private int rating;

    protected Image(Parcel in) {
        id = in.readString();
        title = in.readString();
        url = in.readString();
        des = in.readString();
        keyword = in.readString();
        rating = in.readInt();
        email = in.readString();
        date = in.readString();
        isShared = in.readInt() == 1;

    }

    public Image(String id, String title, String url, String des, String keyword, int rating, String email, Boolean isShared, String date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.des = des;
        this.keyword = keyword;
        this.rating = rating;
        this.email = email;
        this.isShared = isShared;
        this.date = date;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(des);
        parcel.writeString(keyword);
        parcel.writeInt(rating);
        parcel.writeString(email);
        parcel.writeString(date);
        parcel.writeInt(isShared ? 1 : 0);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}