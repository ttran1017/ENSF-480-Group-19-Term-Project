DROP DATABASE IF EXISTS `PRMS_Database`;
CREATE DATABASE `PRMS_Database`; 
USE `PRMS_Database`;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE `Accounts` (
  `account_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`Account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Accounts` VALUES (1,'John_Doe24', '8545');
INSERT INTO `Accounts` VALUES (2,'HughXMorris' , 'dsgasdvvouo2q');
INSERT INTO `Accounts` VALUES (3,'Justin_Credible', '51aesdg6');
INSERT INTO `Accounts` VALUES (4,'HillLarius52', 'a435d45a4@%#');

CREATE TABLE `Properties` (
  `property_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `# of bedrooms` int(11) NOT NULL,
  `# of bathrooms` int(11) NOT NULL,
  `is furnished` boolean NOT NULL,
  `city quadrant` char(2) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `FK_account_id` (`account_id`),
  CONSTRAINT `FK_account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Properties` VALUES (1,3,'apartment',2,1,'SW');
INSERT INTO `Properties` VALUES (2,4,'attached house',3,2,'SE');
INSERT INTO `Properties` VALUES (3,2,'detached house',4,3,'SE');
INSERT INTO `Properties` VALUES (4,2,'townhouse',3,2,'NE');
INSERT INTO `Properties` VALUES (5,1,'condominium',2,1,'NW');




