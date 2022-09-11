package com.classic.base_library.model.http;

import java.security.MessageDigest;

public class MD5Encoder {

    public static String encode(String string) {
        byte[] hash = new byte[0];
        StringBuilder hex = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hex.toString();
    }
}
