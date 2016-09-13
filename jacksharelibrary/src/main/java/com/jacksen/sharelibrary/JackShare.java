package com.jacksen.sharelibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.core.ShareHandlerPool;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.wx.param.BaseShareParam;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jacksen on 2016/8/24.
 */

public class JackShare {

    private static BaseShareHandler shareHandler;

    /**
     * init SDK
     *
     * @param context
     */
    public static void init(@NonNull Context context) {
        parsePlatformInfo(context);
    }

    public static void init(@NonNull String appId, @NonNull String appSecret) {
        PlatformConfigHelper.configPlatform(appId, appSecret);
    }

    public static void share(Context context, @PlatformScope String platform, BaseShareParam shareParam, ShareListener shareListener) {
        /*if (shareHandler != null) {
            shareHandler = null;
        }*/
        shareHandler = ShareHandlerPool.newHandler(context, platform);
        try {
            shareHandler.share(shareParam, shareListener);
        } catch (ShareException e) {
            e.printStackTrace();
        }
    }


    /**
     * parse platform info from JackShare.xml
     *
     * @param context
     */
    private static void parsePlatformInfo(Context context) {
        try {
            InputStream inputStream = context.getResources().getAssets().open("JackShare.xml");
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            int eventType = parser.getEventType();
            String[] platNames = Platform.values;

            while (XmlPullParser.END_DOCUMENT != eventType) {
                if (XmlPullParser.START_TAG == eventType) {

                    for (String platName : platNames) {
                        if (platName.equalsIgnoreCase(parser.getName())) {
                            String appId = parser.getAttributeValue(null, "AppId");
                            String appSecret = parser.getAttributeValue(null, "AppSecret");

                            Log.d("JackShare", appId + "--" + appSecret);
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void release() {
        if (shareHandler != null) {
            shareHandler.release();
        }
        shareHandler = null;
    }
}
