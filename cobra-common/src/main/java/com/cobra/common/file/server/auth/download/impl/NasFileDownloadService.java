package com.cobra.common.file.server.auth.download.impl;

import static com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum.*;

import org.springframework.stereotype.Service;

import com.cobra.common.file.server.auth.download.DownloadInterface;
import com.cobra.common.file.server.auth.download.annonation.DownloadChannel;

@Service
@DownloadChannel(value = {NAS})
public class NasFileDownloadService implements DownloadInterface {

    @Override
    public String downloadFile(String filePath) {
        return filePath;
    }
}
