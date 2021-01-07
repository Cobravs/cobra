package com.cobra.common;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.nio.charset.StandardCharsets;
import java.security.Security;


public class SM3Utils {


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String getSM3(String paramStr) {
        byte[] srcData = paramStr.getBytes(StandardCharsets.UTF_8);
        byte[] resultHash = hash(srcData);
        return ByteUtils.toHexString(resultHash).toUpperCase();
    }

    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(getSM3("superadmin1234566"));
        System.out.println(getSM3("admin123456"));
        System.out.println(getSM3("12345612345678"));
    }
}
