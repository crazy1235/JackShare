package com.jacksen.sharelibrary.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jacksen.sharelibrary.wx.BaseWXShareHandler;
import com.jacksen.sharelibrary.wx.WxChatShareHandler;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Admin on 2016/8/24.
 */

public abstract class BaseWXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI iwxapi;
    private BaseWXShareHandler shareHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1.
        shareHandler = (BaseWXShareHandler) ShareHandlerPool.getCurrentHandler();
        if (shareHandler == null) {
            shareHandler = new WxChatShareHandler(this);
        }

        // 2.
        if (iwxapi == null) {
            iwxapi = WXAPIFactory.createWXAPI(this, getAppId(), false);
            if (iwxapi.isWXAppInstalled()) {
                iwxapi.registerApp(getAppId());
            }
            iwxapi.handleIntent(getIntent(), this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (iwxapi != null) {
            iwxapi.handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        shareHandler.onResp(baseResp);
        // finish this activity
        this.finish();
    }

    protected void release() {
        if (shareHandler != null) {
            shareHandler.release();
        }
    }

    protected abstract String getAppId();

}
