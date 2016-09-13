package com.jacksen.sharelibrary.wx;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * Created by Admin on 2016/8/25.
 */

public class WxChatShareHandler extends BaseWXShareHandler {

    public WxChatShareHandler(Context context) {
        super(context);
    }

    @Override
    protected int getWxShareType() {
        return SendMessageToWX.Req.WXSceneSession; // wechat
    }

}
