package com.example.shopping.admin.service;

import com.example.shopping.common.entity.SysAdmin;
import com.example.shopping.common.mapper.SysAdminMapper;
import com.example.shopping.common.service.MailService;
import com.example.shopping.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>登陆逻辑处理</p>
 *
 * @author EasyArchAyuan
 * 2023/4/3 19:06
 */
@Service
public class AdminLoginService {
    @Value("${site-url}")
    String url;
    IsEmptyUtil isEmptyUtil = IsEmptyUtil.getInstance();
    @Resource
    private SysAdminMapper adminMapper;
    @Resource
    private MailService mailService;

    /**
     * <p>登陆验证逻辑处理</p>
     *
     * @return int
     * 1 登陆成功
     * 0 必填信息不能为空
     * -1 邮箱不存在或密码错误
     */
    public int doLogin(String email, String password) {
        SysAdmin admin = adminMapper.selectByEmail(email);
        // 用户名密码不能为空
        if (isEmptyUtil.strings(email, password)) {
            return 0;
        }
        // 不存在该用户
        if (Objects.isNull(admin)) {
            return -1;
        }
        // 密码验证
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 密码错误
        if (!admin.getPassword().equals(password)) {
            return -1;
        }
        return 1;
    }

    /**
     * <p>返回用户信息</p>
     */
    public SysAdmin adminInfo(String email) {
        return adminMapper.getAdminInfo(email);
    }

    /**
     * <p>注册逻辑处理</p>
     *
     * @return int
     * -1 注册超时请重试
     * 0 必填信息不能为空
     * 1 处理请求合理，已发送验证码
     * 2 两次密码不一致
     * 3 邮箱格式有误
     * 4 该邮箱已被注册
     */
    public int singup(String email, String name, String password) {
        // 必填信息不能为空
        if (isEmptyUtil.strings(email, name, password)) {
            return 0;
        }
        // 邮箱格式验证
        if (mailService.checkEmail(email)) {
            System.out.println(email);
            return 3;
        }

        // 该邮箱已被注册
        SysAdmin admin = adminMapper.selectByEmail(email);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (Objects.nonNull(admin)) {
            if (admin.getMark() != 0) {
                return 4;
            } else {
                if (adminMapper.update(name, password, email) != 1) {
                    return -1;
                }
            }
        } else {
            // 添加信息未成功返回超时
            if (adminMapper.insert(name, password, email, 1) != 1) {
                return -1;
            }
        }
//        // 发送注册验证邮件
//        boolean res = mailService.sendHtmlMail(email, "家乐超市管理员注册验证", "<a href='http://" + this.url + "/admin/sign-check?email=" + email + "'>点击此链接完成注册验证</a>");
//        return res ? 1 : -1;
        return 1;
    }

    /**
     * 验证邮件确认注册
     *
     * @return int
     * -1 404页面
     * 0 网络超时请重试
     * 1 注册成功，跳转至登陆页面
     */
    @Transactional
    public int singCheck(String email) {
        SysAdmin admin = adminMapper.selectByEmail(email);
        if (Objects.nonNull(admin) && admin.getMark() == 0) {
            int res1 = adminMapper.updateMark(1, email);
            return res1 == 1 ? 1 : 0;
        } else {
            return -1;
        }
    }
}
