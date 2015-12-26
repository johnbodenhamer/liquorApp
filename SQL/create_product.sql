CREATE TABLE `liquor_db`.`product` (
  `productID` INT not null auto_increment,
  `brand` VARCHAR(45) NOT NULL DEFAULT ' ',
  `cost` DECIMAL(5,2) NOT NULL DEFAULT 0.0,
  `full_weight` DOUBLE(7,4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`productID`),
  UNIQUE INDEX `productID_UNIQUE` (`productID` ASC));
  
  CREATE TABLE `liquor_db`.`inventory` (
 `inventoryID` INT NOT NULL AUTO_INCREMENT,
 `fullBottles` INT NOT NULL DEFAULT 0,
 `tstamp` DATETIME NOT NULL,
 `productID` VARCHAR(45) NOT NULL,
 `partial_weight` DOUBLE NOT NULL DEFAULT 0,
 PRIMARY KEY (`inventoryID`),
 UNIQUE INDEX `inventoryID_UNIQUE` (`inventoryID` ASC));


14:00:15	PREPARE stmt FROM 'INSERT INTO `liquor_db`.`sales` (`transactionID`,`productID`,`ouncesSold`,`dateTime`) VALUES(?,?,?,?)'	OK	0.000 sec


'sales', 'CREATE TABLE `sales` (\n  `transactionID` int(11) NOT NULL AUTO_INCREMENT,\n  `ouncesSold` varchar(45) NOT NULL DEFAULT \'0\',\n  `productID` int(11) NOT NULL,\n  `dateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n  PRIMARY KEY (`transactionID`),\n  UNIQUE KEY `transactionID_UNIQUE` (`transactionID`)\n) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=big5'
