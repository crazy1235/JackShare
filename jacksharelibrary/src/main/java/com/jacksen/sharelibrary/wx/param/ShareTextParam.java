package com.jacksen.sharelibrary.wx.param;

/**
 * Created by Admin on 2016/8/24.
 */

public class ShareTextParam extends BaseShareParam {

    public ShareTextParam(String title, String content) {
        super(title, content);
    }

    public ShareTextParam(String title, String content, String targetUrl) {
        super(title, content, targetUrl);
    }


}
