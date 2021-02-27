用户信息表
```
CREATE TABLE user_info  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `insert_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
);

```

商品信息表
```
CREATE TABLE product_info  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NULL,
  `price` decimal(10, 2) NULL,
  `insert_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
```

订单表
```
CREATE TABLE order_info  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL,
  `product_id` int(0) NULL,
  `qty` int(0) NULL,
  `insert_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
```