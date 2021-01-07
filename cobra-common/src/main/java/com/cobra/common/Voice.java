package com.cobra.common;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;

public class Voice {

    public static void convertToMp3(String sourcePath, String targetPath, int soundChannels) throws Exception {
        String codec = "libmp3lame";
        File source = new File(sourcePath);
        MultimediaObject msource = new MultimediaObject(source);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(codec);
        audio.setBitRate(16000);
        if (soundChannels != 0) {
            audio.setChannels(soundChannels);
        }
        audio.setSamplingRate(8000);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(msource, target, attrs);
    }
}
