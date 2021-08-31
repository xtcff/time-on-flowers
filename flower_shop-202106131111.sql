-- MySQL dump 10.13  Distrib 5.6.49, for Win64 (x86_64)
--
-- Host: localhost    Database: flower_shop
-- ------------------------------------------------------
-- Server version	5.6.49-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartitem` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flower_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `cartitem_user_FK` (`user_id`),
  KEY `cartitem_flowers_FK` (`flower_id`),
  CONSTRAINT `cartitem_flowers_FK` FOREIGN KEY (`flower_id`) REFERENCES `flowers` (`id`),
  CONSTRAINT `cartitem_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitem`
--

LOCK TABLES `cartitem` WRITE;
/*!40000 ALTER TABLE `cartitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'爱情类','送爱人、送女朋友、送小姐姐，或者向女神表明你的心意，或是取走独属于你的浪漫'),(2,'友情类','送朋友、送室友、送同事，致这些年你们真挚的友情'),(3,'亲情类','送父母、送爷爷奶奶、送亲戚，感谢他们这些年对自己的悉心照顾'),(4,'其他类','一些奇奇怪怪的花，你觉得它应该送给谁？');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flowers`
--

DROP TABLE IF EXISTS `flowers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flowers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flowername` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(100) CHARACTER SET latin1 NOT NULL,
  `description` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `VIP_price` double NOT NULL,
  `sales` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `flowername` (`flowername`),
  KEY `category_id_FK` (`category_id`),
  CONSTRAINT `category_id_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flowers`
--

LOCK TABLES `flowers` WRITE;
/*!40000 ALTER TABLE `flowers` DISABLE KEYS */;
INSERT INTO `flowers` VALUES (1,'99支卡罗拉红玫瑰',588,'redrose.jpg','爱她，就送她一束99枝的玫瑰花！',1,521,90),(2,'11枝香槟玫瑰',198,'xiangbinrose.jpg','喜欢像是一阵风，而爱你是细水长流！',1,178,75),(3,'甜美公主',368,'tianmeigongzhu.jpg','坦尼克玫瑰22枝，粉佳人粉玫瑰14枝，粉色桔梗5枝',1,339,113),(4,'公主假日',219,'gongzhujiari.jpg','戴安娜玫瑰11枝、白色洋桔梗5枝、大叶尤加利10枝',1,199,204),(5,'星河璀璨',288,'xinghecuican.jpg','香槟玫瑰9枝、蓝绣球、向日葵、白色洋桔梗、大叶尤加利',1,259,16),(6,'月光女神',239,'yueguangnvshen.jpg','坦尼克玫瑰11枝，绿色桔梗5枝，小菊3枝，白色石竹梅4枝',1,219,38),(7,'一路向阳',296,'yiluxiangyang.jpg','向日葵3枝、香槟玫瑰9枝、橙色多头玫5枝、黄色腊梅5枝、大叶尤加利5枝',2,269,49),(8,'天秤座守护花',299,'tianchengzuoshouhu.jpg','香槟玫瑰11枝、多头白百合3枝、蕾丝3枝、银叶菊8枝',2,271,22),(9,'巨蟹座守护花',139,'juxiezuoshouhu.jpg','戴安娜玫瑰3枝、坦尼克玫瑰6枝、粉色勿忘我5枝',2,119,160),(10,'乘风破浪',326,'chengfengpolang.jpg','向日葵3枝、橙色康乃馨19枝、香槟玫瑰11枝、橙色腊梅2枝',3,298,137),(11,'健康喜乐',269,'jiankangxile.jpg','红色康乃馨29枝、粉色百合3枝、黄色勿忘我3枝、尤加利10枝',3,239,111),(12,'幸福绽放',159,'xingfuzhanfang.jpg','粉色康乃馨19枝，花儿浓缩了我对您的祝福',3,139,299),(13,'幸福时光',629,'xingfushiguang.jpg','爱情保鲜花，红玫瑰系列，5年不凋谢',4,599,7),(14,'一路有你',359,'yiluyouni.jpg','5年不凋谢，真空保鲜花，彩玫系列，玫瑰5枝',4,329,9),(15,'玫瑰花园',899,'meiguihuayuan.jpg','超大号进口保鲜花，红玫瑰9枝，不凋谢真空玫瑰花',4,839,4),(16,'爱在心头',396,'aizaixintou.jpg','戴安娜粉玫瑰19枝，卡罗拉红玫瑰31枝',1,369,141),(17,'缪斯女神',189,'miusinvshen.jpg','卡罗拉红玫瑰16枝，红豆5枝，粉色桔梗1枝，银叶菊2枝',1,168,217),(18,'此情不渝',278,'ciqingbuyu.jpg','卡罗拉玫瑰19枝、白色洋桔梗3枝、红豆5枝，尤加利5枝',1,259,19),(19,'眷念',335,'juannian.jpg','戴安娜粉玫瑰33枝，石竹梅20枝',1,319,125),(20,'用心爱你',599,'yongxinaini.jpg','99枝：33枝戴安娜玫瑰＋66枝卡罗拉红玫瑰',1,569,196),(21,'爱莎公主99',639,'aishagongzhu.jpg','艾莎玫瑰99枝， 想把你宠成公主，也想给你全部温柔',1,609,8),(22,'我爱你',3299,'woaini.jpg','卡罗拉红玫瑰321枝，粉色戴安娜玫瑰199枝围成心形。',1,2999,3),(23,'青春有你',356,'qingchunyouni.jpg','苏醒玫瑰16枝、白色洋桔梗5枝、浅蓝绣球1枝',1,329,26),(24,'慢慢喜欢你',239,'manmanxihuanni.jpg','艾莎玫瑰16枝、白色洋桔梗5枝、尤加利10枝',1,219,31),(25,'亲爱的你',389,'qinaideni.jpg','粉佳人玫瑰16枝、白和粉色洋桔梗各5枝、尤加利10枝、浅紫色小菊3枝、深粉色/浅粉色绣球1枝',2,369,47),(26,'温柔以待',239,'wenrouyidai.jpg','粉色康乃馨13枝，戴安娜玫瑰5枝、粉色洋桔梗5枝、浅紫紫罗兰5枝、尤加利10枝',2,219,104),(27,'你笑起来真好看',296,'nixiaoqilaizhenhaokan.jpg','艾莎玫瑰19枝、白色洋桔梗3枝、5头白色香水百合3枝、尤加利10枝',2,269,37),(28,'满天星',198,'mantianxing.jpg','我携满天星辰赠你，好教你不逊色人间错落烟火；只有这满天星辰，足够与你相配。',2,179,60),(29,'特别的你',279,'tebiedeni.jpg','戴安娜玫瑰19枝、粉色洋桔梗3枝、尤加利10枝、白色石竹梅5枝',2,259,10),(30,'平淡岁月',295,'pingdansuiyue.jpg','苏醒玫瑰13枝，蓝紫色绣球1枝，香槟洋桔梗5枝，粉洋桔梗3枝，尤加利叶5枝',2,269,31),(31,'十全十美',899,'shiquanshimei.jpg','蝴蝶兰- 室内盆栽（10株红色一品蝴蝶兰）。',4,869,9),(32,'君子兰',360,'junzilan.jpg','君子兰--五年生,君子兰是花叶兼赏的名贵盆栽花卉。',4,339,2),(33,'感恩母亲',169,'ganenmuqin.jpg','玫红色康乃馨9枝、粉色多头康乃馨10枝',3,139,27),(34,'留住好时光',255,'liuzhuhaoshiguang.jpg','粉绣球1枝，粉雪山玫瑰6枝',3,239,152),(35,'一缕清香',189,'yilvqingxiang.jpg',' 向日葵3枝，白色洋桔梗5枝，绿色小雏菊2枝，雪柳1枝',3,169,70),(36,'恩情无限',175,'enqingwuxian.jpg','粉色康乃馨11枝，粉色多头香水百合2枝，栀子叶10枝',3,159,138),(37,'牵挂你',275,'qianguani.jpg','红色康乃馨13枝，粉佳人玫瑰5枝、粉色洋桔梗5枝、粉色紫罗兰3枝、红豆5枝，尤加利10枝',3,259,18),(38,'感恩密码',166,'ganenmima.jpg','粉色康乃馨16枝、浅粉满天星3枝、尤加利10枝',3,139,145),(39,'馨情无限',238,'xinqingwuxian.jpg',' 高档礼盒装鲜花:戴安娜粉玫瑰11枝，红色康乃馨11支，红色石竹梅4枝，栀子叶4枝',3,209,131),(40,'幸福万年长',332,'xingfuwannianchang.jpg','红色康乃馨66枝，搭配白边紫色多头康乃馨15枝，栀子叶2扎',3,309,32);
/*!40000 ALTER TABLE `flowers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_inf`
--

DROP TABLE IF EXISTS `order_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_inf` (
  `orderid` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `user_phone` varchar(40) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `tosb_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `tosb_phone` varchar(40) NOT NULL,
  `whento` varchar(40) NOT NULL,
  `price` double NOT NULL,
  `order_status` varchar(100) CHARACTER SET utf8mb4 DEFAULT '未处理',
  `receive_time` varchar(100) CHARACTER SET utf8mb4 DEFAULT '未收货',
  PRIMARY KEY (`orderid`),
  KEY `order_inf_user_FK` (`user_id`),
  CONSTRAINT `order_inf_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189658246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_inf`
--

LOCK TABLES `order_inf` WRITE;
/*!40000 ALTER TABLE `order_inf` DISABLE KEYS */;
INSERT INTO `order_inf` VALUES (113472142,1,'18530350704','河南郑州','侯宏帆','18530350704','2021-06-13 at 22:27:00 CST',178,'未处理','未收货'),(139427409,1,'18530350704','q','qqq','qqqq','2021-06-13 at 19:02:40 CST',1316,'未处理','未收货'),(189658245,1,'18530350704','aa','aaaa','aaa','2021-06-13 at 18:59:35 CST',178,'未处理','未收货');
/*!40000 ALTER TABLE `order_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `flowers_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderitem_order_inf_FK` (`order_id`),
  KEY `orderitem_flowers_FK` (`flowers_id`),
  CONSTRAINT `orderitem_flowers_FK` FOREIGN KEY (`flowers_id`) REFERENCES `flowers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orderitem_order_inf_FK` FOREIGN KEY (`order_id`) REFERENCES `order_inf` (`orderid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (1,1,178,189658245,2),(2,2,678,139427409,3),(3,1,269,139427409,7),(4,1,369,139427409,25),(5,1,178,113472142,2);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(40) CHARACTER SET latin1 NOT NULL,
  `phone` varchar(40) CHARACTER SET latin1 NOT NULL,
  `email` varchar(40) CHARACTER SET latin1 NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hhf','hhfzs','18530350704','crystalfan0214@163.com','河南省三门峡市'),(2,'xt','hhfzs','13443215678','13443215678@163.com','湖北省武汉市'),(3,'yyb','hhfzs','13699887744','13699887744@163.com','河南省巩义市'),(4,'ys','hhfzs','15636985214','15636985214@163.com','陕西省咸阳市'),(5,'cjl','hhfzs','18533365559','18533365559@163.com','河南省鹤壁市'),(6,'wpf','hhfzs','18533456998','18533456998@163.com','河南省郑州市'),(7,'1435358206','xt991128.','17354346710','1435358206@qq.com','wuhan'),(8,'xll','123456','123456','123456@qq.com','芜湖'),(9,'deam','991214','15303761022','1070507192@qq.com','郑州');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-13 23:11:59
