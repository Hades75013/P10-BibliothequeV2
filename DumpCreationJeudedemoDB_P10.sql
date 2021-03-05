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
INSERT INTO `exemplaire` VALUES (37,1,_binary ''),(38,1,_binary ''),(39,2,_binary ''),(40,2,_binary ''),(41,2,_binary ''),(42,2,_binary ''),(43,2,_binary ''),(44,2,_binary ''),(45,3,_binary ''),(46,3,_binary ''),(47,3,_binary ''),(48,3,_binary ''),(49,4,_binary ''),(50,4,_binary ''),(51,5,_binary ''),(52,5,_binary ''),(53,6,_binary ''),(54,6,_binary ''),(55,6,_binary ''),(56,6,_binary ''),(57,6,_binary '');
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
INSERT INTO `hibernate_sequence` VALUES (514),(1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail`
--

DROP TABLE IF EXISTS `mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `date_envoi` datetime DEFAULT NULL,
  `id_pret_rendu` int(11) DEFAULT NULL,
  `id_reservation` int(11) DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `id_pret` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail`
--

LOCK TABLES `mail` WRITE;
/*!40000 ALTER TABLE `mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail` ENABLE KEYS */;
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
  `nombre_exemplaires_total` int(11) DEFAULT NULL,
  `date_retour_la_plus_proche` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ouvrage`
--

LOCK TABLES `ouvrage` WRITE;
/*!40000 ALTER TABLE `ouvrage` DISABLE KEYS */;
INSERT INTO `ouvrage` VALUES (1,'2015','Stan Lee','SF-15','Sciences-Fiction','83',_binary '','Les Avengers',2,2,NULL),(2,'1857','Baudelaire','Poe-07','Poesie','416',_binary '','Les Fleurs du mal',6,6,NULL),(3,'1942','Camus','Fr-24','Litterature francaise','176',_binary '','L_Etranger',4,4,NULL),(4,'1862','Hugo','Fr-17','Litterature francaise','1664',_binary '','Les Miserables',2,2,NULL),(5,'1943','Saint-Exupery','Fr-05','Litterature francaise','104',_binary '','Le petit prince',2,2,NULL),(6,'1857','Flaubert','Fr-13','Litterature francaise','479',_binary '','Madame BOVARY',5,5,NULL);
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
/*!40000 ALTER TABLE `pret` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_liste_attente`
--

DROP TABLE IF EXISTS `reservation_liste_attente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_liste_attente` (
  `id` int(11) NOT NULL,
  `date_demande` datetime DEFAULT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `ouvrage_id` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `pret_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8bcoa372cocq2y1w26akynjhh` (`ouvrage_id`),
  KEY `FK7w6s3smphfxsbsgmseuo04gn0` (`pret_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_liste_attente`
--

LOCK TABLES `reservation_liste_attente` WRITE;
/*!40000 ALTER TABLE `reservation_liste_attente` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_liste_attente` ENABLE KEYS */;
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
INSERT INTO `role` VALUES (79,'USER'),(80,'ADMIN'),(100,'USER'),(101,'USER'),(102,'USER'),(121,'USER'),(148,'USER'),(489,'USER'),(500,'USER'),(501,'USER'),(509,'USER'),(513,'USER');
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
INSERT INTO `utilisateur` VALUES (79,'17, quartier de la peupleraie 91350 GRIGNY','p7bibliou1@gmail.com','$2a$10$i0WiFH6n8jiKelADzaKqEOlH/myrp6k9rrpwfXOB0Fn9I5B0CznQW','SAMBA','Padoue'),(80,'21bis Rue Des Rossays 91600 SAVIGNY-SUR-ORGE','p7biblioadm@gmail.com','$2a$10$mtAX7bs2YXTqehXi2GKyQOrOU6cMpihhWTP.Wttcga29QE15dvOuK','KESSI','Sif'),(100,'5, allée des sardines 77120 ESBLY','p7bibliou2@gmail.com','$2a$10$9BnwCxcGzAA6ma23SHhDLuv4ItNntAgDDybBaXT64c0cPfaeHpa.u','GOUSSOT','Alexandre'),(102,'3,avenue de l\'Europe 77600 BUSSY-ST-GEORGES','p7bibliou3@gmail.com','$2a$10$p0e7jqBb4X4d2U6lVPVOsuR/A7kXBQ20j0.U0q3.jtCEEAIlgeCJe','SALESSES','Antoine'),(148,'10, rue des Acacias 44000 NANTES','p7bibliou4@gmail.com','$2a$10$G3TviNZOT.DWHpIhcQeP9uZaZ4PgP2cPBxziZjwfug22fBHiDAUq2','JERBI','Aimen');
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

-- Dump completed on 2021-02-24 11:57:50
