package com.cobra.common.voice.tran.impl;

import static com.cobra.common.voice.tran.enums.AudioFormatEnum.*;

import org.springframework.stereotype.Component;

import com.cobra.common.voice.tran.VoiceTransInterface;
import com.cobra.common.voice.tran.annonation.FileType;
import com.cobra.common.voice.tran.utils.FileUtil;
import com.cobra.common.voice.tran.utils.TranToPCM;

/**
 *
 */
@Component
@FileType(WAV)
public class WavVoiceTranServiceImpl implements VoiceTransInterface {

    @Override
    public String transCodePcm(String wavPath, int audioChannels) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(wavPath);
        String prefix = localFilePath.substring(0, localFilePath.length() - WAV.getValue().length());

        String pcmFilePath = prefix.concat(PCM.getValue());
        TranToPCM.convertToPcm(wavPath, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat());
        return pcmFilePath;
    }

    @Override
    public String variableTransPcm(String wavPath, int audioChannels, int bitRate, int samplingRate) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(wavPath);
        String prefix = localFilePath.substring(0, localFilePath.length() - WAV.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(wavPath, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat(), bitRate,
            samplingRate);
        return pcmFilePath;
    }
}
