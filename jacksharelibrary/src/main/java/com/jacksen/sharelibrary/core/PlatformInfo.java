package com.jacksen.sharelibrary.core;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2016/9/14.
 *
 * @author ys
 */

public class PlatformInfo {

    /**
     * parse platform info from JackShare.xml
     *
     * @param context
     */
    public static void parsePlatformInfo(Context context) {
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
}
