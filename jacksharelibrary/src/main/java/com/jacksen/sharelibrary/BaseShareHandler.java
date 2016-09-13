package com.jacksen.sharelibrary;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.wx.param.BaseShareParam;
import com.jacksen.sharelibrary.wx.param.ShareImageParam;
import com.jacksen.sharelibrary.wx.param.ShareMusicParam;
import com.jacksen.sharelibrary.wx.param.ShareTextParam;
import com.jacksen.sharelibrary.wx.param.ShareVideoParam;
import com.jacksen.sharelibrary.wx.param.ShareWebPageParam;

/**
 * Created by Admin on 2016/8/24.
 */

public abstract class BaseShareHandler {

    protected Context context;

    private ShareListener shareListener;

    public BaseShareHandler(Context context) {
        this.context = context;
        try {
            checkConfig();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void share(BaseShareParam shareParam, ShareListener shareListener) throws ShareException {
        this.shareListener = shareListener;

        // before share
        if (shareListener != null) {
            this.shareListener.onPreShare(getSharePlatform());
        }
        // init
        initShare();
        // check platform
        checkPlatform();
        //
        if (shareParam instanceof ShareTextParam) {
            shareText((ShareTextParam) shareParam);
        } else if (shareParam instanceof ShareImageParam) {
            shareImage((ShareImageParam) shareParam);
        } else if (shareParam instanceof ShareMusicParam) {
            shareMusic((ShareMusicParam) shareParam);
        } else if (shareParam instanceof ShareVideoParam) {
            shareVideo((ShareVideoParam) shareParam);
        } else if (shareParam instanceof ShareWebPageParam) {
            shareWebPage((ShareWebPageParam) shareParam);
        }
    }

    /**
     * @return
     */
    protected String buildTransaction() {
        return String.valueOf(System.currentTimeMillis());
    }

    public ShareListener getShareListener() {
        return shareListener;
    }

    /**
     *
     */
    public void release() {
        context = null;
        shareListener = null;
    }

    // share platform
    protected abstract @PlatformScope String getSharePlatform();

    // 检查配置
    protected abstract void checkConfig() throws Exception;

    // 初始化分享操作
    protected abstract void initShare() throws ShareException;

    // 检查平台信息
    protected abstract void checkPlatform() throws ShareException;

    // 分享文本
    protected abstract void shareText(@NonNull ShareTextParam textParam) throws ShareException;

    // 分享图片
    protected abstract void shareImage(@NonNull ShareImageParam imageParam) throws ShareException;

    // 分享音乐
    protected abstract void shareMusic(@NonNull ShareMusicParam musicParam) throws ShareException;

    // 分享视频
    protected abstract void shareVideo(@NonNull ShareVideoParam videoParam) throws ShareException;

    // 分享网页
    protected abstract void shareWebPage(@NonNull ShareWebPageParam webPageParam) throws ShareException;
}
