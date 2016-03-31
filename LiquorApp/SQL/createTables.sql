drop table if exists product;
CREATE TABLE `liquor_db`.`product` (
  `productID` INT not null auto_increment,
  `brand` VARCHAR(45) NOT NULL DEFAULT ' ',
  `cost` DECIMAL(5,2) NOT NULL DEFAULT 0.0,
  `fullWeight` DOUBLE(7,1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`productID`),
  UNIQUE INDEX `productID_UNIQUE` (`productID` ASC)); 
   
  drop table if exists inventory;
  CREATE TABLE `liquor_db`.`inventory` (
 `inventoryID` INT NOT NULL AUTO_INCREMENT,
 `productID` VARCHAR(45) NOT NULL,
  `tstamp` DATETIME NOT NULL,
 `fullBottles` INT NOT NULL DEFAULT 0,
 `partialWeight` DOUBLE(7,1) NOT NULL DEFAULT 0,
 PRIMARY KEY (`inventoryID`),
 UNIQUE INDEX `inventoryID_UNIQUE` (`inventoryID` ASC));

  drop table if exists sales;
CREATE TABLE `sales` (
  `transactionID` int(11) NOT NULL AUTO_INCREMENT,
    `tstamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `productID` int(11) NOT NULL,
  `gramsSold` double(7,1) NOT NULL,
  `saleAmount`double(7,2) Not null,
  PRIMARY KEY (`transactionID`),
  UNIQUE KEY `transactionID_UNIQUE` (`transactionID`)
);

drop table if exists InventoryReport;
CREATE TABLE InventoryReport(
    `reportID`       INT NOT NULL ,
    `begInvID`       INT NOT NULL ,
    `endInvID`       INT NOT NULL ,
    `productID`      INT NOT NULL ,
    `begInvTotGrams` DOUBLE(9,2) NOT NULL ,
    `endInvTotGrams` DOUBLE(9,2) NOT NULL ,
    `soldGrams` DOUBLE(9,2) NOT NULL ,
    `soldDollars` DOUBLE(7,2) NOT NULL ,
    `wasteGrams` DOUBLE(9,2) NOT NULL ,
    `wasteDollar` DOUBLE(7,2) NOT NULL,
    Primary key (`reportID`)
  ) ;