package com.jacksen.sharelib.demo.wxapi;


import com.jacksen.sharelib.demo.util.Constants;
import com.jacksen.sharelibrary.core.BaseWXEntryActivity;

public class WXEntryActivity extends BaseWXEntryActivity {

    @Override
    protected String getAppId() {
        return Constants.WX_APP_ID;
    }
}