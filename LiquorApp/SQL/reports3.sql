

select p.brand, sum(s.ouncesSold), count(p.brand), p.fizz
from product p, sales s
where p.productID=s.productID
group by p.brand;