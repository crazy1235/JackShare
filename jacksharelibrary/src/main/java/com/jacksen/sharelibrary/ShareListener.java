package com.jacksen.sharelibrary;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * Created by Admin on 2016/8/24.
 */

public interface ShareListener {

    /**
     * before share
     *
     * @param platform
     */
    void onPreShare(@PlatformScope String platform);

    /**
     * share success
     *
     * @param platform
     */
    void onSuccess(@PlatformScope String platform);

    /**
     * share failure
     *
     * @param platform
     * @param errMsg
     */
    void onError(@PlatformScope String platform, String errMsg);

    /**
     * share canceled
     *
     * @param platform
     */
    void onCancel(@PlatformScope String platform);
}
