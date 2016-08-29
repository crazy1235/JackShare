package com.jacksen.sharelibrary;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * Created by Admin on 2016/8/24.
 */

public interface ShareListener {

    void onSuccess();

    void onError(String errMsg);

    void onCancel();

    /*void onSuccess(@PlatformScope String platform);

    void onError(@PlatformScope String platform, String errMsg);

    void onCancel(@PlatformScope String platform);*/
}
