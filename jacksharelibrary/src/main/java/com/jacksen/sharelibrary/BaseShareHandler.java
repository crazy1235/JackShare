package com.jacksen.sharelibrary;

import android.content.Context;

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

    // 检查配置
    protected abstract void checkConfig() throws Exception;

    // 初始化分享操作
    protected abstract void initShare() throws ShareException;

    // 检查平台信息
    protected abstract void checkPlatform() throws ShareException;

    // 分享文本
    protected abstract void shareText(ShareTextParam textParam) throws ShareException;

    // 分享图片
    protected abstract void shareImage(ShareImageParam imageParam) throws ShareException;

    // 分享音乐
    protected abstract void shareMusic(ShareMusicParam musicParam) throws ShareException;

    // 分享视频
    protected abstract void shareVideo(ShareVideoParam videoParam) throws ShareException;

    // 分享网页
    protected abstract void shareWebPage(ShareWebPageParam webPageParam) throws ShareException;
}
