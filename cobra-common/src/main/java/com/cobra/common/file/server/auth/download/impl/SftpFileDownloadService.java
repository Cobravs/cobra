package com.cobra.common.file.server.auth.download.impl;

import static com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum.*;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cobra.common.file.server.auth.download.DownloadInterface;
import com.cobra.common.file.server.auth.download.annonation.DownloadChannel;
import com.cobra.common.file.server.auth.model.FileServerAuthentication;
import com.cobra.common.file.server.auth.utils.FileDownUtils;
import com.cobra.common.file.server.auth.utils.SftpUtils;
import com.cobra.common.voice.tran.utils.FileUtil;
import com.jcraft.jsch.ChannelSftp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@DownloadChannel(value = {SFTP})
public class SftpFileDownloadService implements DownloadInterface {

    private static ConcurrentMap<String, FileServerAuthentication> sftpMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        initSftpParam();
    }

    public void initSftpParam() {
        String sftpServers = "10.10.10.10|22|sftpUser|123456;10.10.0.9|22|sftpUser";
        FileDownUtils.initFileServerLoginUser(sftpServers, sftpMap);
    }

    @Override
    public String downloadFile(String filePath) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(filePath);
        URI uri = new URI(filePath);
        String host = uri.getHost();
        if (!sftpMap.containsKey(host)) {
            initSftpParam();
        }
        FileServerAuthentication sftpUser = sftpMap.get(host);
        if (sftpUser == null) {
            initSftpParam();
            sftpUser = sftpMap.get(host);
            if (sftpUser == null) {
                log.error("SftpFileDownloadService get SFTP param null！");
                throw new RuntimeException("SftpFileDownloadService get SFTP param null！！！");
            }
        }
        String path = uri.getPath();
        ChannelSftp sftp = null;
        Instant now = Instant.now();
        try {
            try {
                sftp = SftpUtils.getChannel(sftpUser);
            } catch (Exception e) {
                initSftpParam();
                sftpUser = sftpMap.get(host);
                sftp = SftpUtils.getChannel(sftpUser);
            }
            log.info("SftpFileDownloadService download file:{} local file:{} start！", path, localFilePath);
            sftp.get(path, localFilePath);
            log.info("SftpFileDownloadService download file:{} local file:{} success！cost millis:{} ", path,
                localFilePath, ChronoUnit.MILLIS.between(now, Instant.now()));
            return localFilePath;
        } finally {
            SftpUtils.close(sftp);
        }
    }

}
