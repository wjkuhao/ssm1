package com.wangjian.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String getPasswrod(String str){
        String encode = bCryptPasswordEncoder.encode(str);
        return encode;
    }

    public static void main(String[] args) {
        String passwrod = getPasswrod("123456");
        System.out.println(passwrod);
    }
}
