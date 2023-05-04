**用户模块：**[http://127.0.0.1/login](http://127.0.0.1)（账号-密码：guest@qq.com-123456）

**商户模块：**[http://127.0.0.1/merchant/login](http://127.0.0.1/merchant/login)（账号-密码：guest@qq.com-123456）

**后台模块：**[http://127.0.0.1/admin/login](http://127.0.0.1/admin/login)（账号-密码：guest@qq.com-123456）

**API 文档：** [http://127.0.0.1/doc.html](http://127.0.0.1/doc.html)

**模拟支付成功：** [http://localhost:8866/user/wxpay/success_notify?orderMark=](http://localhost:8866/user/wxpay/success_notify?orderMark=)(orderMark=后面写页面中的订单号)

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

# 架构图

# 各模块的功能

### 用户模块

| 页面   | 主要功能                                                                                                                                 | 
|------|--------------------------------------------------------------------------------------------------------------------------------------|  
| 登录页面 | 登录、注册账号、发送邮箱验证码 。                                                                                                                    |
| 超市主页 | 登录、退出登录、随机推送商品、随机推送商家、推送热卖商品、推送新出的商品、展示商城广告海报；并且以上**商品信息在请求过一次MySQL后，将会保存在Redis缓存中十分钟**，刷新页面时会从Redis中获取，不会再次访问MySQL，以达到减轻MySQL压力的目的。 |
| 我的账户 | 显示登录用户的头像、昵称等信息；用户可通过该页面对自己的头像、昵称、密码进行修改。                                                                                            |
| 系统消息 | 显示网站后台管理员发送的群消息、用户可对消息进行删除操作。                                                                                                        |
| 购买记录 | 显示用户购买商品后的详细订单记录；若存在未支付的订单，点击后可跳转至支付页面。                                                                                              |
| 购物车  | 显示用户的购物车信息、用户可以移出商品、添加商品、批量购买商品。                                                                                                     |
| 商品详情 | 显示商品的名称、价格、介绍、库存、上下架情况、图片；显示商品的用户评价，若存在评价是该登录用户本人所发表的，用户还可对其进行删除评论的操作；若商品已经下架或用户购买数量超过库存数量，将无法进行购买操作。                                |
| 店铺页面 | 显示商户下的商品、随即推送该商户下的商品；若商户被停用，商户页面将会有停用的字样，并且属于该商户的商品都将会被下架。                                                                           |
| 搜索结果 | 当用户通过关键词搜索时，将会通过Elasticsearch搜索引擎进行模糊搜索将符合条件的商品作为搜索结果返回给商户，商户通过点击即可跳转至相应的商品详情页面。                                                     |
| 下单页面 | 当用户在该页面下填好必填和选填的订单信息后，选择微信支付或会员卡支付后，即可生成订单，并且跳转至支付页面。                                                                                |
| 支付页面 | 支付页面会显示支付二维码供用户扫码支付，页面每500ms异步检查订单是否已经完成支付，当用户支付成功后，将会提示用户支付成功，并跳转至用户的购买记录页面。                                                        |

### 商户模块

| 页面   | 主要功能                                          | 
|------|-----------------------------------------------|
| 登录页面 | 登录、注册账号、发送邮箱验证码 。                             |
| 商户主页 | 退出登录、显示商户盈利、商品评论总数、会员总数、订单成交量（不包含未支付订单）。      |
| 店铺相关 | 设置商户头像、店铺广告海报（供超市主页随机向用户推送）。                  |
| 商品相关 | 商品的添加、删除、修改、上下架。                              |
| 优惠相关 | 优惠卷功能（暂未实现）                                   |
| 会员相关 | 查看会员相关信息。                                     |
| 订单相关 | 查看有关店铺的订单信息，可以筛选已支付或未支付的订单，并且支持通过订单号为关键字搜索订单。 |

### 后台模块

| 页面   | 主要功能                                                                                                                                                  | 
|------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| 登录页面 | 登录、注册账号、发送邮箱验证码 。                                                                                                                                     |
| 后台主页 | 退出登录、显示网站盈利、商品的总数、会员总数、订单成交量（不包含未支付订单）。                                                                                                               |
| 广告设置 | 设置超市主页需要向用户推送的广告海报。                                                                                                                                   |
| 订单列表 | 显示网站的所有订单，可以筛选已支付或未支付的订单，并且支持通过订单号为关键字搜索订单。                                                                                                           |
| 商户相关 | 显示商户的基本信息、头像、名称、邮箱、状态等；并且可以对其状态和费率进行修改，或删除该商户（停用商户时，商户的所有商品都将会被下架，并且其账号无法在商户模块登录；启用商户时，系统将会发送通知邮件告知该商户，账号已启用；删除商户时，属于商户的所有商品也将会被删除！）。                 |
| 会员相关 | 查看会员相关信息。                                                                                                                                             |
| 权限管理 | 后台权限管理使用RBAC思想，即权限管理分为用户、角色、权限三大块，每一个管理员都是一个用户，并且都需要赋予一个角色，这个角色拥有什么权限，由最高权限的管理员进行设置（其中：**最高权限的管理员默认为id为1 的root，该管理员不受RBAC权限的控制**，且其他管理员也无权对其进行任何的操作）。 |
| 群发消息 | 群发消息给网站用户时，考虑到项目正式运行后，用户量会激增，若群发消息采用同步发送机制，后台管理员会在发送群消息时非常头疼，因此本网站采用RabbitMQ作为消息队列，对群发消息进行异步处理，提高后台管理效率。                                              |

<br/>