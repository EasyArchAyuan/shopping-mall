package com.example.shopping.user.controller;

import com.example.shopping.common.baseenum.GoodsTypeEnum;
import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysUi;
import com.example.shopping.user.service.UserHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UserHomeService homeService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("超市主页")
    @GetMapping({"/user/home", "/"})
    public ModelAndView home(ModelAndView modelAndView) {
        Map<String, SysUi> imgMap;
        // 获取热卖商品列表
        Map<String, SysGoods> escGoodsMap;
        // 获取新出商品列表
        Map<String, SysGoods> descGoodsMap;
        // 随机获取商品列表
        Map<String, SysGoods> randGoodsMap;
        // 餐具6
        Map<String, SysGoods> tablewareMap;
        // 卫浴用品6
        Map<String, SysGoods> bathroomMap;
        // 电器4
        Map<String, SysGoods> electricalMap;
        // 日杂用品4
        Map<String, SysGoods> dailyNecessitiesMap;
        // 清洁用品6
        Map<String, SysGoods> cleaningSuppliesMap;
        // 家居用品4
        Map<String, SysGoods> homeSuppliesMap;

        if (redisTemplate.opsForHash().entries("homePageCache").size() != 0) {
            Map map = redisTemplate.opsForHash().entries("homePageCache");
            // 超市主页宣传海报
            imgMap = (Map<String, SysUi>) map.get("imgMap");
            // 获取热卖商品列表
            escGoodsMap = (Map<String, SysGoods>) map.get("escGoodsMap");
            // 获取新出商品列表
            descGoodsMap = (Map<String, SysGoods>) map.get("descGoodsMap");
            // 随机获取商品列表
            randGoodsMap = (Map<String, SysGoods>) map.get("randGoodsMap");

            tablewareMap = (Map<String, SysGoods>) map.get("tablewareMap");
            bathroomMap = (Map<String, SysGoods>) map.get("bathroomMap");
            electricalMap = (Map<String, SysGoods>) map.get("electricalMap");
            dailyNecessitiesMap = (Map<String, SysGoods>) map.get("dailyNecessitiesMap");
            cleaningSuppliesMap = (Map<String, SysGoods>) map.get("cleaningSuppliesMap");
            homeSuppliesMap = (Map<String, SysGoods>) map.get("homeSuppliesMap");

        } else {
            // 超市主页宣传海报
            imgMap = homeService.getImage();
            // 获取热卖商品列表
            escGoodsMap = homeService.getEsc(6);
            // 获取新出商品列表
            descGoodsMap = homeService.getDesc(6);
            // 随机获取商品列表
            randGoodsMap = homeService.getRand(12);

            tablewareMap = homeService.getType(6, GoodsTypeEnum.Tableware.getCode());
            bathroomMap = homeService.getType(6, GoodsTypeEnum.Bathroom.getCode());
            electricalMap = homeService.getType(4, GoodsTypeEnum.Electrical.getCode());
            dailyNecessitiesMap = homeService.getType(4, GoodsTypeEnum.DailyNecessities.getCode());
            cleaningSuppliesMap = homeService.getType(6, GoodsTypeEnum.CleaningSupplies.getCode());
            homeSuppliesMap = homeService.getType(4, GoodsTypeEnum.HomeSupplies.getCode());
        }

        modelAndView.addObject("imgMap", imgMap);
        modelAndView.addObject("escGoodsMap", escGoodsMap);
        modelAndView.addObject("descGoodsMap", descGoodsMap);
        modelAndView.addObject("randGoodsMap", randGoodsMap);

        modelAndView.addObject("tablewareMap", tablewareMap);
        modelAndView.addObject("bathroomMap", bathroomMap);
        modelAndView.addObject("electricalMap", electricalMap);
        modelAndView.addObject("dailyNecessitiesMap", dailyNecessitiesMap);
        modelAndView.addObject("cleaningSuppliesMap", cleaningSuppliesMap);
        modelAndView.addObject("homeSuppliesMap", homeSuppliesMap);


        if (redisTemplate.opsForHash().entries("homePageCache").size() == 0) {
            // 设置缓存  十分钟内有效
            homeService.setHomeCache(modelAndView);
        }
        modelAndView.setViewName("user/home/index");
        return modelAndView;
    }
}
