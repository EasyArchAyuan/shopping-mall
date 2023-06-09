package com.example.shopping.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>数据库表的映射类</p>
 *
 * @author EasyArchAyuan
 * @date 2021/8/9 22:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysAccess {
    private int id;
    private String name;
    private String url;
    private int status;
}
