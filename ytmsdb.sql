/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.27 : Database - ytmsdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ytmsdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ytmsdb`;

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `section_id` int(5) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `createdby` int(5) DEFAULT NULL,
  `modifiedby` int(5) DEFAULT NULL,
  `createddate` date NOT NULL,
  `modifieddate` date NOT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1' COMMENT '1. show, 2. hide',
  PRIMARY KEY (`id`),
  KEY `fk_section_id` (`section_id`),
  KEY `fk_category_createdby` (`createdby`),
  KEY `fk_category_modifiedby` (`modifiedby`),
  CONSTRAINT `fk_category_createdby` FOREIGN KEY (`createdby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_category_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_section_id` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

/*Table structure for table `documents` */

DROP TABLE IF EXISTS `documents`;

CREATE TABLE `documents` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) DEFAULT NULL,
  `category_id` int(5) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `createdby` int(5) DEFAULT NULL,
  `modifiedby` int(5) DEFAULT NULL,
  `createddate` date NOT NULL,
  `modifieddate` date NOT NULL,
  `isActive` int(1) NOT NULL DEFAULT '1' COMMENT '1.Active, 2. block',
  `filepath` varchar(200) NOT NULL,
  `isShow` int(1) DEFAULT '2' COMMENT '1.Show, 2. Do not show',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_category_id` (`category_id`),
  KEY `fk_document_createdby` (`createdby`),
  KEY `fk_document_modifiedby` (`modifiedby`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_createdby` FOREIGN KEY (`createdby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_document_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `members` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `documents` */

/*Table structure for table `members` */

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(25) NOT NULL,
  `lastname` varchar(25) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isActive` int(1) NOT NULL DEFAULT '2' COMMENT '1. Active, 2. Block',
  `role` int(1) NOT NULL DEFAULT '3' COMMENT '1.Trainer, 2. Manager, 3. Trainee',
  `createdby` int(5) DEFAULT NULL COMMENT 'user id who has created',
  `modifiedby` int(5) DEFAULT NULL COMMENT 'user id who had modified the existing user',
  `createddate` date NOT NULL,
  `modifiedDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_createdby` (`createdby`),
  KEY `fk_modifiedby` (`modifiedby`),
  CONSTRAINT `fk_createdby` FOREIGN KEY (`createdby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `members` */

/*Table structure for table `sections` */

DROP TABLE IF EXISTS `sections`;

CREATE TABLE `sections` (
  `isActive` int(1) DEFAULT '1' COMMENT '1. show, 2. hide',
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `createdby` int(5) NOT NULL,
  `modifiedby` int(5) NOT NULL,
  `createddate` date NOT NULL,
  `modifieddate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_section_createdby` (`createdby`),
  KEY `fk_section_modifiedby` (`modifiedby`),
  CONSTRAINT `fk_section_createdby` FOREIGN KEY (`createdby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_section_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sections` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
