-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: shopping
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_access`
--

DROP TABLE IF EXISTS `sys_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_access` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限对应的地址',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_access`
--

LOCK TABLES `sys_access` WRITE;
/*!40000 ALTER TABLE `sys_access` DISABLE KEYS */;
INSERT INTO `sys_access` VALUES (1,'权限管理页面','/admin/rbac',1),(2,'后台主页','/admin/home',1),(3,'广告设置页面','/admin/ui',1),(4,'订单列表页面','/admin/order',1),(5,'商户列表页面','/admin/merchant/',1),(6,'会员列表页面','/admin/user',1),(7,'系统消息页面','/admin/notice',1);
/*!40000 ALTER TABLE `sys_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin`
--

DROP TABLE IF EXISTS `sys_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mark` tinyint NOT NULL COMMENT '管理员标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin` DISABLE KEYS */;
INSERT INTO `sys_admin` VALUES (1,'Ayuan','e10adc3949ba59abbe56e057f20f883e','easyarchayuan@126.com',1),(2,'guest','e10adc3949ba59abbe56e057f20f883e','guest@qq.com',1);
/*!40000 ALTER TABLE `sys_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin_role`
--

DROP TABLE IF EXISTS `sys_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_admin_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `role_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin_role`
--

LOCK TABLES `sys_admin_role` WRITE;
/*!40000 ALTER TABLE `sys_admin_role` DISABLE KEYS */;
INSERT INTO `sys_admin_role` VALUES (1,1,2,1),(2,3,4,1);
/*!40000 ALTER TABLE `sys_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_cart`
--

DROP TABLE IF EXISTS `sys_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `user_id` int NOT NULL,
  `goods_num` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_cart`
--

LOCK TABLES `sys_cart` WRITE;
/*!40000 ALTER TABLE `sys_cart` DISABLE KEYS */;
INSERT INTO `sys_cart` VALUES (30,45,2,1),(31,46,2,1);
/*!40000 ALTER TABLE `sys_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_comment`
--

DROP TABLE IF EXISTS `sys_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int NOT NULL,
  `goods` int NOT NULL,
  `merchant` int NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_comment`
--

LOCK TABLES `sys_comment` WRITE;
/*!40000 ALTER TABLE `sys_comment` DISABLE KEYS */;
INSERT INTO `sys_comment` VALUES (25,1,12,5,'耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码',1615988609112),(26,1,12,5,'耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码',1615988693324),(27,1,12,5,'耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码',1615988697577),(28,1,11,5,'耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码\r\n',1616727058835),(29,1,11,5,'耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码\r\n',1616727061866),(30,1,11,5,'耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44',1616727068620),(31,1,44,8,'测试商品！！！！勿拍',1616738590017),(32,1,44,8,'测试商品！！！！勿拍',1616738593903),(33,1,44,8,'测试商品！！！！勿拍',1616738597935),(34,1,45,13,'回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42',1617111760695),(35,1,45,13,'回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42',1617111764783),(36,1,45,13,'回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42',1617111768764),(37,1,45,13,'回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42',1617111781185),(38,1,46,13,'回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42',1617111806109),(39,1,46,13,'回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42',1617111809179),(40,1,46,13,'回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42',1617111812815),(41,1,47,13,'回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39',1617111825697),(42,1,47,13,'回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39',1617111828717),(43,1,47,13,'回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39',1617111832251),(44,1,48,13,'回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35',1617111850625),(45,1,48,13,'回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35',1617111853711),(46,1,48,13,'回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35',1617111857226),(47,1,74,11,'这是一只用于测试的海绵宝宝，勿拍！！！',1617113112104),(48,1,74,11,'这是一只用于测试的海绵宝宝，勿拍！！！',1617113115302),(49,1,74,11,'这是一只用于测试的海绵宝宝，勿拍！！！',1617113118469),(50,1,73,14,'安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)',1617113128909),(51,1,73,14,'安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)',1617113131491),(52,1,73,14,'安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)',1617113135673),(53,1,72,14,'安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)',1617113146086),(54,1,72,14,'安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)',1617113148955),(55,1,72,14,'安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)',1617113152297),(56,1,71,14,'安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)',1617113164057),(57,1,71,14,'安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)',1617113166989),(58,1,71,14,'安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)',1617113169875),(59,1,70,12,'李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5',1618918721874),(60,1,70,12,'李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5',1618918725838),(61,2,72,14,'222',1683186600036);
/*!40000 ALTER TABLE `sys_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_goods`
--

DROP TABLE IF EXISTS `sys_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/image/goods-default.jpg',
  `price` decimal(10,2) NOT NULL,
  `state` tinyint NOT NULL DEFAULT '1' COMMENT '1已上架  0未上架',
  `merchant` int NOT NULL COMMENT '商品所属的商户id',
  `stock` int NOT NULL DEFAULT '0' COMMENT '商品库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_goods`
--

LOCK TABLES `sys_goods` WRITE;
/*!40000 ALTER TABLE `sys_goods` DISABLE KEYS */;
INSERT INTO `sys_goods` VALUES (1,'会员 充值卡100元','会员充值卡100元','/data/goods/1/20210330220346963836.jpg',100.00,1,1,96),(11,'耐克NIKE 男子 板鞋 AJ1','耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码','/data/goods/5/20201109204752392935.jpg',0.01,0,5,0),(12,'耐克NIKE 男子 板鞋 经典','耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码','/data/goods/5/20201109205306213001.jpg',0.01,0,5,0),(13,'休闲鞋 CD5463-200','耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION LOW 休闲鞋 CD5463-200亚麻色44码','/data/goods/5/20201109205751273390.jpg',0.01,0,5,1),(14,'板鞋 AO2810-001','耐克NIKE 女子 简约 百搭 COURT ROYALE AC 板鞋 AO2810-001黑色36码','/data/goods/5/20201109205945154276.jpg',0.01,0,5,1),(15,'休闲鞋 CZ1055-109','耐克NIKE 女子 板鞋 经典 BLAZER MID \'77 休闲鞋 CZ1055-109白色36.5码','/data/goods/5/20201109210325235653.jpg',0.01,0,5,1),(17,'运动鞋 CJ0291-005','耐克NIKE 男子 气垫 跑步鞋 缓震 ZOOM WINFLO 7 运动鞋 CJ0291-005黑色42码','/data/goods/5/20201109210622705242.jpg',0.01,0,5,0),(18,'运动鞋 AV4789-100','耐克NIKE 男子 休闲鞋 缓震 M2K TEKNO 运动鞋 AV4789-100顶峰白色42码','/data/goods/5/20201110142709402793.jpg',0.01,0,5,1),(19,'运动鞋 AO0269-101','耐克NIKE 男子 老爹鞋 气垫 ZOOM 2K 运动鞋 AO0269-101白色42.5码','/data/goods/5/20201110142841106068.jpg',0.01,0,5,1),(20,'运动鞋 CK6467-001','耐克 NIKE 男子 板鞋/复刻鞋 AIR MAX 90 NRG 运动鞋 CK6467-001 黑色 42码','/data/goods/5/20201110143028570004.jpg',0.01,0,5,1),(21,'拖鞋 JORDAN','耐克 NIKE 中性大童 拖鞋 JORDAN HYDRO XII RETRO BG 运动鞋 820267-107 白色 37.5码','/data/goods/5/20201110143155960319.jpg',0.01,0,5,1),(22,'休闲鞋 NIKE DROP-TYPE','耐克 NIKE 男子 休闲鞋 NIKE DROP-TYPE MID 运动鞋 BQ5190-300绿 43码','/data/goods/5/20201110143316976441.jpg',0.01,0,5,1),(23,'运动鞋 CZ8681-167','耐克NIKE 女子 休闲鞋 复古 DAYBREAK 运动鞋 CZ8681-167帆白色38码','/data/goods/5/20201110143451872085.jpg',0.01,0,5,1),(25,'运动外套GE5175','阿迪达斯官网 adidas neo W ESNTL 3S WB 女装运动外套GE5175 如图 L','/data/goods/8/20201110214136520879.jpg',0.01,0,8,1),(26,'防风衣ED7539','阿迪达斯官网 adidas 三叶草LOCK UP TT女装防风衣ED7539 ED7541 淡粉紫灰 32(参考身高:160~165CM)','/data/goods/8/20201110224214151666.jpg',0.01,0,8,1),(28,'防风衣 DV0857','阿迪达斯官网 adidas 三叶草 女装防风衣 DV0857 DW3890 DX3694 浅粉紫 38(参考身高:169~172CM)','/data/goods/8/20201110224428269386.jpg',0.01,0,8,1),(29,'防风衣 ED7217','阿迪达斯官网 adidas 三叶草 男装防风衣 ED7217 FL1763 黑色 S(参考身高:173~178CM)','/data/goods/8/20201110224542348334.jpg',0.01,0,8,1),(30,'冬季运动羽绒服FL0036','阿迪达斯官网 adidas 三叶草 DOWN JACKET 女装冬季运动羽绒服FL0036 黑色 34(参考身高:164~167CM)','/data/goods/8/20201110224729185763.jpg',0.01,0,8,1),(31,'运动棉服FJ6523','阿迪达斯官网adidas 三叶草 女装冬季运动棉服FJ6523 黑色 34(参考身高:164~167CM)','/data/goods/8/20201110224846847911.jpg',0.01,0,8,1),(32,'运动棉服 ED7601','阿迪达斯官网 adidas 三叶草 LONG BOMBER 女装冬季运动棉服 ED7601 亮光粉 40(参考身高:170~175CM)','/data/goods/8/20201110225011851415.jpg',0.01,0,8,1),(34,'冬季运动棉服GL6407 ','阿迪达斯官网 adidas 三叶草 Short Sherpa 女装冬季运动棉服GL6407 森林绿/传奇墨水蓝 40(参考身高:170~175CM)','/data/goods/8/20201110225219159233.jpg',0.01,0,8,1),(35,'连帽外套DN8151','阿迪达斯官网 adidas 三叶草 3STR ZIP HOODIE女装连帽外套DN8151 如图 34','/data/goods/8/20201110225330615623.jpg',0.01,0,8,0),(36,'连帽外套 DV1935','阿迪达斯官网 adidas 三叶草 FZ HOODY 男装连帽外套 DV1935 绿 XL(参考身高:188~192CM)','/data/goods/8/20201110225449776961.jpg',0.01,0,8,1),(37,'短袖上衣 CE1666','阿迪达斯官网 adidas 三叶草 女装短袖上衣 CE1666 CE1667 白 32(参考身高:160~165CM)','/data/goods/8/20201110225601894276.jpg',0.01,0,8,1),(38,'短袖上衣 DV1922','阿迪达斯官网adidas 三叶草TEE男装短袖上衣 DV1922 DV1925DV1929 粉蓝 XL(参考身高:188~192CM)','/data/goods/8/20201110225703611247.jpg',0.01,0,8,0),(39,'棒球帽 ARCHIVE','PUMA彪马官方 杨洋同款新款棒球帽 ARCHIVE LOGO LABEL 022778 粉紫色 06 ADULT','/data/goods/9/20201112192043550007.jpg',0.01,0,9,0),(40,'条纹棒球帽 ARCHIVE','PUMA彪马官方 新款条纹棒球帽 ARCHIVE LOGO 022554 粗呢蓝-条纹-09 均码','/data/goods/9/20201112192208777150.jpg',0.01,0,9,0),(41,'运动休闲手提包','PUMA彪马官方娜扎同款新款女子运动休闲手提包 PRIME PUFFA 078192 银色 02 OSFA/均码','/data/goods/9/20201112192355215509.jpg',0.01,0,9,0),(44,'测试商品(勿拍)','测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)测试商品(勿拍)！！！','/data/goods/8/20210109201101998677.jpg',0.01,0,8,82),(45,'国潮手绘皮卡丘帆布鞋','回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42','/data/goods/13/20210330212031740396.jpg',0.01,1,13,5),(46,' 国潮手绘高帮帆布鞋','回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42','/data/goods/13/20210330212214866150.jpg',0.01,1,13,5),(47,'本命年高帮帆布鞋','回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39','/data/goods/13/20210330212245424549.jpg',0.01,1,13,8),(48,'板鞋女鞋2021秋冬季新','回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35','/data/goods/13/20210330212317794584.jpg',0.01,1,13,8),(49,'国潮手绘星球宇航员帆布鞋','回力官方旗舰 国潮手绘星球宇航员帆布鞋情侣2021春夏季新款爆改NASA高帮女士板鞋男鞋子潮鞋子 星球宇航员（下单后五天内发货） 39','/data/goods/13/20210330212343230193.jpg',0.01,1,13,9),(50,'国潮手绘彩虹天使马帆布鞋','回力官方旗舰 国潮手绘彩虹天使马帆布鞋男鞋子2021春夏季女士新款爆改手绘独角兽高帮板鞋潮鞋布鞋 彩虹天使马（下单后五天内发货） 37','/data/goods/13/20210330212408614514.jpg',0.01,1,13,9),(51,'ins潮复古老爹鞋','回力官方旗舰 跑步鞋男鞋2021秋季新款ins潮复古老爹鞋学生透气网面减震休闲运动鞋子男 军绿WXY0170 42','/data/goods/13/20210330212444207204.jpg',0.01,1,13,9),(52,'荧光手绘帆布鞋星际宇航员高帮鞋','回力官方旗舰 荧光手绘帆布鞋星际宇航员高帮鞋2021新款春季男女鞋潮流休闲鞋国潮板鞋子男 夜光黑色星球宇航员 42','/data/goods/13/20210330212516740293.jpg',0.01,1,13,9),(53,'国潮手绘恶魔小丑高帮帆布鞋','【手绘】回力官方旗舰 国潮手绘恶魔小丑高帮帆布鞋樱花爆改恶魔系情侣休闲鞋男女鞋涂鸦潮鞋子 恶魔小丑（下单后五天内发货） 42','/data/goods/13/20210330212538705519.jpg',0.01,1,13,9),(54,'国潮手绘浮世绘仙鹤鲸鱼帆布鞋','【手绘】回力官方旗舰 国潮手绘浮世绘仙鹤鲸鱼帆布鞋男鞋手绘2021春夏季女爆改涂鸦中国风布鞋子 浮世绘仙鹤鲸鱼（下单后五天内发货） 42','/data/goods/13/20210330212601581309.jpg',0.01,1,13,9),(55,'小雏菊经典情侣高帮','回力官方旗舰 小雏菊经典情侣高帮帆布休闲鞋男正品板鞋手绘印刷新款女高帮鞋子 黑色花(女生建议选小一码) 35','/data/goods/13/20210330212628919032.jpg',0.01,1,13,9),(56,'国潮蓝手绘经典款帆布鞋','回力官方旗舰 国潮蓝手绘经典款帆布鞋高帮男女2021春季新款情侣国潮红百搭休闲爆改涂鸦鞋子 国潮蓝（下单后五天内发货） 41','/data/goods/13/20210330212653590038.jpg',0.01,1,13,8),(57,'运动裤卫裤','李宁运动裤卫裤女官方旗舰网运动时尚系列女子收口卫裤休闲长裤时尚 黑色A-5 M','/data/goods/12/20210330213038709716.jpg',0.01,1,12,10),(59,'T恤短袖女夏季新品','李宁T恤短袖女夏季新品女子休闲运动短袖T恤青年薄款休闲跑步健身服圆领休闲上衣短袖官方旗舰网 浅桃粉-3 M','/data/goods/12/20210330213112628569.jpg',0.01,1,12,10),(60,'女子减震休闲鞋','李宁女鞋运动鞋女子减震休闲鞋2021年新品低帮舒适减震透气运动鞋学生户外休闲鞋官方旗舰网 云雾白-1 37','/data/goods/12/20210330213147125049.jpg',0.01,1,12,10),(61,'一体织反光速干凉爽短袖T恤','李宁短袖T恤2021春夏新品男子一体织反光速干凉爽短袖T恤男跑步系列健身训练修身短袖官方旗舰网 灰水蓝标准白-5 L','/data/goods/12/20210330213233328992.jpg',0.01,1,12,10),(62,'运动短裤男裤2021春夏新品','李宁运动短裤男裤2021春夏新品男透气五分裤训练系列夏季短裤经典多色两侧拉链口袋休闲短裤官方旗舰网 黑色/绿-4 L','/data/goods/12/20210330213254176860.jpg',0.01,1,12,10),(63,'运动短裤男裤2021夏季新品宽松透气','李宁运动短裤男裤2021夏季新品宽松透气五分裤男宽松短卫裤男士透气舒适宽松运动裤官方旗舰网 黑色-1 L','/data/goods/12/20210330213319617195.jpg',0.01,1,12,10),(64,'男子棉舒适印花短袖','李宁短袖T恤2021夏季新品男子棉舒适印花短袖文化衫男吸湿透气短袖星球大战联名系列男装官方旗舰网 黑色/B-6 L','/data/goods/12/20210330213355751756.jpg',0.01,1,12,10),(66,'星球大战联名系列男子宽松短袖','李宁短袖T恤男2021新品星球大战联名系列男子宽松短袖文化衫官方旗舰网AHSR407 黑色-4 L','/data/goods/12/20210330213446627820.jpg',0.01,1,12,10),(67,'超轻17男子轻质透气网面运动鞋','李宁男鞋跑步鞋超轻17男子轻质透气网面运动鞋舒适耐磨防滑旅游鞋官方旗舰网 藏青蓝/民谣蓝（男款）ARBQ003-5 42','/data/goods/12/20210330213532368165.jpg',0.01,1,12,10),(68,'低帮运动鞋新款情侣小白鞋','李宁女鞋板鞋低帮运动鞋新款情侣小白鞋经典减震防滑耐磨休闲鞋时尚学生滑板鞋帆布鞋型 云雾白-8 37','/data/goods/12/20210330213601133941.jpg',0.01,1,12,10),(69,'春夏新品舒适减震跑鞋','李宁䨻beng超轻18女子2021春夏新品舒适减震跑鞋学生轻质透气经典百搭运动鞋旅游鞋女鞋官方旗舰网 标准白/荧光橙橘-7 38','/data/goods/12/20210330213633953914.jpg',0.01,1,12,10),(70,'拖鞋女鞋夏季新品','李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5','/data/goods/12/20210330213652173582.jpg',0.01,1,12,6),(71,'安踏官方旗舰老爹鞋女鞋','安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)','/data/goods/14/20210330213748876483.jpg',0.01,1,14,0),(72,'安踏脉冲2代男鞋','安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)','/data/goods/14/20210330213810878030.jpg',0.01,1,14,6),(73,'安踏女休闲鞋2021春季新款','安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)','/data/goods/14/20210330213829639750.jpg',0.01,1,14,0);
/*!40000 ALTER TABLE `sys_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_mt`
--

DROP TABLE IF EXISTS `sys_mt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_mt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ratio` float(11,1) NOT NULL DEFAULT '1.0' COMMENT '商户每单收费需要支付的费率',
  `state` tinyint NOT NULL DEFAULT '0' COMMENT '1启用 0未启用 2未通过注册',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/data/header/merchant/default.png',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_mt`
--

LOCK TABLES `sys_mt` WRITE;
/*!40000 ALTER TABLE `sys_mt` DISABLE KEYS */;
INSERT INTO `sys_mt` VALUES (1,'家乐超市会员充值','ayuan@qq.com','e10adc3949ba59abbe56e057f20f883e',1.0,1,'/data/header/merchant/1/20210322192948971290.jpg'),(5,'Nike耐克','nike@qq.com','e10adc3949ba59abbe56e057f20f883e',1.0,0,'/data/header/merchant/5/20210322192948971290.jpg'),(8,'Adidas阿迪达斯','adidas@qq.com','e10adc3949ba59abbe56e057f20f883e',1.0,0,'/data/header/merchant/8/20210322193005818751.jpg'),(9,'Puma彪马','puma@qq.com','e10adc3949ba59abbe56e057f20f883e',1.0,0,'/data/header/merchant/9/20210322193031653785.jpg'),(12,'LI-NING李宁','lining@qq.com','e10adc3949ba59abbe56e057f20f883e',0.1,1,'/data/header/merchant/12/20210330170610424273.jpeg'),(13,'WARRIOR回力','guest@qq.com','e10adc3949ba59abbe56e057f20f883e',0.1,1,'/data/header/merchant/13/20210330170639894124.jpeg'),(14,'ANTA安踏','anta@qq.com','e10adc3949ba59abbe56e057f20f883e',0.1,1,'/data/header/merchant/14/20210330170723131771.jpeg');
/*!40000 ALTER TABLE `sys_mt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_mt_ui`
--

DROP TABLE IF EXISTS `sys_mt_ui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_mt_ui` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int NOT NULL,
  `height` int NOT NULL,
  `merchant` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_mt_ui`
--

LOCK TABLES `sys_mt_ui` WRITE;
/*!40000 ALTER TABLE `sys_mt_ui` DISABLE KEYS */;
INSERT INTO `sys_mt_ui` VALUES (5,'/data/mtui/5/20201111171430806651.jpg',400,320,5),(6,'/data/mtui/5/20201111171436443523.jpg',600,310,5),(8,'/data/mtui/8/20201111172245637416.jpg',600,310,8),(9,'/data/mtui/9/20201111174106561114.jpg',400,320,9),(10,'/data/mtui/9/20201111174116118997.jpg',600,310,9),(12,'/data/mtui/8/20210109201740252613.jpg',400,320,8),(13,'/data/mtui/12/20210330191353774645.jpg',400,320,12),(14,'/data/mtui/12/20210330191411134816.jpeg',600,310,12),(15,'/data/mtui/13/20210330191433420565.jpg',400,320,13),(16,'/data/mtui/13/20210330191441571457.jpg',600,310,13),(17,'/data/mtui/14/20210330191733928362.jpg',400,320,14),(18,'/data/mtui/14/20210330191638457952.jpg',600,310,14),(19,'/data/mtui/1/20210807190318252283.jpg',400,320,1),(20,'/data/mtui/1/20210807190324130420.jpg',600,310,1);
/*!40000 ALTER TABLE `sys_mt_ui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0给管理员发送 1 商户  2用户',
  `receive` int NOT NULL COMMENT '接收者的id',
  `create_time` bigint NOT NULL COMMENT '通知发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (30,'系统群发消息测试','系统群发消息测试（无需理会）','2',1,1648468781372),(31,'系统群发消息测试','系统群发消息测试（无需理会）','2',2,1648468781375),(35,'欢迎访问~！！','欢迎访问~！！','2',1,1648468890462),(36,'欢迎访问~！！','欢迎访问~！！','2',2,1648468890464);
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_order`
--

DROP TABLE IF EXISTS `sys_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint NOT NULL,
  `pay_time` bigint DEFAULT NULL,
  `goods_num` int NOT NULL COMMENT '商品数量',
  `order_state` tinyint NOT NULL DEFAULT '0' COMMENT '1已支付  0未支付',
  `order_mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品标记',
  `order_user` int NOT NULL COMMENT '用户id',
  `order_merchant` int NOT NULL COMMENT '商户id',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `goods_id` int NOT NULL,
  `order_notes` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单特殊注释',
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `user_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `coupon_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '优惠卷代码',
  `pay_way` tinyint NOT NULL COMMENT '1支付宝 0微信支付',
  `pay_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付链接',
  `cart_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '批量购买的商品',
  `merchant_ratio` float(11,1) NOT NULL DEFAULT '1.0' COMMENT '订单创建时商户的费率',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `order_id` (`order_id`) USING BTREE COMMENT '订单号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_order`
--

LOCK TABLES `sys_order` WRITE;
/*!40000 ALTER TABLE `sys_order` DISABLE KEYS */;
INSERT INTO `sys_order` VALUES (136,'WKF202305124174537479000',1683193537851,1683193537857,2,1,'GIX202305124174537841043',2,13,0.01,45,'','1','1','1','',2,NULL,'26',0.0),(137,'NCR202305124174753992719',1683193673561,NULL,1,0,'AVW202305124174753771065',2,1,100.00,1,'','1','1','13512295947','',2,'/data/pay/AVW202305124174753771065.jpg',NULL,0.0),(138,'NWW202305124175810699895',1683194290461,1683194290470,1,1,'SQE202305124175810961029',2,14,0.01,72,'','1','1','1','',2,NULL,'27',0.0),(139,'NDV202305124180256823628',1683194576095,1683194576101,1,1,'UHB202305124180256985484',2,14,0.01,72,'1','1','1','1','',2,NULL,'27',0.0),(140,'TSC202305124180312707024',1683194592517,1683194592522,1,1,'WSU202305124180312949144',2,14,0.01,72,'','1','1','1','',2,NULL,NULL,0.0),(141,'JLE202305124180720503676',1683194840837,NULL,1,0,'LIZ202305124180720312229',2,13,0.01,56,'','1','1','1','',0,'/data/pay/LIZ202305124180720312229.jpg','29',0.0),(142,'ULJ202305124182106201159',1683195666616,1683195666626,1,1,'SXW202305124182106829940',2,13,0.01,56,'','1','1','1','',2,'/data/pay/SXW202305124182106829940.jpg','29',0.0),(143,'XUH202305124182152109036',1683195712618,NULL,1,0,'FYS202305124182152900147',2,1,100.00,1,'','1','1','1','',2,NULL,NULL,0.0),(144,'ZEY202305124182309862035',1683195789812,1683195924530,1,1,'EGJ202305124182309298432',2,1,100.00,1,'','1','1','1','',0,'/data/pay/EGJ202305124182309298432.jpg',NULL,0.0),(145,'ITE202305124182513383430',1683195913816,1683195913823,1,1,'HFR202305124182513899397',2,1,100.00,1,'','1','1','1','',2,'/data/pay/HFR202305124182513899397.jpg',NULL,0.0),(146,'JFH202305124183133253834',1683196293916,NULL,1,0,'UUR202305124183133192435',2,13,0.01,46,'','1','1','1','',0,'/data/pay/UUR202305124183133192435.jpg',NULL,0.0);
/*!40000 ALTER TABLE `sys_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1已启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'未定义角色',1),(2,'超级管理员',1),(3,'普通管理员',1),(4,'后台访问者',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_access`
--

DROP TABLE IF EXISTS `sys_role_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_access` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `access_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1启用  0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_access`
--

LOCK TABLES `sys_role_access` WRITE;
/*!40000 ALTER TABLE `sys_role_access` DISABLE KEYS */;
INSERT INTO `sys_role_access` VALUES (9,2,1,1),(10,2,2,1),(11,2,3,1),(12,2,4,1),(13,2,5,1),(14,4,1,1),(15,4,2,1),(16,4,3,1),(17,4,4,1),(18,4,5,1),(19,4,6,1),(20,4,7,1);
/*!40000 ALTER TABLE `sys_role_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_ui`
--

DROP TABLE IF EXISTS `sys_ui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_ui` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int NOT NULL,
  `height` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_ui`
--

LOCK TABLES `sys_ui` WRITE;
/*!40000 ALTER TABLE `sys_ui` DISABLE KEYS */;
INSERT INTO `sys_ui` VALUES (18,'/data/library/20210328110354280473.jpg',1230,535),(19,'/data/library/20210330181647732986.jpg',3151,282),(20,'/data/library/20210330181628569458.jpeg',3152,282),(21,'/data/library/20210330190628805198.jpeg',475,570),(22,'/data/library/20210330185815215536.jpg',674,264),(23,'/data/library/20210322125308458944.jpg',1920,737);
/*!40000 ALTER TABLE `sys_ui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mark` tinyint NOT NULL COMMENT '0未启用  1已启用',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/data/header/user/default.jpg',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '会员卡余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'Ayuan','e10adc3949ba59abbe56e057f20f883e','easyarchayuan@126.com',1,'/data/header/user/1.jpg',0.00),(2,'guest','e10adc3949ba59abbe56e057f20f883e','guest@qq.com',1,'/data/header/user/default.jpg',298.92);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04 18:33:53
