package com.jacksen.sharelibrary.exception;

import com.jacksen.sharelibrary.core.ShareStatus;

/**
 * Created by Admin on 2016/8/26.
 */

public class UnsupportedOperateException extends ShareException {

    public UnsupportedOperateException(String message) {
        super(message);
    }

    public UnsupportedOperateException(String message, int code) {
        super(message, code);
    }
}
