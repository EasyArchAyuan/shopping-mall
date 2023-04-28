package com.example.shopping.user.service;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.index.GoodsIndex;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.repository.GoodsIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {
    @Autowired
    SysGoodsMapper goodsMapper;

    @Autowired
    GoodsIndexRepository goodsIndexRepository;

    /**
     * <p>mysql通过关键字模糊查找商品</p>
     */
    public List<SysGoods> mysqlSearchGoods(String keyword) {
        return goodsMapper.search(keyword);
    }

}
