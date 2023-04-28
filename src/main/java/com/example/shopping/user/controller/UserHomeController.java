package com.example.shopping.user.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysMtUi;
import com.example.shopping.common.entity.SysUi;
import com.example.shopping.user.service.UserHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author EasyArchAyuan
 * 2023/4/2 20:03
 */
@Api(tags = "用户模块主页接口")
@RestController
public class UserHomeController {
    @Autowired
    UserHomeService homeService;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("商城主页")
    @GetMapping({"/user/home", "/"})
    public ModelAndView home(ModelAndView modelAndView) {
        Map<String, SysUi> imgMap;
        // 获取热卖商品列表
        Map<String, SysGoods> escGoodsMap;
        // 获取新出商品列表
        Map<String, SysGoods> descGoodsMap;
        // 随机获取商品列表
        Map<String, SysGoods> randGoodsMap;
        // 获取商户宣传店铺的海报
        Map<String, SysMtUi> homeImg;
        Map<String, SysMtUi> lowImg;

        if (redisTemplate.opsForHash().entries("homePageCache").size() != 0) {
            Map map = redisTemplate.opsForHash().entries("homePageCache");
            // 商城主页宣传海报
            imgMap = (Map<String, SysUi>) map.get("imgMap");
            // 获取热卖商品列表
            escGoodsMap = (Map<String, SysGoods>) map.get("escGoodsMap");
            // 获取新出商品列表
            descGoodsMap = (Map<String, SysGoods>) map.get("descGoodsMap");
            // 随机获取商品列表
            randGoodsMap = (Map<String, SysGoods>) map.get("randGoodsMap");
            // 获取商户宣传店铺的海报
            homeImg = (Map<String, SysMtUi>) map.get("homeImg");
            lowImg = (Map<String, SysMtUi>) map.get("lowImg");
        } else {
            // 商城主页宣传海报
            imgMap = homeService.getImage();
            // 获取热卖商品列表
            escGoodsMap = homeService.getEsc(13);
            // 获取新出商品列表
            descGoodsMap = homeService.getDesc(6);
            // 随机获取商品列表
            randGoodsMap = homeService.getRand(12);
            // 获取商户宣传店铺的海报
            homeImg = homeService.getMtImg(400, 320, 3);
            lowImg = homeService.getMtImg(600, 310, 3);
        }
        modelAndView.addObject("imgMap", imgMap);
        modelAndView.addObject("escGoodsMap", escGoodsMap);
        modelAndView.addObject("descGoodsMap", descGoodsMap);
        modelAndView.addObject("randGoodsMap", randGoodsMap);
        modelAndView.addObject("homeImg", homeImg);
        modelAndView.addObject("lowImg", lowImg);

        if (redisTemplate.opsForHash().entries("homePageCache").size() == 0) {
            // 设置缓存  十分钟内有效
            homeService.setHomeCache(modelAndView);
        }
        modelAndView.setViewName("user/home/index");
        return modelAndView;
    }
}
