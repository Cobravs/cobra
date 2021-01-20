package com.cobra.common.voice.tran.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cobra.common.voice.tran.enums.AudioFormatEnum;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {

    AudioFormatEnum value() default AudioFormatEnum.PCM;
}
