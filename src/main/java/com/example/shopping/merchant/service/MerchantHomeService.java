package com.example.shopping.merchant.service;

import com.example.shopping.common.mapper.SysCommentMapper;
import com.example.shopping.common.mapper.SysOrderMapper;
import com.example.shopping.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantHomeService {
    @Autowired
    SysCommentMapper commentMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysOrderMapper orderMapper;

    public int getCommandCount(int merchant) {
        return commentMapper.getAllCountByMt(merchant);
    }

    public int getUserCount() {
        return userMapper.userCount();
    }

    public int getOrderCount(int merchant) {
        return orderMapper.payCountByMt(merchant);
    }

}
