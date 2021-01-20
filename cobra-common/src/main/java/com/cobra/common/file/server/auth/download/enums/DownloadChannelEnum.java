package com.cobra.common.file.server.auth.download.enums;

import lombok.Getter;

@Getter
public enum DownloadChannelEnum {

    SFTP("sftp"),
    FTP("ftp"),
    HTTP("http"),
    HTTPS("https"),
    NAS("nas");

    private String name;

    DownloadChannelEnum(String name) {
        this.name = name;

    }

}
