package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.mapper.SysOrderMapper;
import com.example.shopping.user.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "用户模块订单支付接口")
@RestController
public class UserPayController {
    @Autowired
    WxPayService wxPayService;
    @Autowired
    SysOrderMapper orderMapper;

    @ApiOperation("微信支付")
    @PostMapping("/user/pay/wx")
    public ModelAndView wx(ModelAndView modelAndView, String nums, String users, String merchants, String goods, String prices,
                           String notes, String address, String name, String phone, String code, String carts) {
        // 生成orderMark
        String orderMark = wxPayService.getOrderId();
        // 添加订单
        String res = wxPayService.addOrder(nums, orderMark, users, merchants, prices, notes, goods, address, name, phone, code, 0, carts);
        if (!res.equals("-1") && !res.equals("0") && !res.equals("")) {
            modelAndView.addObject("orderMark", orderMark);
            modelAndView.setViewName("redirect:/user/wxpay/index?mark=" + orderMark);
        }
        return modelAndView;
    }

    @ApiOperation("微信支付回调地址")
    @PostMapping("/user/wxpay/notify")
    public String wxNotify(HttpServletRequest request) {
        return wxPayService.wxNotify(request);
    }

    @GetMapping("/user/wxpay/success_notify")
    public String success_notify(String orderMark) {
        return wxPayService.successWxNotify(orderMark);
    }

    @ApiOperation("检查订单状态")
    @GetMapping("/user/check-order-status")
    public boolean checkOrderStatus(String orderMark) {
        List<SysOrder> orderList = orderMapper.findByOrderMark(orderMark);
        if (orderList.size() == 0) {
            return false;
        } else {
            for (SysOrder order : orderList) {
                if (order.getOrderState() != 1) {
                    return false;
                }
            }
            return true;
        }
    }

    @ApiOperation("订单未支付，重新显示二维码给用户支付")
    @GetMapping("/user/wxpay/index")
    public ModelAndView wxNotify(ModelAndView modelAndView, String mark) {
        String codeUrl = orderMapper.findByOrderMark(mark).get(0).getPayCodeUrl();
        modelAndView.addObject("codeUrl", codeUrl);
        modelAndView.addObject("orderMark", mark);
        modelAndView.setViewName("user/pay/wx");
        return modelAndView;
    }

}
