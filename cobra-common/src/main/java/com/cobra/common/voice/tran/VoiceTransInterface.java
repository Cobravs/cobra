package com.cobra.common.voice.tran;

/**
 * @author cobra
 * @since 2021/1/20 9:34
 */
public interface VoiceTransInterface {

    /**
     * tran to pcm
     * @param path source voice path
     * @param audioChannels channel number
     * @return pcm path
     * @throws Exception
     */
    String transCodePcm(String path, int audioChannels) throws Exception;

    /**
     * tran to pcm with params
     * @param path source voice path
     * @param audioChannels channel number
     * @param bitRate bit rate
     * @param samplingRate sampling rate
     * @return pcm path
     * @throws Exception
     */
    String variableTransPcm(String path, int audioChannels,int bitRate,int samplingRate ) throws Exception;
}
