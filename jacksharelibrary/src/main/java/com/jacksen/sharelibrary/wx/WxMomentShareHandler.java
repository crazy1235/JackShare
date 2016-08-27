package com.jacksen.sharelibrary.wx;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * Created by Admin on 2016/8/25.
 */
public class WxMomentShareHandler extends BaseWXShareHandler {

    public WxMomentShareHandler(Context context) {
        super(context);
    }

    @Override
    protected int getShareType() {
        return SendMessageToWX.Req.WXSceneTimeline; // 分享朋友圈
    }
}
