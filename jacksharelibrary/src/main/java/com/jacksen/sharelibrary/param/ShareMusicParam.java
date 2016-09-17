package com.jacksen.sharelibrary.param;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.exception.InvalidParamException;
import com.tencent.connect.share.QQShare;

/**
 * Created by Admin on 2016/8/26.
 */

public class ShareMusicParam extends BaseShareParam {

    private String audioUrl;

    private String imgPath;

    public ShareMusicParam() {
        super();
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * 返回QQ分享音乐的参数
     */
    public Bundle getShareQQParams(Context context) throws InvalidParamException {
        Bundle bundle = null;
        if (TextUtils.isEmpty(getTitle())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_title_invalid));
        }
        if (TextUtils.isEmpty(getTargetUrl())) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_targeturl_invalid));
        }
        if (TextUtils.isEmpty(audioUrl)) {
            throw new InvalidParamException(context.getString(R.string.error_qq_param_audiourl_invalid));
        }
        bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, getTitle());
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, getTargetUrl());
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioUrl);
        if (!TextUtils.isEmpty(getSummary())) {
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, getSummary());
        }
        if (!TextUtils.isEmpty(imgPath)){
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgPath);
        }

        return bundle;
    }
}
