/*
SQLyog v10.2 
MySQL - 5.6.17 : Database - noteblog2.0
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`noteblog2.0` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `noteblog2.0`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `name` varchar(20) NOT NULL,
  `passwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`name`,`passwd`) values ('admin','admin');

/*Table structure for table `note` */

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `_guid` varchar(50) NOT NULL,
  `_title` varchar(50) DEFAULT NULL,
  `_time` datetime NOT NULL,
  `_isUpdated` double DEFAULT NULL,
  `_isHave` int(10) DEFAULT NULL,
  PRIMARY KEY (`_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `note` */

/*Table structure for table `note2tag` */

DROP TABLE IF EXISTS `note2tag`;

CREATE TABLE `note2tag` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `_noteGuid` varchar(50) DEFAULT NULL,
  `_tagGuid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `note2tag_ibfk_1` (`_noteGuid`),
  KEY `note2tag_ibfk_2` (`_tagGuid`),
  CONSTRAINT `note2tag_ibfk_1` FOREIGN KEY (`_noteGuid`) REFERENCES `note` (`_guid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `note2tag_ibfk_2` FOREIGN KEY (`_tagGuid`) REFERENCES `tag` (`_guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `note2tag` */

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `_guid` varchar(50) NOT NULL,
  `_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
