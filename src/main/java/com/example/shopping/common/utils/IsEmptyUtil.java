package com.example.shopping.common.utils;

import org.springframework.stereotype.Component;

/**
 * <p>判空工具类</p>
 *
 * @author EasyArchAyuan
 * 2023/4/10 16:03
 */
@Component
public class IsEmptyUtil {

    public static IsEmptyUtil getInstance() {
        return IsEmptyUtilHoler.INSTANCE;
    }

    /**
     * <p>多个字符串判空操作,当存在有空字符时返回true</p>
     */
    public boolean strings(String... strings) {
        for (String string : strings) {
            if (string == null || "".equals(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>单例模式</p>
     */
    private static class IsEmptyUtilHoler {
        private static IsEmptyUtil INSTANCE = new IsEmptyUtil();
    }
}
