USE `employee_demo`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `ID` varchar(255) NOT NULL,
  `CREATED_BY` varchar(255) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(255) NOT NULL,
  `LAST_UPDATED_ON` datetime NOT NULL,
  `VERSION_NUMBER` bigint(20) NOT NULL,
  `CODE` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `ID` varchar(255) NOT NULL,
  `CREATED_BY` varchar(255) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(255) NOT NULL,
  `LAST_UPDATED_ON` datetime NOT NULL,
  `VERSION_NUMBER` bigint(20) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `GENDER` int(11) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `DEPARTMENT_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfvt83ye0flecxfewcniue0n9n` (`DEPARTMENT_ID`),
  CONSTRAINT `FKfvt83ye0flecxfewcniue0n9n` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `department` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

