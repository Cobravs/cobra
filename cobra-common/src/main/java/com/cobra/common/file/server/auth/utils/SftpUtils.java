package com.cobra.common.file.server.auth.utils;

import java.util.Properties;

import com.cobra.common.file.server.auth.model.FileServerAuthentication;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class SftpUtils {

    private static int TIME_OUT = 120000;

    /**
     *
     * @param sftpUser
     * @return
     * @throws JSchException
     */
    public static ChannelSftp getChannel(FileServerAuthentication sftpUser) throws JSchException {

        JSch jsch = new JSch();
        Session tempSession = jsch.getSession(sftpUser.getUserName(), sftpUser.getHost(), sftpUser.getPort());
        if (sftpUser.getPassword() != null) {
            tempSession.setPassword(sftpUser.getPassword());
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        tempSession.setConfig(config);
        tempSession.setTimeout(TIME_OUT);
        // connect with session
        tempSession.connect();
        // open channel
        Channel tempChannel = tempSession.openChannel("sftp");
        // connect channel
        tempChannel.connect();
        return (ChannelSftp)tempChannel;
    }

    public static void close(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                sftp.getSession().disconnect();
                sftp.quit();
                sftp.disconnect();
            }
        } catch (JSchException e) {
            log.error("SftpUtils close ERROR:", e);
        }
    }
}
