package com.jacksen.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.param.ShareImageParam;
import com.jacksen.sharelibrary.param.ShareMusicParam;
import com.jacksen.sharelibrary.param.ShareTextParam;
import com.jacksen.sharelibrary.param.ShareVideoParam;
import com.jacksen.sharelibrary.param.ShareWebPageParam;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.Tencent;

/**
 * QZone只支持图文分享
 *
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public class QZoneShareHandler extends BaseQQShareHandler {

    public QZoneShareHandler(Context context) {
        super(context);
    }

    @Override
    protected String getSharePlatform() {
        return Platform.QZone;
    }


    /**
     * @param bundle
     */
    private void startShareToQzone(final Bundle bundle) {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQzone((Activity) context, bundle, listener);
            }
        });
    }

    /**
     * @param bundle
     */
    private void startPublishToQzone(final Bundle bundle) {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                tencent.publishToQzone((Activity) context, bundle, listener);
            }
        });
    }

    @Override
    protected void shareText(@NonNull ShareTextParam textParam) throws ShareException {

    }

    @Override
    protected void shareImage(@NonNull ShareImageParam imageParam) throws ShareException {

    }

    @Override
    protected void shareMusic(@NonNull ShareMusicParam musicParam) throws ShareException {

    }

    @Override
    protected void shareVideo(@NonNull ShareVideoParam videoParam) throws ShareException {

    }

    @Override
    protected void shareWebPage(@NonNull ShareWebPageParam webPageParam) throws ShareException {
        startShareToQzone(webPageParam.getShareQZoneParams(context));
//        startPublishToQzone(webPageParam.getShareQZoneParams(context));
    }

    /**
     * 发表说说
     */
    private void shareMood() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, listener);
            if (resultCode == Constants.ACTIVITY_OK) {
                Tencent.handleResultData(data, listener);
            }
        }
    }
}
