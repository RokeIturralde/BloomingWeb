-- MySQL dump 10.13  Distrib 5.7.39, for Win64 (x86_64)
--
-- Host: localhost    Database: bloomingdb
-- ------------------------------------------------------
-- Server version	5.7.39-log

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creator_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d4oa4ua6ilfk70hqjdeou9s97` (`creator_login`),
  CONSTRAINT `FK_d4oa4ua6ilfk70hqjdeou9s97` FOREIGN KEY (`creator_login`) REFERENCES `user` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `uploadDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contents_albums`
--

DROP TABLE IF EXISTS `contents_albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contents_albums` (
  `album_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  KEY `FK_cu37olmsj3orqkayiywpjeuml` (`content_id`),
  KEY `FK_ia7ao2s6gqa7mfoqrgyg4dc3m` (`album_id`),
  CONSTRAINT `FK_cu37olmsj3orqkayiywpjeuml` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `FK_ia7ao2s6gqa7mfoqrgyg4dc3m` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contents_albums`
--

LOCK TABLES `contents_albums` WRITE;
/*!40000 ALTER TABLE `contents_albums` DISABLE KEYS */;
/*!40000 ALTER TABLE `contents_albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customimage`
--

DROP TABLE IF EXISTS `customimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customimage` (
  `bytes` tinyblob,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_frfh8xuajt93aa1jv1hcwafbk` FOREIGN KEY (`id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customimage`
--

LOCK TABLES `customimage` WRITE;
/*!40000 ALTER TABLE `customimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `customimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customtext`
--

DROP TABLE IF EXISTS `customtext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customtext` (
  `text` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_3hbfccthxrixblp9y3ysst8l3` FOREIGN KEY (`id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customtext`
--

LOCK TABLES `customtext` WRITE;
/*!40000 ALTER TABLE `customtext` DISABLE KEYS */;
/*!40000 ALTER TABLE `customtext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `memberEndingDate` date DEFAULT NULL,
  `memberStartingDate` date DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `plan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`login`),
  KEY `FK_e7eube1cedw89mq0ymjxyfgmj` (`plan_id`),
  CONSTRAINT `FK_6m3rhsd4t4ngr0y1a5gd83beq` FOREIGN KEY (`login`) REFERENCES `user` (`login`),
  CONSTRAINT `FK_e7eube1cedw89mq0ymjxyfgmj` FOREIGN KEY (`plan_id`) REFERENCES `membershipplan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('2023-01-18','2023-01-18','opticks',-1),('2023-01-19','2023-01-19','rokelius',-1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membershipplan`
--

DROP TABLE IF EXISTS `membershipplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membershipplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `albumLimit` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `shareable` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membershipplan`
--

LOCK TABLES `membershipplan` WRITE;
/*!40000 ALTER TABLE `membershipplan` DISABLE KEYS */;
INSERT INTO `membershipplan` VALUES (-1,5,'whatever','5','beggar',6.25,_binary '\0');
/*!40000 ALTER TABLE `membershipplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `lastPasswordChange` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('FBe9','nerea@gmail.com','Nerea Oceja',NULL,'abcd*1234','CLIENT','ENABLE'),('magicsalad','nicorod@gmail.com','Nicolás Rodriguez',NULL,'abcd*1234','CLIENT','ENABLE'),('opticks','danielbarrios2002@gmail.com','Daniel Barrios Abad','2023-01-18 13:51:54','abcd*1234','MEMBER','ENABLE'),('rokelius','roke@gmail.com','Roke Iturralde',NULL,'abcd*1234','MEMBER','ENABLE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sharedalbums`
--

DROP TABLE IF EXISTS `user_sharedalbums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sharedalbums` (
  `user_id` varchar(255) NOT NULL,
  `album_id` int(11) NOT NULL,
  KEY `FK_e5yinjvectpmiy8jxhlk0t9sv` (`album_id`),
  KEY `FK_6rs49nrsr8im4o0t7u2yf44ia` (`user_id`),
  CONSTRAINT `FK_6rs49nrsr8im4o0t7u2yf44ia` FOREIGN KEY (`user_id`) REFERENCES `user` (`login`),
  CONSTRAINT `FK_e5yinjvectpmiy8jxhlk0t9sv` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sharedalbums`
--

LOCK TABLES `user_sharedalbums` WRITE;
/*!40000 ALTER TABLE `user_sharedalbums` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_sharedalbums` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-19 14:27:35
