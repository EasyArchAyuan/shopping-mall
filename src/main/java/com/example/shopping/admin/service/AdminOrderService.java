package com.example.shopping.admin.service;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    private SysGoodsMapper goodsMapper;

    /**
     * <p>获取订单信息</p>
     */
    public List<SysOrder> getOrderList(int page, int num, int flag) {
        List<SysOrder> voList = new ArrayList<>();
        switch (flag) {
            // 未支付
            case 0:
                voList = orderMapper.findNotPayLimit((page - 1) * num, num);
                break;
            // 已支付
            case 1:
                voList = orderMapper.findPayLimit((page - 1) * num, num);
                break;
            // 全部订单
            case 2:
                voList = orderMapper.findLimit((page - 1) * num, num);
                break;
            default:
                break;
        }
        voList.forEach(it -> {
            SysGoods sysGoods = goodsMapper.findById(it.getGoodsId());
            it.setGoodsName(sysGoods.getName());
        });
        return voList;
    }

    /**
     * <p>通过订单号查找</p>
     */
    public List<SysOrder> getOrderById(String orderId) {
        SysOrder order = orderMapper.findByOrderId(orderId);
        List<SysOrder> list = new ArrayList<>();
        if (order != null) {
            SysGoods sysGoods = goodsMapper.findById(order.getGoodsId());
            order.setGoodsName(sysGoods.getName());
            list.add(order);
        }
        return list;
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int flag, int num) {
        switch (flag) {
            case 0:// 未支付
                return (int) Math.ceil((double) orderMapper.notPayCount() / (double) num);
            case 1:// 已支付
                return (int) Math.ceil((double) orderMapper.payCount() / (double) num);
            case 2:// 全部订单
                return (int) Math.ceil((double) orderMapper.orderCount() / (double) num);
            case 3:
                return 1;
            default:
                break;
        }
        return 1;
    }

    public void updateOrderState(int orderState, int id) {
        int result = orderMapper.updateOrderStateById(orderState, id);
        System.out.println("update结果：" + result);
    }
}
