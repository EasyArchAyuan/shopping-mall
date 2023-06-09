package com.example.shopping.merchant.service;

import com.example.shopping.common.entity.SysMt;
import com.example.shopping.common.mapper.SysMtMapper;
import com.example.shopping.common.service.MailService;
import com.example.shopping.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class MerchantLoginService {
    IsEmptyUtil isEmptyUtil = new IsEmptyUtil();
    @Autowired
    SysMtMapper mtMapper;
    @Autowired
    MailService mailService;
    @Value("${site-url}")
    String url;

    /**
     * <p>登录逻辑处理</p>
     *
     * @return 0 必填信息不能为空
     * -1 邮箱或密码错误
     * 1 登录成功
     */
    public int doLogin(String email, String password) {
        if (isEmptyUtil.strings(email, password)) {
            return 0;
        }
        SysMt merchant = mtMapper.findByEmail(email);
        if (merchant == null) {
            return -1;
        } else {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (merchant.getPassword().equals(password)) {
                return 1;
            } else {
                return -1;
            }
        }

    }

    /**
     * <p>注册逻辑处理</p>
     *
     * @return 0 必填信息不能为空
     * -1 验证邮件发送失败，请重试
     * 1 注册成功
     * 2 该邮箱已被注册
     * 3 邮箱格式不正确
     */
    public int signUp(String email, String name, String password) {
        if (isEmptyUtil.strings(email, name, password)) {
            return 0;
        }
        // 邮箱格式验证
        if (mailService.checkEmail(email)) {
            System.out.println(email);
            return 3;
        }
        // 该邮箱已被注册
        SysMt merchant = mtMapper.findByEmail(email);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (merchant != null) {
            if (merchant.getState() != 2) {
                return 2;
            } else {
                if (mtMapper.update(name, password, email) != 1) {
                    return -1;
                }
            }
        } else {
            // 添加信息未成功返回超时
            if (mtMapper.insert(name, email, password, 0) != 1) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * <p>验证邮件确认注册</p>
     *
     * @return int
     * -1 404页面
     * 0 网络超时请重试
     * 1 注册成功，跳转至登陆页面
     */
    public int singCheck(String email) {
        SysMt merchant = mtMapper.findByEmail(email);
        if (merchant != null && merchant.getState() == 2) {
            int res = mtMapper.updateState(email, 0);
            if (res == 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    /**
     * <p>获取商户信息</p>
     */
    public SysMt getMerchantInfo(String email) {
        return mtMapper.findByEmail(email);
    }
}
