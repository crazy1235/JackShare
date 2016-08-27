package com.jacksen.sharelibrary;

/**
 * Created by Admin on 2016/8/24.
 */

public interface ShareListener {

    void onSuccess();

    void onError(String errMsg);

    void onCancel();
}
