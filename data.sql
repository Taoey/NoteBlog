/*
SQLyog v10.2 
MySQL - 5.6.17 : Database - evernote
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`evernote` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `evernote`;

/*Table structure for table `notes` */

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `_title` varchar(100) DEFAULT NULL,
  `_guid` varchar(50) NOT NULL,
  `_tagsGuid` blob,
  `_isUpdated` bigint(20) DEFAULT NULL,
  `_url` blob,
  `_isHave` int(11) DEFAULT NULL,
  PRIMARY KEY (`_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notes` */

insert  into `notes`(`_title`,`_guid`,`_tagsGuid`,`_isUpdated`,`_url`,`_isHave`) values ('言叶之庭--记住我和你的雨季','14272d7a-27dd-487e-8347-9cda1801f016','',1496244090000,'http://127.0.0.1:7443/blogs/14272d7a-27dd-487e-8347-9cda1801f016',0),('Python(简单验证码的识别)','2172dcfd-b177-4986-be1a-c884884955bc','',1494332522000,'http://127.0.0.1:7443/blogs/2172dcfd-b177-4986-be1a-c884884955bc',0),('#JavaWeb编程常用','5beee5bc-fc43-42cf-a893-a2f3c430c7b5','',1498640257000,'http://127.0.0.1:7443/blogs/5beee5bc-fc43-42cf-a893-a2f3c430c7b5',0),('标准C++中的string类的用法总结','b2037cd9-39b9-4ee8-be39-dd8a64c8a810','',1460425299000,'http://127.0.0.1:7443/blogs/b2037cd9-39b9-4ee8-be39-dd8a64c8a810',0),('list容器学习','bef4afb7-57b1-41d2-86a6-f719a0bd33ed','',1461572046000,'http://127.0.0.1:7443/blogs/bef4afb7-57b1-41d2-86a6-f719a0bd33ed',0),('三人小分队成员','dd1d9980-c56b-4226-a3c4-dc85ccd0ecce','',1503801620000,'http://127.0.0.1:7443/blogs/dd1d9980-c56b-4226-a3c4-dc85ccd0ecce',0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `_name` varchar(20) DEFAULT NULL,
  `_email` varchar(25) NOT NULL,
  `_token` varchar(100) DEFAULT NULL,
  `_storeUrl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`_name`,`_email`,`_token`,`_storeUrl`) values ('1','1','1','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
