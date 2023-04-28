package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysMt;
import com.example.shopping.user.service.UserShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author EasyArchAyuan
 * 2023/4/2 20:26
 */
@Api(tags = "用户模块店铺接口")
@RestController
public class UserShopController {
    @Autowired
    UserShopService shopService;

    @ApiOperation("店铺首页")
    @GetMapping("/user/shop")
    public ModelAndView index(ModelAndView modelAndView, int id, @RequestParam(defaultValue = "1") int page) {
        // 获取商户信息
        SysMt merchant = shopService.getMerchantInfo(id);
        modelAndView.addObject("merchant", merchant);
        // 获取商户商品信息
        Map<Integer, SysGoods> goodsMap = shopService.getGoodsList(id, page, 12);
        modelAndView.addObject("goodsMap", goodsMap);
        // 获取随机商品信息
        Map<Integer, SysGoods> randGoodsMap = shopService.getRandGoodsMap(id, 12);
        modelAndView.addObject("randGoodsMap", randGoodsMap);
        // 当前页数和总页数
        modelAndView.addObject("page", page);
        modelAndView.addObject("allPage", shopService.getAllPage(id, 12));

        modelAndView.setViewName("user/shop/index");
        return modelAndView;
    }
}
