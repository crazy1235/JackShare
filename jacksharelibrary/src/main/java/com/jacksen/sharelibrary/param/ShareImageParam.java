package com.jacksen.sharelibrary.param;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;

import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.exception.InvalidParamException;
import com.jacksen.sharelibrary.util.ImageType;
import com.tencent.connect.share.QQShare;

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

    /**
     * 图片类型
     *
     * @return
     */
    public ImageType getImageType() {
        if (!TextUtils.isEmpty(netImgPath)) {
            return ImageType.NET;
        } else if (!TextUtils.isEmpty(localImgPath)) {
            return ImageType.LOCAL;
        } else if (bitmap != null && !bitmap.isRecycled()) {
            return ImageType.BITMAP;
        }
        return ImageType.UNKNOWN;
    }

    /**
     * @param context
     * @return
     */
    public Bundle getQQShareParams(Context context) throws InvalidParamException {
        Bundle bundle = null;
        if (TextUtils.isEmpty(getLocalImgPath())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_local_img_url_invalid));
        }
        bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, getLocalImgPath());
        return bundle;
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
