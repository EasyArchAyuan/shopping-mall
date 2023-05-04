package com.example.shopping.user.service;

import com.example.shopping.common.entity.SysMt;
import com.example.shopping.common.entity.SysOrder;
import com.example.shopping.common.entity.SysUser;
import com.example.shopping.common.mapper.*;
import com.example.shopping.common.utils.IsEmptyUtil;
import com.example.shopping.common.utils.QRCodeUtil;
import com.example.shopping.common.utils.StringUtil;
import com.example.shopping.wxpay.config.MyWXPayConfig;
import com.example.shopping.wxpay.sdk.WXPay;
import com.example.shopping.wxpay.sdk.WXPayConstants;
import com.example.shopping.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>微信支付服务类</p>
 *
 * @author EasyArchAyuan
 * 2023/4/4 20:53
 */
@Service
public class WxPayService {
    @Autowired
    StringUtil stringUtil;
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysMtMapper merchantMapper;
    @Autowired
    SysCartMapper cartMapper;
    @Autowired
    SysUserMapper userMapper;
    @Value("${call-back-site-url}")
    String url;
    MyWXPayConfig config = new MyWXPayConfig();
    WXPay wxPay = new WXPay(config);
    String classPath;
    IsEmptyUtil isEmptyUtil = new IsEmptyUtil();

    public WxPayService() throws Exception {
        this.classPath = ResourceUtils.getURL("classpath:static/").getPath();
    }

    /**
     * <p>添加订单</p>
     *
     * @return -1 sql语句执行失败
     * 0 必填信息不能为空
     * 1 添加成功
     */
    @Transactional
    public String addOrder(String nums, String mark, String users, String merchants, String prices, String notes,
                           String goods, String address, String name, String phone, String code, int way, String carts) {
        if (isEmptyUtil.strings(address, name, phone)) {
            return "0";
        }
        // 获取当前时间戳
        long createTime = System.currentTimeMillis();
        DecimalFormat dfPrice = new DecimalFormat("#.00");
        String[] numArr = getStringArray(nums);
        String[] userArr = getStringArray(users);
        String[] cartArr = getStringArray(carts);
        String[] goodsArr = getStringArray(goods);
        String[] pricesArr = getStringArray(prices);
        String[] merchantsArr = getStringArray(merchants);
        for (int i = 0; i < numArr.length; i++) {
            // 生成订单号
            String orderId = getOrderId();
            // 获取商户信息
            SysMt merchant = merchantMapper.findById(Integer.parseInt(merchantsArr[i]));
            // 执行sql语句
            int sql;
            if (cartArr[i].equals("0")) {
                sql = orderMapper.insert1(orderId, createTime, Integer.parseInt(numArr[i]), mark, Integer.parseInt(userArr[i]),
                        Integer.parseInt(merchantsArr[i]), new BigDecimal(dfPrice.format(Double.valueOf(pricesArr[i]))),
                        Integer.parseInt(goodsArr[i]), notes, address, name, phone, code, way, merchant.getRatio());
            } else {
                sql = orderMapper.insert2(orderId, createTime, Integer.parseInt(numArr[i]), mark, Integer.parseInt(userArr[i]),
                        Integer.parseInt(merchantsArr[i]), new BigDecimal(dfPrice.format(Double.valueOf(pricesArr[i]))),
                        Integer.parseInt(goodsArr[i]), notes, address, name, phone, code, way, cartArr[i], merchant.getRatio());
            }
            // 将商品库存做相应的减少
            int delStock = goodsMapper.delStock(Integer.parseInt(goodsArr[i]), Integer.parseInt(numArr[i]));
            // 库存检查，库存少于零时回滚
            if (sql != 1 || delStock != 1 || goodsMapper.findById(Integer.parseInt(goodsArr[i])).getStock() < 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        //根据支付渠道返回不同东西
        if (way == 0) {
            //微信支付
            return wxCodeUrl(mark, getwxAllPrice(pricesArr, numArr));
        } else if (way == 2) {
            //会员卡余额支付
            SysUser sysUser = userMapper.selectById(Integer.parseInt(userArr[0]));
            BigDecimal subtract = sysUser.getBalance().subtract(this.getAllPrice(pricesArr, numArr));
            if (subtract.doubleValue() < 0.00D) {
                return "-2";
            }
            //扣款
            userMapper.updateBalanceById(subtract, sysUser.getId());
            //回调后续业务逻辑
            this.successWxNotify(mark);
            return "ok";
        } else {
            //其他未开通
            return "-1";
        }
    }

    /**
     * <p>分割js传递的数组</p>
     */
    public String[] getStringArray(String array) {
        return array.split(",");
    }

    /**
     * <p>微信支付以分为单位，微信总金额运算</p>
     */
    public String getwxAllPrice(String[] prices, String[] nums) {
        //微信支付以分为单位，解决字符串类型乘以100
        DecimalFormat dfPrice = new DecimalFormat("#.00");
        DecimalFormat dfNum = new DecimalFormat("#");
        BigDecimal allPrice = new BigDecimal("0.00");

        for (int i = 0; i < prices.length; i++) {
            BigDecimal price = new BigDecimal(dfPrice.format(Double.valueOf(prices[i])));
            BigDecimal num = new BigDecimal(dfNum.format(Double.valueOf(nums[i])));
            allPrice = allPrice.add(price.multiply(num));
        }
        return allPrice.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
    }

    /**
     * <p>总金额运算</p>
     */
    public BigDecimal getAllPrice(String[] prices, String[] nums) {
        //微信支付以分为单位，解决字符串类型乘以100
        DecimalFormat dfPrice = new DecimalFormat("#.00");
        DecimalFormat dfNum = new DecimalFormat("#");
        BigDecimal allPrice = new BigDecimal("0.00");

        for (int i = 0; i < prices.length; i++) {
            BigDecimal price = new BigDecimal(dfPrice.format(Double.valueOf(prices[i])));
            BigDecimal num = new BigDecimal(dfNum.format(Double.valueOf(nums[i])));
            allPrice = allPrice.add(price.multiply(num));
        }
        return allPrice;
    }

    /**
     * <p>生成二维码</p>
     */
    public String wxCodeUrl(String orderMark, String price) {
        Map<String, String> data = new HashMap<>();
        data.put("body", "家乐超市商品");
        data.put("out_trade_no", orderMark);
        data.put("device_info", orderMark);
        data.put("total_fee", price);
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", url + "/user/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", orderMark);
        try {
            Map<String, String> resp = wxPay.unifiedOrder(data);
            System.out.println(resp);
            QRCodeUtil.zxingCodeCreate(resp.get("code_url"), classPath + "/data/pay/", orderMark, 500, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String codeUrl = "/data/pay/" + orderMark + ".jpg";
        orderMapper.updateCodeUrl(codeUrl, orderMark);
        return codeUrl;
    }

    /**
     * <p>生成订单号</p>
     */
    public String getOrderId() {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] c = s.toCharArray();
        Random random = new Random();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            word.append(c[random.nextInt(c.length)]);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
        String time = sdf.format(System.currentTimeMillis());
        String number = String.valueOf(Math.round(Math.random() * (999999 - 100000) + 100000));
        return word + time + number;
    }

    /**
     * <p>回调处理</p>
     */
    public String wxNotify(HttpServletRequest request) {
        return payAsyncNotifyVerificationSign(request);
    }

    /**
     * <p>微信支付异步通知验证签名</p>
     */
    public String payAsyncNotifyVerificationSign(HttpServletRequest request) {
        String returnXmlMessage;
        String notifyXmlData;
        try {
            notifyXmlData = readXmlFromStream(request);
            Map<String, String> notifyMapData = WXPayUtil.xmlToMap(notifyXmlData);
            // 验证签名
            boolean signatureValid = wxPay.isPayResultNotifySignatureValid(notifyMapData);
            if (signatureValid) {
                this.successWxNotify(notifyMapData.get("device_info"));
                // 一切正常返回的xml数据
                returnXmlMessage = setReturnXml(WXPayConstants.SUCCESS, "OK");
            } else {
                returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "Verification sign failed2");
            }
        } catch (IOException e) {
            returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "An exception occurred while reading the WeChat server returning xml data in the stream.");
        } catch (Exception e) {
            returnXmlMessage = setReturnXml(WXPayConstants.FAIL, "Payment successful, exception occurred during asynchronous notification processing.");
        }

        return returnXmlMessage;
    }

    /**
     * <p>从流中读取微信返回的xml数据</p>
     *
     * @param httpServletRequest
     * @return
     * @throws IOException
     */
    private String readXmlFromStream(HttpServletRequest httpServletRequest) throws IOException {
        InputStream inputStream = httpServletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            bufferedReader.close();
            inputStream.close();
        }

        return sb.toString();
    }

    /**
     * <p>设置返回给微信服务器的xml信息</p>
     *
     * @param returnCode
     * @param returnMsg
     * @return
     */
    private String setReturnXml(String returnCode, String returnMsg) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
    }

    public String successWxNotify(String orderMark) {
        if (StringUtils.isEmpty(orderMark)){
            return "请输入orderMark订单号";
        }
        // 订单支付成功之后相关业务逻辑...
        orderMapper.updateState(System.currentTimeMillis(), orderMark);
        List<SysOrder> orderList = orderMapper.findByOrderMark(orderMark);
        for (SysOrder order : orderList) {
            // 移除购物车内商品
            cartMapper.deleteById(order.getCartId());
            // 判断商品是否是会员卡充值
            if (1 == order.getGoodsId()) {
                SysUser sysUser = userMapper.selectById(order.getOrderUser());
                userMapper.updateBalanceById(sysUser.getBalance().add(BigDecimal.valueOf(order.getGoodsNum() * 100.00)), order.getOrderUser());
            }
        }
        // 一切正常返回的xml数据
        return "OK";
    }
}
