DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hour_limit` int(11) NOT NULL,
  `minute_hour_limit` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `id_schedule` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FKD162537EF7A9A753` (`id_schedule`),
  CONSTRAINT `FKD162537EF7A9A753` FOREIGN KEY (`id_schedule`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `assistance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assistance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_assistance` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `id_worker` bigint(20) DEFAULT NULL,
  `hour_limit_reference` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK51F4E73826B56E61` (`id_worker`),
  CONSTRAINT `FK51F4E73826B56E61` FOREIGN KEY (`id_worker`) REFERENCES `worker` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



