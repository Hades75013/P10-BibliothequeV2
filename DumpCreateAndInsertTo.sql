CREATE DATABASE  IF NOT EXISTS `p7-microservice` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `p7-microservice`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: p7-microservice
-- ------------------------------------------------------
-- Server version	5.7.29-log

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
-- Table structure for table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exemplaire` (
  `id` int(11) NOT NULL,
  `ouvrage_id` int(11) DEFAULT NULL,
  `disponible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKanyher7vd5o14qpmcgcfede10` (`ouvrage_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` VALUES (37,1,_binary '\0'),(38,1,_binary '\0'),(39,1,_binary '\0'),(40,5,_binary '\0'),(41,5,_binary '\0'),(42,5,_binary '\0'),(49,2,_binary '\0'),(50,2,_binary ''),(51,2,_binary ''),(52,2,_binary ''),(53,2,_binary ''),(54,2,_binary ''),(55,3,_binary ''),(56,3,_binary ''),(57,3,_binary ''),(58,3,_binary ''),(59,3,_binary ''),(60,3,_binary ''),(61,3,_binary ''),(62,3,_binary ''),(63,8,_binary '\0'),(64,8,_binary ''),(65,8,_binary ''),(66,8,_binary ''),(67,8,_binary ''),(68,4,_binary '\0'),(69,4,_binary ''),(70,4,_binary ''),(71,4,_binary ''),(72,4,_binary ''),(73,4,_binary ''),(74,4,_binary ''),(75,4,_binary ''),(76,4,_binary ''),(77,4,_binary '');
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (121),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ouvrage`
--

DROP TABLE IF EXISTS `ouvrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ouvrage` (
  `id` int(11) NOT NULL,
  `annee_edition` varchar(255) DEFAULT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `cote` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `nombre_pages` varchar(255) DEFAULT NULL,
  `statut` bit(1) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `nombre_exemplaires_dispos` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ouvrage`
--

LOCK TABLES `ouvrage` WRITE;
/*!40000 ALTER TABLE `ouvrage` DISABLE KEYS */;
INSERT INTO `ouvrage` VALUES (1,'2016','Stan Lee','SF-15','Sciences-Fiction','83',_binary '\0','Les Avengers',0),(2,'1857','Baudelaire','Poe-07','Poesie','416',_binary '','Les Fleurs du mal',4),(3,'1942','Camus','Fr-24','Litterature francaise','176',_binary '','L_Etranger',7),(4,'1862','Hugo','Fr-17','Litterature francaise','1664',_binary '','Les Miserables',9),(5,'1943','Saint-Exupery','Fr-05','Litterature francaise','104',_binary '\0','Le petit prince',0),(8,'1857','Flaubert','Fr-13','Litterature française','479',_binary '','Madame BOVARY',4);
/*!40000 ALTER TABLE `ouvrage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pret`
--

DROP TABLE IF EXISTS `pret`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pret` (
  `id` int(11) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `ouvrage_id` int(11) DEFAULT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `prolongeable` bit(1) DEFAULT NULL,
  `exemplaire_id` int(11) DEFAULT NULL,
  `date_reservation` datetime DEFAULT NULL,
  `date_retour` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtomu4b9nf3r5tlk8uo9y2exl` (`exemplaire_id`),
  KEY `FKryd1x5fme3awtoykchid0ri0y` (`ouvrage_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pret`
--

LOCK TABLES `pret` WRITE;
/*!40000 ALTER TABLE `pret` DISABLE KEYS */;
INSERT INTO `pret` VALUES (95,'2020-11-16 09:53:05','2020-11-18 09:53:05','DEPASSE',2,79,_binary '\0',49,'2020-11-16 09:50:47',NULL),(96,'2020-11-16 09:53:11','2020-12-16 09:53:11','TERMINE',3,79,_binary '\0',55,'2020-11-16 09:50:52','2020-11-17 10:58:28'),(98,'2020-11-17 10:52:42','2020-12-17 10:52:42','EN_COURS',5,79,_binary '',40,'2020-11-16 09:51:02',NULL),(99,'2020-11-17 10:52:50','2020-12-17 10:52:50','TERMINE',8,79,_binary '\0',63,'2020-11-16 09:51:08','2020-11-17 10:58:46'),(103,'2020-11-16 11:02:38','2021-01-16 11:02:38','PROLONGE',1,102,_binary '\0',38,'2020-11-16 10:59:57',NULL),(104,NULL,NULL,'EN_ATTENTE',2,102,_binary '\0',NULL,'2020-11-16 11:00:10',NULL),(105,'2020-10-01 09:27:44','2020-11-01 09:27:44','DEPASSE',8,102,_binary '',63,'2020-11-16 11:00:16',NULL),(107,'2020-12-07 11:10:26','2021-01-07 11:10:26','TERMINE',4,100,_binary '\0',69,'2020-11-16 11:01:25','2020-12-07 11:10:54'),(108,'2020-11-17 10:53:51','2020-12-17 10:53:51','EN_COURS',5,100,_binary '',41,'2020-11-16 11:01:33',NULL),(115,'2020-11-17 10:53:26','2020-12-17 10:53:26','TERMINE',4,102,_binary '\0',68,'2020-11-17 09:20:02','2020-11-17 10:59:06'),(117,'2020-11-17 09:32:59','2020-11-27 09:32:59','DEPASSE',1,100,_binary '\0',39,'2020-11-17 09:30:46',NULL),(120,'2020-11-30 13:24:19','2020-12-30 13:24:19','EN_COURS',1,79,_binary '',37,'2020-11-30 13:23:38',NULL),(119,'2020-11-19 11:01:58','2020-12-19 11:01:58','EN_COURS',5,102,_binary '',42,'2020-11-19 11:01:36',NULL);
/*!40000 ALTER TABLE `pret` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_utilisateur` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `INDEX_UTILISATEUR_ROLE` (`id_utilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (79,'USER'),(80,'ADMIN'),(100,'USER'),(101,'USER'),(102,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (79,'17, quartier de la peupleraie 91350 GRIGNY','p7bibliou1@gmail.com','$2a$10$i0WiFH6n8jiKelADzaKqEOlH/myrp6k9rrpwfXOB0Fn9I5B0CznQW','SAMBA','Padoue'),(80,'21bis Rue Des Rossays 91600 SAVIGNY-SUR-ORGE','p7biblioadm@gmail.com','$2a$10$mtAX7bs2YXTqehXi2GKyQOrOU6cMpihhWTP.Wttcga29QE15dvOuK','KESSI','Sif'),(100,'5, allée des sardines 77120 ESBLY','p7bibliou2@gmail.com','$2a$10$9BnwCxcGzAA6ma23SHhDLuv4ItNntAgDDybBaXT64c0cPfaeHpa.u','GOUSSOT','Alexandre'),(102,'3,avenue de l\'Europe 77600 BUSSY-ST-GEORGES','p7bibliou3@gmail.com','$2a$10$p0e7jqBb4X4d2U6lVPVOsuR/A7kXBQ20j0.U0q3.jtCEEAIlgeCJe','SALESSES','Antoine');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-10 14:09:53
