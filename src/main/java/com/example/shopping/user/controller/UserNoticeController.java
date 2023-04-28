package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysNotice;
import com.example.shopping.common.entity.SysUser;
import com.example.shopping.common.mapper.SysNoticeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "用户模块通知信息接口")
@RestController
public class UserNoticeController {
    @Autowired
    SysNoticeMapper noticeMapper;

    @ApiOperation("通知信息页面")
    @GetMapping("/user/notice")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session, @RequestParam(defaultValue = "1") int page) {
        SysUser user = (SysUser) session.getAttribute("user");

        List<SysNotice> noticeList =
                noticeMapper.findByReceiveTypeLimit(user.getId(), 2, (page - 1) * 8, 8);
        modelAndView.addObject("noticeList", noticeList);

        modelAndView.setViewName("user/notice/index");
        return modelAndView;
    }

    @ApiOperation("删除通知信息")
    @GetMapping("/user/del-notice")
    public ModelAndView delNotice(ModelAndView modelAndView, int id) {
        if (noticeMapper.deleteById(id) == 1) {
            modelAndView.addObject("msg", "删除通知信息成功");
        } else {
            modelAndView.addObject("msg", "删除通知信息成功失败");
        }

        modelAndView.setViewName("redirect:/user/notice");
        return modelAndView;
    }
}
