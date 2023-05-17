package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysUser;
import com.example.shopping.common.mapper.SysCartMapper;
import com.example.shopping.user.service.UserCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author EasyArchAyuan
 * 2023/4/3 13:06
 */
@Api(tags = "用户模块购物车接口")
@RestController
public class UserCartController {
    @Autowired
    UserCartService cartService;
    @Autowired
    SysCartMapper cartMapper;

    @ApiOperation("购物车页面")
    @GetMapping("/user/cart")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        List<Map> cartList = cartService.getCartList(request);
        modelAndView.addObject("cartList", cartList);

        modelAndView.setViewName("user/cart/index");
        return modelAndView;
    }

    @ApiOperation("添加至购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id"),
            @ApiImplicitParam(name = "num", value = "商品数量"),
    })
    @RequestMapping(value = "/user/add-cart", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addCart(ModelAndView modelAndView, String id, @RequestParam(defaultValue = "1") int num, HttpServletRequest request) {
        if (num <= 0) {
            modelAndView.addObject("msg", "商品数量不合法");
        } else {
            int result = cartService.addCart(Integer.parseInt(id), num, request);
            if (result == 1) {
                modelAndView.addObject("msg", "已成功添加至购物车");
            }
            if (result == -1) {
                modelAndView.addObject("msg", "添加至购物车失败");
            }
            if (result == 2) {
                modelAndView.addObject("msg", "您在此前已将此商品添加至购物车");
            }
            if (result == 3) {
                modelAndView.addObject("msg", "充值卡禁止添加至购物车");
            }
        }
        modelAndView.setViewName("redirect:/user/product?id=" + id);
        return modelAndView;
    }

    @ApiOperation("移除购物车商品")
    @GetMapping("/user/del-cart")
    public ModelAndView delCart(ModelAndView modelAndView, int id) {
        int sql = cartMapper.deleteById(id);
        if (sql == 1) {
            modelAndView.addObject("msg", "移除购物车商品成功");
        } else {
            modelAndView.addObject("msg", "移除购物车商品失败");
        }
        modelAndView.setViewName("redirect:/user/cart");
        return modelAndView;
    }

    @ApiOperation("批量移除购物车商品")
    @GetMapping("/user/batch-del-cart")
    public ModelAndView batchDelCart(ModelAndView modelAndView, HttpServletRequest request) {
        // 获取用户信息
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        int sql = cartMapper.deleteByUserId(user.getId());
        if (sql == 1) {
            modelAndView.addObject("msg", "移除购物车商品成功");
        }
        modelAndView.setViewName("redirect:/user/cart");
        return modelAndView;
    }
}
