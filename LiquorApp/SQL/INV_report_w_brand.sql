SELECT i.productID, p.brand, fullBottles, partial_weight
FROM liquor_db.inventory as i join liquor_db.product as p
ON i.productID=p.productID
ORDER BY p.productID;