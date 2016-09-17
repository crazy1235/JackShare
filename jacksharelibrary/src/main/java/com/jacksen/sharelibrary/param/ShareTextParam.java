package com.jacksen.sharelibrary.param;

/**
 * Created by Admin on 2016/8/24.
 */

public class ShareTextParam extends BaseShareParam {

    // 文本类型 title 字段不起作用

    public ShareTextParam() {
        super();
    }

    public ShareTextParam(String text) {
        super(null, text);
    }

}
