package com.example.shopping.common.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>时间工具类</p>
 *
 * @author EasyArchAyuan
 * 2023/4/6 10:36
 */
@Component
public class DateUtil {
    /**
     * <p>获取纯年月日时分秒的字符串</p>
     */
    public String getNMDHIS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
