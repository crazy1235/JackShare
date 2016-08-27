package com.jacksen.sharelibrary.exception;

/**
 * Created by Admin on 2016/8/25.
 */

public class ShareException extends Exception {

    private int errorCode = -1;

    private String message;

    public ShareException(String message) {
        this(message, -1);
    }

    public ShareException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return this.message;
    }
}


