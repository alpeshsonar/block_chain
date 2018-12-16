-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: block_chain
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `consignment`
--

DROP TABLE IF EXISTS `consignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consignment` (
  `consignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `tag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`consignment_id`),
  KEY `FK_I_3_idx` (`item_id`),
  CONSTRAINT `FK_I_3` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consignment`
--

LOCK TABLES `consignment` WRITE;
/*!40000 ALTER TABLE `consignment` DISABLE KEYS */;
INSERT INTO `consignment` VALUES (1,1,'6ebeba5a-de51-4ca3-a064-db80aef4b50b'),(2,1,'51514ad7-2e90-4a7a-b400-8e27022e7874'),(3,1,'38cf4446-94f1-43fd-ab5d-b4bc550a44ae');
/*!40000 ALTER TABLE `consignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `characteristics` text,
  `features` text,
  `image_location` varchar(255) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FK_idx` (`org_id`),
  CONSTRAINT `FK` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Aishwarya (NCH-744)','<h5>Characteristics</h5><ul><table><tr><td><h6>Plant habit</h6></td><td><h6>Tall, erect, semi-open & wide spreading</h6></td></tr><tr><td><h6>Duration</h6></td><td><h6>Medium to late</h6></td></tr><tr><td><h6>Boll size and shape</h6></td><td><h6>Medium big round to oblong</h6></td></tr><tr><td><h6>Avg. Boll Weight (gm)</h6></td><td><h6>4.0 to 4.5</h6></td></tr><tr><td><h6>Staple length(mm)</h6></td><td><h6>28-30</h6></td></tr><tr><td><h6>Fibre Strength (g/tex)</h6></td><td><h6>21-22</h6></td></tr><tr><td><h6>Micronaire(ug/inch)</h6></td><td><h6>4.0-4.5</h6></td></tr><tr><td><h6>Ginning (%)</h6></td><td><h6>40-41</h6></td></tr></table></ul>','<h5>Special Feature</h5><ul><table><tr><td><h6>Tall spreading plant type</h6></td></tr><tr><td><h6>Good bearing and boll retention capacity</h6></td></tr><tr><td><h6>Highly tolerant to leaf reddening & sucking pestSuitable for irrigated and rainfed cultivation Suitable forCentral and South zones of India</h6></td></tr></table></ul>','aishwarya.PNG',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_request`
--

DROP TABLE IF EXISTS `item_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_request` (
  `request_id` int(10) NOT NULL AUTO_INCREMENT,
  `requested_by` int(11) DEFAULT NULL,
  `requested_to` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `requested_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT 'Pending',
  PRIMARY KEY (`request_id`),
  KEY `FK_1_idx` (`item_id`),
  KEY `FK_2_idx` (`requested_by`),
  KEY `Fk_3_idx` (`requested_to`),
  CONSTRAINT `FK_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_2` FOREIGN KEY (`requested_by`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_3` FOREIGN KEY (`requested_to`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_request`
--

LOCK TABLES `item_request` WRITE;
/*!40000 ALTER TABLE `item_request` DISABLE KEYS */;
INSERT INTO `item_request` VALUES (1,3,5,1,10,'2018-12-16 08:05:30','Approved'),(2,4,3,1,10,'2018-12-16 08:06:25','Approved'),(3,2,4,1,20,'2018-12-16 08:10:50','Approved'),(4,4,1,1,12,'2018-12-16 08:42:34','Approved'),(5,1,5,1,12,'2018-12-16 08:43:20','Approved'),(6,1,6,1,10,'2018-12-16 10:28:01','Approved'),(7,6,5,1,500,'2018-12-16 10:28:33','Approved'),(8,6,1,1,10,'2018-12-16 10:32:49','Approved');
/*!40000 ALTER TABLE `item_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `loc_id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Mumbai'),(2,'Delhi'),(3,'Kolkata'),(4,'Chennai'),(5,'Pune');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organisation` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organisation`
--

LOCK TABLES `organisation` WRITE;
/*!40000 ALTER TABLE `organisation` DISABLE KEYS */;
INSERT INTO `organisation` VALUES (1,'Niramal Seeds');
/*!40000 ALTER TABLE `organisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `consignment_id` int(11) DEFAULT NULL,
  `depart_date` timestamp NULL DEFAULT NULL,
  `arrival_date` timestamp NULL DEFAULT NULL,
  `depart_loc_id` int(11) DEFAULT NULL,
  `arrival_loc_id` int(11) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `requested_by` int(11) DEFAULT NULL,
  `requested_to` int(11) DEFAULT NULL,
  `previous_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`track_id`),
  KEY `FK_T_2_idx` (`depart_loc_id`),
  KEY `FK_T_3_idx` (`arrival_loc_id`),
  KEY `FK_T_4_idx` (`requested_by`),
  KEY `FK_T_5_idx` (`requested_to`),
  CONSTRAINT `FK_T_2` FOREIGN KEY (`depart_loc_id`) REFERENCES `locations` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_T_3` FOREIGN KEY (`arrival_loc_id`) REFERENCES `locations` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_T_4` FOREIGN KEY (`requested_by`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_T_5` FOREIGN KEY (`requested_to`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,1,'2018-12-15 18:30:00','2018-12-16 08:05:58',1,3,'d510f902989b5a564ff642a8cb39c5610ec8419f684e193b7c2998996f55bd1e',3,5,'0'),(2,1,'2018-12-15 18:30:00','2018-12-16 08:06:47',3,4,'62c9d9b9b95302c3cdfbec861fa35362bb218d67690432c1ebbe6dba5780b578',4,3,'d510f902989b5a564ff642a8cb39c5610ec8419f684e193b7c2998996f55bd1e'),(3,1,'2018-12-15 20:10:50','2018-12-16 08:19:14',4,2,'cda743482e80fd853945fe3957cc877a036331ea7da484f68e466fc7036924f7',2,4,'62c9d9b9b95302c3cdfbec861fa35362bb218d67690432c1ebbe6dba5780b578'),(4,2,'2018-12-15 20:43:20','2018-12-16 08:43:35',1,1,'0ccdceef1c9baad6104430d6f64fe75e06f4f51177c95f9d49442c285162310e',1,5,'0'),(5,2,'2018-12-15 20:42:34','2018-12-16 08:44:02',1,4,'93aae75831c6f3d1c7d5790cd6a58a6335441ddbd86e200bf157d9744d15a9f9',4,1,'0ccdceef1c9baad6104430d6f64fe75e06f4f51177c95f9d49442c285162310e'),(6,3,'2018-12-15 22:28:33','2018-12-16 10:28:44',1,2,'7ea6a81ea50586586b1c7ec005137b672d11714198accd31ad5907c9115446ab',6,5,'0'),(7,3,'2018-12-15 22:28:01','2018-12-16 10:29:51',2,1,'b44909bed230070e64a7b7ca64f76277282e5d19d95399a5ea92c81931ee22be',1,6,'7ea6a81ea50586586b1c7ec005137b672d11714198accd31ad5907c9115446ab'),(8,3,'2018-12-15 22:32:49','2018-12-16 10:33:15',1,2,'7ea6a81ea50586586b1c7ec005137b672d11714198accd31ad5907c9115446ab',6,1,'b44909bed230070e64a7b7ca64f76277282e5d19d95399a5ea92c81931ee22be');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Organisation'),(2,'Wholesaler'),(3,'Dealer');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `loc_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `FK_1_idx` (`loc_id`),
  KEY `FK_L_2_idx` (`type_id`),
  CONSTRAINT `FK_L_1` FOREIGN KEY (`loc_id`) REFERENCES `locations` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_L_2` FOREIGN KEY (`type_id`) REFERENCES `user_type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'abc','abc',1,2,'Abc Pqr'),(2,'pqr','pqr',2,3,'Stv Xyz'),(3,'stv','stv',3,2,'Awr Irf'),(4,'xyz','xyz',4,3,'Ert Our'),(5,'org','org',1,1,'Mnu Oje'),(6,'alpeshsonar','Alpesh',2,2,'Alpesh Sonar');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 16:05:51
