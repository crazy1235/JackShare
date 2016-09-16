package com.jacksen.sharelibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.LoginListener;
import com.jacksen.sharelibrary.core.ShareHandlerPool;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.wx.param.BaseShareParam;

/**
 * Created by jacksen on 2016/8/24.
 */

public class JackShare {

    private static BaseShareHandler shareHandler;

    /**
     * init SDK
     *
     * @param context
     */
    public static void init(@NonNull Context context) {
        PlatformConfigHelper.parsePlatformInfo(context);
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

    public static void login(Context context, @PlatformScope String platform, LoginListener loginListener) {

    }

    /**
     * receive the result
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        shareHandler.onActivityResult(requestCode, resultCode, data);
    }

    private void release() {
        if (shareHandler != null) {
            shareHandler.release();
        }
        shareHandler = null;
    }


}
