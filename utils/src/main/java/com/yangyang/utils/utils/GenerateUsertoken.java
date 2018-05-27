package com.yangyang.utils.utils;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class GenerateUsertoken {
    public static String SECRET = "hisense";
    static final Base64.Decoder decoder = Base64.getDecoder();
    static final Base64.Encoder encoder = Base64.getEncoder();

    /*    final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
    System.out.println(encodedText);*/
//解码
//System.out.println(new String(decoder.decode(encodedText) throws UnsupportedEncodingException, "UTF-8"));
    public static String createToken(String userId, Integer role) {
        Long timeeStamp = System.currentTimeMillis() + 1200000;
        String token = "";
        token = timeeStamp + "hit" + userId + "hit" + role;
        try {
            byte[] bytes = token.getBytes("UTF-8");
            token = encoder.encodeToString(bytes);
            token = encoder.encodeToString(token.getBytes("UTF-8"));
            return token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> getInfoFromToken(String token) {
        String info = new String(decoder.decode(token));
        info = new String(decoder.decode(info));
        String[] strings = info.split("hit");
        Long timeStamp= Long.valueOf(strings[0]);
        String userId = strings[1];
        Integer role = Integer.valueOf(strings[2]);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("role", role);
        map.put("timestamp",timeStamp);
        System.out.println(map);
        return map;
    }

    public static void main(String[] args) {
        String userId = "111111111111111";
        Integer role = 1;
        String token = createToken(userId, role);
        System.out.println(token);
        getInfoFromToken(token);
    }
}
