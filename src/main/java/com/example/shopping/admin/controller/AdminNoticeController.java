package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminNoticeService;
import com.example.shopping.common.utils.IsEmptyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author EasyArchAyuan
 * @date 2021/8/9 22:24
 */
@Api(tags = "后台模块消息群发接口")
@RestController
public class AdminNoticeController {
    @Autowired
    AdminNoticeService noticeService;
    @Autowired
    IsEmptyUtil isEmptyUtil;

    @ApiOperation("后台群发页面")
    @GetMapping("/admin/notice")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/notice/index");
        return modelAndView;
    }

    @ApiOperation("系统消息发送 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "通知标题"),
            @ApiImplicitParam(name = "context", value = "通知内容"),
            @ApiImplicitParam(name = "type", value = "通知类型 0给管理员发送 1商户 2用户")
    })
    @PostMapping("/admin/send-notice")
    public ModelAndView sendNotice(ModelAndView modelAndView, String title, String context, int type) {
        if (isEmptyUtil.strings(title, context)) {
            modelAndView.addObject("msg", "必填信息不能为空");
        } else {
            // 发送至redis
            noticeService.sendByRedis(title, context, type);

            // 发送至RabbitMQ
            // noticeService.sendByRabbitMQ(title, context, type);

            modelAndView.addObject("msg", "系统消息发送成功");
        }
        modelAndView.setViewName("redirect:/admin/notice");
        return modelAndView;
    }
}
