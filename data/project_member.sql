-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 192.168.0.34    Database: project
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_email` varchar(50) NOT NULL,
  `pwd` varchar(500) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `member_regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('1234','$2a$10$MGL1TWoc/i/Qcke1iLpeoutaXaFTHKkeBy5hlcleCwm1RzEjZEcGG','테스트','010-1111-1111',NULL,'2024-05-07 10:07:34'),('dlgyqls@naver.com','$2a$10$e7G3Tx.hgBZpsFYc7ZYyqOJRPBPVfRuEGmS8jH5hRgBBZPSD3KA1i','수정한이름','010-7219-3750',NULL,'2024-05-09 18:08:50'),('dlgyqls11@nate.com',NULL,'이효빈','01025521653',NULL,'2024-05-13 18:24:48'),('dlgyqls11@naver.com','$2a$10$MvbCd.YQUJ3hEuU7CBw3geJLZtYPk3zYaUmrjDLllazVthKRcAa/a','이효빈','01025521653',NULL,'2024-05-24 13:02:52'),('kjhun2727@gmail.com','$2a$10$uwR68LX1AxKMh9p0qggK/.A5w3PJf7radxtagM6KpJUcMDINZ7y12','김정훈','01000000000',NULL,'2024-05-09 18:03:49'),('test12','$2a$10$gQWCv/9qcOLDeHGboy.nmu78oVU3VF8QMT6l3KQhp6UrwJreuplb6','임재현','010-1111-1111',NULL,'2024-05-03 16:55:49'),('test1234','1234','임재현','010-2478-3348',NULL,'2024-05-02 14:17:05'),('ubeoppu@gmail.com','$2a$10$F7HTixvCfnwqJqSIKUVED.bXpjCLIYUDlFr6TyS7DRHFlO7M33iHm','재현이','010-2478-3348','ubeoppu@gmail.com-boardget.jpg','2024-05-10 15:58:02'),('wer','$2a$10$22BNqLoH8Re1uEbnYuN3C.JCnUzzT3xGsK8UxmxVhCqMH/vb6Exme','신발','000-0000-0000',NULL,'2024-05-03 13:01:39'),('wowlswoguswo@naver.com',NULL,'임재현','010-2478-3348',NULL,'2024-05-13 17:10:13');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-24 19:12:00
