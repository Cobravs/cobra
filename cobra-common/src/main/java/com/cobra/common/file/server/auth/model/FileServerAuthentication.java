package com.cobra.common.file.server.auth.model;

import lombok.Getter;
import lombok.Setter;

/**
 * sftp/ftp authentication params
 */
@Getter
@Setter
public class FileServerAuthentication {

    private String userName;

    private String password;

    private String host;

    private int port;

    public FileServerAuthentication(String split) {
        String[] spl = split.split("\\|");
        if (spl.length == 3) {
            this.host = spl[0];
            this.port = Integer.parseInt(spl[1]);
            this.userName = spl[2];
        } else if (spl.length == 4) {
            this.host = spl[0];
            this.port = Integer.parseInt(spl[1]);
            this.userName = spl[2];
            this.password = spl[3];
        } else {
            throw new NullPointerException("sftp/ftp params error");
        }
    }

}
