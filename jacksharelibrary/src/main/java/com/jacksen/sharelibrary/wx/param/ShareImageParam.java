package com.jacksen.sharelibrary.wx.param;

import android.graphics.Bitmap;

/**
 * Created by Admin on 2016/8/26.
 */

public class ShareImageParam extends BaseShareParam {

    private Bitmap bitmap;

    public ShareImageParam() {
        super();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
