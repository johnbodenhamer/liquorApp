— insert values into inventory 
insert into inventory (productID,tstamp, fullBottles, partialWeight) 
values(3,now() - Interval 48 hour,1,706);
insert into inventory (productID, tstamp, fullBottles, partialWeight) 
values(3,now(),0,500)

—insert values into product
insert into product (brand, cost, fullWeight) values ('14 hands', 15, 1263);
insert into product (brand, cost, fullWeight) values ('Beefeater', 25, 2200);
insert into product (brand, cost, fullWeight) values ('Avion', 30, 2200);
insert into product (brand, cost, fullWeight) values ('Bacardi', 20, 2200);
insert into product (brand, cost, fullWeight) values ('Goose', 20, 2200);
insert into product (brand, cost, fullWeight) values ('Jack', 25, 2200);
insert into product (brand, cost, fullWeight) values ('Makers', 25, 2200);
insert into product (brand, cost, fullWeight) values ('Makers 46', 35, 2200);

— insert into sales
insert into sales (productID, gramsSold, saleAmount) value (3,34,8)

