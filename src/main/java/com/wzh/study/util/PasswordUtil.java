package com.wzh.study.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {

    public static String generateSalt(){

        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    public static String encryptPassword(String password,String salt){

        SimpleHash simpleHash = new SimpleHash("MD5",password, ByteSource.Util.bytes(salt),1024);
        return simpleHash.toHex();
    }
}
