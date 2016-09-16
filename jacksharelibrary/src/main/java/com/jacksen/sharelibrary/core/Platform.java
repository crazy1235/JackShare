package com.jacksen.sharelibrary.core;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * Created by Admin on 2016/8/26.
 */

public class Platform {

    public static final String DEFAULT = "";

    public static final String WX_SESSION = "Wechat";

    public static final String WX_MOMENT = "WechatMoments";

    public static final String WX_FAVORITE = "WechatFavorite";

    public static final String QQ = "QQ";

    public static final String QZone = "QZone";

    public static String[] values = new String[]{WX_SESSION, WX_MOMENT, WX_FAVORITE, QQ, QZone};

    @PlatformScope
    private String platform;

    public Platform(@PlatformScope String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return this.platform;
    }
}
