package com.cobra.common.voice.tran;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cobra.common.voice.tran.annonation.FileType;
import com.cobra.common.voice.tran.enums.AudioFormatEnum;

import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.Encoder;

/**
 *
 */
@Slf4j
@Component
public class VoiceTranFactory {

    private static ConcurrentMap<String, VoiceTransInterface> voiceTranInterfaceConcurrentMap =
        new ConcurrentHashMap<>();

    @Autowired
    public VoiceTranFactory(List<VoiceTransInterface> voiceTranServices) {
        for (VoiceTransInterface service : voiceTranServices) {
            AudioFormatEnum value = service.getClass().getAnnotation(FileType.class).value();
            voiceTranInterfaceConcurrentMap.put(value.getValue(), service);
        }
    }

    @Bean
    public Encoder createEncoder() {
        log.info("Init Encoder");
        return new Encoder();
    }

    public VoiceTransInterface create(String type) {
        return voiceTranInterfaceConcurrentMap.get(type);
    }
}
