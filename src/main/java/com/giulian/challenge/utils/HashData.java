package com.giulian.challenge.utils;

import org.springframework.stereotype.Controller;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Controller
public class HashData {

    public String getHash(byte[] inputBytes, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {

        }
        return hashValue;
    }

}
