package com.jacksen.sharelibrary.core;

import android.content.Context;

import com.jacksen.sharelibrary.BaseShareHandler;
import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.qq.QQChatShareHandler;
import com.jacksen.sharelibrary.qq.QZoneShareHandler;
import com.jacksen.sharelibrary.wx.WxChatShareHandler;
import com.jacksen.sharelibrary.wx.WxFavoriteHandler;
import com.jacksen.sharelibrary.wx.WxMomentShareHandler;

import static com.jacksen.sharelibrary.core.Platform.QQ;
import static com.jacksen.sharelibrary.core.Platform.QZone;
import static com.jacksen.sharelibrary.core.Platform.WX_FAVORITE;
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
            case WX_FAVORITE:
                handler = new WxFavoriteHandler(context);
                break;
            case QQ:
                handler = new QQChatShareHandler(context);
                break;
            case QZone:
                handler = new QZoneShareHandler(context);
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
