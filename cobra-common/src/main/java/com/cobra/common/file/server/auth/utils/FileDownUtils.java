package com.cobra.common.file.server.auth.utils;

import java.util.concurrent.ConcurrentMap;

import com.cobra.common.file.server.auth.model.FileServerAuthentication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cobra
 * @since 2021/1/20 12:04
 */
@Slf4j
public class FileDownUtils {

    public static void initFileServerLoginUser(String servers, ConcurrentMap<String, FileServerAuthentication> map) {
        String[] splits = servers.split(";");
        for (String split : splits) {
            try {
                FileServerAuthentication authentication = new FileServerAuthentication(split);
                if (authentication.getHost() != null) {
                    map.put(authentication.getHost(), authentication);
                }
            } catch (Exception e) {
                log.error("sftp/ftp params error");
            }
        }

    }
}
