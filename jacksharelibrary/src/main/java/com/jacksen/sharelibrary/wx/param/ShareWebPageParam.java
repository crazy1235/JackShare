package com.jacksen.sharelibrary.wx.param;

import android.graphics.Bitmap;

/**
 * Created by Admin on 2016/8/24.
 */

public class ShareWebPageParam extends BaseShareParam {

    private Bitmap thumbBitmap;

    public ShareWebPageParam() {
    }

    public ShareWebPageParam(String title, String content) {
        super(title, content);
    }

    public ShareWebPageParam(String title, String content, String targetUrl) {
        super(title, content, targetUrl);
    }

    public Bitmap getThumbBitmap() {
        return thumbBitmap;
    }

    public void setThumbBitmap(Bitmap thumbBitmap) {
        this.thumbBitmap = thumbBitmap;
    }
}
