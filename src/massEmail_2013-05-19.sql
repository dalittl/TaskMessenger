# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.11)
# Database: massEmail
# Generation Time: 2013-05-20 03:36:44 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table candidates
# ------------------------------------------------------------

DROP TABLE IF EXISTS `candidates`;

CREATE TABLE `candidates` (
  `CANDIDATE_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CANDIDATE_FIRSTNAME` varchar(100) DEFAULT NULL,
  `CANDIDATE_LASTNAME` varchar(100) DEFAULT NULL,
  `CANDIDATE_EMAIL` varchar(100) DEFAULT NULL,
  `CANDIDATE_CREATED` date DEFAULT NULL,
  `CANDIDATE_EXPERIENCE` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`CANDIDATE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;

INSERT INTO `candidates` (`CANDIDATE_ID`, `CANDIDATE_FIRSTNAME`, `CANDIDATE_LASTNAME`, `CANDIDATE_EMAIL`, `CANDIDATE_CREATED`, `CANDIDATE_EXPERIENCE`)
VALUES
	(14,'Kunta','Little','kunta78@gmail.com','2013-05-19',NULL),
	(17,'Test','Me1','kunta78@gmail.com','2013-05-19',NULL),
	(18,'Test','Me2','kunta78@gmail.com','2013-05-19',NULL),
	(19,'Test','Me3','kunta78@gmail.com','2013-05-19',NULL);

/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table emailRepo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `emailRepo`;

CREATE TABLE `emailRepo` (
  `EMAILREPO_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `JOB_ID` decimal(11,0) unsigned NOT NULL,
  `CANDIDATE_ID` decimal(11,0) unsigned NOT NULL,
  `CREATE_DT` date DEFAULT NULL,
  PRIMARY KEY (`EMAILREPO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `emailRepo` WRITE;
/*!40000 ALTER TABLE `emailRepo` DISABLE KEYS */;

INSERT INTO `emailRepo` (`EMAILREPO_ID`, `JOB_ID`, `CANDIDATE_ID`, `CREATE_DT`)
VALUES
	(1,11,14,'2013-05-19'),
	(2,12,14,'2013-05-19'),
	(3,13,14,'2013-05-19'),
	(4,14,14,'2013-05-19');

/*!40000 ALTER TABLE `emailRepo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table jobs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
  `JOB_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `JOB_TITLE` varchar(100) DEFAULT NULL,
  `JOB_DESCRIPTION` varchar(4000) DEFAULT NULL,
  `JOB_DATE` date DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;

INSERT INTO `jobs` (`JOB_ID`, `JOB_TITLE`, `JOB_DESCRIPTION`, `JOB_DATE`)
VALUES
	(11,'Software Engineer Level 1','Software Engineer Level 1','2013-05-19'),
	(12,'Software Engineer Level 2','Software Engineer Level 2','2013-05-19'),
	(13,'Software Engineer Level 3','Software Engineer Level 3','2013-05-19'),
	(14,'Software Engineer Level 4','Software Engineer Level 4','2013-05-19');

/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
