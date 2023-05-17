package com.example.shopping.admin.controller;

import com.example.shopping.common.mapper.SysCommentMapper;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.mapper.SysOrderMapper;
import com.example.shopping.common.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author EasyArchAyuan
 * 2023/4/3 20:38
 */
@Api(tags = "后台模块主页接口")
@RestController
public class AdminHomeController {
    @Autowired
    private SysOrderMapper orderMapper;
    @Autowired
    private SysGoodsMapper goodsMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysCommentMapper commentMapper;

    @ApiOperation("后台主页页面")
    @GetMapping("/admin/home")
    public ModelAndView index(ModelAndView modelAndView) {

        Float profit = orderMapper.merchantProfitCount(1);
        int orderCount = orderMapper.payCount();
        int goodsCount = goodsMapper.allGoodsCount();
        int userCount = userMapper.userCount();
        int commentCount = commentMapper.getAllCountByMt(1);

        modelAndView.addObject("profit", profit);
        modelAndView.addObject("orderCount", orderCount);
        modelAndView.addObject("goodsCount", goodsCount);
        modelAndView.addObject("userCount", userCount);
        modelAndView.addObject("commentCount", commentCount);
        modelAndView.setViewName("admin/home/index");
        return modelAndView;
    }
}
