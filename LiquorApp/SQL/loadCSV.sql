LOAD DATA LOCAL INFILE '/Users/ryan/Documents/Projects/LiquorApp/misc/sales.csv' INTO TABLE  `liquor_db`.`sales` 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
;

