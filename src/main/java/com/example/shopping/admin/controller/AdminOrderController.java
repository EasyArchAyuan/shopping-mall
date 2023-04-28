package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminOrderService;
import com.example.shopping.common.entity.SysOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author EasyArchAyuan
 * 2023/4/1 13:02
 */
@Api(tags = "后台模块订单管理接口")
@RestController
public class AdminOrderController {
    @Autowired
    AdminOrderService orderService;

    @ApiOperation("后台订单管理页面")
    @GetMapping("/admin/order")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(defaultValue = "2") int flag, @RequestParam(defaultValue = "1") int page) {
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderList(page, 18, flag);
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        int allPage = orderService.getAllPage(flag, 18);
        modelAndView.addObject("page", page);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("admin/order/index");
        return modelAndView;
    }

    @ApiOperation("通过订单号查找订单")
    @PostMapping("/admin/search-order")
    public ModelAndView searchOrder(ModelAndView modelAndView, String order) {
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderById(order);
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        modelAndView.addObject("page", 1);
        modelAndView.addObject("flag", 2);
        modelAndView.addObject("allPage", 1);

        modelAndView.setViewName("admin/order/index");
        return modelAndView;
    }
}
