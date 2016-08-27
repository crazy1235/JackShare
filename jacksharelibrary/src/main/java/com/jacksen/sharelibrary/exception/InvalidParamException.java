package com.jacksen.sharelibrary.exception;

import com.jacksen.sharelibrary.core.ShareStatus;

/**
 * 参数不正确异常
 * <p>
 * Created by ys on 2016/8/25.
 */

public class InvalidParamException extends ShareException {

    public InvalidParamException(String message) {
        super(message, ShareStatus.StatusCode.CODE_SHARE_ERROR_PARAM_INVALID);
    }
}
