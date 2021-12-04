DROP DATABASE IF EXISTS `PRMS_Database`;
CREATE DATABASE `PRMS_Database`; 
USE `PRMS_Database`;

CREATE TABLE `Accounts` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`account_id`)
);

INSERT INTO `Accounts`(email,username,password) VALUES ('johndoe@yahoo.com','John_Doe24', '8545');
INSERT INTO `Accounts`(email,username,password) VALUES ('hughmorris@gmail.com','HughXMorris' , 'dsgasdvvouo2q');
INSERT INTO `Accounts`(email,username,password) VALUES ('justincredible@icloud.com','Justin_Credible', '51aesdg6');
INSERT INTO `Accounts`(email,username,password) VALUES ('hilllarius@hotmail.com','HillLarius52', 'a435d45a4@%#');

CREATE TABLE `Properties` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `# of bedrooms` int(11) NOT NULL,
  `# of bathrooms` int(11) NOT NULL,
  `is furnished` boolean NOT NULL,
  `city quadrant` char(2) NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `FK_account_id` (`account_id`),
  CONSTRAINT `FK_account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
INSERT INTO `Properties`(account_id,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`) VALUES (3,'apartment',2,1,TRUE,'SW');
INSERT INTO `Properties`(account_id,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`) VALUES (4,'attached house',3,2,TRUE,'SE');
INSERT INTO `Properties`(account_id,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`) VALUES (2,'detached house',4,3,FALSE,'SE');
INSERT INTO `Properties`(account_id,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`) VALUES (2,'townhouse',3,2,FALSE,'NE');
INSERT INTO `Properties`(account_id,type,`# of bedrooms`,`# of bathrooms`,`is furnished`,`city quadrant`) VALUES (1,'condominium',2,1,TRUE,'NW');




