package com.softy.softy.Utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Securite {
    public static String getSha256(String value){
        return DigestUtils.sha256Hex(value);
    }
}
