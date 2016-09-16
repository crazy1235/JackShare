package com.jacksen.sharelibrary.wx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import com.jacksen.sharelibrary.PlatformConfigHelper;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.core.ShareHandlerPool;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Admin on 2016/8/24.
 */

public class BaseWXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI iwxapi;
    private BaseWXShareHandler shareHandler;
    private String appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            appId = bundle.getString("id", "wx67948e9412b651ad");
        }

        appId = PlatformConfigHelper.getAppId(Platform.WX_SESSION);

        // 1.
        shareHandler = (BaseWXShareHandler) ShareHandlerPool.getCurrentHandler();
        if (shareHandler == null) {
            shareHandler = new WxChatShareHandler(this);
        }

        // 2.
        if (iwxapi == null) {
            iwxapi = WXAPIFactory.createWXAPI(this, appId, false);
            if (iwxapi.isWXAppInstalled()) {
                iwxapi.registerApp(appId);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return super.onTouchEvent(event);
    }

    protected void release() {
        if (shareHandler != null) {
            shareHandler.release();
        }
    }
}
