package com.jacksen.sharelibrary.core;

import android.content.Context;

import com.jacksen.sharelibrary.BaseShareHandler;
import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.wx.WxChatShareHandler;
import com.jacksen.sharelibrary.wx.WxMomentShareHandler;

import static com.jacksen.sharelibrary.core.Platform.WX_MOMENT;
import static com.jacksen.sharelibrary.core.Platform.WX_SESSION;
import static com.jacksen.sharelibrary.core.Platform.WX_MOMENT;
import static com.jacksen.sharelibrary.core.Platform.WX_SESSION;


/**
 * Created by Admin on 2016/8/25.
 */

public class ShareHandlerPool {

    private static ShareHandlerPool handlerPool = new ShareHandlerPool();

    private BaseShareHandler shareHandler;

    //
    private ShareHandlerPool() {

    }

    public static BaseShareHandler newHandler(Context context, @PlatformScope String platform) {
        BaseShareHandler handler = null;
        switch (platform) {
            case WX_SESSION:
                handler = new WxChatShareHandler(context);
                break;
            case WX_MOMENT:
                handler = new WxMomentShareHandler(context);
                break;
            default:
                handler = new DefaultShareHandler(context);
                break;
        }
        handlerPool.shareHandler = handler;
        return handler;
    }


    /**
     * @return
     */
    public static BaseShareHandler getCurrentHandler() {
        return handlerPool.shareHandler;
    }

}
