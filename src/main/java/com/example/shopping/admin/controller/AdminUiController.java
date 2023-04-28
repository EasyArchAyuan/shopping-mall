package com.example.shopping.admin.controller;

import com.example.shopping.admin.service.AdminUiService;
import com.example.shopping.common.entity.SysUi;
import com.example.shopping.user.service.UserHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author EasyArchAyuan
 * 2023/4/4 21:00
 */
@Api(tags = "后台模块广告设置接口")
@RestController
public class AdminUiController {
    @Autowired
    AdminUiService adminUiService;
    @Autowired
    UserHomeService userHomeService;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("后台广告设置页面")
    @GetMapping("/admin/ui")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/ui/index");
        Map<String, SysUi> imgMap = userHomeService.getImage();
        modelAndView.addObject("imgMap", imgMap);
        return modelAndView;
    }

    @ApiOperation("文件上传操作")
    @PostMapping("/admin/up-image")
    public ModelAndView upImage(ModelAndView modelAndView, MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request, int width, int height) {
        if (Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            modelAndView.addObject("msg", "文件上传失败");
        } else {
            String upFileResult = adminUiService.upImage(file, redirectAttributes, request, "/data/library/", width, height);
            if (upFileResult == null) {
                modelAndView.addObject("msg", "文件上传失败");
            } else {
                modelAndView.addObject("msg", "文件上传成功");
                clearCache();
            }
        }
        modelAndView.setViewName("redirect:ui");
        return modelAndView;
    }

    @ApiOperation("删除海报")
    @PostMapping("/admin/del-image")
    public ModelAndView delImg(ModelAndView modelAndView, int width, int height) {
        modelAndView.setViewName("redirect:ui");
        if (adminUiService.delImg(width, height)) {
            modelAndView.addObject("msg", "删除成功");
            clearCache();
        } else {
            modelAndView.addObject("msg", "删除失败");
        }
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
