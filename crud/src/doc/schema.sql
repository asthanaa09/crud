MySql V5

----------------------------------------------------
Table Structure : User
----------------------------------------------------

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `sur_name` varchar(100) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `creation_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
