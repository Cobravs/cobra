package com.cobra.common.file.server.auth.download.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DownloadChannel {

    DownloadChannelEnum[] value() default {};
}
