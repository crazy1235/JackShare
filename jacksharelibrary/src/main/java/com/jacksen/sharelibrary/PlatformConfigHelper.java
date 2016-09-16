package com.jacksen.sharelibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.Platform;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2016/8/24.
 */
public class PlatformConfigHelper {

    public static final String APP_ID = "AppId";

    public static final String APP_SECRET = "AppSecret";

    public static final String ENABLE = "Enable";

    public static final String REDIRECT_URL = "RedirectUrl";

    private static Map<String, Map<String, String>> platformInfoMap = new HashMap<>();


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
                            Map<String, String> infoMap = new HashMap<>();
                            if (!TextUtils.isEmpty(parser.getAttributeValue(null, APP_ID))) {
                                infoMap.put(APP_ID, parser.getAttributeValue(null, APP_ID));
                            }
                            if (!TextUtils.isEmpty(parser.getAttributeValue(null, APP_SECRET))) {
                                infoMap.put(APP_SECRET, parser.getAttributeValue(null, APP_SECRET));
                            }
                            if (!TextUtils.isEmpty(parser.getAttributeValue(null, ENABLE))) {
                                infoMap.put(ENABLE, parser.getAttributeValue(null, ENABLE));
                            }
                            if (!TextUtils.isEmpty(parser.getAttributeValue(null, REDIRECT_URL))) {
                                infoMap.put(REDIRECT_URL, parser.getAttributeValue(null, REDIRECT_URL));
                            }
                            platformInfoMap.put(platName, infoMap);
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param platform
     * @return
     */
    public static Map<String, String> getPlatformInfo(@PlatformScope String platform) {
        return platformInfoMap.get(platform);
    }

    /**
     * @param platform
     * @return
     */
    public static String getAppId(@PlatformScope String platform) {
        return platformInfoMap.get(platform).get(APP_ID);
    }

}
