package com.example.shopping.merchant.controller;

import com.example.shopping.common.entity.SysUser;
import com.example.shopping.merchant.service.MerchantUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api(tags = "员工模块用户管理接口")
@RestController
public class MerchantUserController {
    @Autowired
    MerchantUserService userService;

    @ApiOperation("用户管理页面")
    @GetMapping("/merchant/user")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(defaultValue = "1") int page) {
        // 获取订单信息
        List<SysUser> userList = userService.getUserList(page, 10);
        modelAndView.addObject("userList", userList);

        // 获取订单总页数
        int allPage = userService.getAllPage(10);
        modelAndView.addObject("page", page);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("merchant/user/index");
        return modelAndView;
    }
}
