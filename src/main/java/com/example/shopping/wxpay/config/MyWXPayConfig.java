package com.example.shopping.wxpay.config;

import com.example.shopping.wxpay.sdk.IWXPayDomain;
import com.example.shopping.wxpay.sdk.WXPayConfig;
import com.example.shopping.wxpay.sdk.WXPayConstants;

import java.io.*;

public class MyWXPayConfig extends WXPayConfig {

    private static final String appId = "wx8397f8696b538317";

    private static final String mchId = "1473426802";

    private static final String key = "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";


    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return mchId;
    }

    public String getKey() {
        return key;
    }

    public InputStream getCertStream() {
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        // 这个方法需要这样实现, 否则无法正常初始化WXPay
        return new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
