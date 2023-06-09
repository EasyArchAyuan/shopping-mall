package com.example.shopping.user.service;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;

    /**
     * <p>获取用户订单</p>
     */
    public List<SysOrder> getOrderList(int userId, int page, int num) {
        return orderMapper.findByUser(userId, (page - 1) * num, num);
    }

    /**
     * <p>获取订单中的商品信息</p>
     */
    public List<SysGoods> getGoodsList(List<SysOrder> orderList) {
        List<SysGoods> list = new ArrayList<>();
        for (SysOrder order : orderList) {
            list.add(goodsMapper.findById(order.getGoodsId()));
        }
        return list;
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int userId, int num) {
        return (int) Math.ceil((double) orderMapper.orderCountByUser(userId) / (double) num);
    }
}
