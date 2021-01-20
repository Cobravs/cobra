package com.cobra.common.file.server.auth.download.impl;

import static com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.cobra.common.file.server.auth.download.DownloadInterface;
import com.cobra.common.file.server.auth.download.annonation.DownloadChannel;
import com.cobra.common.voice.tran.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DownloadChannel(value = {HTTP, HTTPS})
public class HttpFileDownloadService implements DownloadInterface {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String downloadFile(String url) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(url);
        Instant now = Instant.now();
        try {
            // request header
            RequestCallback requestCallback = request -> request.getHeaders()
                .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
            // response
            ResponseExtractor<Void> responseExtractor = response -> {
                // write file
                Files.copy(response.getBody(), Paths.get(localFilePath));
                return null;
            };
            restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);
        } catch (Throwable e) {
            log.error("HttpFileDownloadService error:", e);
            throw new Exception("HttpFileDownloadService download file ERROR！！！");
        }
        log.info("HttpFileDownloadService download SUCCESS ,COST millis:{}",
            ChronoUnit.MILLIS.between(now, Instant.now()));
        return localFilePath;
    }
}
