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
  PRIMARY KEY (`id`),
  KEY `FK2lntfak7rdl0c30rtdhwfalaj` (`exam_room_id`),
  KEY `FKjpsaveugt9xnhphp7x0xj96yr` (`examine_id`),
  CONSTRAINT `FK2lntfak7rdl0c30rtdhwfalaj` FOREIGN KEY (`exam_room_id`) REFERENCES `exam_room` (`id`),
  CONSTRAINT `FKjpsaveugt9xnhphp7x0xj96yr` FOREIGN KEY (`examine_id`) REFERENCES `examine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_course`
--

LOCK TABLES `exam_course` WRITE;
/*!40000 ALTER TABLE `exam_course` DISABLE KEYS */;
INSERT INTO `exam_course` VALUES (1,9,'A2K092021',2021,1),(2,9,'B1K092021',2021,2),(3,10,'A2K102021',2021,1),(7,9,'B1K112021',2021,2);
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
INSERT INTO `exam_course_examine` VALUES (2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_room`
--

LOCK TABLES `exam_room` WRITE;
/*!40000 ALTER TABLE `exam_room` DISABLE KEYS */;
INSERT INTO `exam_room` VALUES (2,'2021-11-15 07:30:00','A2P01',0,1,3,2),(3,'2021-11-15 09:30:00','A2P02',0,1,2,3),(6,'2021-11-15 09:30:00','B1P01',0,2,2,3),(10,'2021-10-15 09:30:00','B1P02',0,2,2,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examine`
--

LOCK TABLES `examine` WRITE;
/*!40000 ALTER TABLE `examine` DISABLE KEYS */;
INSERT INTO `examine` VALUES (1,'Nha Trang','2001-06-14',NULL,'Quốc Tuấn',1,'Trần','0776521474',1);
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
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','Quản trị viên'),(2,'ROLE_OFFICER','Nhân viên văn phòng'),(3,'ROLE_TEACHER','Giáo viên');
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
INSERT INTO `user` VALUES (2,'TP.HCM','1986-03-22','ngocha123@gmail.com',1,'Ngọc Hà',1,'Phạm','$2a$10$YCW10ls/rakZnb806GplDO0jUb4708AcWoyq/unUIENjx/MOOyw5q','0905412231'),(3,'TP.HCM','1980-08-05','tuandung123@gmail.com',1,'Tuấn Dũng',1,'Nguyễn','$2a$10$sZrZnaQjipAACEPk4cz23uWozXr9.7pvHwkfaukxbCbnqcqCEi62O','0905632511'),(4,'TP.HCM','1976-02-15','admin@gmail.com',1,'Ren',1,'Lucifer','$2a$10$olz3wulouTRHbATTSBf4IeYiPzUjnL5QERW6UIaSHe4LQqu/DKGKK','0905632123');
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

-- Dump completed on 2021-09-16 23:33:39
