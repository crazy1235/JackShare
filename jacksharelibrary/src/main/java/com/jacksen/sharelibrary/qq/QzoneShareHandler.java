package com.jacksen.sharelibrary.qq;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.wx.param.ShareImageParam;
import com.jacksen.sharelibrary.wx.param.ShareMusicParam;
import com.jacksen.sharelibrary.wx.param.ShareTextParam;
import com.jacksen.sharelibrary.wx.param.ShareVideoParam;
import com.jacksen.sharelibrary.wx.param.ShareWebPageParam;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public class QZoneShareHandler extends BaseQQShareHandler {

    public QZoneShareHandler(Context context) {
        super(context);
    }

    @Override
    protected String getSharePlatform() {
        return Platform.QZone;
    }

    @Override
    protected void shareText(@NonNull ShareTextParam textParam) throws ShareException {

    }

    @Override
    protected void shareImage(@NonNull ShareImageParam imageParam) throws ShareException {

    }

    @Override
    protected void shareMusic(@NonNull ShareMusicParam musicParam) throws ShareException {

    }

    @Override
    protected void shareVideo(@NonNull ShareVideoParam videoParam) throws ShareException {

    }

    @Override
    protected void shareWebPage(@NonNull ShareWebPageParam webPageParam) throws ShareException {

    }
}
