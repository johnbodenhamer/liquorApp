-- select products sold
select p.brand, s.ouncesSold, s.dateTime
from product p, sales s
where p.productID=s.productID;
