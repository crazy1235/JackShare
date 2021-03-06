package com.jacksen.sharelibrary.core;

import android.content.Context;
import android.content.Intent;

import com.jacksen.sharelibrary.BaseShareHandler;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.param.ShareImageParam;
import com.jacksen.sharelibrary.param.ShareMusicParam;
import com.jacksen.sharelibrary.param.ShareTextParam;
import com.jacksen.sharelibrary.param.ShareVideoParam;
import com.jacksen.sharelibrary.param.ShareWebPageParam;

/**
 * Created by Admin on 2016/8/25.
 */

public class DefaultShareHandler extends BaseShareHandler {

    public DefaultShareHandler(Context context) {
        super(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    protected String getSharePlatform() {
        return Platform.DEFAULT;
    }

    @Override
    protected void checkConfig() throws Exception {

    }

    @Override
    protected void initShare() throws ShareException {

    }

    @Override
    protected void checkPlatform() throws ShareException {

    }

    @Override
    protected void shareText(ShareTextParam textParam) {

    }

    @Override
    protected void shareImage(ShareImageParam imageParam) throws ShareException {

    }

    @Override
    protected void shareMusic(ShareMusicParam musicParam) throws ShareException {

    }

    @Override
    protected void shareVideo(ShareVideoParam videoParam) throws ShareException {

    }

    @Override
    protected void shareWebPage(ShareWebPageParam webPageParam) {

    }
}
