package com.example.shopping.common.mapper;

import com.example.shopping.common.entity.SysRoleAccess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleAccessMapper {
    List<SysRoleAccess> findAll();

    List<SysRoleAccess> findByRoleId(int roleId);

    int insert(int roleId, int accessId);

    int deleteById(int id);

    void deleteByRoleId(int roleId);

    int deleteByAccessId(int accessId);

    int updateStatus(int status, int id);
}
