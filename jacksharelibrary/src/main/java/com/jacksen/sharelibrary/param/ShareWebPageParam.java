package com.jacksen.sharelibrary.param;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;

import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.exception.InvalidParamException;
import com.jacksen.sharelibrary.util.ImageType;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/8/24.
 */

public class ShareWebPageParam extends BaseShareParam {

    private Bitmap thumbBitmap;

    private ShareImageParam imageParam;

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

    public ShareImageParam getImageParam() {
        return imageParam;
    }

    public void setImageParam(ShareImageParam imageParam) {
        this.imageParam = imageParam;
    }

    /**
     * @param context
     * @return
     */
    public Bundle getShareQQParams(Context context) throws InvalidParamException {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(getTitle())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_title_invalid));
        }
        if (TextUtils.isEmpty(getTargetUrl())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_targeturl_invalid));
        }
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, getTitle());
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, getSummary());
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, getTargetUrl());
        if (ImageType.NET == imageParam.getImageType()) {
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageParam.getNetImgPath());
        } else if (ImageType.LOCAL == imageParam.getImageType()) {
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageParam.getLocalImgPath());
        }
        return bundle;
    }


    /**
     * @param context
     * @return
     * @throws InvalidParamException
     */
    public Bundle getShareQZoneParams(Context context) throws InvalidParamException {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(getTitle())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_title_invalid));
        }
        if (TextUtils.isEmpty(getTargetUrl())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_targeturl_invalid));
        }
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, getTitle());
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, getTargetUrl());
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, getSummary());
        ArrayList<String> imageUrls = new ArrayList<>();
        if (ImageType.NET == imageParam.getImageType()) {
            imageUrls.add(imageParam.getNetImgPath());
        } else if (ImageType.LOCAL == imageParam.getImageType()) {
            imageUrls.add(imageParam.getLocalImgPath());
        }
        bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);

        return bundle;
    }
}
