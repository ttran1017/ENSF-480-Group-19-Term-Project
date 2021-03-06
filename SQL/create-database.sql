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
INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('User','user@test.com','user', '123');
INSERT INTO `Accounts`(`account type`,email,username,password) VALUES ('Manager','manager@test.com','manager', '123');

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
  `date listed` date,
  `date rented` date,
  PRIMARY KEY (`property_id`),
  KEY `FK_account_id` (`account_id`),
  CONSTRAINT `FK_account_id` FOREIGN KEY (`account_id`) REFERENCES `Accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status,`date listed`,`date rented`) VALUES (1,'64 Zoo Lane','Apartment',2,1,TRUE,'SW',5,'Active','2019-12-5',null);
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status,`date listed`,`date rented`) VALUES (1,'1435 korobeiniki Drive','AttachedHouse',3,2,TRUE,'SE',6,'Rented','2020-12-4','2020-12-9');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status,`date listed`,`date rented`) VALUES (2,'42 Wallaby Way','DetachedHouse',4,3,FALSE,'SE',3,'Active','2021-12-7','2021-12-10');
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status,`date listed`,`date rented`) VALUES (2,'15 Yemen Road','Townhouse',3,2,FALSE,'NE',2,'Rented','2021-12-8',null);
INSERT INTO `Properties`(account_id,address,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`,days,status,`date listed`,`date rented`) VALUES (1,'4 Privet Drive','Condo',2,1,TRUE,'NW',1,'Active','2021-12-9',null);

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

INSERT INTO `Filters`VALUES (1,'Mansion','null',-1,-1,-1,-1,True);
INSERT INTO `Filters`VALUES (2,'Mansion','null',-1,-1,-1,-1,True);
INSERT INTO `Filters`VALUES (5,'null','null',-1,-1,-1,-1,-1);

CREATE TABLE `Subscriptions` (
  `account_id` int(11) NOT NULL,
  `subscribed` boolean NOT NULL DEFAULT TRUE,
  KEY (`account_id`)
  -- KEY `KF_account_id` (`account_id`),
  -- CONSTRAINT `KF_account_id` FOREIGN KEY (`account_id`) REFERENCES `Accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO `Subscriptions`VALUES (1,True);
INSERT INTO `Subscriptions`VALUES (2,False);
INSERT INTO `Subscriptions`VALUES (5,True);



