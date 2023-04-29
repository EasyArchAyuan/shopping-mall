package com.example.shopping.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>数据库表的映射类</p>
 *
 * @author EasyArchAyuan
 * @date 2021/8/9 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysMtUi {
    private int id;
    private String url;
    private int width;
    private int height;
    private int merchant;
}