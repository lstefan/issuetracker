CREATE DATABASE  IF NOT EXISTS `issuetracker` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `issuetracker`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: issuetracker
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `PRIORITY` varchar(255) DEFAULT NULL,
  `RESOLUTION` varchar(255) DEFAULT NULL,
  `SEVERITY` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `ASSIGNED` bigint(20) DEFAULT NULL,
  `CREATED_BY` bigint(20) DEFAULT NULL,
  `FIX_VERSION_ID` bigint(20) DEFAULT NULL,
  `MODIFIED_BY` bigint(20) DEFAULT NULL,
  `OPEN_VERSION_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ISSUE_ASSIGNED_ID` (`ASSIGNED`),
  KEY `FK_ISSUE_CREATED_BY_ID` (`CREATED_BY`),
  KEY `FK_ISSUE_FIXED_VERSION_ID` (`FIX_VERSION_ID`),
  KEY `FK_ISSUE_MODIFIED_BY_ID` (`MODIFIED_BY`),
  KEY `FK_ISSUE_OPEN_VERSION_ID` (`OPEN_VERSION_ID`),
  KEY `FK_ISSUE_PROJECT_ID` (`PROJECT_ID`),
  CONSTRAINT `FK_ISSUE_ASSIGNED_ID` FOREIGN KEY (`ASSIGNED`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ISSUE_CREATED_BY_ID` FOREIGN KEY (`CREATED_BY`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ISSUE_FIXED_VERSION_ID` FOREIGN KEY (`FIX_VERSION_ID`) REFERENCES `version` (`id`),
  CONSTRAINT `FK_ISSUE_MODIFIED_BY_ID` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ISSUE_OPEN_VERSION_ID` FOREIGN KEY (`OPEN_VERSION_ID`) REFERENCES `version` (`id`),
  CONSTRAINT `FK_ISSUE_PROJECT_ID` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'BUG','2015-05-28 23:57:58','Issue1','2015-06-29 00:38:00','MEDIUM','FIXED','MAJOR','CLOSED','Issue1',1,1,1,1,1,1),(2,'BUG','2015-06-28 23:58:26','Issue2','2015-06-28 23:58:26','MEDIUM',NULL,'MAJOR','OPEN','Issue2',1,1,NULL,1,1,1),(3,'BUG','2015-05-29 00:00:55','Descr','2015-06-29 00:01:25','LOW',NULL,'CRITICAL','OPEN','Issue3',1,2,NULL,2,2,2),(4,'IMPROVEMENT','2015-06-29 00:01:15','Descr','2015-06-29 00:38:28','MEDIUM','FIXED','MINOR','CLOSED','Issue4',1,2,4,1,4,4),(5,'BUG','2015-05-29 00:02:18','Descr','2015-06-29 00:39:04','LOW',NULL,'MINOR','IN_PROGRESS','Issue5',1,3,NULL,1,3,3),(6,'BUG','2015-06-29 00:03:33','desc','2015-06-29 00:17:48','MEDIUM',NULL,'MODERATE','OPEN','Issue7',1,4,NULL,5,5,5),(7,'IMPROVEMENT','2015-05-29 00:04:32','desc','2015-06-29 00:04:32','MEDIUM',NULL,'MAJOR','OPEN','Issue6',1,5,NULL,5,4,4),(8,'BUG','2015-06-29 00:45:21','','2015-06-29 00:45:21','HIGH',NULL,'CRITICAL','OPEN','issue10',1,1,NULL,1,12,12),(9,'BUG','2015-06-29 01:23:12','The logout button is missing from the page.','2015-06-29 01:23:32','HIGH','FIXED','CRITICAL','CLOSED','Logout button is missing',1,1,2,1,2,2),(10,'BUG','2015-07-05 15:26:05','','2015-07-05 15:26:05','HIGH',NULL,'MAJOR','OPEN','issueproj3',1,12,NULL,12,3,3),(11,'BUG','2015-07-05 15:34:50','issueproj10','2015-07-05 15:34:50','LOW',NULL,'MINOR','OPEN','issueproj10',5,12,NULL,12,9,9),(13,'IMPROVEMENT','2015-07-08 12:43:40','efedgedh','2015-07-08 12:43:40','MEDIUM',NULL,'MAJOR','OPEN','issuel',1,1,NULL,1,17,16);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_history`
--

DROP TABLE IF EXISTS `issue_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISSUE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ISSUE_ISSUE_ID` (`ISSUE_ID`),
  CONSTRAINT `FK_ISSUE_ISSUE_ID` FOREIGN KEY (`ISSUE_ID`) REFERENCES `issue` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_history`
--

LOCK TABLES `issue_history` WRITE;
/*!40000 ALTER TABLE `issue_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_issue_history`
--

DROP TABLE IF EXISTS `issue_issue_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_issue_history` (
  `ISSUE_id` bigint(20) NOT NULL,
  `issueHistory_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_13y4c52avhh6ombl7dgf2e1kv` (`issueHistory_id`),
  KEY `FK_cb20nxw9sch8hi1ckkqj6i18p` (`ISSUE_id`),
  CONSTRAINT `FK_13y4c52avhh6ombl7dgf2e1kv` FOREIGN KEY (`issueHistory_id`) REFERENCES `issue_history` (`id`),
  CONSTRAINT `FK_cb20nxw9sch8hi1ckkqj6i18p` FOREIGN KEY (`ISSUE_id`) REFERENCES `issue` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_issue_history`
--

LOCK TABLES `issue_issue_history` WRITE;
/*!40000 ALTER TABLE `issue_issue_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_issue_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `PROJECT_NAME` varchar(45) DEFAULT NULL,
  `RELEASE_NOTES` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PROJECT_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_PROJECT_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'2015-06-28 23:56:26','Project1','https://github.com/lstefan/project1','2015-06-28 23:56:26','Project1','',1),(2,'2015-06-28 23:56:49','Project2','https://github.com/lstefan/project2','2015-06-28 23:56:49','Project2','',1),(3,'2015-06-28 23:57:04','Project3','https://github.com/lstefan/project3','2015-06-28 23:57:04','Project3','',1),(4,'2015-06-28 23:57:25','Project4','https://github.com/lstefan/project4','2015-06-28 23:57:25','Project4','Notes',1),(5,'2015-06-28 23:57:44','Test','https://github.com/lstefan/project5','2015-06-28 23:57:44','Project5','',1),(6,'2015-06-29 00:05:25','proj6','https://github.com/lstefan/project6','2015-06-29 00:05:25','Project6','',5),(7,'2015-06-29 00:05:58','test','https://github.com/lstefan/project7','2015-06-29 00:05:58','Project7','test',5),(8,'2015-06-29 00:06:16','test','https://github.com/lstefan/project8','2015-06-29 00:06:16','Project8','notes',5),(9,'2015-06-29 00:06:52','test','https://github.com/lstefan/project10','2015-06-29 00:06:52','Project10','test',5),(10,'2015-06-29 00:07:17','desc','https://github.com/lstefan/project11','2015-05-29 00:07:17','Project11','test',5),(11,'2015-06-29 00:41:03','desc','www','2015-06-29 00:41:03','Project12','notes',1),(12,'2015-06-29 00:41:21','desc','','2015-06-29 00:41:21','Project13','',1),(13,'2015-06-29 00:41:36','','','2015-06-29 00:41:36','Project14','',1),(14,'2015-07-05 01:28:26','','','2015-07-05 01:28:26','Proj_test','',1),(16,'2015-07-08 12:42:07','fwsfw','www','2015-07-08 12:42:48','licenta','fdgd',1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_users`
--

DROP TABLE IF EXISTS `project_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_users` (
  `PROJECT_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`PROJECT_ID`,`USER_ID`),
  KEY `FK_lyt9w7lvufh8qvr3i98bhecqw` (`USER_ID`),
  CONSTRAINT `FK_lyt9w7lvufh8qvr3i98bhecqw` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ryqmi5iqfndxb0llcpv265m53` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_users`
--

LOCK TABLES `project_users` WRITE;
/*!40000 ALTER TABLE `project_users` DISABLE KEYS */;
INSERT INTO `project_users` VALUES (2,1);
/*!40000 ALTER TABLE `project_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(45) DEFAULT NULL,
  `FIRSTNAME` varchar(45) DEFAULT NULL,
  `LASTNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  `sign_in_provider` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ejfk3g58oxsgbb4ju3u4fhivk` (`EMAIL`),
  UNIQUE KEY `UK_glhidjw15gnif0a8lr0yxbcrg` (`PASSWORD`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'livia.andreea.333@gmail.com','Livia','Ştefan',NULL,'USER','FACEBOOK'),(2,'test1@gmail.com','test1','test1','$2a$10$EeSDUONVql/wVt5R0svRNu8i/nCWeLGMA.sh6a.KAomwCAQuMhqua','USER',NULL),(3,'test2@gmail.com','test2','test2','$2a$10$r/41alG9MpKAFMNkng.48e0RBgIhKI5dAVp9VfQYya9Tj/BWWeDGu','USER',NULL),(4,'test3@gmail.com','test3','test3','$2a$10$XeBxy.pXNtw/lds2cmnL/OkloG5JPfS2A6X5KBCryCiDn9CvIUKt2','USER',NULL),(5,'test4@gmail.com','test4','test4','$2a$10$HjeQusz0cq2TbDSE5ByAhe29IgnddzjCPEfGldMs1NoE7RTK/kU9W','USER',NULL),(6,'test5@gmail.com','test5','test5','$2a$10$3xiIj41kuX.udHSRj5f1BO4gCqBulQV.D6.W3onsl6LspNTvQQaV6','USER',NULL),(7,'livia@gmail.com','aaa','bbb','$2a$10$Wv61UmT5DuH8fjMzWZLqw.71GdoGA0WUjjAGf3q1SbIY2RaolfjJS','USER',NULL),(8,'bogdan@gmail.com','Bogdan','Bogdan','$2a$10$twv79FBcC5WVAteoaRJFb.rZFmmM26WNCn73l6pgBU0m1YqO9fyP6','USER',NULL),(9,'livia.andreea@gmail.com','livia','livia','$2a$10$8do74pUYx2ccqhVTq1gE3.oYtqnp2VGVLUs6M4EY015L0.L327L8q','USER',NULL),(10,'livia2@gmail.com','Livia','Livia','$2a$10$90koI14gGQ94/n/gtws2zO7ncFVc6U44xs3MKd.Yi7QkBL0gH0cEm','USER',NULL),(11,'livia3@gmail.com','livia','livia','$2a$10$xy0nuUjKscnTbR8gkDUcHe.5Ak6BfAF4jaejVm967mHFXoT5X68OG','USER',NULL),(12,'bogdan.test@gmail.com','Bogdan','Test','$2a$10$vQmbb57gA2ciEC8yp.b4LecPu/v7lXv1BpRutK5weaFbkJWDXSznC','USER',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
INSERT INTO `userconnection` VALUES ('livia.andreea.333@gmail.com','facebook','949557755077039',1,NULL,'http://facebook.com/profile.php?id=949557755077039','http://graph.facebook.com/v1.0/949557755077039/picture','CAAUTjcyYaHYBADW2jLvLyqHB5Oi3OtKm706dIdnFsjoJi0DINrBvCbZAMGzLVKVc5fB0DtK89nEQ8UbxCWi0uIg1AidX61rXui0RJhwig0zmSyU2ZC9PkL8oBoNWBb8YLCHxd11qTGJ9jEiGKqveB6XQwSR1TrY8T01SzksD7CBGoPnJyjKW0tlkIbhwsZD',NULL,NULL,1450600265653);
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VERSION_PROJECT_ID` (`PROJECT_ID`),
  CONSTRAINT `FK_VERSION_PROJECT_ID` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (1,'V0.1',1),(2,'v1.0',2),(3,'V0.1',3),(4,'v1.1',4),(5,'v2.0',5),(6,'v2.1',6),(7,'v3.0',7),(8,'v1.0',8),(9,'V0.2',9),(10,'v4.0',10),(11,'V0.2',11),(12,'0.0.2',12),(13,'',13),(14,'0.0.2',14),(16,'V0.1',16),(17,'v0.2',16);
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-22  0:29:18
