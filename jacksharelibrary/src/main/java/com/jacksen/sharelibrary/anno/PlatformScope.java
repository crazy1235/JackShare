package com.jacksen.sharelibrary.anno;

import android.support.annotation.StringDef;

import com.jacksen.sharelibrary.core.Platform;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Admin on 2016/8/26.
 */

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@StringDef({Platform.WX_MOMENT, Platform.WX_SESSION})
public @interface PlatformScope {

}