package com.jacksen.sharelibrary.wx.param;

import android.graphics.Bitmap;

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

    private String localImgPath; // 本地图片地址

    private String netImgPath; // 网络图片地址

    public ShareImageParam() {
        super();
    }

    public ShareImageParam(String localImgPath) {
        super();
        this.localImgPath = localImgPath;
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

    public String getLocalImgPath() {
        return localImgPath;
    }

    public void setLocalImgPath(String localImgPath) {
        this.localImgPath = localImgPath;
    }

    public String getNetImgPath() {
        return netImgPath;
    }

    public void setNetImgPath(String netImgPath) {
        this.netImgPath = netImgPath;
    }
}
