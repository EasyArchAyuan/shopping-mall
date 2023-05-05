-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: shopping
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `sys_access`
--

DROP TABLE IF EXISTS `sys_access`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_access`
(
    `id`     int(11)      NOT NULL AUTO_INCREMENT,
    `name`   varchar(50)  NOT NULL COMMENT '权限名称',
    `url`    varchar(255) NOT NULL COMMENT '权限对应的地址',
    `status` tinyint(4)   NOT NULL DEFAULT '1' COMMENT '1启用 0未启用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_access`
--

LOCK TABLES `sys_access` WRITE;
/*!40000 ALTER TABLE `sys_access`
    DISABLE KEYS */;
INSERT INTO `sys_access`
VALUES (1, '权限管理', '/admin/rbac', 1),
       (2, '后台主页', '/admin/home', 1),
       (3, '广告设置', '/admin/ui', 1),
       (4, '订单列表', '/admin/order', 1),
       (5, '员工列表', '/admin/merchant/', 1),
       (6, '会员列表', '/admin/user', 1),
       (7, '系统消息', '/admin/notice', 1);
/*!40000 ALTER TABLE `sys_access`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin`
--

DROP TABLE IF EXISTS `sys_admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_admin`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `name`     varchar(50) NOT NULL,
    `password` varchar(32) NOT NULL,
    `email`    varchar(75) NOT NULL,
    `mark`     tinyint(4)  NOT NULL COMMENT '管理员标记',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin`
    DISABLE KEYS */;
INSERT INTO `sys_admin`
VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@qq.com', 1),
       (2, 'guest', 'e10adc3949ba59abbe56e057f20f883e', 'guest@qq.com', 1);
/*!40000 ALTER TABLE `sys_admin`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin_role`
--

DROP TABLE IF EXISTS `sys_admin_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_admin_role`
(
    `id`       int(11)    NOT NULL AUTO_INCREMENT,
    `admin_id` int(11)    NOT NULL,
    `role_id`  int(11)    NOT NULL,
    `status`   tinyint(4) NOT NULL DEFAULT '1' COMMENT '1启用 0未启用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin_role`
--

LOCK TABLES `sys_admin_role` WRITE;
/*!40000 ALTER TABLE `sys_admin_role`
    DISABLE KEYS */;
INSERT INTO `sys_admin_role`
VALUES (1, 1, 1, 1),
       (2, 2, 2, 1);
/*!40000 ALTER TABLE `sys_admin_role`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_cart`
--

DROP TABLE IF EXISTS `sys_cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_cart`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `goods_id`  int(11) NOT NULL,
    `user_id`   int(11) NOT NULL,
    `goods_num` int(11) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_cart`
--

LOCK TABLES `sys_cart` WRITE;
/*!40000 ALTER TABLE `sys_cart`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_cart`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_comment`
--

DROP TABLE IF EXISTS `sys_comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_comment`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `user`        int(11)      NOT NULL,
    `goods`       int(11)      NOT NULL,
    `merchant`    int(11)      NOT NULL,
    `context`     varchar(255) NOT NULL,
    `create_time` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_comment`
--

LOCK TABLES `sys_comment` WRITE;
/*!40000 ALTER TABLE `sys_comment`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_comment`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_goods`
--

DROP TABLE IF EXISTS `sys_goods`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_goods`
(
    `id`       int(11)        NOT NULL AUTO_INCREMENT,
    `name`     varchar(50)    NOT NULL COMMENT '商品名称',
    `describe` varchar(255)   NOT NULL COMMENT '商品描述',
    `img`      varchar(100)   NOT NULL DEFAULT '/image/goods-default.jpg' COMMENT '商品图片',
    `price`    decimal(10, 2) NOT NULL COMMENT '商品售价',
    `state`    tinyint(4)     NOT NULL DEFAULT '1' COMMENT '1已上架  0未上架',
    `stock`    int(11)        NOT NULL DEFAULT '0' COMMENT '商品库存',
    `type`     int(11)        NOT NULL DEFAULT '1' COMMENT '商品类目',
    `merchant` int(11)        NOT NULL DEFAULT '1' COMMENT '默认商户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100050
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_goods`
--

LOCK TABLES `sys_goods` WRITE;
/*!40000 ALTER TABLE `sys_goods`
    DISABLE KEYS */;
INSERT INTO `sys_goods`
VALUES (1, '会员 充值卡100元', '家乐超市 会员 充值卡 100元', '/data/goods/1/vip.jpg', 100.00, 1, 99, 1, 1),
       (100002, '筷子', '双枪抗菌金福合金筷子家用 无漆无蜡 高档酒店餐具套装 10双', '/data/goods/1/筷子.jpg', 32.90, 1,
        99, 2, 1),
       (100021, '盘子', '洁雅杰陶瓷盘家用白瓷盘子8英寸中式釉下彩餐盘菜盘汤盘微波炉可用6只装', '/data/goods/1/盘子.jpg',
        39.90, 1, 99, 2, 1),
       (100022, '勺子', '勺子', '/data/goods/1/勺子.jpg', 5.90, 1, 99, 2, 1),
       (100023, '水杯', '水杯', '/data/goods/1/水杯.jpg', 28.80, 1, 99, 2, 1),
       (100024, '汤盆', '汤盆', '/data/goods/1/汤盆.jpg', 15.00, 1, 99, 2, 1),
       (100025, '碗', '碗', '/data/goods/1/碗.jpg', 23.80, 1, 99, 2, 1),
       (100026, '毛巾', '毛巾', '/data/goods/1/毛巾.jpg', 19.90, 1, 99, 3, 1),
       (100027, '沐浴露', '沐浴露', '/data/goods/1/沐浴露.jpg', 55.90, 1, 99, 3, 1),
       (100028, '洗发水', '洗发水', '/data/goods/1/洗发水.jpg', 45.90, 1, 99, 3, 1),
       (100029, '洗衣液', '洗衣液', '/data/goods/1/洗衣液.jpg', 69.90, 1, 99, 3, 1),
       (100030, '牙膏', '牙膏', '/data/goods/1/牙膏.jpg', 10.80, 1, 99, 3, 1),
       (100031, '牙刷', '牙刷', '/data/goods/1/牙刷.jpg', 5.80, 1, 99, 3, 1),
       (100032, '插排', '插排', '/data/goods/1/插排.jpg', 79.90, 1, 99, 4, 1),
       (100033, '吹风机', '吹风机', '/data/goods/1/吹风机.jpg', 99.90, 1, 99, 4, 1),
       (100034, '电扇', '电扇', '/data/goods/1/电扇.jpg', 129.00, 1, 99, 4, 1),
       (100035, '电熨斗', '电熨斗', '/data/goods/1/电熨斗.jpg', 159.80, 1, 99, 4, 1),
       (100036, '挂钩', '挂钩', '/data/goods/1/挂钩.jpg', 2.99, 1, 99, 5, 1),
       (100037, '垃圾袋', '垃圾袋', '/data/goods/1/垃圾袋.jpg', 5.99, 1, 99, 5, 1),
       (100038, '暖水瓶', '暖水瓶', '/data/goods/1/暖水瓶.jpg', 29.90, 1, 99, 5, 1),
       (100039, '热水袋', '热水袋', '/data/goods/1/热水袋.jpg', 59.00, 1, 99, 5, 1),
       (100040, '垃圾桶', '垃圾桶', '/data/goods/1/垃圾桶.jpg', 36.90, 1, 99, 6, 1),
       (100041, '清洁球', '清洁球', '/data/goods/1/清洁球.jpg', 29.00, 1, 99, 6, 1),
       (100042, '清洁手套', '清洁手套', '/data/goods/1/清洁手套.jpg', 18.80, 1, 99, 6, 1),
       (100043, '扫把', '扫把', '/data/goods/1/扫把.jpg', 29.90, 1, 99, 6, 1),
       (100044, '拖把', '拖把', '/data/goods/1/拖把.jpg', 36.00, 1, 99, 6, 1),
       (100045, '洗洁精', '洗洁精', '/data/goods/1/洗洁精.jpg', 10.00, 1, 99, 6, 1),
       (100046, '晾衣架', '晾衣架', '/data/goods/1/晾衣架.jpg', 69.80, 1, 99, 7, 1),
       (100047, '沙发垫', '沙发垫', '/data/goods/1/沙发垫.jpg', 59.90, 1, 99, 7, 1),
       (100048, '拖鞋', '拖鞋', '/data/goods/1/拖鞋.jpg', 29.00, 1, 99, 7, 1),
       (100049, '衣架', '衣架', '/data/goods/1/衣架.jpg', 15.80, 1, 99, 7, 1);
/*!40000 ALTER TABLE `sys_goods`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_mt`
--

DROP TABLE IF EXISTS `sys_mt`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_mt`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `name`     varchar(50) NOT NULL,
    `email`    varchar(50) NOT NULL,
    `password` varchar(32) NOT NULL,
    `state`    tinyint(4)  NOT NULL DEFAULT '0' COMMENT '1启用 0未启用 2未通过注册',
    `header`   varchar(255)         DEFAULT '/data/header/merchant/default.png',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_mt`
--

LOCK TABLES `sys_mt` WRITE;
/*!40000 ALTER TABLE `sys_mt`
    DISABLE KEYS */;
INSERT INTO `sys_mt`
VALUES (1, '家乐超市直营店', 'admin@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '/data/header/merchant/1/img.jpg'),
       (15, '测试员工', 'test@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1, '/data/header/merchant/default.png');
/*!40000 ALTER TABLE `sys_mt`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `title`       varchar(50)  NOT NULL,
    `context`     varchar(255) NOT NULL,
    `type`        varchar(255) NOT NULL COMMENT '0给管理员发送 1 商户  2用户',
    `receive`     int(11)      NOT NULL COMMENT '接收者的id',
    `create_time` bigint(20)   NOT NULL COMMENT '通知发送时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_order`
--

DROP TABLE IF EXISTS `sys_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_order`
(
    `id`             int(11)        NOT NULL AUTO_INCREMENT,
    `order_id`       varchar(25)    NOT NULL,
    `create_time`    bigint(20)     NOT NULL,
    `pay_time`       bigint(20)              DEFAULT NULL,
    `goods_num`      int(11)        NOT NULL COMMENT '商品数量',
    `order_state`    tinyint(4)     NOT NULL DEFAULT '0' COMMENT '1已支付  0未支付',
    `order_mark`     varchar(50)             DEFAULT NULL COMMENT '商品标记',
    `order_user`     int(11)        NOT NULL COMMENT '用户id',
    `order_price`    decimal(10, 2) NOT NULL COMMENT '订单总金额',
    `goods_id`       int(11)        NOT NULL,
    `order_notes`    varchar(100)            DEFAULT NULL COMMENT '订单特殊注释',
    `user_address`   varchar(255)   NOT NULL COMMENT '地址',
    `user_name`      varchar(50)    NOT NULL COMMENT '收件人',
    `user_phone`     varchar(50)    NOT NULL COMMENT '电话号码',
    `coupon_code`    varchar(50)             DEFAULT NULL COMMENT '优惠卷代码',
    `pay_way`        tinyint(4)     NOT NULL COMMENT '1支付宝 0微信支付',
    `pay_code_url`   varchar(255)            DEFAULT NULL COMMENT '支付链接',
    `cart_id`        varchar(20)             DEFAULT NULL COMMENT '批量购买的商品',
    `order_merchant` int(11)        NOT NULL DEFAULT '1',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `order_id` (`order_id`) USING BTREE COMMENT '订单号唯一'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_order`
--

LOCK TABLES `sys_order` WRITE;
/*!40000 ALTER TABLE `sys_order`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_order`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role`
(
    `id`     int(11)     NOT NULL AUTO_INCREMENT,
    `name`   varchar(50) NOT NULL COMMENT '角色名',
    `status` tinyint(4)  NOT NULL DEFAULT '1' COMMENT '1已启用 0未启用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role`
    DISABLE KEYS */;
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 1),
       (2, '商家管理员', 1);
/*!40000 ALTER TABLE `sys_role`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_access`
--

DROP TABLE IF EXISTS `sys_role_access`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_access`
(
    `id`        int(11)    NOT NULL AUTO_INCREMENT,
    `role_id`   int(11)    NOT NULL,
    `access_id` int(11)    NOT NULL,
    `status`    tinyint(4) NOT NULL DEFAULT '1' COMMENT '1启用  0未启用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_access`
--

LOCK TABLES `sys_role_access` WRITE;
/*!40000 ALTER TABLE `sys_role_access`
    DISABLE KEYS */;
INSERT INTO `sys_role_access`
VALUES (1, 1, 1, 1),
       (2, 1, 2, 1),
       (3, 1, 3, 1),
       (4, 1, 4, 1),
       (5, 1, 5, 1),
       (6, 1, 6, 1),
       (7, 1, 7, 1),
       (9, 2, 1, 1),
       (10, 2, 2, 1),
       (11, 2, 3, 1),
       (12, 2, 4, 1),
       (13, 2, 5, 1);
/*!40000 ALTER TABLE `sys_role_access`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_ui`
--

DROP TABLE IF EXISTS `sys_ui`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_ui`
(
    `id`     int(11)      NOT NULL AUTO_INCREMENT,
    `url`    varchar(255) NOT NULL,
    `width`  int(11)      NOT NULL,
    `height` int(11)      NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_ui`
--

LOCK TABLES `sys_ui` WRITE;
/*!40000 ALTER TABLE `sys_ui`
    DISABLE KEYS */;
INSERT INTO `sys_ui`
VALUES (1, '/data/library/banner2.jpg', 1230, 535),
       (2, '/data/library/20210330181647732986.jpg', 3151, 282),
       (3, '/data/library/20210330181628569458.jpeg', 3152, 282),
       (4, '/data/library/20210330190628805198.jpeg', 475, 570),
       (5, '/data/library/20210330185815215536.jpg', 674, 264),
       (6, '/data/library/banner1.jpg', 1920, 737);
/*!40000 ALTER TABLE `sys_ui`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user`
(
    `id`       int(11)        NOT NULL AUTO_INCREMENT,
    `username` varchar(50)    NOT NULL,
    `password` varchar(32)    NOT NULL,
    `email`    varchar(50)    NOT NULL,
    `mark`     tinyint(4)     NOT NULL COMMENT '0未启用  1已启用',
    `header`   varchar(255)   NOT NULL DEFAULT '/data/header/user/default.jpg',
    `balance`  decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '会员卡余额',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user`
    DISABLE KEYS */;
INSERT INTO `sys_user`
VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@qq.com', 1, '/data/header/user/1.jpg', 0.00),
       (2, 'guest', 'e10adc3949ba59abbe56e057f20f883e', 'guest@qq.com', 1, '/data/header/user/default.jpg', 298.92),
       (8, 'test@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'test@qq.com', 1, '/data/header/user/default.jpg', 0.00);
/*!40000 ALTER TABLE `sys_user`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-05 17:10:55
