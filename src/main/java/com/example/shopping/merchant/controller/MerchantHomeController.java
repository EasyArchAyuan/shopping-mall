package com.example.shopping.merchant.controller;

import com.example.shopping.common.mapper.SysOrderMapper;
import com.example.shopping.merchant.service.MerchantHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author EasyArchAyuan
 * 2023/4/8 14:27
 */
@Api(tags = "员工模块主页接口")
@RestController
public class MerchantHomeController {
    @Autowired
    MerchantHomeService homeService;
    @Autowired
    SysOrderMapper orderMapper;

    @ApiOperation("商户主页")
    @GetMapping({"/merchant/home", "/merchant/"})
    public ModelAndView index(ModelAndView modelAndView) {
        int commentCount = homeService.getCommandCount(1);
        int userCount = homeService.getUserCount();
        int orderCount = homeService.getOrderCount(1);
        float profit = 0;
        if (orderMapper.merchantProfitCount(1) != null) {
            profit = orderMapper.merchantProfitCount(1);
        }

        modelAndView.addObject("commentCount", commentCount);
        modelAndView.addObject("profit", profit);
        modelAndView.addObject("userCount", userCount);
        modelAndView.addObject("orderCount", orderCount);

        modelAndView.setViewName("merchant/home/index");
        return modelAndView;
    }
}
