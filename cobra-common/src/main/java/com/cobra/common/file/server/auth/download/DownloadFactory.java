package com.cobra.common.file.server.auth.download;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cobra.common.file.server.auth.download.annonation.DownloadChannel;
import com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum;

@Component
public class DownloadFactory {

    private static ConcurrentMap<DownloadChannelEnum, DownloadInterface> fileDownloadInterfaceConcurrentMap =
        new ConcurrentHashMap<>();

    @Autowired
    public DownloadFactory(List<DownloadInterface> fileDownloadInterfaces) {
        for (DownloadInterface service : fileDownloadInterfaces) {
            DownloadChannelEnum[] values = service.getClass().getAnnotation(DownloadChannel.class).value();
            for (DownloadChannelEnum value : values) {
                fileDownloadInterfaceConcurrentMap.put(value, service);
            }
        }
    }

    public DownloadInterface create(DownloadChannelEnum type) {
        return fileDownloadInterfaceConcurrentMap.get(type);
    }
}
