package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminUserService;
import com.example.shopping.common.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author EasyArchAyuan
 * @date 2021/8/9 22:34
 */
@Api(tags = "后台模块用户列表接口")
@RestController
public class AdminUserController {
    @Autowired
    AdminUserService userService;

    @ApiOperation("用户列表页面")
    @GetMapping("/admin/user")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(defaultValue = "1") int page) {
        // 获取订单信息
        List<SysUser> userList = userService.getUserList(page, 10);
        modelAndView.addObject("userList", userList);

        // 获取订单总页数
        int allPage = userService.getAllPage(10);
        modelAndView.addObject("page", page);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("admin/user/index");
        return modelAndView;
    }

}
