package com.cobra.common.voice.tran.impl;

import static com.cobra.common.voice.tran.enums.AudioFormatEnum.*;

import org.springframework.stereotype.Component;

import com.cobra.common.voice.tran.VoiceTransInterface;
import com.cobra.common.voice.tran.annonation.FileType;

/**
 *
 */
@Component
@FileType(PCM)
public class PcmVoiceTranServiceImpl implements VoiceTransInterface {

    @Override
    public String transCodePcm(String path, int audioChannels) {
        return path;
    }

    @Override
    public String variableTransPcm(String path, int audioChannels, int bitRate, int samplingRate) {
        return path;
    }
}
