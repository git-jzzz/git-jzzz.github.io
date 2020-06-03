/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.28 : Database - audio
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`audio` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `audio`;

/*Table structure for table `audio` */

DROP TABLE IF EXISTS `audio`;

CREATE TABLE `audio` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库主键',
  `song_id` varchar(50) DEFAULT NULL COMMENT '网易云歌曲id',
  `name` varchar(50) DEFAULT NULL COMMENT '歌曲名',
  `artist` varchar(50) DEFAULT NULL COMMENT '作者',
  `url` varchar(500) DEFAULT NULL COMMENT '歌曲url',
  `lrc` varchar(500) DEFAULT NULL COMMENT '歌词路径',
  `cover` varchar(500) DEFAULT NULL COMMENT '歌曲封面地址',
  `status` int(11) DEFAULT NULL COMMENT '状态1:使用   2禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `audio` */

insert  into `audio`(`id`,`song_id`,`name`,`artist`,`url`,`lrc`,`cover`,`status`) values (4,'1374329431','Dancing With Your Ghost','Sasha Sloan','https://music.163.com/song/media/outer/url?id=1374329431','/lrc/Dancing With Your Ghost.lrc','https://p2.music.126.net/yKjpbkjawkKABkHy818gOQ==/109951164175679778.jpg?param=130y130',1),(5,'557581476','去年夏天','王大毛','https://music.163.com/song/media/outer/url?id=557581476','/lrc/去年夏天.lrc','https://p1.music.126.net/5M7Qvv32HaFmMrLN8uehuA==/109951163293243877.jpg?param=130y130',1),(6,'26111503','花海','中孝介','https://music.163.com/song/media/outer/url?id=26111503','/lrc/花海.lrc','https://p1.music.126.net/25DGuAa24cQQthqeLOBiyg==/7989051488911119.jpg?param=130y130',1),(7,'494645920','ＡＬＯＮＥ','TRA$H','https://music.163.com/song/media/outer/url?id=494645920','/lrc/ＡＬＯＮＥ.lrc','https://p2.music.126.net/J1OXPgff80ZmF9uyapQzbA==/18522372883513330.jpg?param=130y130',1),(8,'512359195','LOSER','米津玄師','https://music.163.com/song/media/outer/url?id=512359195','/lrc/LOSER.lrc','https://p2.music.126.net/A-VUmkAaUVQ88iXBrsbQ1A==/109951163561900568.jpg?param=130y130',1),(9,'5255987','你若成风','许嵩','https://music.163.com/song/media/outer/url?id=5255987','/lrc/你若成风.lrc','https://p2.music.126.net/OjibHiyRong4S0RgBFp-Pw==/2301277836958388.jpg?param=130y130',1),(10,'1331821429','Better Now','Yuna','https://music.163.com/song/media/outer/url?id=1331821429','/lrc/Better Now.lrc','https://p2.music.126.net/-K5BGTrjHLiwhwd11eXixg==/109951163718521323.jpg?param=130y130',1),(11,'16607998','The Show','Lenka','https://music.163.com/song/media/outer/url?id=16607998','/lrc/The Show.lrc','https://p1.music.126.net/RbiJjFAmdPhzaqpM6nAczw==/109951164841022611.jpg?param=130y130',1),(12,'1313591404','Let Me Down Slowly ','Beth','https://music.163.com/song/media/outer/url?id=1313591404','/lrc/Let Me Down Slowly .lrc','https://p2.music.126.net/2-l6KNTqA62nLdGxSvkoZg==/109951163575046014.jpg?param=130y130',1),(13,'1364247901','Love is Gone','SLANDER / Dylan Matthew','https://music.163.com/song/media/outer/url?id=1364247901','/lrc/Love is Gone.lrc','https://p2.music.126.net/dTC6xKCa2MWLgl7ZSJm8HA==/109951164062390886.jpg?param=130y130',1),(14,'478507889','卡农（经典钢琴版）','dylanf','https://music.163.com/song/media/outer/url?id=478507889','/lrc/卡农（经典钢琴版）.lrc','https://p1.music.126.net/-ShCoe12zt2C2mPQgaq0ZQ==/109951162915837220.jpg?param=130y130',1),(15,'1326840518','Send It','Alexi Noa','https://music.163.com/song/media/outer/url?id=1326840518','/lrc/Send It.lrc','https://p1.music.126.net/uKyamUfWL0qfFvJNrAVkCQ==/109951163676771046.jpg?param=130y130',1),(16,'5100769','Nothing On You','B.o.B / Bruno Mars','https://music.163.com/song/media/outer/url?id=5100769','/lrc/Nothing On You.lrc','https://p2.music.126.net/LpNeJdD3VtiThH5uIi62Hg==/1698745464926789.jpg?param=130y130',1),(17,'31010566','Sold Out','Hawk Nelson','https://music.163.com/song/media/outer/url?id=31010566','/lrc/Sold Out.lrc','https://p1.music.126.net/UR8jAfqus_o1j_QkaYkZ_g==/109951163664241365.jpg?param=130y130',1),(18,'518904426','Umbrella (Matte Remix)','Matte / Ember Island','https://music.163.com/song/media/outer/url?id=518904426','/lrc/Umbrella (Matte Remix).lrc','https://p2.music.126.net/1LrtvH8EpKb5iHKR9qEU0Q==/109951163063843501.jpg?param=130y130',1),(19,'27180681','Lemon Tree','Fool\'s Garden','https://music.163.com/song/media/outer/url?id=27180681','/lrc/Lemon Tree.lrc','https://p1.music.126.net/r3i_ohvymLmdMpiF-dcN2A==/1353498813846118.jpg?param=130y130',1),(20,'28718313','The Way I Still Love You','Reynard Silva','https://music.163.com/song/media/outer/url?id=28718313','/lrc/The Way I Still Love You.lrc','https://p2.music.126.net/JyPsd_g00M-4mqXLLtHncw==/5984641790343690.jpg?param=130y130',1),(21,'1441097233','Salt (Syn Cole Remix)','Ava Max / Syn Cole','https://music.163.com/song/media/outer/url?id=1441097233','/lrc/Salt (Syn Cole Remix).lrc','https://p2.music.126.net/dntPpej_HoH6CyAogRnbew==/109951164911660483.jpg?param=130y130',1),(22,'1439374208','My beautiful','Davin大文 / 小郭雨儿','https://music.163.com/song/media/outer/url?id=1439374208','/lrc/My beautiful.lrc','https://p1.music.126.net/sl9AJN53iMkn4v0WtitcLg==/109951164890315366.jpg?param=130y130',1),(23,'1397573144','Natural','Planet in Limbo','https://music.163.com/song/media/outer/url?id=1397573144','/lrc/Natural.lrc','https://p2.music.126.net/wMjXwlpC_DFVJQ9MDmAy4Q==/109951164433255998.jpg?param=130y130',1),(24,'504265014','Dusk Till Dawn','ZAYN / Sia','https://music.163.com/song/media/outer/url?id=504265014','/lrc/Dusk Till Dawn.lrc','https://p1.music.126.net/c3lWgFxA0nZQt1Pc5B1p7A==/109951163019943856.jpg?param=130y130',1),(25,'1356248072','YELLOW','神山羊','https://music.163.com/song/media/outer/url?id=1356248072','/lrc/YELLOW.lrc','https://p1.music.126.net/b8q7AOU3zQFPpayJ4k3J-Q==/109951163971418603.jpg?param=130y130',1),(26,'28442976','Too Far','Anna F','https://music.163.com/song/media/outer/url?id=28442976','/lrc/Too Far.lrc','https://p2.music.126.net/l7vAbhmCkx4sBg6f9a6oPQ==/6069304185603873.jpg?param=130y130',1),(27,'526116053','50 Feet','SoMo','https://music.163.com/song/media/outer/url?id=526116053','/lrc/50 Feet.lrc','https://p2.music.126.net/-uGrdpcVwLIMEx6NXGaNrQ==/109951163168473869.jpg?param=130y130',1),(28,'472361236','Galway Girl','MADILYN','https://music.163.com/song/media/outer/url?id=472361236','/lrc/Galway Girl.lrc','https://p2.music.126.net/QaDXHrAwn1C6pp167uoMHw==/19085322835018013.jpg?param=130y130',1),(29,'4038411','Coming Home','Skylar Grey / Diddy-Dirty Money','https://music.163.com/song/media/outer/url?id=4038411','/lrc/Coming Home.lrc','https://p2.music.126.net/yEqg_-WA0Zr9R1k8oSo7vg==/827932255760295.jpg?param=130y130',1),(30,'29480187','We Can\'t Stop','Boyce Avenue / Bea Miller','https://music.163.com/song/media/outer/url?id=29480187','/lrc/We Can\'t Stop.lrc','https://p2.music.126.net/STR62HQZHcb76i3RrM9SCw==/109951164473866599.jpg?param=130y130',1),(31,'1297742167','MELANCHOLY','White Cherry','https://music.163.com/song/media/outer/url?id=1297742167','/lrc/MELANCHOLY.lrc','https://p2.music.126.net/-CFt0bZ07CkM8Chc6sM8Og==/109951164922344481.jpg?param=130y130',1),(32,'2001320','Valder Fields','Tamas Wells','https://music.163.com/song/media/outer/url?id=2001320','/lrc/Valder Fields.lrc','https://p1.music.126.net/XbQhfWII58akaVQ6_xECEw==/6635552673830427.jpg?param=130y130',1),(33,'1421470173','Girls Like You（toby randall）','AGAM','https://music.163.com/song/media/outer/url?id=1421470173','/lrc/Girls Like You（toby randall）.lrc','https://p1.music.126.net/sAW4JrYhauNAotk6BIQiqg==/109951164684327278.jpg?param=130y130',1),(34,'479598964','椿','沈以诚','https://music.163.com/song/media/outer/url?id=479598964','/lrc/椿.lrc','https://p2.music.126.net/CpwIPy2YUWC6ksF88eGNyw==/109951162931819035.jpg?param=130y130',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
