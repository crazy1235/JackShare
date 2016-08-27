package com.jacksen.sharelibrary;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.PlatformConfigHelper;
import com.jacksen.sharelibrary.ShareListener;
import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.ShareHandlerPool;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.wx.param.BaseShareParam;


/**
 * Created by Admin on 2016/8/24.
 */

public class JackShare {

    private static BaseShareHandler shareHandler;

    public static void init(@NonNull String appId, @NonNull String appSecret) {
        PlatformConfigHelper.configPlatform(appId, appSecret);
    }

    public static void share(Context context, @PlatformScope String platform, BaseShareParam shareParam, ShareListener shareListener) {
        /*if (shareHandler != null) {
            shareHandler = null;
        }*/
        shareHandler = ShareHandlerPool.newHandler(context, platform);
        try {
            shareHandler.share(shareParam, shareListener);
        } catch (ShareException e) {
            e.printStackTrace();
        }
    }

    private void release() {
        if (shareHandler != null) {
            shareHandler.release();
        }
        shareHandler = null;
    }
}
