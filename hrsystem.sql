-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: hrsystem
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `application_inf`
--

DROP TABLE IF EXISTS `application_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_inf` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `attend_id` int(11) NOT NULL,
  `app_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `app_result` tinyint(1) DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`app_id`),
  KEY `type_id` (`type_id`),
  KEY `attend_id` (`attend_id`),
  CONSTRAINT `application_inf_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `attend_type_inf` (`type_id`),
  CONSTRAINT `application_inf_ibfk_2` FOREIGN KEY (`attend_id`) REFERENCES `attend_inf` (`attend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_inf`
--

LOCK TABLES `application_inf` WRITE;
/*!40000 ALTER TABLE `application_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attend_inf`
--

DROP TABLE IF EXISTS `attend_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attend_inf` (
  `attend_id` int(11) NOT NULL AUTO_INCREMENT,
  `duty_day` varchar(50) COLLATE utf8_bin NOT NULL,
  `punch_time` datetime DEFAULT NULL,
  `is_come` tinyint(1) NOT NULL,
  `type_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  PRIMARY KEY (`attend_id`),
  KEY `type_id` (`type_id`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `attend_inf_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `attend_type_inf` (`type_id`),
  CONSTRAINT `attend_inf_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attend_inf`
--

LOCK TABLES `attend_inf` WRITE;
/*!40000 ALTER TABLE `attend_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `attend_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attend_type_inf`
--

DROP TABLE IF EXISTS `attend_type_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attend_type_inf` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `amerce_amount` double NOT NULL,
  `type_name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attend_type_inf`
--

LOCK TABLES `attend_type_inf` WRITE;
/*!40000 ALTER TABLE `attend_type_inf` DISABLE KEYS */;
INSERT INTO `attend_type_inf` VALUES (1,0,'姝ｅ父'),(2,-20,'浜嬪亣'),(3,-10,'鐥呭亣'),(4,-10,'杩熷埌'),(5,-10,'鏃╅€€'),(6,-30,'鏃峰伐'),(7,10,'鍑哄樊');
/*!40000 ALTER TABLE `attend_type_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkback_inf`
--

DROP TABLE IF EXISTS `checkback_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkback_inf` (
  `check_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL,
  `check_result` tinyint(1) NOT NULL,
  `check_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mgr_id` int(11) NOT NULL,
  PRIMARY KEY (`check_id`),
  KEY `app_id` (`app_id`),
  KEY `mgr_id` (`mgr_id`),
  CONSTRAINT `checkback_inf_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `application_inf` (`app_id`),
  CONSTRAINT `checkback_inf_ibfk_2` FOREIGN KEY (`mgr_id`) REFERENCES `employee_inf` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkback_inf`
--

LOCK TABLES `checkback_inf` WRITE;
/*!40000 ALTER TABLE `checkback_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkback_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_inf`
--

DROP TABLE IF EXISTS `employee_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_inf` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_type` int(11) DEFAULT NULL,
  `emp_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `emp_pass` varchar(50) COLLATE utf8_bin NOT NULL,
  `emp_salary` double NOT NULL,
  `mgr_id` int(11) DEFAULT NULL,
  `dept_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_name` (`emp_name`),
  KEY `mgr_id` (`mgr_id`),
  CONSTRAINT `employee_inf_ibfk_1` FOREIGN KEY (`mgr_id`) REFERENCES `employee_inf` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_inf`
--

LOCK TABLES `employee_inf` WRITE;
/*!40000 ALTER TABLE `employee_inf` DISABLE KEYS */;
INSERT INTO `employee_inf` VALUES (1,2,'oracle','oracle',5000,NULL,'DB閮?),(2,2,'weblogic','weblogic',6000,NULL,'Server閮?),(3,1,'mysql','mysql',3000,1,NULL),(4,1,'hsql','hsql',3200,1,NULL),(5,1,'tomcat','tomcat',2800,2,NULL),(6,1,'jetty','jetty',2560,2,NULL),(9,1,'q1','q1',8000,1,NULL),(10,1,'yg','510781',9000,1,NULL),(11,1,'llp','llp',123456,1,NULL);
/*!40000 ALTER TABLE `employee_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_inf`
--

DROP TABLE IF EXISTS `payment_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_inf` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_month` varchar(50) COLLATE utf8_bin NOT NULL,
  `pay_amount` double NOT NULL,
  `emp_id` int(11) NOT NULL,
  PRIMARY KEY (`pay_id`),
  KEY `emp_id` (`emp_id`),
  CONSTRAINT `payment_inf_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_inf`
--

LOCK TABLES `payment_inf` WRITE;
/*!40000 ALTER TABLE `payment_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_user`
--

DROP TABLE IF EXISTS `test_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鐢ㄦ埛ID',
  `email` varchar(255) NOT NULL COMMENT '鐢ㄦ埛閭',
  `password` varchar(255) NOT NULL COMMENT '鐢ㄦ埛瀵嗙爜',
  `username` varchar(255) NOT NULL COMMENT '鐢ㄦ埛鍚嶇О',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_user`
--

LOCK TABLES `test_user` WRITE;
/*!40000 ALTER TABLE `test_user` DISABLE KEYS */;
INSERT INTO `test_user` VALUES (1,'123455@qq.com','12345','test');
/*!40000 ALTER TABLE `test_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-27 20:19:17
