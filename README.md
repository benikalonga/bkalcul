# bkalcul - Momentum
## by Beni Kalonga - Technical Assignment

[![Build Status](https://travis-ci.org/ElliottLandsborough/dog-ceo-api.svg?branch=master)](src/)
[![CircleCI](https://circleci.com/gh/ElliottLandsborough/dog-ceo-api.svg?style=svg)](/WebContent)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/28e7bd35f2fe4d42a19aec5f705c5024)](src/)


The link to the web site : [BKalcul - Momentum](https://bkalcul.herokuapp.com/) 👍

The link to the git repository [BKalcul Bit](https://bitbucket.org/benikalonga/bkalcul/src/master/) 👍


### User {username:benikalonga, password:bkalcul)
### Admin (username:admin, password:admin)

# Depencies and Libraries
[In the pom.xml file](pom.xml)

- [PrimeFaces](https://www.primefaces.org/) is a popular open source framework for JavaServer Faces featuring over 100 components, touch optimized mobilekit, client side validation, theme engine and more.

- [JSF ](https://www.tutorialspoint.com/jsf/index.htm) Java Server Faces (JSF) is a Java-based web application framework intended to simplify development integration of web-based user interfaces. JavaServer Faces is a standardized display technology, which was formalized in a specification through the Java Community Process.

# Architecture (MVC)
### Model - View - Controler
The MVC is an architectural pattern consisting of three parts: Model, View, Controller. Model: Handles data logic. View: It displays the information from the model to the user. Controller: It controls the data flow into a model object and updates the view whenever data changes

- Model: Handles data logic.
- View: It displays the information from the model to the user.
- Controller: It controls the data flow into a model object and updates the view whenever data changes.

# Server Apache Tomcat 9.5.0

# Database MariaDB
  Database name -> bkalcul
Table (2)
	user {username(primary key, varchar(50), password(varchar(20), notnull), isAdmin(boolean))
	records {id(primary key, int(20), auto-implement), calcRequest(text, notnull), answer(text, notnull), 
		timeCalc(int(20)), dateInserted(datatime, default:current-time-stamp)}

## SQL Requests

CREATE DATABASE 'bkalcul'
CREATE TABLE 'bkalcul'.'user' ('username' VARCHAR(50) NOT NULL , 'password' VARCHAR(20) NOT NULL ,
			'isAdmin' BOOLEAN NOT NULL DEFAULT FALSE , PRIMARY KEY ('username')) ENGINE = InnoDB;
CREATE TABLE 'bkalcul'.'record' ('id' INT(20) NOT NULL AUTO_INCREMENT , 'calcRequest' TEXT NOT NULL , 'answer' TEXT NOT NULL , 'calcTime' INT(20) NOT NULL , 'dateInserted' DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY ('id')) ENGINE = InnoDB;

## GUI Interface

1. Login : index.xhtml
2. basicKalcul : basic.xhtml
3. advancedKalcul : advanced.xhtml
4. adminInterface : admin.xhtml
5. About : about.xhtml

## Java classes

1. User (ManagedBean)
2. Admin (ManagdBean)
3. Record (ManagedBean)
3. SimpleCalculator (ManagedBean)
4. AdvancedCalculator (ManagedBean)

5. UserDAO Data access object for the user
6. RecordDAO Data access object to get and handle access of data
8. Utils a static class providing some feature
9. Session a class that handle the session for the loggin 

10. Bkalculator a class that evaluate and solve all mathematic expressions
11. DBConnexion a class that handles the connectivity between the app and the MariaBD Database
12. Router a class that manages the routing of some URLs;


## Features implemented
According to the requierement,

1. The database should contain a list of users with their passwords and an indicator if the user is an administrator or not : DONE
2. The basic calculation screen will contain 3 input fields : DONE. it does +, -, *, /, root, ^, pi, e
3. Advanced calculation screen containing one text input field : DONE. it solves complex mathematic expressions keeping the operators precedence 
4. Each calculation done must be recorded for audit purposes in the database. The following must be recorded: DONE
5. Provide an additional screen to allow an administrator to query the audit log using filters like username and from and to date to narrow the search results
