-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: certification_exam
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `english_level`
--

DROP TABLE IF EXISTS `english_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `english_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `english_level_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `english_level`
--

LOCK TABLES `english_level` WRITE;
/*!40000 ALTER TABLE `english_level` DISABLE KEYS */;
INSERT INTO `english_level` VALUES (1,'A2'),(2,'B1');
/*!40000 ALTER TABLE `english_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `listening_grade` int(11) DEFAULT NULL,
  `reading_grade` int(11) DEFAULT NULL,
  `speaking_grade` int(11) DEFAULT NULL,
  `writing_grade` int(11) DEFAULT NULL,
  `exam_room_id` bigint(20) DEFAULT NULL,
  `examine_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2lntfak7rdl0c30rtdhwfalaj` (`exam_room_id`),
  KEY `FKjpsaveugt9xnhphp7x0xj96yr` (`examine_id`),
  CONSTRAINT `FK2lntfak7rdl0c30rtdhwfalaj` FOREIGN KEY (`exam_room_id`) REFERENCES `exam_room` (`id`),
  CONSTRAINT `FKjpsaveugt9xnhphp7x0xj96yr` FOREIGN KEY (`examine_id`) REFERENCES `examine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (4,90,90,90,70,2,2,2),(5,60,70,80,100,2,1,2),(6,80,60,70,60,10,3,1),(7,50,40,40,60,2,8,1),(8,50,40,50,60,2,14,1),(9,80,80,70,75,10,2,2),(11,80,70,70,60,10,1,1),(12,50,70,60,70,2,19,2);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_course`
--

DROP TABLE IF EXISTS `exam_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `month` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `english_level_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exam_course_name_unique` (`name`),
  KEY `FKldcd13826uj9h70sxyoo24ipp` (`english_level_id`),
  CONSTRAINT `FKldcd13826uj9h70sxyoo24ipp` FOREIGN KEY (`english_level_id`) REFERENCES `english_level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_course`
--

LOCK TABLES `exam_course` WRITE;
/*!40000 ALTER TABLE `exam_course` DISABLE KEYS */;
INSERT INTO `exam_course` VALUES (1,9,'A2K092021',2021,1),(2,9,'B1K092021',2021,2),(3,10,'A2K102021',2021,1),(7,11,'B1K112021',2021,2);
/*!40000 ALTER TABLE `exam_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_course_examine`
--

DROP TABLE IF EXISTS `exam_course_examine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_course_examine` (
  `exam_course_id` bigint(20) NOT NULL,
  `examine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exam_course_id`,`examine_id`),
  KEY `FKdsed0twlip7syp1qbjvkb79r5` (`examine_id`),
  CONSTRAINT `FKdsed0twlip7syp1qbjvkb79r5` FOREIGN KEY (`examine_id`) REFERENCES `examine` (`id`),
  CONSTRAINT `FKpo63mwlkb8qs38ndmnbh839gk` FOREIGN KEY (`exam_course_id`) REFERENCES `exam_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_course_examine`
--

LOCK TABLES `exam_course_examine` WRITE;
/*!40000 ALTER TABLE `exam_course_examine` DISABLE KEYS */;
INSERT INTO `exam_course_examine` VALUES (2,1),(2,2),(2,3),(2,4),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(2,15),(1,16),(2,16),(1,17),(2,17),(1,18),(2,18),(1,19),(2,19),(1,20),(2,20),(1,21),(2,21),(1,22),(2,22),(1,23),(2,23),(1,24),(2,24),(1,25),(2,25),(1,26),(2,26),(1,27),(2,27),(1,28),(2,28),(1,29),(2,29),(1,30),(2,30),(1,31),(2,31),(1,32),(2,32),(1,33),(2,33),(2,34),(1,35),(2,35),(2,36),(1,37),(2,37),(2,38),(1,39),(2,39),(2,40),(1,41),(2,41),(1,42),(2,42),(1,43),(1,44),(2,44),(1,45),(2,45),(2,46),(1,47),(2,47),(1,48),(2,48),(3,48),(3,49);
/*!40000 ALTER TABLE `exam_course_examine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_room`
--

DROP TABLE IF EXISTS `exam_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `exam_course_id` bigint(20) DEFAULT NULL,
  `examiner_id` bigint(20) DEFAULT NULL,
  `proctor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exam_room_name_unique` (`name`),
  KEY `FKca524x1ealnr5ved1twn5tr77` (`exam_course_id`),
  KEY `FK6i5hgfvu1p37p8ht7a6l643hg` (`examiner_id`),
  KEY `FKah73e5vfgy3bm7eld48h4kd0b` (`proctor_id`),
  CONSTRAINT `FK6i5hgfvu1p37p8ht7a6l643hg` FOREIGN KEY (`examiner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKah73e5vfgy3bm7eld48h4kd0b` FOREIGN KEY (`proctor_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKca524x1ealnr5ved1twn5tr77` FOREIGN KEY (`exam_course_id`) REFERENCES `exam_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_room`
--

LOCK TABLES `exam_room` WRITE;
/*!40000 ALTER TABLE `exam_room` DISABLE KEYS */;
INSERT INTO `exam_room` VALUES (2,'2021-09-20 07:30:00','A2P01',35,1,3,2),(3,'2021-09-20 09:30:00','A2P02',0,1,3,2),(6,'2021-09-21 09:30:00','B1P01',0,2,2,3),(10,'2021-09-21 15:00:00','B1P02',3,2,4,2),(11,'2021-09-19 07:30:00','A2P03',4,1,NULL,4),(12,'2021-09-21 10:00:00','A2P04',0,1,NULL,NULL),(13,'2021-09-20 15:00:00','B1P03',0,2,NULL,NULL),(15,'2021-10-18 08:00:00','A2P05',2,3,NULL,NULL),(16,'2021-11-01 09:00:00','A2P06',0,3,NULL,NULL);
/*!40000 ALTER TABLE `exam_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_room_examine`
--

DROP TABLE IF EXISTS `exam_room_examine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_room_examine` (
  `exam_room_id` bigint(20) NOT NULL,
  `examine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exam_room_id`,`examine_id`),
  KEY `FKnngglkhpopj11s2fw70pjhbaw` (`examine_id`),
  CONSTRAINT `FKah54ql6f4xfwxi06uy2ofu5jk` FOREIGN KEY (`exam_room_id`) REFERENCES `exam_room` (`id`),
  CONSTRAINT `FKnngglkhpopj11s2fw70pjhbaw` FOREIGN KEY (`examine_id`) REFERENCES `examine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_room_examine`
--

LOCK TABLES `exam_room_examine` WRITE;
/*!40000 ALTER TABLE `exam_room_examine` DISABLE KEYS */;
INSERT INTO `exam_room_examine` VALUES (10,1),(10,2),(10,3),(2,7),(2,8),(11,8),(2,9),(11,9),(2,10),(11,10),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(11,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,35),(2,37),(2,39),(2,41),(2,42),(2,43),(2,44),(2,45),(2,48),(15,48),(15,49);
/*!40000 ALTER TABLE `exam_room_examine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examine`
--

DROP TABLE IF EXISTS `examine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `examine_id` varchar(30) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` int(11) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `examine_phone_unique` (`phone`),
  UNIQUE KEY `examine_examine_id_unique` (`examine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examine`
--

LOCK TABLES `examine` WRITE;
/*!40000 ALTER TABLE `examine` DISABLE KEYS */;
INSERT INTO `examine` VALUES (1,'Nha Trang','2001-06-14','B1003','Qu???c Tu???n',1,'Tr???n','0776521474',1),(2,'Nha Trang','2000-09-11','B1001','Th??? Th??y',2,'L??','0906521766',1),(3,'V??ng T??u','2000-09-06','B1008','B???o Ch??u',2,'L??','0904522158',1),(4,'TP.HCM','2000-09-06','B10010','Ho??ng Minh',1,'Nguy???n','0908822128',1),(6,'TP.HCM','2000-09-30','B1009','V??n Linh',1,'Ho??ng','0933633612',1),(7,'H?? N???i','2002-03-20','A2001','V?? Minh',1,'Tr???n','0953622727',1),(8,'H?? N???i','1999-04-27','A2011','M??? Linh',2,'Nguy???n','0975375637',1),(9,'H???i Ph??ng','1997-05-14','A2010','M???u Huy',1,'Tr???n','0963563276',1),(10,'Qu??ng Ninh','1999-08-22','A2012','Minh T??i',1,'Nguy???n','0973268678',1),(11,'Ninh B??nh','1998-02-11','A2004','Th??? Hoa',2,'Ph???m','0912587572',1),(12,'Long An','2001-01-03','A2006','Ng???c Li??n',2,'Tr???n','0915856756',1),(13,'B??nh D????ng','1999-01-02','A2007','H??ng Khang',1,'L??','0915615776',1),(14,'B???c Li??u','2002-04-22','A2008','Th??? Di???u',2,'Tr???n','0965737357',1),(15,'B???n Tre','2000-06-23','A2009','Ng???c Di???u',2,'Ph???m','0983567563',1),(16,'B???n Tre','1999-01-23','A20010','Anh T??i',1,'Nguy???n','0987586356',1),(17,'B???c Li??u','1999-05-13',NULL,'Th??? Ng???c',2,'Nguy???n','0935873275',1),(18,'???? N???ng','2002-03-12',NULL,'Minh ?????t',1,'Tr???n','0964747123',1),(19,'???? N???ng','2000-03-12','A2013','V??n Kh???i',1,'Tr???n','0976575575',1),(20,'Ninh Thu???n','2001-05-12',NULL,'Ng???c Nh??',2,'Tr???n','0946347277',1),(21,'B??nh Thu???n','2002-07-12',NULL,'Minh H??ng',1,'Nguy???n','0964646626',1),(22,'Ti???n Giang','2003-03-12',NULL,'Th??? L???',2,'Nguy???n','0968636787',1),(23,'G?? C??ng','2002-03-12',NULL,'Th??i M???',2,'Ph???m','0946636439',1),(24,'?????ng Th??p','2003-09-12',NULL,'Th??? Nh??',2,'L???i','0936474737',1),(25,'G?? C??ng','2002-12-12',NULL,'Nh???t Long',1,'T??ng','0937747575',1),(26,'Long An','2002-03-12',NULL,'Ng???c Tr??n',2,'Nguy???n','0967684646',1),(27,'C?? Mau','2002-03-22',NULL,'Th??? Ng???c',2,'Ph???m','0968666372',1),(28,'C?? Mau','2002-04-11',NULL,'V??n H??ng',1,'V??','0915325758',1),(29,'B???c Li??u','2002-03-12',NULL,'V??n H??ng',1,'Nguy???n','0932623686',1),(30,'B???c Li??u','2002-03-12',NULL,'Minh Duy',1,'Tr???n','0915235575',1),(31,'S??c Tr??ng','2002-04-12',NULL,'Ng???c Di???p',2,'L??','0934634637',1),(32,'Ninh Thu???n','2001-03-12',NULL,'Th??? Nh??',2,'L??','0946347437',1),(33,'H???i Ph??ng','2002-03-12',NULL,'V??n Ki???t',1,'H??','0946363678',1),(34,'H???i Ph??ng','2002-12-22',NULL,'M???nh Nam',1,'Nguy???n','0924373475',1),(35,'Qu??ng Ng??i','2002-11-10',NULL,'M??? L???',2,'Tr???n','0934717183',1),(36,'Qu??ng Ng??i','2002-10-12',NULL,'Minh H??ng',1,'L??','0917437438',1),(37,'H?? Giang','2002-03-17',NULL,'Ng???c Nh??',2,'L??','0915873562',1),(38,'Ki??n Giang','2002-03-14',NULL,'V??n Huy',1,'L??','0912581756',1),(39,'T??y Ninh','2001-07-22',NULL,'Ng???c Anh',2,'V??','0923871623',1),(40,'T??y Ninh','2001-09-11',NULL,'Ng???c ??nh',2,'V??','0923728673',1),(41,'?????ng Nai','2003-03-12','A2014','Minh Anh',1,'Tr???n','0923572836',1),(42,'?????ng Nai','2004-08-12','A2016','Ng???c Nga',2,'H??','0912517865',1),(43,'B??nh Thu???n','2002-05-12','A2015','Minh Long',1,'L??','0957238572',1),(44,'B??nh Thu???n','2004-03-12','A2017','Minh Ng???c',2,'Nguy???n','0935782367',1),(45,'Kh??nh H??a','2001-09-12','A2018','Minh Anh',1,'Ph???m','0935823766',1),(46,'H?? N???i','2003-11-12',NULL,'Minh H??ng',1,'Ph???m','0923628767',2),(47,'TPHCM','1999-08-09',NULL,'H???i H??ng',1,'Ph???m','0932687283',2),(48,'TPHCM','1999-12-11','A2025','V??n H???i',1,'L???i','0923273872',1),(49,'Nha Trang','2020-01-01','A2022','Tu???n D??ng',1,'Nguy???n','0908546224',1);
/*!40000 ALTER TABLE `examine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `normalized_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_unique` (`name`),
  UNIQUE KEY `role_normalized_name_unique` (`normalized_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','Qu???n tr??? vi??n'),(2,'ROLE_OFFICER','Nh??n vi??n v??n ph??ng'),(3,'ROLE_TEACHER','Gi??o vi??n');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `first_name` varchar(255) NOT NULL,
  `gender` int(11) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_unique` (`email`),
  UNIQUE KEY `user_phone_unique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'TP.HCM','1986-03-22','ngocha123@gmail.com',1,'Ng???c H??',1,'Ph???m','$2a$10$YCW10ls/rakZnb806GplDO0jUb4708AcWoyq/unUIENjx/MOOyw5q','0905412231'),(3,'TP.HCM','1980-08-05','tuandung123@gmail.com',1,'Tu???n D??ng',1,'Nguy???n','$2a$10$sZrZnaQjipAACEPk4cz23uWozXr9.7pvHwkfaukxbCbnqcqCEi62O','0905632511'),(4,'TP.HCM','1976-02-15','admin@gmail.com',1,'Ren',1,'Lucifer','$2a$10$olz3wulouTRHbATTSBf4IeYiPzUjnL5QERW6UIaSHe4LQqu/DKGKK','0905632123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (4,1),(2,3),(3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-21 22:29:13
