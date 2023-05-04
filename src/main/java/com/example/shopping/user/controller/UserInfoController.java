package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysUser;
import com.example.shopping.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>用户信息页面</p>
 *
 * @author EasyArchAyuan
 * 2023/4/24 21:29
 */
@Api(tags = "用户模块账户信息接口")
@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation("账户信息页面")
    @GetMapping("/user/info")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        user = userInfoService.getInfo(request,user.getId());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/info/index");
        return modelAndView;
    }

    @ApiOperation("修改信息")
    @PostMapping("/user/update-info")
    public ModelAndView updateInfo(RedirectAttributes redirectAttributes, HttpServletRequest request, ModelAndView modelAndView, MultipartFile img, int id, String username, String email, String oldPwd, String newPwd, String rePwd) {
        int result = userInfoService.updateInfo(redirectAttributes, request, img, id, username, email, oldPwd, newPwd, rePwd);
        switch (result) {
            case -1:
                modelAndView.addObject("msg", "修改信息失败");
                break;
            case 0:
                modelAndView.addObject("msg", "必填信息不能为空");
                break;
            case 1:
                modelAndView.addObject("msg", "修改信息成功");
                break;
            case 2:
                modelAndView.addObject("msg", "邮箱格式不正确");
                break;
            case 3:
                modelAndView.addObject("msg", "原密码错误");
                break;
            case 4:
                modelAndView.addObject("msg", "两次输入的密码不一致");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:/user/info");
        return modelAndView;
    }
}
