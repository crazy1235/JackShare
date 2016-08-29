package com.jacksen.sharelibrary.wx.param;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * 三种形式：
 * 1. 项目中图片
 * 2. 磁盘中图片
 * 3. 网络上图片
 *
 * @author jacksen on 2016/8/26.
 */
public class ShareImageParam extends BaseShareParam {

    private Bitmap bitmap;

    private byte[] imageData;

    private String imagePath;

    public ShareImageParam() {
        super();
    }

    public ShareImageParam(String imagePath) {
        super();
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
