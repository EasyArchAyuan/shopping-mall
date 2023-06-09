package com.example.shopping.common.mapper;

import com.example.shopping.common.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    List<SysRole> findAll();

    SysRole findById(int id);

    int countByName(String name);

    int insert(String name);

    int deleteById(int id);

    int updateStatus(int status, int id);
}
