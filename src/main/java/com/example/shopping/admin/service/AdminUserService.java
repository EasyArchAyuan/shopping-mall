package com.example.shopping.admin.service;

import com.example.shopping.common.entity.SysUser;
import com.example.shopping.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    SysUserMapper userMapper;

    /**
     * <p>获取用户信息</p>
     */
    public List<SysUser> getUserList(int page, int num) {
        return userMapper.findLimit((page - 1) * num, num);
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int num) {
        return (int) Math.ceil((double) userMapper.userCount() / (double) num);
    }
}
