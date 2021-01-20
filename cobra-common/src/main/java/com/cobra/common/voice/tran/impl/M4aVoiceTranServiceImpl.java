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
@FileType(M4A)
public class M4aVoiceTranServiceImpl implements VoiceTransInterface {

    @Override
    public String transCodePcm(String m4aPath, int audioChannels) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(m4aPath);
        String prefix = localFilePath.substring(0, localFilePath.length() - M4A.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(m4aPath, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat());
        return pcmFilePath;

    }

    @Override
    public String variableTransPcm(String m4aPath, int audioChannels, int bitRate, int samplingRate) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(m4aPath);
        String prefix = localFilePath.substring(0, localFilePath.length() - M4A.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(m4aPath, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat(), bitRate,
            samplingRate);
        return pcmFilePath;

    }
}
