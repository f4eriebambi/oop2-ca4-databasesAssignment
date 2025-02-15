drop database if exists `finance_tracker`;
create database `finance_tracker`;
use `finance_tracker`;

drop table if exists `expense`;
create table `expense`
(
    `expenseID`    INTEGER        NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(50)    NOT NULL,
    `category`     VARCHAR(50)    NOT NULL,
    `amount`       DECIMAL(10, 2) NOT NULL,
    `dateIncurred` DATE           NOT NULL,
    PRIMARY KEY (`expenseID`)
);

INSERT INTO expense
VALUES (1, "weekly shop", "groceries", 47.50, "2025-01-07"),
       (2, "gym membership", "fitness", 30.00, "2025-01-09");


drop table if exists `income`;
create table `income`
(
    `incomeID`    INTEGER        NOT NULL AUTO_INCREMENT,
    `title`      VARCHAR(50)    NOT NULL,
    `amount`     DECIMAL(10, 2) NOT NULL,
    `dateEarned` DATE           NOT NULL,
    PRIMARY KEY (`incomeID`)
);

INSERT INTO income
VALUES (1, "babysitting", 60.00, "2025-01-05"),
       (2, "bar work", 100.00, "2025-01-07");