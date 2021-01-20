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
@FileType(V3)
public class V3VoiceTranServiceImpl implements VoiceTransInterface {

    @Override
    public String transCodePcm(String inputV3Path, int audioChannels) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(inputV3Path);
        String prefix = localFilePath.substring(0, localFilePath.length() - V3.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(inputV3Path, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat());
        return pcmFilePath;
    }

    @Override
    public String variableTransPcm(String inputV3Path, int audioChannels, int bitRate, int samplingRate)
        throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(inputV3Path);
        String prefix = localFilePath.substring(0, localFilePath.length() - V3.getValue().length());
        String pcmFilePath = prefix.concat(PCM.getValue());

        TranToPCM.convertToPcm(inputV3Path, pcmFilePath, audioChannels, PCM.getCodec(), PCM.getFormat(), bitRate,
            samplingRate);
        return pcmFilePath;
    }
}
