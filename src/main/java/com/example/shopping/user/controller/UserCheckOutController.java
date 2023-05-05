package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.user.service.UserCheckOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author EasyArchAyuan
 * 2023/4/3 9:53
 */
@Api(tags = "用户模块订单结算接口")
@RestController
public class UserCheckOutController {

    @Autowired
    UserCheckOutService checkOutService;

    @ApiOperation("结算订单")
    @PostMapping("/user/checkout")
    public ModelAndView index(ModelAndView modelAndView, String ids, @RequestParam(defaultValue = "-1") int num) {
        if (ids == null || "".equals(ids)) {
            modelAndView.addObject("msg", "请选择您要结算的商品");
            modelAndView.setViewName("redirect:/user/cart");
        } else {
            // 获取商品信息
            Map<SysGoods, Integer> goodsMap = checkOutService.getGoodsInfo(ids);
            modelAndView.addObject("goodsMap", goodsMap);
            // 获取对应的购物车id
            List<Integer> carts = checkOutService.getCartIds(ids);
            modelAndView.addObject("carts", carts);
            // 总金额计算
            BigDecimal allPrice = checkOutService.getGoodsNumPrice(goodsMap);
            modelAndView.addObject("allPrice", allPrice);

            modelAndView.setViewName("user/checkout/index");
        }
        return modelAndView;
    }
}
