package com.jacksen.sharelibrary.util;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/8/23
 */
public enum Platform {

    WECHAT("wechat"), // weixin
    WECHAT_MOMENT("wechat_moment"), // weixin moment
    QQ("qq"),  // qq
    QZONE("qzone"), // qzone
    SINA("sina"); // sina

    private String platform;

    Platform(String platform) {
        this.platform = platform;
    }

}
