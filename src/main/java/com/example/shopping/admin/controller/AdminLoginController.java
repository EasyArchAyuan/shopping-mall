package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminLoginService;
import com.example.shopping.common.entity.SysAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author EasyArchAyuan
 * 2023/4/3 14:03
 */
@Api(tags = "后台模块登陆接口")
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    AdminLoginService service;

    @ApiOperation("登陆页面")
    @RequestMapping({"login", ""})
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/login/index");
        return modelAndView;
    }

    @ApiOperation("登录处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("do-login")
    public ModelAndView doLogin(HttpSession session, ModelAndView modelAndView, String email, String password) {
        int result = service.doLogin(email, password);
        if (result == 1) {
            // 登陆成功
            SysAdmin admin = service.adminInfo(email);
            if (admin == null) {
                modelAndView.addObject("msg", "账号不存在或处于停用状态！");
                modelAndView.setViewName("redirect:login");
                return modelAndView;
            }
            session.setAttribute("admin", admin);
            modelAndView.setViewName("redirect:/admin/home");
        } else if (result == -1) {
            // 用户名或密码错误！
            modelAndView.addObject("msg", "用户名或密码错误！");
            modelAndView.setViewName("redirect:login");
        } else if (result == 0) {
            // 必填信息不能为空！
            modelAndView.addObject("msg", "必填信息不能为空！");
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }

    @ApiOperation("退出登录")
    @GetMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.setAttribute("merchant", null);
        modelAndView.setViewName("redirect:/admin/login");
        return modelAndView;
    }

    @ApiOperation("注册请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("sign-up")
    public String signUp(String name, String email, String password) {
        int result = service.singup(email, name, password);
        if (result == 0) {
            return "必填信息不能为空！";
        }
        if (result == 4) {
            return "该邮箱已被注册";
        }
        if (result == 3) {
            return "邮箱格式不正确";
        }
        if (result == 2) {
            return "两次密码输入不一致";
        }
        if (result == 1) {
            return "验证邮件已发送，请留意您的邮箱";
        }
        if (result == -1) {
            return "注册超时，请重试";
        }
        return null;
    }

    @ApiOperation("验证邮件，确认注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱"),
    })
    @GetMapping("sign-check")
    public ModelAndView singCheck(ModelAndView modelAndView, @Param("email") String email) {
        int res = service.singCheck(email);
        if (res == -1) {
            modelAndView.setViewName("redirect:/error");
        }
        if (res == 0) {
            modelAndView.addObject("msg", "网络超时请重试");
            modelAndView.setViewName("redirect:login");
        }
        if (res == 1) {
            modelAndView.addObject("msg", "注册成功，请完成登录！");
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }
}
