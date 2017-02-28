# formdbwrite
A simple form to MySQL table example in Java.

## Components
### Form
```
index.html
```
This is the form that lets you enter a first and last name to be submitted to the servlet to be written to the database.
### Servlet
```
org.providence.formdb.FormDbWrite
```
This is the servlet that takes the form post and executes an INSERT statement to add the person to the database.
## Database
Create the database
```
CREATE DATABASE formdbwrite
```
Create the table
```
CREATE TABLE `formdbwrite`.`people` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(128) NULL,
  `firstName` VARCHAR(128) NULL,
  PRIMARY KEY (`id`));
```
Grant test user on DB so the connection can be made:
```
mysql> grant all on formdbwrite.* to 'test'@'%' identified by 'test';
Query OK, 0 rows affected, 1 warning (0.03 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.03 sec)
```

