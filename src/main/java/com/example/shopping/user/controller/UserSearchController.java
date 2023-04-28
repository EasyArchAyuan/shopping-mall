package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.index.GoodsIndex;
import com.example.shopping.user.service.UserSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author EasyArchAyuan
 * 2023/4/29 19:48
 */
@Api(tags = "用户模块关键字查找接口")
@RestController
public class UserSearchController {
    @Autowired
    UserSearchService searchService;

    @ApiOperation("查找结果")
    @GetMapping("/user/search")
    public ModelAndView index(ModelAndView modelAndView, String keyword) {
        // 使用mysql获取返回的商品列表
        List<SysGoods> goodsList = searchService.mysqlSearchGoods(keyword);

        // 使用es获取返回的商品列表
        // List<GoodsIndex> goodsList = searchService.esSearchGoods(keyword);

        modelAndView.addObject("goodsList", goodsList);
        modelAndView.setViewName("user/search/index");
        return modelAndView;
    }
}
