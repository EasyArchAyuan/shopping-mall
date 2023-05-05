package com.example.shopping.user.service;

import com.example.shopping.common.entity.SysGoods;
import com.example.shopping.common.entity.SysUi;
import com.example.shopping.common.mapper.SysGoodsMapper;
import com.example.shopping.common.mapper.SysUiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserHomeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUiMapper uiMapper;

    @Autowired
    private SysGoodsMapper goodsMapper;

    /**
     * <p>设置缓存  十分钟内有效</p>
     */
    public void setHomeCache(ModelAndView modelAndView) {
        redisTemplate.opsForHash().put("homePageCache", "imgMap", modelAndView.getModelMap().get("imgMap"));
        redisTemplate.opsForHash().put("homePageCache", "escGoodsMap", modelAndView.getModelMap().get("escGoodsMap"));
        redisTemplate.opsForHash().put("homePageCache", "descGoodsMap", modelAndView.getModelMap().get("descGoodsMap"));
        redisTemplate.opsForHash().put("homePageCache", "randGoodsMap", modelAndView.getModelMap().get("randGoodsMap"));

        redisTemplate.opsForHash().put("homePageCache", "tablewareMap", modelAndView.getModelMap().get("tablewareMap"));
        redisTemplate.opsForHash().put("homePageCache", "bathroomMap", modelAndView.getModelMap().get("bathroomMap"));
        redisTemplate.opsForHash().put("homePageCache", "electricalMap", modelAndView.getModelMap().get("electricalMap"));
        redisTemplate.opsForHash().put("homePageCache", "dailyNecessitiesMap", modelAndView.getModelMap().get("dailyNecessitiesMap"));
        redisTemplate.opsForHash().put("homePageCache", "cleaningSuppliesMap", modelAndView.getModelMap().get("cleaningSuppliesMap"));
        redisTemplate.opsForHash().put("homePageCache", "homeSuppliesMap", modelAndView.getModelMap().get("homeSuppliesMap"));


        redisTemplate.opsForHash().put("homePageCache", "homeImg", modelAndView.getModelMap().get("homeImg"));
        redisTemplate.opsForHash().put("homePageCache", "lowImg", modelAndView.getModelMap().get("lowImg"));

        redisTemplate.expire("homePageCache", 10, TimeUnit.MINUTES);
    }

    /*
     * 获取超市网页的宣传海报
     */
    public Map<String, SysUi> getImage() {
        Map<String, SysUi> map = new HashMap<>();

        SysUi img1920 = uiMapper.findByMark(1920, 737);
        SysUi img1230 = uiMapper.findByMark(1230, 535);
        SysUi img475 = uiMapper.findByMark(475, 570);
        SysUi img674 = uiMapper.findByMark(674, 264);
        SysUi img3151 = uiMapper.findByMark(3151, 282);
        SysUi img3152 = uiMapper.findByMark(3152, 282);

        map.put(1920 + "", img1920);
        map.put(1230 + "", img1230);
        map.put(475 + "", img475);
        map.put(674 + "", img674);
        map.put(3151 + "", img3151);
        map.put(3152 + "", img3152);

        return map;
    }

    /*
     * 获取热卖商品
     */
    public Map<String, SysGoods> getEsc(int num) {
        List<SysGoods> list = goodsMapper.findAllEsc(num);
        Map<String, SysGoods> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }
        return map;
    }

    /*
     * 获取分类商品
     */
    public Map<String, SysGoods> getType(int num, int type) {
        List<SysGoods> list = goodsMapper.findType(num, type);
        Map<String, SysGoods> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }
        return map;
    }

    /*
     * 获取新出商品
     */
    public Map<String, SysGoods> getDesc(int num) {
        List<SysGoods> list = goodsMapper.findAllDesc(num);
        Map<String, SysGoods> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }
        return map;
    }

    /*
     * 随机获取商品
     */
    public Map<String, SysGoods> getRand(int num) {
        List<SysGoods> list = goodsMapper.findRand(num);
        Map<String, SysGoods> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }
        return map;
    }
}
