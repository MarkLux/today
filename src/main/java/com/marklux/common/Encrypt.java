package com.marklux.common;

import com.marklux.exception.BaseException;
import com.marklux.exception.UnkownException;

import java.security.MessageDigest;

/**
 * Created by mark on 17/11/1.
 */
public class Encrypt {
    private static String salt = "aafje9890312hkaf";
    public static String encrypt(String secret) throws BaseException {
        try {
            secret = secret + salt;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(secret.getBytes());
            return md.digest().toString();
        }catch (Exception e) {
            throw new UnkownException("Fail to get MD5");
        }
    }

    public static boolean check(String raw,String old) throws BaseException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            raw = raw + salt;
            md.update(raw.getBytes());
            return md.digest().toString().equals(old);
        }catch (Exception e) {
            throw new UnkownException("Fail to get MD5");
        }
    }
}
