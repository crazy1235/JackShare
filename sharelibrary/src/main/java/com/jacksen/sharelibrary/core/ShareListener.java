package com.jacksen.sharelibrary.core;

/**
 * @author jacksen
 *         <br/>
 * @since 2016/8/23
 */
public interface ShareListener {

    void onStart();

    void onProgress();

    void onSuccess();

    void onError();

    void onCancel();

}
