package com.example.shopping.common.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StringUtil {
    public static String GetMapToXML(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">");
            sb.append(entry.getValue());
            sb.append("</").append(entry.getKey()).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
