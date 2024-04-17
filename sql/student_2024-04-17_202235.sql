-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: 127.0.0.1    Database: student
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `article_read`
--

DROP TABLE IF EXISTS `article_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_read` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '所属文章id',
  `read_num` int unsigned DEFAULT '1' COMMENT '阅读数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unionKey` (`article_id`) USING BTREE COMMENT '文章唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_read`
--

/*!40000 ALTER TABLE `article_read` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_read` ENABLE KEYS */;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (3,'2024-02-20 19:22:00','A1','A1是男生宿舍1'),(4,'2024-02-20 19:22:56','A3','A3是女生宿舍，请男生禁止入内。如果特殊情况请老师审批。请谅解，谢谢配合。1');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '所属文章id',
  `comment_user_id` int unsigned DEFAULT NULL COMMENT '评论人id',
  `parent_id` int unsigned DEFAULT NULL COMMENT '所属评论id，主评论为null',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '评论内容',
  `like` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '点赞(存储点赞人id数组)',
  `status` tinyint unsigned DEFAULT '0' COMMENT '状态，0-未审核，1-展现，2-审核驳回，3-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'7',13,NULL,'是真的吗','[15,13]',1,'2024-04-17 12:14:31','2024-04-17 05:07:17'),(2,'7',15,1,'@王五 是真的。确实漏水。','[]',1,'2024-04-17 13:16:39','2024-04-17 13:16:39'),(4,'7',13,NULL,'我一会去看看。','[]',1,'2024-04-17 13:56:33','2024-04-17 13:56:33'),(5,'7',13,1,'@丛兰军 好的，马上去','[]',1,'2024-04-17 13:57:15','2024-04-17 13:57:15');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

--
-- Table structure for table `consumption`
--

DROP TABLE IF EXISTS `consumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumption` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `type` varchar(255) DEFAULT NULL COMMENT '耗材类别，例如：螺丝刀，电线，透明胶，绝缘胶带，手套等',
  `inventory` int DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption`
--

/*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
INSERT INTO `consumption` VALUES (2,'2024-02-20 17:03:32','橡胶手套',21),(3,'2024-02-20 16:10:16','透明胶带',96),(4,'2024-02-20 16:12:55','绝缘胶带',30),(5,'2024-02-20 16:16:29','螺丝刀',10),(6,'2024-02-20 16:19:12','安全帽',10),(9,'2024-02-20 17:16:50','雨靴',14),(10,'2024-02-20 17:16:58','工作服',8);
/*!40000 ALTER TABLE `consumption` ENABLE KEYS */;

--
-- Table structure for table `consumption_record`
--

DROP TABLE IF EXISTS `consumption_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumption_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `consumption_id` int DEFAULT NULL,
  `consumption_number` int DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption_record`
--

/*!40000 ALTER TABLE `consumption_record` DISABLE KEYS */;
INSERT INTO `consumption_record` VALUES (2,'2024-03-18 12:55:28',2,3,13);
/*!40000 ALTER TABLE `consumption_record` ENABLE KEYS */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `title` varchar(255) DEFAULT NULL,
  `message` varchar(1024) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (7,'2024-02-26 15:42:48','寝室漏水','下雨就漏水',15);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `text` text,
  `image_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'2024-02-05 20:04:35','请维护寝室公共设施，1及时报修。','http://localhost:8080/images/2ac477e2-336c-44da-83dd-96fd60d52411.png'),(2,'2024-02-05 20:04:35','注意保管好个人贵重物品。','http://localhost:8080/images/88ef66e9-4342-4cbf-aab5-ce826fcf79fb.jpeg'),(3,'2024-02-05 20:04:35','不要在走廊喧哗打闹，注意安全。','http://localhost:8080/images/6b90cf63-5e71-4604-9389-4658b24803b1.jpeg');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `name` varchar(255) DEFAULT NULL COMMENT 'admin student dormitory-manager maintenance-manager maintainer',
  `display_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','管理员'),(2,'student','学生'),(3,'dormitory-manager','宿舍管理员'),(4,'maintenance-manager','维修管理员'),(5,'maintainer','维修人员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `building_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (8,'2024-02-23 11:41:00','101','一楼一寝室',3),(9,'2024-02-23 11:41:12','102','一楼二寝室',3),(10,'2024-02-23 11:41:24','201','二楼一寝室',3),(11,'2024-02-23 11:41:36','202','二楼二寝室',3),(12,'2024-02-23 11:42:00','101','一楼女一寝室',4),(13,'2024-02-23 11:42:10','102','一楼女二寝室',4),(14,'2024-02-23 11:42:26','201','二楼女一寝室',4),(15,'2024-02-23 11:42:36','202','二楼女二寝室',4),(16,'2024-02-26 15:32:52','301','3楼寝室',3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `title` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL COMMENT 'CREATED PROCESSING DONE',
  `creator_id` int DEFAULT NULL,
  `maintainer_id` int DEFAULT NULL,
  `rate` int DEFAULT NULL COMMENT 'maintainer rate 1-5 stars',
  `comment` varchar(1024) DEFAULT NULL,
  `type_id` int DEFAULT NULL COMMENT 'service type id',
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (5,'2024-02-17 15:31:22','修水管','维修水管哈','FINISHED',15,12,4,'已经修复，感谢',1,8),(6,'2024-02-17 15:33:22','床坏了','床板子断了，请换新的','FINISHED',1,12,3,'修复完毕，就是修复太慢了。',1,8),(7,'2024-02-17 15:36:18','窗户坏了','请来维修窗户','SUBMITTED',1,0,0,'NULL',1,9),(8,'2024-02-18 11:11:56','寝室灯坏了','请维修寝室吊灯，昨晚突然坏掉了。','SUBMITTED',15,0,0,'NULL',1,10),(9,'2024-02-18 12:09:37','修复窗户','最近寝室门窗有异响，请及时修复，感谢。','SUBMITTED',15,0,0,'NULL',7,11),(19,'2024-02-18 19:41:50','吊灯','修吊灯','SUBMITTED',1,0,0,'NULL',7,12),(21,'2024-02-18 22:51:19','电路','电路老化','SUBMITTED',15,0,0,'NULL',8,13),(22,'2024-02-20 01:02:30','漏电','请尽快修复电灯','SUBMITTED',15,0,0,'NULL',8,14),(23,'2024-02-21 00:01:04','修电器','冰箱坏了','FINISHED',15,12,5,'不错哦',8,15),(24,'2024-02-23 14:37:01','水电1','维修水电1，男生宿舍','SUBMITTED',1,0,0,'NULL',8,8),(30,'2024-02-26 15:35:59','维修床','寝室床坏了','FINISHED',15,12,5,'修的很快，非常好',10,12);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;

--
-- Table structure for table `service_type`
--

DROP TABLE IF EXISTS `service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_type`
--

/*!40000 ALTER TABLE `service_type` DISABLE KEYS */;
INSERT INTO `service_type` VALUES (1,'2024-02-18 13:28:21','宿舍开关'),(6,'2024-02-18 17:46:00','门窗'),(7,'2024-02-18 18:00:38','灯具'),(8,'2024-02-18 18:00:44','水电'),(9,'2024-02-21 00:00:58','煤气'),(10,'2024-02-26 15:35:52','座椅床');
/*!40000 ALTER TABLE `service_type` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `password` varchar(255) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL COMMENT 'job id or student id',
  `role_id` int DEFAULT NULL COMMENT 'role id',
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='student information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'管理员','19999999999','2024-02-17 14:09:13','e10adc3949ba59abbe56e057f20f883e','00001',1,0),(11,'张三','123123123','2024-02-05 00:35:02','202cb962ac59075b964b07152d234b70','21001',3,14),(12,'李四','1231231231','2024-02-05 00:37:50','202cb962ac59075b964b07152d234b70','21002',5,0),(13,'王五','123123123123','2024-02-05 00:38:19','202cb962ac59075b964b07152d234b70','21003',4,0),(14,'赵六','18740036039','2024-02-05 11:09:15','e10adc3949ba59abbe56e057f20f883e','21004',2,12),(15,'丛兰军','18740036034','2024-02-05 11:25:09','e10adc3949ba59abbe56e057f20f883e','21005',2,8),(16,'张宿管','10000000000','2024-02-20 17:25:36','e10adc3949ba59abbe56e057f20f883e','NULL',3,8),(18,'王一','11111111111','2024-02-20 19:52:11','e10adc3949ba59abbe56e057f20f883e','NULL',2,8),(20,'测试楼','12345678901','2024-03-28 10:50:05','e10adc3949ba59abbe56e057f20f883e',NULL,2,13);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping routines for database 'student'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17 20:22:39
