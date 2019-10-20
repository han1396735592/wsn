-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: qqhxj.cn    Database: am
-- ------------------------------------------------------
-- Server version	5.7.24-log

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
-- Table structure for table `expense_item`
--

DROP TABLE IF EXISTS `expense_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `expense_type_id` int(11) NOT NULL,
  `money` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `expense_item_project_id_fk` (`project_id`),
  KEY `expense_item_expense_type_id_fk` (`expense_type_id`),
  CONSTRAINT `expense_item_expense_type_id_fk` FOREIGN KEY (`expense_type_id`) REFERENCES `expense_type` (`id`),
  CONSTRAINT `expense_item_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='项目费用明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_item`
--

LOCK TABLES `expense_item` WRITE;
/*!40000 ALTER TABLE `expense_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_type`
--

DROP TABLE IF EXISTS `expense_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `expense_type_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `expense_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='项目费用类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_type`
--

LOCK TABLES `expense_type` WRITE;
/*!40000 ALTER TABLE `expense_type` DISABLE KEYS */;
INSERT INTO `expense_type` VALUES (1,'建筑安装费用',NULL),(2,'直接费',1),(3,'间接费',1),(7,'直接工程费',2),(8,'人工费',7),(9,'材料费',7),(10,'措施费',2),(11,'环境保护费',10),(12,'文明施工费',10),(13,'安全施工费',10),(14,'临时设施费',10),(15,'规费',3),(16,'工程排污费',15),(17,'社会保障费',15),(18,'企业管理费',3),(19,'办公费',18),(20,'管理人员工资',18),(21,'工会经费',18);
/*!40000 ALTER TABLE `expense_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_use_plan`
--

DROP TABLE IF EXISTS `expense_use_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_use_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` char(30) NOT NULL,
  `money` double NOT NULL,
  `project_expense_Category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_use_plan`
--

LOCK TABLES `expense_use_plan` WRITE;
/*!40000 ALTER TABLE `expense_use_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense_use_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `enable` binary(1) NOT NULL DEFAULT '0',
  `remark` char(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `model` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit` char(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '个',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (42,'水泥',_binary '1','水泥','LIS_S','顿'),(53,'钢筋',_binary '1','','L-a','个'),(54,'楠木',_binary '1','楠木','hello','跟');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_input_storage`
--

DROP TABLE IF EXISTS `material_input_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_input_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `user_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `remark` char(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_input_storage`
--

LOCK TABLES `material_input_storage` WRITE;
/*!40000 ALTER TABLE `material_input_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_input_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_input_storage_item`
--

DROP TABLE IF EXISTS `material_input_storage_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_input_storage_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materials_id` int(11) NOT NULL,
  `material_input_storage_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `material_input_storage_item_material_input_storage_id_fk` (`material_input_storage_id`),
  KEY `material_input_storage_item_materials_id_fk` (`materials_id`),
  CONSTRAINT `material_input_storage_item_material_input_storage_id_fk` FOREIGN KEY (`material_input_storage_id`) REFERENCES `material_input_storage` (`id`),
  CONSTRAINT `material_input_storage_item_materials_id_fk` FOREIGN KEY (`materials_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_input_storage_item`
--

LOCK TABLES `material_input_storage_item` WRITE;
/*!40000 ALTER TABLE `material_input_storage_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_input_storage_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_output_storage`
--

DROP TABLE IF EXISTS `material_output_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_output_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `user_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `remark` char(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_output_storage`
--

LOCK TABLES `material_output_storage` WRITE;
/*!40000 ALTER TABLE `material_output_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_output_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_output_storage_item`
--

DROP TABLE IF EXISTS `material_output_storage_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_output_storage_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_output_storage_id` int(11) NOT NULL,
  `materials_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `material_output_storage_item_materials_id_fk` (`materials_id`),
  KEY `material_output_storage_item_material_output_storage_id_fk` (`material_output_storage_id`),
  CONSTRAINT `material_output_storage_item_material_output_storage_id_fk` FOREIGN KEY (`material_output_storage_id`) REFERENCES `material_output_storage` (`id`),
  CONSTRAINT `material_output_storage_item_materials_id_fk` FOREIGN KEY (`materials_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_output_storage_item`
--

LOCK TABLES `material_output_storage_item` WRITE;
/*!40000 ALTER TABLE `material_output_storage_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_output_storage_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_use_plan`
--

DROP TABLE IF EXISTS `material_use_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_use_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `material_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `am_material_use_plan_am_project_id_fk` (`project_id`),
  KEY `am_material_use_plan_am_materials_id_fk` (`material_id`),
  CONSTRAINT `am_material_use_plan_am_materials_id_fk` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `am_material_use_plan_am_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_use_plan`
--

LOCK TABLES `material_use_plan` WRITE;
/*!40000 ALTER TABLE `material_use_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_use_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `name` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `detail` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `principal_Id` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creator_id` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('201903181833000530584','项目一','2019-03-25 00:24:13','',NULL,'141429435538203635',NULL),('201903231229000520543','郑州大学项目','2019-03-25 00:09:47',NULL,NULL,'',NULL),('f78-50b996dabb60','项目二','2019-03-14 00:00:00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `sort` int(11) NOT NULL DEFAULT '1',
  `type` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '菜单',
  PRIMARY KEY (`id`),
  KEY `sys_menu_sys_menu_id_fk` (`pid`),
  CONSTRAINT `sys_menu_sys_menu_id_fk` FOREIGN KEY (`pid`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理','',NULL,1,1,'菜单'),(2,'菜单管理','/modules/sys/sysMenu.html',1,1,1,'菜单'),(3,'接口文档','/swagger-ui.html',1,1,1,'菜单'),(11,'数据监控','/druid/index.html',1,1,1,'菜单'),(12,'基本信息','',NULL,1,1,'菜单'),(13,'材料管理','/modules/am/material.html',12,1,1,'菜单'),(15,'项目管理','/modules/am/project.html',16,1,1,'菜单'),(16,'项目管理','',NULL,1,1,'菜单'),(18,'项目费用类别','/modules/am/expenseType.html',12,1,1,'菜单'),(19,'项目预算','/modules/am/projectBudget.html',16,1,1,'菜单');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-03 15:35:21
