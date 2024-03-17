DROP TABLE IF EXISTS customer;
CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);
INSERT INTO customer (name, age, city) VALUES ('John Doe', 30, 'New York'),
                                              ('Jane Doe', 25, 'Los Angeles'),('Alice Smith', 42, 'Chicago'),
                                           ('David Johnson', 40, 'Houston'),
                                           ('Margaret Miller', 28, 'Miami'),
                                           ('Elizabeth Jones', 35, 'Seattle');
