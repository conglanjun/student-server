/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
DROP TABLE IF EXISTS building;
CREATE TABLE `building` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS consumption;
CREATE TABLE `consumption` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `type` varchar(255) DEFAULT NULL COMMENT '耗材类别，例如：螺丝刀，电线，透明胶，绝缘胶带，手套等',
  `inventory` int DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS message;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `title` varchar(255) DEFAULT NULL,
  `message` varchar(1024) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS notice;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `text` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS role;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `name` varchar(255) DEFAULT NULL COMMENT 'admin student dormitory-manager maintenance-manager maintainer',
  `display_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS room;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `building_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS service;
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS service_type;
CREATE TABLE `service_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `create_time` datetime DEFAULT NULL COMMENT 'Create Time',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='student information';

INSERT INTO building(id,create_time,name,description) VALUES('3','2024-02-20 19:22:00','A1','A1是男生宿舍1'),('4','2024-02-20 19:22:56','A3','A3是女生宿舍，请男生禁止入内。如果特殊情况请老师审批。请谅解，谢谢配合。1');

INSERT INTO consumption(id,create_time,type,inventory) VALUES('2','2024-02-20 17:03:32','橡胶手套','29'),('3','2024-02-20 16:10:16','透明胶带','96'),('4','2024-02-20 16:12:55','绝缘胶带','30'),('5','2024-02-20 16:16:29','螺丝刀','10'),('6','2024-02-20 16:19:12','安全帽','10'),('9','2024-02-20 17:16:50','雨靴','14'),('10','2024-02-20 17:16:58','工作服','8');

INSERT INTO message(id,create_time,title,message,user_id) VALUES('7','2024-02-26 15:42:48','寝室漏水','下雨就漏水','15');

INSERT INTO notice(id,create_time,text) VALUES('1','2024-02-05 20:04:35','Xe6b688e998b2e696b9e99da2efbc8ce8afb7e5ada6e7949fe4b88de5be97e4bdbfe794a8e7828ae585b7e785aee9a5ade78292e88f9cefbc8ce4b88de5be97e4bdbfe794a8e9ab98e58a9fe78e87e794b5e599a8efbc8ce4b88de4b9b1e68b89e794b5e7babfefbc8ce5819ae588b0e4babae7a6bbe5bc80e5908ee58f8ae697b6e585b3e997ade794b5e599a8efbc8ce58887e696ade794b5e6ba90e38082e7a7afe69e81e5819ae5a5bde998b2e781abe5ae89e585a8efbc8ce58f8ae697b6e6b688e999a4e781abe781bee99a90e682a3e38082'),('2','2024-02-05 20:04:35','Xe998b2e79b97e696b9e99da2efbc8ce59084e4bd8de5908ce5ada6e4bf9de7aea1e5a5bde887aae5b7b1e79a84e78eb0e98791efbc8ce794b5e88491e7ad89e8b4b5e9878de789a9e59381efbc8ce7a6bbe5bc80e5aebfe8888de58f8ae697b6e585b3e5a5bde997a8e7aa97efbc8ce5b086e997a8e99481e5a5bde38082e5aebfe8888de59198e5b7a5e4b8a5e7a681e7a781e887aae79599e5aebfe5a496e4babaefbc8ce998b2e6ada2e5a496e4babae99a8fe6848fe8bf9be585a5e5aebfe8888defbc8ce88ba5e58f91e78eb0e58fafe79691e4babae59198efbc8ce8afb7e9809fe68aa5e5918ae69cace6a5bce7aea1e79086e59198e5928ce4bf9de58dabe7a791e38082'),('3','2024-02-05 20:04:35','Xe4b8a5e7a681e59ca8e5aebfe8888de58685e8819ae4bc97e8b58ce58d9ae7ad89e8bf9de6b395e6b4bbe58aa8efbc8ce4b8a5e7a681e9ab98e5a3b0e596a7e59397efbc8ce68993e997b9efbc8ce69697e6aeb4efbc8ce4bba5e5858de5a6a8e7a28de4bb96e4babae4bc91e681afe38082e6999ae4b88ae58f8ae697b6e5b0b1e5af9de38082');

INSERT INTO role(id,name,display_name) VALUES('1','admin','管理员'),('2','student','学生'),('3','dormitory-manager','宿舍管理员'),('4','maintenance-manager','维修管理员'),('5','maintainer','维修人员');

INSERT INTO room(id,create_time,name,description,building_id) VALUES('8','2024-02-23 11:41:00','101','一楼一寝室','3'),('9','2024-02-23 11:41:12','102','一楼二寝室','3'),('10','2024-02-23 11:41:24','201','二楼一寝室','3'),('11','2024-02-23 11:41:36','202','二楼二寝室','3'),('12','2024-02-23 11:42:00','101','一楼女一寝室','4'),('13','2024-02-23 11:42:10','102','一楼女二寝室','4'),('14','2024-02-23 11:42:26','201','二楼女一寝室','4'),('15','2024-02-23 11:42:36','202','二楼女二寝室','4'),('16','2024-02-26 15:32:52','301','3楼寝室','3');

INSERT INTO service(id,create_time,title,detail,status,creator_id,maintainer_id,rate,comment,type_id,room_id) VALUES('5','2024-02-17 15:31:22','修水管','维修水管哈','FINISHED','15','12','4','已经修复，感谢','1','8'),('6','2024-02-17 15:33:22','床坏了','床板子断了，请换新的','FINISHED','1','12','3','修复完毕，就是修复太慢了。','1','8'),('7','2024-02-17 15:36:18','窗户坏了','请来维修窗户','SUBMITTED','1','NULL','NULL','NULL','1','9'),('8','2024-02-18 11:11:56','寝室灯坏了','请维修寝室吊灯，昨晚突然坏掉了。','SUBMITTED','15','NULL','NULL','NULL','1','10'),('9','2024-02-18 12:09:37','修复窗户','最近寝室门窗有异响，请及时修复，感谢。','SUBMITTED','15','NULL','NULL','NULL','7','11'),('19','2024-02-18 19:41:50','吊灯','修吊灯','SUBMITTED','1','NULL','NULL','NULL','7','12'),('21','2024-02-18 22:51:19','电路','电路老化','SUBMITTED','15','NULL','NULL','NULL','8','13'),('22','2024-02-20 01:02:30','漏电','请尽快修复电灯','SUBMITTED','15','NULL','NULL','NULL','8','14'),('23','2024-02-21 00:01:04','修电器','冰箱坏了','FINISHED','15','12','5','不错哦','8','15'),('24','2024-02-23 14:37:01','水电1','维修水电1，男生宿舍','SUBMITTED','1','NULL','NULL','NULL','8','8'),('30','2024-02-26 15:35:59','维修床','寝室床坏了','FINISHED','15','12','5','修的很快，非常好','10','12');

INSERT INTO service_type(id,create_time,name) VALUES('1','2024-02-18 13:28:21','宿舍开关'),('6','2024-02-18 17:46:00','门窗'),('7','2024-02-18 18:00:38','灯具'),('8','2024-02-18 18:00:44','水电'),('9','2024-02-21 00:00:58','煤气'),('10','2024-02-26 15:35:52','座椅床');
INSERT INTO `user`(id,name,phone,create_time,password,identity,role_id,room_id) VALUES('1','管理员','19999999999','2024-02-17 14:09:13','e10adc3949ba59abbe56e057f20f883e','00001','1','NULL'),('11','张三','123123123','2024-02-05 00:35:02','202cb962ac59075b964b07152d234b70','21001','3','14'),('12','李四','1231231231','2024-02-05 00:37:50','202cb962ac59075b964b07152d234b70','21002','5','NULL'),('13','王五','123123123123','2024-02-05 00:38:19','202cb962ac59075b964b07152d234b70','21003','4','NULL'),('14','赵六','18740036039','2024-02-05 11:09:15','e10adc3949ba59abbe56e057f20f883e','21004','2','12'),('15','丛兰军','18740036034','2024-02-05 11:25:09','e10adc3949ba59abbe56e057f20f883e','21005','2','8'),('16','张宿管','10000000000','2024-02-20 17:25:36','e10adc3949ba59abbe56e057f20f883e','NULL','3','8'),('18','王一','11111111111','2024-02-20 19:52:11','e10adc3949ba59abbe56e057f20f883e','NULL','2','8');