package com.cobra.common.voice.tran.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lombok.Getter;

/**
 * audio format
 */
@Getter
public enum AudioFormatEnum {
    /**
     *
     */
    M4A("m4a", "", ""),
    AMR("amr", "", ""),
    MP3("mp3", "", ""),
    WAV("wav", "", ""),
    PCM("pcm", "s16be", "pcm_s16le"),
    V3("v3", "", "");

    private String value;
    private String format;
    private String codec;

    AudioFormatEnum(String value, String format, String codec) {
        this.value = value;
        this.format = format;
        this.codec = codec;
    }

    public static AudioFormatEnum getFileType(String fileType) {
        return Arrays.stream(AudioFormatEnum.values()).filter(Predicate.isEqual(fileType)).findFirst().orElse(null);
    }

    public static String[] getExtensions() {
        List<String> extensions = new ArrayList<>();
        for (AudioFormatEnum value : AudioFormatEnum.values()) {
            extensions.add(value.getValue());
        }
        return extensions.toArray(new String[0]);
    }

}
