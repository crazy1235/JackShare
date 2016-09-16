package com.jacksen.sharelibrary.core;

/**
 * Created by Admin on 2016/8/25.
 */

public interface ShareStatus {

    interface StatusCode {

        int CODE_SHARE_SUCCESS = 100;

        int CODE_SHARE_CANCEL = 101;

        int CODE_SHARE_ERROR = 102;

        // 没有安装第三方
        int CODE_SHARE_ERROR_NOT_INSTALL = 1021;
        // 没有配置id
        int CODE_SHARE_ERROR_NO_ID = 1022;
        // 分享参数不正确
        int CODE_SHARE_ERROR_PARAM_INVALID = 1021;
        // 不支持分享到朋友圈
        int CODE_SHARE_ERROR_UNSUPPORTED_MOMENT = 1022;
    }

}
