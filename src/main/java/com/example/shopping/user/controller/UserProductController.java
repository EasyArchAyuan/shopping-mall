package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.mapper.SysCommentMapper;
import com.example.shopping.user.service.UserHomeService;
import com.example.shopping.user.service.UserProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author EasyArchAyuan
 * 2023/4/2 23:13
 */
@Api(tags = "用户模块商品详情接口")
@RestController
public class UserProductController {
    @Autowired
    UserProductService productService;
    @Autowired
    UserHomeService homeService;
    @Autowired
    SysCommentMapper commentMapper;

    @ApiOperation("商品详情页面")
    @GetMapping("/user/product")
    public ModelAndView index(ModelAndView modelAndView, int id, @RequestParam(defaultValue = "1") int page) {
        // 通过id值获取商品对象
        SysGoods goods = productService.getGoods(id);
        modelAndView.addObject("goods", goods);
        // 随机获取商品列表
        Map<String, SysGoods> randGoodsMap = homeService.getRand(8);
        modelAndView.addObject("randGoodsMap", randGoodsMap);
        // 获取商品评论信息
        List<Map> commentList = productService.getCommentList(id, page, 3);
        modelAndView.addObject("commentList", commentList);
        // 评论当前页数
        modelAndView.addObject("page", page);
        // 评论的总页数
        int allPage = productService.getAllCountPage(id, 3);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("user/product/index");
        return modelAndView;
    }

    @ApiOperation("添加评论")
    @PostMapping("/user/product/add-comment")
    public ModelAndView addComment(ModelAndView modelAndView, int goods, int merchant, String context, HttpServletRequest request) {
        if ("".equals(context)) {
            modelAndView.addObject("msg", "评论内容不能为空");
        } else {
            int result = productService.addComment(goods, merchant, context, request);
            if (result == 1) {
                modelAndView.addObject("msg", "发表评论成功");
            }
            if (result == -1) {
                modelAndView.addObject("msg", "发表评论失败");
            }
        }
        modelAndView.setViewName("redirect:/user/product?id=" + goods);
        return modelAndView;
    }

    @ApiOperation("删除评论")
    @PostMapping("/user/product/del-comment")
    public ModelAndView delComment(ModelAndView modelAndView, int id, int goods) {
        int sql = commentMapper.deleteById(id);
        if (sql == 1) {
            modelAndView.addObject("msg", "删除评论成功");
        } else {
            modelAndView.addObject("msg", "删除评论失败");
        }
        modelAndView.setViewName("redirect:/user/product?id=" + goods);
        return modelAndView;
    }
}
