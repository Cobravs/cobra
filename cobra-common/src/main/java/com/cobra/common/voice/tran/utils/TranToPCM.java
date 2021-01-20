package com.cobra.common.voice.tran.utils;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

public class TranToPCM {
    public static void convertToPcm(String sourcePath, String targetPath, int soundChannels, String codec,
        String format) throws Exception {
        // String codec = "pcm_s16le";
        File source = new File(sourcePath);
        MultimediaObject mSource = new MultimediaObject(source);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(codec);
        audio.setBitRate(16000);
        if (soundChannels != 0) {
            audio.setChannels(soundChannels);
        }
        audio.setSamplingRate(8000);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat(format);
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(mSource, target, attrs);
    }

    public static void convertToPcm(String sourcePath, String targetPath, int soundChannels, String codec,
        String format, int bitRate, int samplingRate) throws Exception {
        File source = new File(sourcePath);
        MultimediaObject mSource = new MultimediaObject(source);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(codec);
        audio.setBitRate(bitRate);
        if (soundChannels != 0) {
            audio.setChannels(soundChannels);
        }
        audio.setSamplingRate(samplingRate);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat(format);
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(mSource, target, attrs);
    }
}
