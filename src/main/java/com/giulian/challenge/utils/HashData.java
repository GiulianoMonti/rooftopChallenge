package com.giulian.challenge.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;

@Controller
@Slf4j
public class HashData {

    public String getHash(byte[] inputBytes,byte parameter, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();


            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

            hashValue+=parameter;
        } catch (Exception e) {

        }
        log.info(hashValue + " HASH AHAHSS");
        return hashValue;
    }

}
