# ************************************************************
# Sequel Pro SQL dump
# Версия 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Адрес: 127.0.0.1 (MySQL 5.5.5-10.5.6-MariaDB-1:10.5.6+maria~focal)
# Схема: family_tree
# Время создания: 2020-12-21 07:05:35 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Дамп таблицы family_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `family_member`;

CREATE TABLE `family_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) NOT NULL,
  `sex` char(6) NOT NULL,
  `birth_year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `family_member` WRITE;
/*!40000 ALTER TABLE `family_member` DISABLE KEYS */;

INSERT INTO `family_member` (`id`, `name`, `sex`, `birth_year`)
VALUES
	(1,'FirstChild','male',2012),
	(2,'Name','male',1985),
	(3,'Lol','female',1990),
	(4,'Daddy','male',1965),
	(6,'NewOne','female',1968),
	(7,'Test 1','male',1920),
	(8,'Test 2','female',1925),
	(9,'Test 3','female',1945),
	(10,'Test 4','female',1948),
	(11,'Test 5','male',1946),
	(12,'Test 6','female',1966),
	(13,'Test 7','female',1989),
	(14,'Test 8','female',2009),
	(15,'Test 9','male',1984),
	(16,'Test 10','female',2011),
	(17,'Test 11','female',2014);

/*!40000 ALTER TABLE `family_member` ENABLE KEYS */;
UNLOCK TABLES;


# Дамп таблицы parents
# ------------------------------------------------------------

DROP TABLE IF EXISTS `parents`;

CREATE TABLE `parents` (
  `member_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  KEY `parents_family_member_id_fk` (`member_id`),
  KEY `parents_family_member_id_fk_2` (`parent_id`),
  CONSTRAINT `parents_family_member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `family_member` (`id`),
  CONSTRAINT `parents_family_member_id_fk_2` FOREIGN KEY (`parent_id`) REFERENCES `family_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;

INSERT INTO `parents` (`member_id`, `parent_id`)
VALUES
	(1,3),
	(3,4),
	(3,6),
	(1,2),
	(2,4),
	(2,6),
	(4,8),
	(4,7),
	(9,7),
	(9,8),
	(10,7),
	(10,8),
	(13,10),
	(13,11),
	(12,9),
	(12,11),
	(14,4),
	(14,12),
	(16,13),
	(16,15),
	(17,13),
	(17,15);

/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
