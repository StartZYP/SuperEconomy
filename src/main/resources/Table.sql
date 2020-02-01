/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `iConomy` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `balance` double(64,2) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `PlayerUUid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=32063 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_c