package com.example.shopping.merchant.controller;

import com.example.shopping.common.entity.SysMt;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.merchant.service.MerchantOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "商户模块订单管理接口")
@RestController
public class MerchantOrderController {
    @Autowired
    MerchantOrderService orderService;

    @ApiOperation("订单页面")
    @GetMapping("/merchant/order")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView, @RequestParam(defaultValue = "2") int flag, @RequestParam(defaultValue = "1") int page) {
        SysMt merchant = (SysMt) request.getSession().getAttribute("merchant");
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderList(merchant.getId(), page, 18, flag);
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        int allPage = orderService.getAllPage(merchant.getId(), flag, 18);
        modelAndView.addObject("page", page);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }

    @ApiOperation("通过订单号查找订单")
    @PostMapping("/merchant/search-order")
    public ModelAndView searchOrder(HttpServletRequest request, ModelAndView modelAndView, String order) {
        SysMt merchant = (SysMt) request.getSession().getAttribute("merchant");
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderById(order, merchant.getId());
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        modelAndView.addObject("page", 1);
        modelAndView.addObject("flag", 2);
        modelAndView.addObject("allPage", 1);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }
}
