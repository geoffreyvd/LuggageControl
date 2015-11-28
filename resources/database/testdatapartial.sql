-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: luggagecontroldata
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `cellphone` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(11) DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `id_UNIQUE` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'henk','tank','lovetanks@aol.com','06638638638','1822-04-22','Androgenous','tanklane 1337','1337TANK'),(2,'mary','currie','uranium@hotmail.com','06238','1922-03-17','fEMALE','cesiumlane 13','84572OP');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_flight`
--

DROP TABLE IF EXISTS `customer_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_flight` (
  `flight_id` int(10) unsigned NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`flight_id`,`customer_id`),
  KEY `fk_client_flights_clients1_idx` (`customer_id`),
  CONSTRAINT `fk_client_flights_clients1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_flights_flights` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`flight_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_flight`
--

LOCK TABLES `customer_flight` WRITE;
/*!40000 ALTER TABLE `customer_flight` DISABLE KEYS */;
INSERT INTO `customer_flight` VALUES (2,1);
/*!40000 ALTER TABLE `customer_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_luggage`
--

DROP TABLE IF EXISTS `customer_luggage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_luggage` (
  `customer_id` int(10) unsigned NOT NULL,
  `luggage_id` int(10) unsigned NOT NULL,
  KEY `fk_client_luggage_clients1_idx` (`customer_id`),
  KEY `fk_client_luggage_luggage1_idx` (`luggage_id`),
  CONSTRAINT `fk_client_luggage_clients1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_luggage_luggage1` FOREIGN KEY (`luggage_id`) REFERENCES `luggage` (`luggage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_luggage`
--

LOCK TABLES `customer_luggage` WRITE;
/*!40000 ALTER TABLE `customer_luggage` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_luggage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `flight_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `origin` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `departure` datetime DEFAULT NULL,
  `arrival` datetime DEFAULT NULL,
  PRIMARY KEY (`flight_id`),
  UNIQUE KEY `id_UNIQUE` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,'New york','Las Vegas','1700-01-01 00:00:00','1700-01-01 12:00:00'),(2,'Las Vegas','New york','1700-01-02 00:00:00','1700-01-02 12:00:00');
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luggage`
--

DROP TABLE IF EXISTS `luggage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luggage` (
  `luggage_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `size` varchar(6) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  `image` blob,
  PRIMARY KEY (`luggage_id`),
  UNIQUE KEY `id_UNIQUE` (`luggage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luggage`
--

LOCK TABLES `luggage` WRITE;
/*!40000 ALTER TABLE `luggage` DISABLE KEYS */;
INSERT INTO `luggage` VALUES (1,'Las Vegas','pink',1337,'Large','Dildo, Dildo, Amphetamine 2 gram, Xanax 5 gram, Arkasia ethereality cd ','Found',NULL);
/*!40000 ALTER TABLE `luggage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luggage_flight`
--

DROP TABLE IF EXISTS `luggage_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luggage_flight` (
  `flight_id` int(10) unsigned NOT NULL,
  `luggage_id` int(10) unsigned NOT NULL,
  KEY `fk_luggage_flights_flights1_idx` (`flight_id`),
  KEY `fk_luggage_flights_luggage1_idx` (`luggage_id`),
  CONSTRAINT `fk_luggage_flights_flights1` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`flight_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_luggage_flights_luggage1` FOREIGN KEY (`luggage_id`) REFERENCES `luggage` (`luggage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luggage_flight`
--

LOCK TABLES `luggage_flight` WRITE;
/*!40000 ALTER TABLE `luggage_flight` DISABLE KEYS */;
/*!40000 ALTER TABLE `luggage_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luggage_lost_found`
--

DROP TABLE IF EXISTS `luggage_lost_found`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luggage_lost_found` (
  `luggage_lost_id` int(10) unsigned NOT NULL,
  `luggage_found_id` int(10) unsigned NOT NULL,
  `status` varchar(25) DEFAULT NULL,
  `luggage_lost_foundcol` varchar(45) DEFAULT NULL,
  KEY `fk_table1_luggage1_idx` (`luggage_lost_id`),
  KEY `fk_table1_luggage2_idx` (`luggage_found_id`),
  CONSTRAINT `fk_table1_luggage1` FOREIGN KEY (`luggage_lost_id`) REFERENCES `luggage` (`luggage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_luggage2` FOREIGN KEY (`luggage_found_id`) REFERENCES `luggage` (`luggage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luggage_lost_found`
--

LOCK TABLES `luggage_lost_found` WRITE;
/*!40000 ALTER TABLE `luggage_lost_found` DISABLE KEYS */;
/*!40000 ALTER TABLE `luggage_lost_found` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(16) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `cellphone` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(11) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `image` blob,
  `permission` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'user1','verysecure',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(8,'user2','verysecure',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(9,'user3','verysecure',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3),(10,'user0','verysecure',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
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

-- Dump completed on 2015-11-28 13:14:12
