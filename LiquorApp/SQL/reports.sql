--select from product and sales table
-- second comment
select p.brand, s.ouncesSold, s.dateTime
from product p, sales s
where p.productID=s.productID;
