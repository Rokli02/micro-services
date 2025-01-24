CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    rights INT
);

CREATE TABLE Product (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description VARCHAR(255) NOT NULL,
     base_price INT NOT NULL,
     discount FLOAT DEFAULT 0,
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
     is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE Order (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     product_id BIGINT NOT NULL,
     user_id BIGINT NOT NULL,
     quantity INT NOT NULL,
     price INT NOT NULL,
     group VARCHAR(255),
     status INT NOT NULL,
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (product_id) REFERENCES Product(id),
     FOREIGN KEY (user_id) REFERENCES User(id)
);