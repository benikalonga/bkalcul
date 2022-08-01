# bkalcul - Momentum
## by Beni Kalonga - Technical Assignment

[![Build Status](https://travis-ci.org/ElliottLandsborough/dog-ceo-api.svg?branch=master)](apk/BeniDogApi.apk)
[![CircleCI](https://circleci.com/gh/ElliottLandsborough/dog-ceo-api.svg?style=svg)](apk/BeniDogApi.apk)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/28e7bd35f2fe4d42a19aec5f705c5024)](app/src/main)


The link of the web site [Dog Api Demo](apk/BeniDogApi.apk) 👍

# Depencies and Libraries
[In the pom.xml file](pom.xml)

- [PrimeFaces](https://square.github.io/retrofit/) is a library layered atop of OkHttp that aims to simplify making REST-style Web service calls. You use annotations on a Java/Kotlin interface to describe the REST URLs that you wish to access, along with the HTTP operations to perform (e.g., GET, POST, PUT).
- [JSF ](https://bumptech.github.io/glide/) According to me, Glide is a fast and efficient image loading library for Android focused on smooth scrolling. Glide offers an easy to use API, a performant and extensible resource decoding pipeline and automatic resource pooling

# Architecture (MVC)
### Model - View - Controler
The MVC is a Model-View-Controler architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.
- Model: It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.
- View: It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.
- ViewModel: It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view who is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.

# Database
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

## Java classes

1. User (ManagedBean)
2. Admin (ManagdBean)
3. Record (ManagedBean)
3. SimpleCalculator (ManagedBean)
4. AdvancedCalculator (ManagedBean)

5. UserDAO Data access object for the user
6. Record DAO Data access object to get and handle access of data
8. Utils a static class providing some feature
9. Session a class that handle the session for the loggin 

10. Bkalculator a class that evaluate and solve all mathematic expressions
11. DBConnexion a class that handles the connectivity between the app and the MariaBD Database


## Features implemented
According to the requierement,

1. The database should contain a list of users with their passwords and an indicator if the user is an administrator or not : DONE
2. The basic calculation screen will contain 3 input fields : DONE. it does +, -, *, /, root, ^, pi, e
3. Advanced calculation screen containing one text input field : DONE. it solves complex mathematic expressions keeping the operators precedence 
4. Each calculation done must be recorded for audit purposes in the database. The following must be recorded: DONE
5. Provide an additional screen to allow an administrator to query the audit log using filters like username and from and to date to narrow the search results


//Bitbucket link 
Git -> https://bitbucket.org/benikalonga/bkalcul/src/main/
//Red hat

<-- Data in DBB -->
User{benikalonga, bkalcul)
Admin {benikalongadmin, bkalcul}
