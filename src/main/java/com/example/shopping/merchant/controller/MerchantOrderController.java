package com.example.shopping.merchant.controller;

import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.merchant.service.MerchantOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "员工模块订单管理接口")
@RestController
public class MerchantOrderController {
    @Autowired
    MerchantOrderService orderService;

    @ApiOperation("订单页面")
    @GetMapping("/merchant/order")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView, @RequestParam(defaultValue = "2") int flag, @RequestParam(defaultValue = "1") int page) {

        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderList(1, page, 18, flag);
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        int allPage = orderService.getAllPage(1, flag, 18);
        modelAndView.addObject("page", page);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }

    @ApiOperation("通过订单号查找订单")
    @PostMapping("/merchant/search-order")
    public ModelAndView searchOrder(ModelAndView modelAndView, String order) {
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderById(order, 1);
        modelAndView.addObject("orderList", orderList);

        // 获取订单总页数
        modelAndView.addObject("page", 1);
        modelAndView.addObject("flag", 2);
        modelAndView.addObject("allPage", 1);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }
}
