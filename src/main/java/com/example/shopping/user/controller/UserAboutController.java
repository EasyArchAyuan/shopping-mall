package com.example.shopping.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author EasyArchAyuan
 * 2023/4/2 21:00
 */
@Api(tags = "用户模块有关作者接口")
@RestController
public class UserAboutController {
    @ApiOperation("有关作者页面")
    @GetMapping("/user/about")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("user/about/index");
        return modelAndView;
    }
}
