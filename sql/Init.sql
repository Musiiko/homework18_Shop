CREATE TABLE Products(
product_id INT PRIMARY KEY AUTO_INCREMENT,
price FLOAT,
name VARCHAR(50),
description VARCHAR(50)
);

CREATE TABLE Orders(
order_id INT PRIMARY KEY AUTO_INCREMENT,
order_date DATE,
order_number VARCHAR(50)
);

CREATE TABLE Product_Order(
FK_Order INT,
FK_Product INT,
FOREIGN KEY (FK_Order) REFERENCES Orders(order_id),
FOREIGN KEY (FK_Product) REFERENCES Products(product_id),
UNIQUE KEY (FK_Order, FK_Product)
);