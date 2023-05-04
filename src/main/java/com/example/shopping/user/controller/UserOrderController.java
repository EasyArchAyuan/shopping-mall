package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.entity.SysUser;
import com.example.shopping.user.service.UserOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author EasyArchAyuan
 * 2023/4/3 12:57
 */
@Api(tags = "用户模块订单列表接口")
@RestController
public class UserOrderController {

    @Autowired
    UserOrderService orderService;

    @ApiOperation("订单页面")
    @GetMapping("/user/order")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView, @RequestParam(defaultValue = "1") int page) {
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        List<SysOrder> orderList = orderService.getOrderList(user.getId(), page, 10);
        List<SysGoods> goodsList = orderService.getGoodsList(orderList);
        int allPage = orderService.getAllPage(user.getId(), 10);

        modelAndView.addObject("orderList", orderList);
        modelAndView.addObject("goodsList", goodsList);
        modelAndView.addObject("page", page);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("user/order/index");
        return modelAndView;
    }
}
