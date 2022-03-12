package com.giulian.challenge.utils;

import org.springframework.stereotype.Controller;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SyllablesCounter {



    public LinkedHashMap<String,Integer> countSyllables(String text, Integer chars) {
        StringBuilder str = new StringBuilder();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
// 3 pointers,
        int str_size = text.length();
        int p1 = 0;
        int p2 = 0;
        int p3 = chars;

        while (p1 < str_size) {
            //dynamic size
            for (int i = p2; i < p3; i++) {

//                System.out.print(text.charAt(i));
                str.append(text.charAt(i));
            }
            // ejercicio leetcode
            if (map.containsKey(str.toString())) {
                map.put(str.toString(), map.get(str.toString()) + 1);
            } else {
                map.put(str.toString(), 1);
            }

            // refreshemos el StringBuilder
            str.setLength(0);

            System.out.println();
            // testing more cases
            if (p3 == text.length() || p2 == text.length() - 1) {
                break;
            }
            // los pointers afuera del for
            p3++;
            p2++;
            p1++;
        }

        return map;

    }

}
