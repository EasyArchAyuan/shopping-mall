package com.example.shopping.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * <p>数据库表的映射类</p>
 *
 * @author EasyArchAyuan
 * @date 2021/8/9 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysGoods {
    private int id;
    private String name;
    private String describe;
    private String img;
    private BigDecimal price;
    private int state;
    private int merchant;
    private int stock;
    private int type;
}
