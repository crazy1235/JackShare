package com.jacksen.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.exception.UnsupportedOperateException;
import com.jacksen.sharelibrary.param.ShareImageParam;
import com.jacksen.sharelibrary.param.ShareMusicParam;
import com.jacksen.sharelibrary.param.ShareTextParam;
import com.jacksen.sharelibrary.param.ShareVideoParam;
import com.jacksen.sharelibrary.param.ShareWebPageParam;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.Tencent;

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
        throw new UnsupportedOperateException(context.getString(R.string.error_qq_unsupported_share_text));
    }

    /**
     * do the real share operate.
     * <p>
     * must do it in the main thread.
     */
    private void startShare(final Bundle bundle) {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQQ((Activity) context, bundle, listener);
            }
        });
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
        throw new UnsupportedOperateException(context.getString(R.string.error_qq_unsupported_share_video));
    }

    /**
     * 必填：标题、跳转连接
     *
     * @param webPageParam
     * @throws ShareException
     */
    @Override
    protected void shareWebPage(@NonNull ShareWebPageParam webPageParam) throws ShareException {
        startShare(webPageParam.getShareQQParams(context));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, listener);
            if (resultCode == Constants.ACTIVITY_OK) {
                Tencent.handleResultData(data, listener);
            }
        }
    }

}
