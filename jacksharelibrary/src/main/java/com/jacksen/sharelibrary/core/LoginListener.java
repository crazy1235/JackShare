package com.jacksen.sharelibrary.core;

import com.jacksen.sharelibrary.anno.PlatformScope;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/9/16
 */

public interface LoginListener {

    /**
     * before login
     *
     * @param platform
     */
    void onPreLogin(@PlatformScope String platform);

    /**
     * login success
     *
     * @param platform
     * @param info
     */
    void onLoginSuccess(@PlatformScope String platform, String info);

    /**
     * login failure
     *
     * @param platform
     * @param errMsg
     */
    void onLoginError(@PlatformScope String platform, String errMsg);

    /**
     * login canceled
     *
     * @param platform
     */
    void onLoginCancel(@PlatformScope String platform);

}
