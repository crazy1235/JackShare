package com.jacksen.sharelibrary.core;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public interface AuthListener {

    /**
     * before Auth
     *
     * @param platform
     */
    void onPreAuth(@PlatformScope String platform);

    /**
     * Auth success
     *
     * @param platform
     * @param info
     */
    void onAuthSuccess(@PlatformScope String platform, String info);

    /**
     * Auth failure
     *
     * @param platform
     * @param errMsg
     */
    void onAuthError(@PlatformScope String platform, String errMsg);

    /**
     * Auth canceled
     *
     * @param platform
     */
    void onAuthCancel(@PlatformScope String platform);

}
