CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) DEFAULT NULL,
  `age` int(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)