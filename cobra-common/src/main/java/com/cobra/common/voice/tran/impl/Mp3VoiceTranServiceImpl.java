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
@FileType(MP3)
public class Mp3VoiceTranServiceImpl implements VoiceTransInterface {

    @Override
    public String transCodePcm(String mp3path, int audioChannels) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(mp3path);
        String prefix = localFilePath.substring(0, localFilePath.length() - MP3.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(mp3path, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat());
        return pcmFilePath;
    }

    @Override
    public String variableTransPcm(String mp3path, int audioChannels, int bitRate, int samplingRate) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(mp3path);
        String prefix = localFilePath.substring(0, localFilePath.length() - MP3.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(mp3path, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat(), bitRate,
            samplingRate);
        return pcmFilePath;
    }
}
