package com.jacksen.sharelibrary.wx;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public class WxFavoriteHandler extends BaseWXShareHandler {
    public WxFavoriteHandler(Context context) {
        super(context);
    }

    @Override
    protected int getWxShareType() {
        return SendMessageToWX.Req.WXSceneFavorite;
    }
}
