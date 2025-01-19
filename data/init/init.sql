-- Test Tábla, nyugodtan lehet törölni
CREATE TABLE test_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(500) NOT NULL,
    status INT DEFAULT 0
);

-- Test beillesztés
INSERT INTO test_info (name) VALUES('szuper kiraly');