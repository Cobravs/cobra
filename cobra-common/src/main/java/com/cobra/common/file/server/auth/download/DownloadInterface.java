package com.cobra.common.file.server.auth.download;

/**
 * @author cobra
 * @since 2021/1/20 11:53
 */
public interface DownloadInterface {
    /**
     * download file
     * @param filePath file path ftp://10.10.10.10/test/2020.mp3
     *                 sftp://10.10.10.10/test/3040.mp3
     *                 /data/mp3/2020.mp3
     *                 http://www.test.com
     *                 https://www.test.com
     * @return local file path
     * @throws Exception
     */
    String downloadFile(String filePath) throws Exception;
}
