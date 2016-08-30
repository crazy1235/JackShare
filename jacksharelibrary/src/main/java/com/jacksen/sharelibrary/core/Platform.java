package com.jacksen.sharelibrary.core;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * Created by Admin on 2016/8/26.
 */

public class Platform {

    public static final String DEFAULT = "";

    public static final String WX_SESSION = "wx_session";

    public static final String WX_MOMENT = "wx_moment";

    public static final String WX_FAVORITE = "wx_favorite";

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
