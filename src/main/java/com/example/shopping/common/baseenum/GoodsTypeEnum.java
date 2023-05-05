package com.example.shopping.common.baseenum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ayuan
 * @Description: 商品类型枚举
 * @date 2023/5/5 14:56
 */
public enum GoodsTypeEnum {

    Vip(1, "会员卡"),
    Tableware(2, "餐具"),
    Bathroom(3, "卫浴用品"),
    Electrical(4, "电器"),
    DailyNecessities(5, "日杂用品"),
    CleaningSupplies(6, "清洁用品"),
    HomeSupplies(7, "家居用品"),
    ;


    private final Integer code;
    private final String desc;
    private static final Map<Integer, String> map = new HashMap<>();

    GoodsTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static {
        for (GoodsTypeEnum val : GoodsTypeEnum.values()) {
            map.put(val.getCode(), val.getDesc());
        }
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static Map<Integer, String> getMap() {
        return map;
    }

    public static String getDescByType(Integer type) {
        return map.get(type);
    }
}
