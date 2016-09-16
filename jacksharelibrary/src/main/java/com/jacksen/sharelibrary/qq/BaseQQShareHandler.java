package com.jacksen.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jacksen.sharelibrary.BaseShareHandler;
import com.jacksen.sharelibrary.PlatformConfigHelper;
import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.exception.ConfigErrorException;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.exception.UnsupportedOperateException;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public abstract class BaseQQShareHandler extends BaseShareHandler {

    private String appId;
    protected Tencent tencent;

    public BaseQQShareHandler(Context context) {
        super(context);
    }

    @Override
    protected void checkConfig() throws Exception {
        appId = PlatformConfigHelper.getAppId(Platform.QQ);
        if (TextUtils.isEmpty(appId)) {
            throw new ConfigErrorException(context.getString(R.string.error_wx_app_id_invalid));
        }
    }

    @Override
    protected void initShare() throws ShareException {
        if (tencent == null) {
            tencent = Tencent.createInstance(appId, context);
        }
    }

    @Override
    protected void checkPlatform() throws ShareException {
        if (!Util.isMobileQQSupportShare(context)) {
            getShareListener().onError(getSharePlatform(), context.getString(R.string.error_qq_unsupported_version));
            throw new UnsupportedOperateException(context.getString(R.string.error_qq_unsupported_version));
        }
    }

    /**
     * do the real share operate.
     * <p>
     * must do it in the main thread.
     */
    protected void startShare(final Bundle bundle) {
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQQ((Activity) context, bundle, listener);
            }
        });
    }


    protected IUiListener listener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            if (getShareListener() != null) {
                getShareListener().onSuccess(getSharePlatform());
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (getShareListener() != null) {
                getShareListener().onError(getSharePlatform(), uiError.errorMessage);
            }
        }

        @Override
        public void onCancel() {
            if (getShareListener() != null) {
                getShareListener().onCancel(getSharePlatform());
            }
        }
    };

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
