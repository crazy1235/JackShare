package com.jacksen.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.util.ImageType;
import com.jacksen.sharelibrary.wx.param.ShareImageParam;
import com.jacksen.sharelibrary.wx.param.ShareMusicParam;
import com.jacksen.sharelibrary.wx.param.ShareTextParam;
import com.jacksen.sharelibrary.wx.param.ShareVideoParam;
import com.jacksen.sharelibrary.wx.param.ShareWebPageParam;
import com.tencent.connect.share.QQShare;

/**
 * QQ分享支持图文、音乐、图片
 *
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public class QQChatShareHandler extends BaseQQShareHandler {
    public QQChatShareHandler(Context context) {
        super(context);
    }

    @Override
    protected String getSharePlatform() {
        return Platform.QQ;
    }

    @Override
    protected void shareText(@NonNull ShareTextParam textParam) throws ShareException {

    }

    /**
     * 必填：图片路径
     * 必须是本地图片
     *
     * @param imageParam
     * @throws ShareException
     */
    @Override
    protected void shareImage(@NonNull ShareImageParam imageParam) throws ShareException {
        startShare(imageParam.getQQShareParams(context));
    }

    /**
     * 必填： 标题、音乐文件链接、跳转连接
     *
     * @param musicParam
     * @throws ShareException
     */
    @Override
    protected void shareMusic(@NonNull ShareMusicParam musicParam) throws ShareException {
        startShare(musicParam.getShareQQParams(context));
    }

    @Override
    protected void shareVideo(@NonNull ShareVideoParam videoParam) throws ShareException {

    }

    /**
     * 必填：跳比、跳转连接
     *
     * @param webPageParam
     * @throws ShareException
     */
    @Override
    protected void shareWebPage(@NonNull ShareWebPageParam webPageParam) throws ShareException {
        startShare(webPageParam.getShareQQParams(context));
    }

}
