# formdbwrite
A simple form to MySQL table example in Java.

## Database
Create the database
`CREATE DATABASE formdbwrite`
Create the table
```
CREATE TABLE `formdbwrite`.`people` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(128) NULL,
  `firstName` VARCHAR(128) NULL,
  PRIMARY KEY (`id`));
```
