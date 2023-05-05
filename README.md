**用户模块：**[http://127.0.0.1:8866/login](http://127.0.0.1:8866)（账号-密码：guest@qq.com-123456）

**商户模块：**[http://127.0.0.1:8866/merchant/login](http://127.0.0.1:8866/merchant/login)（账号-密码：guest@qq.com-123456）

**后台模块：**[http://127.0.0.1:8866/admin/login](http://127.0.0.1:8866/admin/login)（账号-密码：guest@qq.com-123456）

**API 文档：** [http://127.0.0.1:8866/doc.html](http://127.0.0.1:8866/doc.html)

**模拟支付成功：
** [http://localhost:8866/user/wxpay/success_notify?orderMark=](http://localhost:8866/user/wxpay/success_notify?orderMark=)(
orderMark=后面写页面中的订单号)

例子:
```
http://localhost:8866/user/wxpay/index?mark=UUR202305124183133192435&orderMark=UUR202305124183133192435
```
---

# 技术选型

| 技术            | 版本     | 端口         | 说明                |
|---------------|--------|------------|-------------------|
| Tomcat        | 8.5.0  | 8866       | Web服务器            |
| MySQL         | 5.7.16 | 3306       | 关系型数据库            |
| Redis         | 2.8.21 | 6379       | 缓存框架框架、主要用于主页页面缓存 |
| RabbitMQ      | 3.7.8  | 5672、15672 | 消息中间件、用于群发消息的队列处理 |
| Elasticsearch | 7.7.0  | 9200、9300  | 搜索引擎、用于搜索商品的关键字查找 |
| SpringBoot    | 2.3.5  | -          | MVC核心框架           |
| Mybatis       | 3.5.0  | -          | ORM框架             |
| Maven         | 3.3.9  | -          | 用于构建springboot项目  |
| wxpay-sdk     | -      | -          | 微信支付工具包           |

---
<br/>