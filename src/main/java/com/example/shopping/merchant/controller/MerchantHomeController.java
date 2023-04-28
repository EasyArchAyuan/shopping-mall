package com.example.shopping.merchant.controller;

import com.example.shopping.common.entity.SysMt;
import com.example.shopping.common.mapper.SysOrderMapper;
import com.example.shopping.merchant.service.MerchantHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author EasyArchAyuan
 * 2023/4/8 14:27
 */
@Api(tags = "商户模块主页接口")
@RestController
public class MerchantHomeController {
    @Autowired
    MerchantHomeService homeService;
    @Autowired
    SysOrderMapper orderMapper;

    @ApiOperation("商户主页")
    @GetMapping({"/merchant/home", "/merchant/"})
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        SysMt merchant = (SysMt) session.getAttribute("merchant");

        int commentCount = homeService.getCommandCount(merchant.getId());
        int userCount = homeService.getUserCount();
        int orderCount = homeService.getOrderCount(merchant.getId());
        float profit = 0;
        if (orderMapper.merchantProfitCount(merchant.getId()) != null) {
            profit = orderMapper.merchantProfitCount(merchant.getId());
        }

        modelAndView.addObject("commentCount", commentCount);
        modelAndView.addObject("profit", profit);
        modelAndView.addObject("userCount", userCount);
        modelAndView.addObject("orderCount", orderCount);

        modelAndView.setViewName("merchant/home/index");
        return modelAndView;
    }
}
