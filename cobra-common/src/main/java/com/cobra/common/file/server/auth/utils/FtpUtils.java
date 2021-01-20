package com.cobra.common.file.server.auth.utils;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.cobra.common.file.server.auth.model.FileServerAuthentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpUtils {

    private static int READ_TIMEOUT = 120000;

    public static FTPClient getFtpClient(FileServerAuthentication ftpUser) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        // connect ftp server
        ftpClient.connect(ftpUser.getHost(), ftpUser.getPort());
        // login
        ftpClient.login(ftpUser.getUserName(), ftpUser.getPassword());
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(READ_TIMEOUT);
        // is success
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            log.error("connect failed...ftp server:" + ftpUser.getHost() + ":" + ftpUser.getPort());
            throw new RuntimeException("connect failed ftp server:" + ftpUser.getHost() + ":" + ftpUser.getPort());
        }
        log.debug("connect success...ftp server:{} port:{} ", ftpUser.getHost(), ftpUser.getPort());
        return ftpClient;
    }

    public static void closeFtpClient(FTPClient ftpClient) {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
