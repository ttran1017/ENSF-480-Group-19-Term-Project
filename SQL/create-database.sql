DROP DATABASE IF EXISTS `PRMS_Database`;
CREATE DATABASE `PRMS_Database`; 
USE `PRMS_Database`;

CREATE TABLE `Accounts` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account type` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`account_id`)
);

INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('User','johndoe@yahoo.com','John_Doe24', '8545');
INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('User','hughmorris@gmail.com','HughXMorris' , 'dsgasdvvouo2q');
INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('Manager','justincredible@icloud.com','Justin_Credible', '51aesdg6');
INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('Manager','hilllarius@hotmail.com','HillLarius52', 'a435d45a4@%#');

CREATE TABLE `Properties` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `address` varchar(150) NOT NULL,
  `type` varchar(50) NOT NULL,
  `# of bedrooms` int(11) NOT NULL,
  `# of bathrooms` int(11) NOT NULL,
  `is furnished` boolean NOT NULL,
  `city quadrant` char(2) NOT NULL,
  `days` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `FK_account_id` (`account_id`),
  CONSTRAINT `FK_account_id` FOREIGN KEY (`account_id`) REFERENCES `Accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (3,'64 Zoo Lane','apartment',2,1,TRUE,'SW',5,'active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (4,'1435 korobeiniki Drive','attached house',3,2,TRUE,'SE',6,'active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (2,'42 Wallaby Way','detached house',4,3,FALSE,'SE',3,'active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (2,'15 Yemen Road','townhouse',3,2,FALSE,'NE',2,'active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (1,'4 Privet Drive','condo',2,1,TRUE,'NW',1,'active');

CREATE TABLE `Financing` (
  `fee` int(11) NOT NULL ,
  `period` int(11) NOT NULL,
  `balance` int(11) NOT NULL
);

INSERT INTO `Financing`VALUES (20,7, 60);



