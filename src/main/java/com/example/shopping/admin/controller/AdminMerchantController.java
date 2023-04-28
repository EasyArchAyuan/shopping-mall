package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminMerchantService;
import com.example.shopping.common.entity.SysMt;
import com.example.shopping.common.mapper.SysMtMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p></p>
 *
 * @author EasyArchAyuan
 * 2023/4/8 10:50
 */
@Api(tags = "后台模块商户管理接口")
@RestController
@RequestMapping("/admin/merchant")
public class AdminMerchantController {
    @Autowired
    AdminMerchantService merchantService;
    @Autowired
    SysMtMapper merchantMapper;

    @ApiOperation("商户模块主页")
    @GetMapping("")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("admin/merchant/index");

        int pageSize = 10;
        int pageCount = merchantService.getMtPage(pageSize);
        int num = 1;
        int preNum;
        int nextNum;
        if (request.getParameter("num") != null) {
            num = Integer.parseInt(request.getParameter("num"));
        }
        if (num <= 1) {
            preNum = 1;
            nextNum = 2;
        } else {
            preNum = num - 1;
            if (num >= pageCount - 1) {
                nextNum = pageCount;
            } else {
                nextNum = num + 1;
            }
        }
        List<SysMt> mtList = merchantService.getMtInfo(num, pageSize);
        if (mtList.size() <= 10) {
            nextNum = num;
        }
        modelAndView.addObject("mtList", mtList);
        modelAndView.addObject("pageCount", pageCount);
        modelAndView.addObject("nextNum", nextNum);
        modelAndView.addObject("preNum", preNum);
        modelAndView.addObject("num", num);

        return modelAndView;
    }

    @ApiOperation("修改费率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商户id"),
            @ApiImplicitParam(name = "ratio", value = "修改后的费率")
    })
    @PostMapping("update-ratio")
    public ModelAndView updateRatio(ModelAndView modelAndView, String ratio, int id) {
        try {
            float ratioFloat = ((float) ((int) (Float.parseFloat(ratio) * 10))) / 10;
            if (ratioFloat > 1.0 || ratioFloat < 0.1) {
                modelAndView.addObject("msg", "费率应在0.1~1.0之间（一位小数）");
            } else {
                if (merchantMapper.updateRatio(id, ratioFloat) == 1) {
                    modelAndView.addObject("msg", "修改成功");
                } else {
                    modelAndView.addObject("msg", "修改失败");
                }
            }
        } catch (Exception e) {
            modelAndView.addObject("msg", "输入的费率不合法");
        }

        modelAndView.setViewName("redirect:/admin/merchant");
        return modelAndView;
    }

    @ApiOperation("修改商户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "商户邮箱"),
            @ApiImplicitParam(name = "state", value = "修改后的状态 1启用 0未启用")
    })
    @PostMapping("update-merchant")
    public ModelAndView updateMerchant(ModelAndView modelAndView, String email, int state) {
        if (merchantService.updateMerchantState(email, state) == 1) {
            modelAndView.addObject("msg", "操作成功！");
        } else {
            modelAndView.addObject("msg", "操作失败！");
        }
        modelAndView.setViewName("redirect:/admin/merchant");
        return modelAndView;
    }
}
