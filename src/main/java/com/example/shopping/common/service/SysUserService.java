package com.example.shopping.common.service;

import com.example.shopping.common.entity.SysUser;
import com.example.shopping.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author EasyArchAyuan
 * @date 2021/8/9 22:51
 */
@Service
public class SysUserService {
    @Autowired
    SysUserMapper userMapper;

    public SysUser selectById(int id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public SysUser getUserInfo(String email) {
        return userMapper.getUserInfo(email);
    }

    public List<SysUser> findAll() {
        return userMapper.findAll();
    }

    public int insert(String username, String password, String email, int mark) {
        return userMapper.insert(username, password, email, mark);
    }

    public int updateMark(int mark, String email) {
        return userMapper.updateMark(mark, email);
    }

    public int update(String username, String password, String email) {
        return userMapper.update(username, password, email);
    }
}