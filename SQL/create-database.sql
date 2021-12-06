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
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (3,'64 Zoo Lane','Apartment',2,1,TRUE,'SW',5,'Active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (4,'1435 korobeiniki Drive','AttachedHouse',3,2,TRUE,'SE',6,'Active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (2,'42 Wallaby Way','DetachedHouse',4,3,FALSE,'SE',3,'Active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (2,'15 Yemen Road','Townhouse',3,2,FALSE,'NE',2,'Active');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status) VALUES (1,'4 Privet Drive','Condo',2,1,TRUE,'NW',1,'Active');

CREATE TABLE `Financing` (
  `fee` int(11) NOT NULL ,
  `period` int(11) NOT NULL,
  `balance` int(11) NOT NULL
);

INSERT INTO `Financing`VALUES (20,7, 60);

CREATE TABLE `Filters` (
  `account_id` int(11) NOT NULL,
  `property type` varchar(50) DEFAULT "null",
  `property quadrant` varchar(50) DEFAULT "null",
  `minimum bedrooms` int(11) DEFAULT -1,
  `maximum bedrooms` int(11) DEFAULT -1,
  `minimum bathrooms` int(11) DEFAULT -1,
  `maximum bathrooms` int(11) DEFAULT -1,
  `is furnished` boolean DEFAULT -1,
  KEY (`account_id`)
  -- KEY `FKK_account_id` (`account_id`),
  -- CONSTRAINT `FKK_account_id` FOREIGN KEY (`account_id`) REFERENCES `Accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO `Filters`VALUES (1,'Apartment','SW',1,5,2,3,True);
INSERT INTO `Filters`VALUES (2,'Condo','NW',2,4,1,3,False);

CREATE TABLE `Subscriptions` (
  `account_id` int(11) NOT NULL,
  `subscribed` boolean NOT NULL DEFAULT TRUE,
  KEY (`account_id`)
  -- KEY `KF_account_id` (`account_id`),
  -- CONSTRAINT `KF_account_id` FOREIGN KEY (`account_id`) REFERENCES `Accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO `Subscriptions`VALUES (1,True);
INSERT INTO `Subscriptions`VALUES (2,False);



