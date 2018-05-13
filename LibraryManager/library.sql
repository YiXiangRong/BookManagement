/*
SQLyog Ultimate v9.60 
MySQL - 5.7.11-log : Database - library
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `library`;

/*Table structure for table `tb_lib_books` */

DROP TABLE IF EXISTS `tb_lib_books`;

CREATE TABLE `tb_lib_books` (
  `SUM` int(8) DEFAULT NULL COMMENT '总数',
  `NUMBER` int(8) DEFAULT NULL COMMENT '库存',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BOOKNAME` varchar(60) DEFAULT NULL COMMENT '书名',
  `BOOKCODE` varchar(20) DEFAULT NULL COMMENT '图书编码',
  `AUTHOR` varchar(60) DEFAULT NULL COMMENT '作者',
  `BOOKTYPE` varchar(10) DEFAULT NULL COMMENT '图书类型',
  `PRESS` varchar(100) DEFAULT NULL COMMENT '出版社',
  `PRICE` double(6,2) DEFAULT NULL COMMENT '价格',
  `PRINTING` varchar(100) DEFAULT NULL COMMENT '印刷',
  `PRESSDATE` varchar(20) DEFAULT NULL COMMENT '出版日期',
  `CONTENT` varchar(2000) DEFAULT NULL COMMENT '主要内容',
  `IMGADD` varchar(1000) DEFAULT NULL COMMENT '图片地址',
  `CREATEUSER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATEDATE` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `STORAGETYPE` varchar(10) DEFAULT NULL COMMENT '入库类型 1,自购，2捐赠',
  `STATE` varchar(10) DEFAULT NULL COMMENT '状态，1为借出，0为未借出',
  `DONATIONUSER` varchar(100) DEFAULT NULL COMMENT '损赠人员（单位)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `tb_lib_books` */

insert  into `tb_lib_books`(`SUM`,`NUMBER`,`id`,`BOOKNAME`,`BOOKCODE`,`AUTHOR`,`BOOKTYPE`,`PRESS`,`PRICE`,`PRINTING`,`PRESSDATE`,`CONTENT`,`IMGADD`,`CREATEUSER`,`CREATEDATE`,`STORAGETYPE`,`STATE`,`DONATIONUSER`) values (10,10,15,'《简爱》','201612345678902','夏洛蒂','小说','中华出版社',60.00,'上海天宇印刷厂','2001-09-17','略','jian_ai.jpg','赵云','2009-06-01','购买','0',''),(10,9,19,'《边城》','201612345678903','沈从文','文学','上海出版社',40.00,'上海天宇印刷厂','2001-09-17','略','bian_cheng.jpg','赵云','2009-06-08','购买','1',''),(10,10,20,'《小王子》','201612345678904','安托万·德·圣·埃克苏佩里','名著','上海出版社',60.00,'上海天宇印刷厂','2001-09-17','略','xiaowangzi.jpg','张三','2016-08-10','购买','0',''),(10,10,23,'《诛仙》','201612345678900','萧鼎','小说','中华出版社',50.00,'上海天宇印刷厂','2016-08-09','123','zhu_xian.jpg','张三','2016-08-17','购买','0',''),(10,10,25,'《西游记》','201612345678907','吴承恩','名著','中华出版社',60.00,'上海天宇印刷厂','2012-08-16','略','xi_you_ji.jpg','赵云','2014-08-16','购买','0',''),(10,10,26,'《红楼梦》','20161234567777','曹雪芹','名著','中华出版社',40.00,'上海天宇印刷厂','2016-09-13','null','hong_lou_meng.jpg','赵云','2016-09-30','购买','0','');

/*Table structure for table `tb_lib_borrowandretuenbooks` */

DROP TABLE IF EXISTS `tb_lib_borrowandretuenbooks`;

CREATE TABLE `tb_lib_borrowandretuenbooks` (
  `BOOKNAME` varchar(100) DEFAULT NULL COMMENT '书名',
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(30) DEFAULT NULL COMMENT '借书人姓名',
  `LIBCARDNO` varchar(20) DEFAULT NULL COMMENT '借书证号',
  `BOOKCODE` varchar(32) DEFAULT NULL COMMENT '图书编码',
  `BORROWDATE` date DEFAULT NULL COMMENT '借书日期',
  `SHOULDRETURNDATE` date DEFAULT NULL COMMENT '应还书日期',
  `BORROWMANAGER` varchar(32) DEFAULT NULL COMMENT '借书受理人ID',
  `RETURNDATE` date DEFAULT NULL COMMENT '实际还书日期',
  `RETURNMANAGER` varchar(32) DEFAULT NULL COMMENT '还书受理人',
  `ISOVERDUE` varchar(50) DEFAULT NULL COMMENT '还书是否超期,0 正常，1超期',
  `FINE` double(10,2) DEFAULT NULL COMMENT '罚款',
  `NOTE` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `tb_lib_borrowandretuenbooks` */

insert  into `tb_lib_borrowandretuenbooks`(`BOOKNAME`,`ID`,`NAME`,`LIBCARDNO`,`BOOKCODE`,`BORROWDATE`,`SHOULDRETURNDATE`,`BORROWMANAGER`,`RETURNDATE`,`RETURNMANAGER`,`ISOVERDUE`,`FINE`,`NOTE`) values ('《简爱》',33,'易向荣','201402','201612345678902','2016-09-08','2016-09-23','张三','2016-10-08','李四','超期',15.00,'超期罚款，业务结束！'),('《边城》',34,'李四','201403','201612345678903','2016-09-01','2016-09-10','张三','2016-09-24','张三','超期',14.00,'超期罚款，业务结束！'),('《简爱》',35,'张三','201402','201612345678902','2016-09-01','2016-10-01','王晓','2016-10-08','李四','超期',15.00,'超期罚款，业务结束！'),('《边城》',36,'的','1','201612345678903','2018-04-03','2018-04-27','我',NULL,'unknow','unknow',0.00,'ҵ�������');

/*Table structure for table `tb_lib_libcard` */

DROP TABLE IF EXISTS `tb_lib_libcard`;

CREATE TABLE `tb_lib_libcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LIBCARDNO` varchar(20) DEFAULT NULL COMMENT '借书证号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `CARDTYPE` varchar(3) DEFAULT NULL COMMENT '证件类型',
  `CARDNO` varchar(32) DEFAULT NULL COMMENT '证件编码',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '住所',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT 'Email',
  `CREATEUSER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATEDATE` varchar(30) DEFAULT NULL COMMENT '创建日期',
  `EFFECTIVEDATE` varchar(30) DEFAULT NULL COMMENT '有效日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tb_lib_libcard` */

insert  into `tb_lib_libcard`(`id`,`LIBCARDNO`,`NAME`,`CARDTYPE`,`CARDNO`,`PHONE`,`ADDRESS`,`EMAIL`,`CREATEUSER`,`CREATEDATE`,`EFFECTIVEDATE`) values (6,'201402','易向荣','身份证','421022199609164860','15671564672','武汉科技大学南八','12345678@qq.com','赵云','19960916','20200807'),(9,'201403','张三','身份证','421022199609164234','15671564636','武汉科技大学南八','12319678@qq.com','赵云','19960916','20200807'),(11,'201404','李四','身份证','421022199609164830','15671564655','武汉科技大学南八','12319678@qq.com','赵云','1996-09-13','2016-09-13'),(14,'201601','李四','身份证','421022199609164830','123456','武汉科技大学南八','1335567@qq.com','赵云','2016-08-04','2016-08-04'),(15,'201401','李四','身份证','421022199609164860','15671564672','武汉科技大学南八','12319678@qq.com','赵云','2012-08-17','2016-08-30'),(16,'201405','李四','身份证','421022199609164830','15671564672','武汉科技大学南十','1335567@qq.com','赵云','1996-09-13','2016-08-04');

/*Table structure for table `tb_lib_managers` */

DROP TABLE IF EXISTS `tb_lib_managers`;

CREATE TABLE `tb_lib_managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNTS` varchar(32) DEFAULT NULL COMMENT '登录帐号',
  `PWD` varchar(12) DEFAULT NULL COMMENT '密码',
  `NAME` varchar(40) DEFAULT NULL COMMENT '姓名',
  `CARDTYPE` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `CARDNO` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `PHONE` varchar(13) DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT 'Email',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '住所',
  `TYPE` int(10) DEFAULT NULL COMMENT '类型，1为超级管理员，0为管理员',
  `CREATEDATE` varchar(30) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `tb_lib_managers` */

insert  into `tb_lib_managers`(`id`,`ACCOUNTS`,`PWD`,`NAME`,`CARDTYPE`,`CARDNO`,`PHONE`,`EMAIL`,`ADDRESS`,`TYPE`,`CREATEDATE`) values (2,'admin','abc123','易向荣','身份证','421022199609164848','15671564650','921118570@qq.com','武汉科技大学南十',1,'20160801'),(17,'admin01','1234567','张三','身份证','421022199809167878','15671564657','1335567@qq.com','武汉科技大学南十',0,'19960916'),(18,'admin02','12345678','王五','身份证','421866199810182626','15671564654','1234579@qq.com','武汉科技大学南八',0,'2016-08-20');

/*Table structure for table `tb_lib_scrapbook` */

DROP TABLE IF EXISTS `tb_lib_scrapbook`;

CREATE TABLE `tb_lib_scrapbook` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BOOKID` varchar(32) DEFAULT NULL COMMENT '图书ID',
  `BOOKCODE` varchar(32) DEFAULT NULL COMMENT '图书编码',
  `SCRAPREASON` varchar(32) DEFAULT NULL COMMENT '报废原因(1 损坏，2 丢失，3捐赠)',
  `DESCRIBES` varchar(200) DEFAULT NULL COMMENT '详细描述',
  `SCRAPDATE` date DEFAULT NULL COMMENT '报废日期',
  `CREATEUSER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATEDATE` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_lib_scrapbook` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
