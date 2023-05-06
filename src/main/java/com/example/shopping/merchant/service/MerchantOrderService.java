package com.example.shopping.merchant.service;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantOrderService {
    @Autowired
    private SysOrderMapper orderMapper;

    @Autowired
    private SysGoodsMapper goodsMapper;

    /**
     * <p>获取订单信息</p>
     */
    public List<SysOrder> getOrderList(int merchantId, int page, int num, int flag) {
        List<SysOrder> voList = new ArrayList<>();
        switch (flag) {
            // 未支付
            case 0:
                voList = new ArrayList<>(orderMapper.findNotPayLimitByMt(merchantId, (page - 1) * num, num));
                break;
            // 已支付
            case 1:
                voList = orderMapper.findPayLimitByMt(merchantId, (page - 1) * num, num);
                break;
            // 全部订单
            case 2:
                voList = orderMapper.findLimitByMt(merchantId, (page - 1) * num, num);
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
     * <p>获取总页数</p>
     */
    public int getAllPage(int merchantId, int flag, int num) {
        switch (flag) {
            // 未支付
            case 0:
                return (int) Math.ceil((double) orderMapper.notPayCountByMt(merchantId) / (double) num);
            // 已支付
            case 1:
                return (int) Math.ceil((double) orderMapper.payCountByMt(merchantId) / (double) num);
            // 全部订单
            case 2:
                return (int) Math.ceil((double) orderMapper.orderCountByMt(merchantId) / (double) num);
            default:
                break;
        }
        return 1;
    }

    /**
     * <p>通过订单号查找</p>
     */
    public List<SysOrder> getOrderById(String orderId, int merchant) {
        SysOrder order = orderMapper.findByOrderIdAndMt(orderId, merchant);
        List<SysOrder> list = new ArrayList<>();
        if (order != null) {
            SysGoods sysGoods = goodsMapper.findById(order.getGoodsId());
            order.setGoodsName(sysGoods.getName());
            list.add(order);
        }
        return list;
    }

    public void updateOrderState(int orderState, int id) {
        int result = orderMapper.updateOrderStateById(orderState, id);
        System.out.println("update结果：" + result);
    }
}
