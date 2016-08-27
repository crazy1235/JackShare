package com.jacksen.sharelibrary.exception;

import com.jacksen.sharelibrary.core.ShareStatus;

/**
 * Created by Admin on 2016/8/26.
 */

public class ConfigErrorException extends ShareException {

    public ConfigErrorException(String message) {
        super(message, ShareStatus.StatusCode.CODE_SHARE_ERROR_NO_ID);
    }

    public ConfigErrorException(String message, int errorCode) {
        super(message, errorCode);
    }
}
