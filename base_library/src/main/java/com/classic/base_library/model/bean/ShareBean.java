package com.classic.base_library.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.classic.base_library.model.bean.base.BaseBean;

/**
 * Created by sj on 2016/12/13.
 * 分享
 */

public class ShareBean extends BaseBean implements Parcelable {


    /**
     * title : 分享标题
     * content : 分享内容
     * url : http://test.haomaijr.com:8081/api/share
     * img : http://test.haomaijr.com:8081/img/share.png
     */

    private String title;
    private String content;
    private String url;
    private String img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.url);
        dest.writeString(this.img);
    }

    public ShareBean() {
    }

    protected ShareBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.url = in.readString();
        this.img = in.readString();
    }

    public static final Creator<ShareBean> CREATOR = new Creator<ShareBean>() {
        @Override
        public ShareBean createFromParcel(Parcel source) {
            return new ShareBean(source);
        }

        @Override
        public ShareBean[] newArray(int size) {
            return new ShareBean[size];
        }
    };
}
