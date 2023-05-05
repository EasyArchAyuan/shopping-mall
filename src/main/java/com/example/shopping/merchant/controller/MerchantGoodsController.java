package com.example.shopping.merchant.controller;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysMt;
import com.example.shopping.merchant.service.MerchantGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author EasyArchAyuan
 * 2023/4/8 16:27
 */
@Api(tags = "员工模块商品管理接口")
@RestController
public class MerchantGoodsController {
    @Autowired
    MerchantGoodsService goodsService;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("商品管理页面")
    @GetMapping("/merchant/goods")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session, @RequestParam(defaultValue = "1") int page) {

        List<SysGoods> goodsList = goodsService.getMtGoods(session, page, 10);
        modelAndView.addObject("goodsList", goodsList);
        // 获取订单总页数
        int allPage = goodsService.getAllPage(10, (SysMt) session.getAttribute("merchant"));
        modelAndView.addObject("page", page);
        modelAndView.addObject("allPage", allPage);

        modelAndView.setViewName("merchant/goods/index");
        return modelAndView;
    }

    @ApiOperation("增加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称"),
            @ApiImplicitParam(name = "describe", value = "商品描述"),
            @ApiImplicitParam(name = "price", value = "商品价格"),
            @ApiImplicitParam(name = "img", value = "商品图片"),
            @ApiImplicitParam(name = "stock", value = "商品库存"),
    })
    @PostMapping("/merchant/add-goods")
    public ModelAndView addGoods(ModelAndView modelAndView, String name, String describe,
                                 String price, MultipartFile img, String stock,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        // 类型转换
        BigDecimal priceRes;
        int stockTemp;
        try {
            stockTemp = Integer.parseInt(stock);
            if (stockTemp == 0) {
                throw new Exception();
            }
            priceRes = new BigDecimal(price);
        } catch (Exception e) {
            modelAndView.addObject("msg", "输入的金额或库存不合法");
            modelAndView.setViewName("redirect:goods");
            return modelAndView;
        }

        // 添加商品
        int res = goodsService.addGoods(name, describe, priceRes, img, redirectAttributes, stockTemp, request);
        switch (res) {
            case -1:
                modelAndView.addObject("msg", "图片上传失败");
                break;
            case 0:
                modelAndView.addObject("msg", "网络超时");
                break;
            case 1:
                modelAndView.addObject("msg", "添加商品成功");
                clearCache();
                break;
            case 2:
                modelAndView.addObject("msg", "必填信息不能为空");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }


    @ApiOperation("上架商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id")
    })
    @GetMapping("/merchant/put-goods")
    public ModelAndView putGoods(ModelAndView modelAndView, int id) {
        if (goodsService.putGoods(id)) {
            modelAndView.addObject("msg", "上架商品成功");
            clearCache();
        } else {
            modelAndView.addObject("msg", "上架商品失败");
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }

    @ApiOperation("下架商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id")
    })
    @GetMapping("/merchant/off-goods")
    public ModelAndView OffGoods(ModelAndView modelAndView, int id) {
        if (goodsService.OffGoods(id)) {
            modelAndView.addObject("msg", "下架商品成功");
            clearCache();
        } else {
            modelAndView.addObject("msg", "下架商品失败");
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名称"),
            @ApiImplicitParam(name = "describe", value = "商品描述"),
            @ApiImplicitParam(name = "price", value = "商品价格"),
            @ApiImplicitParam(name = "img", value = "商品图片"),
            @ApiImplicitParam(name = "stock", value = "商品库存"),
            @ApiImplicitParam(name = "id", value = "商品id"),
    })
    @PostMapping("/merchant/update-goods")
    public ModelAndView updateGoods(ModelAndView modelAndView, String name, String describe,
                                    String price, MultipartFile img, String stock,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request, int id) {
        // 类型转换
        BigDecimal priceRes;
        int stockTemp;
        try {
            stockTemp = Integer.parseInt(stock);
            if (stockTemp == 0) {
                throw new Exception();
            }
            priceRes = new BigDecimal(price);
        } catch (Exception e) {
            modelAndView.addObject("msg", "输入的金额或库存不合法");
            modelAndView.setViewName("redirect:goods");
            return modelAndView;
        }
        // 修改商品信息
        int res = goodsService.updateGoods(name, describe, priceRes, img, redirectAttributes, stockTemp, request, id);
        switch (res) {
            case -1:
                modelAndView.addObject("msg", "图片上传失败");
                break;
            case 0:
                modelAndView.addObject("msg", "网络超时");
                break;
            case 1:
                modelAndView.addObject("msg", "修改商品成功");
                clearCache();
                break;
            case 2:
                modelAndView.addObject("msg", "必填信息不能为空");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }

    /**
     * <p>清首页缓存</p>
     */
    public void clearCache() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(Objects.requireNonNull(keys));
    }
}
