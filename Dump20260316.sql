-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: attendance_management
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  `reg_no` varchar(255) NOT NULL,
  PRIMARY KEY (`class_id`,`reg_no`),
  KEY `FK90pc2hkk33hyi5wkffk7f8kri` (`reg_no`),
  CONSTRAINT `FK90pc2hkk33hyi5wkffk7f8kri` FOREIGN KEY (`reg_no`) REFERENCES `student` (`reg_no`),
  CONSTRAINT `FKr4vfbaogg4oinxb1m9xx816ud` FOREIGN KEY (`class_id`) REFERENCES `schedule_class` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_code` varchar(50) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `duration` varchar(50) NOT NULL,
  PRIMARY KEY (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_subject`
--

DROP TABLE IF EXISTS `course_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_subject` (
  `course_code` varchar(50) NOT NULL,
  `subject_code` varchar(50) NOT NULL,
  KEY `FK1axh6lx6a91kmw0629i0ntepq` (`subject_code`),
  KEY `FKl4bm3s9xm5yi4ow5dpqybeu4v` (`course_code`),
  CONSTRAINT `FK1axh6lx6a91kmw0629i0ntepq` FOREIGN KEY (`subject_code`) REFERENCES `subject` (`subject_code`),
  CONSTRAINT `FKl4bm3s9xm5yi4ow5dpqybeu4v` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `batch` int NOT NULL,
  `status` varchar(50) NOT NULL,
  `course_code` varchar(255) NOT NULL,
  `reg_no` varchar(255) NOT NULL,
  PRIMARY KEY (`course_code`,`reg_no`),
  KEY `FK3fsb0jhnq103f26th5hls08gu` (`reg_no`),
  CONSTRAINT `FK3fsb0jhnq103f26th5hls08gu` FOREIGN KEY (`reg_no`) REFERENCES `student` (`reg_no`),
  CONSTRAINT `FK7siinf5y574avt15ecepqswm7` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `user_id` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tele_no` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKb314ssqbaj4smad2emv6257u1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schedule_class`
--

DROP TABLE IF EXISTS `schedule_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_class` (
  `class_id` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `end_time` varchar(50) NOT NULL,
  `start_time` varchar(50) NOT NULL,
  `course_course_code` varchar(50) DEFAULT NULL,
  `lecturer_user_id` varchar(50) DEFAULT NULL,
  `subject_subject_code` varchar(50) DEFAULT NULL,
  `batch` int NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FKo7164u6h6agsdfgvwtiqb3dqk` (`course_course_code`),
  KEY `FKkr8nwpxbgma33m6law7w7udr7` (`lecturer_user_id`),
  KEY `FKa2unyov1t9oxppk0ad1e2cuuh` (`subject_subject_code`),
  CONSTRAINT `FKa2unyov1t9oxppk0ad1e2cuuh` FOREIGN KEY (`subject_subject_code`) REFERENCES `subject` (`subject_code`),
  CONSTRAINT `FKkr8nwpxbgma33m6law7w7udr7` FOREIGN KEY (`lecturer_user_id`) REFERENCES `lecturer` (`user_id`),
  CONSTRAINT `FKo7164u6h6agsdfgvwtiqb3dqk` FOREIGN KEY (`course_course_code`) REFERENCES `course` (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `reg_no` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tele_no` varchar(50) NOT NULL,
  PRIMARY KEY (`reg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_code` varchar(50) NOT NULL,
  `subject_name` varchar(50) NOT NULL,
  PRIMARY KEY (`subject_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject_lecturer`
--

DROP TABLE IF EXISTS `subject_lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_lecturer` (
  `subject_code` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  KEY `FKmia5xri4bc028yk24o2lvkl91` (`user_id`),
  KEY `FK4u1rec7f9tj6wts5ahqxo65yw` (`subject_code`),
  CONSTRAINT `FK4u1rec7f9tj6wts5ahqxo65yw` FOREIGN KEY (`subject_code`) REFERENCES `subject` (`subject_code`),
  CONSTRAINT `FKmia5xri4bc028yk24o2lvkl91` FOREIGN KEY (`user_id`) REFERENCES `lecturer` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `username` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-16 23:16:23
