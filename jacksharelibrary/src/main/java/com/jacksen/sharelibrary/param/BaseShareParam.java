package com.jacksen.sharelibrary.param;

import android.text.TextUtils;

/**
 * Created by Admin on 2016/8/24.
 */

public abstract class BaseShareParam {

    protected String title;

    protected String content;

    protected String description;

    protected String targetUrl;

    public BaseShareParam() {

    }

    public BaseShareParam(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BaseShareParam(String title, String content, String targetUrl) {
        this.title = title;
        this.content = content;
        this.targetUrl = targetUrl;
    }

    /**
     * for qq
     *
     * @return
     */
    public String getSummary() {
        return TextUtils.isEmpty(getContent()) ? (TextUtils.isEmpty(getDescription()) ?
                "" : getDescription()) : getContent();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
