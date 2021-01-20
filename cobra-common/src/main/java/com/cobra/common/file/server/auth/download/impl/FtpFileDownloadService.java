package com.cobra.common.file.server.auth.download.impl;

import static com.cobra.common.file.server.auth.download.enums.DownloadChannelEnum.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import com.cobra.common.file.server.auth.download.DownloadInterface;
import com.cobra.common.file.server.auth.download.annonation.DownloadChannel;
import com.cobra.common.file.server.auth.model.FileServerAuthentication;
import com.cobra.common.file.server.auth.utils.FileDownUtils;
import com.cobra.common.file.server.auth.utils.FtpUtils;
import com.cobra.common.voice.tran.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DownloadChannel(value = {FTP})
public class FtpFileDownloadService implements DownloadInterface {

    private static ConcurrentMap<String, FileServerAuthentication> ftpMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        initFtpParam();
    }

    private void initFtpParam() {
        String ftpServers = "10.10.10.10|23|ftpUser|123456;10.10.0.9|22|ftpUser";
        FileDownUtils.initFileServerLoginUser(ftpServers, ftpMap);

    }

    @Override
    public String downloadFile(String ftpFilePath) throws Exception {
        String localFilePath = FileUtil.getLocalFilePath(ftpFilePath);
        URI uri = new URI(ftpFilePath);
        String host = uri.getHost();

        if (!ftpMap.containsKey(host)) {
            initFtpParam();
        }
        FileServerAuthentication ftpUser = ftpMap.get(host);
        if (ftpUser == null) {
            initFtpParam();
            ftpUser = ftpMap.get(host);
            if (ftpUser == null) {
                log.error("FtpFileDownloadService get FTP param null！");
                throw new Exception("FtpFileDownloadService get FTP param null！！！");
            }
        }
        String path = uri.getPath();
        FTPClient ftpClient = null;
        try (OutputStream os = new FileOutputStream(localFilePath)) {
            try {
                ftpClient = FtpUtils.getFtpClient(ftpUser);
            } catch (Exception e) {
                initFtpParam();
                ftpUser = ftpMap.get(host);
                ftpClient = FtpUtils.getFtpClient(ftpUser);
            }
            boolean bool = ftpClient.retrieveFile(path, os);
            if (!bool) {
                throw new RuntimeException("FtpFileDownloadService download" + ftpFilePath + "failure！！！");
            }
            return localFilePath;
        } finally {
            FtpUtils.closeFtpClient(ftpClient);
        }
    }
}
