# Tour-And-Travel-DataBase-Management-System

The Tours and Travel Management System is a web based application. The objective of this project is to develop a system that automates the processes. 

Backend- Java, JDBC, Servlets, JSP, MySQL 

Frontend- HTML, CSS

Project-Video-Link :  https://drive.google.com/file/d/10VC1Pi-mv2V6w3ov0NBDwd76NT-EJVzA/view?usp=sharing


# Project Overview:-

# Home Page:-
![1](https://user-images.githubusercontent.com/61588604/120923256-37c1e400-c6eb-11eb-9c22-bf24e1a530c7.PNG)

**------------------------------------**

# Admin Things :-

**1) First of all, admin has to login with admin credientials.**
![2](https://user-images.githubusercontent.com/61588604/120923385-dfd7ad00-c6eb-11eb-8729-1ea897ed01fc.PNG)
-------------------------------

**2) Admin can do many thing, Admin can add new tour package.**

In order to add new tour package, admin add the tour data like package id, place name, place description, stay amount, food amount, travel type(bus, train, airplane), place image....

![3](https://user-images.githubusercontent.com/61588604/120923494-72784c00-c6ec-11eb-86b1-42b2ff7a6d35.PNG)
---------------------------------

**3*) Admin can view all the packages, which are offered by company.**

![4](https://user-images.githubusercontent.com/61588604/120923522-a8b5cb80-c6ec-11eb-931b-d64310714d90.PNG)
----------------------------------

**4)Admin can see all the details about USERS( User Information)**

![6](https://user-images.githubusercontent.com/61588604/120923564-e0247800-c6ec-11eb-9078-e697c8bed0fb.PNG)
----------------------------------

**5) Admin can view all the details about BOOKING DETAILS.**

![5](https://user-images.githubusercontent.com/61588604/120923589-1104ad00-c6ed-11eb-9d85-beb0582f7173.PNG)

**-----------------------------------------------------------------------**

**-----------------------------------------------------------------------**

# User Things :- 

**1) First of user has to register, if he is visiting website as a first time otherwise he/she has to login.**

![7](https://user-images.githubusercontent.com/61588604/120923664-6476fb00-c6ed-11eb-8444-de2658a7d637.PNG)
---------------------------------

**2) Now user can view all the packages. User can search for a perticular package (place).**

![8](https://user-images.githubusercontent.com/61588604/120923718-9e480180-c6ed-11eb-8abb-9c6d7468224c.PNG)
-----------------------------------

**3) After selecting the package user has to enter the important information like travel type,  number of adults, number of children....**

![9](https://user-images.githubusercontent.com/61588604/120923753-ca638280-c6ed-11eb-9b8e-93f8c597039c.PNG)
------------------------------------

**4) Now our system automatically calculates the total fare and show to user, so user can enter his/her atm card details and can book the package.**

![10](https://user-images.githubusercontent.com/61588604/120923794-03035c00-c6ee-11eb-90f4-47f8d87dbe8b.PNG)

**---------------------------------------------------------------------------------**


# About Database:-

**MySQL Database** is used for this application.

**==>> Three tables are used. One for storing PACKAGE details, second used for USER details and third is used for storing Booking details.**

**==>> JAVA Servlets (& JSP) is used for server side technology. Java Database Connectivity (JDBC) is used for connectiong the JAVA to MySQL database.**



# Connectivity with Database

Whenever we have to connect out Java application with database, we have to add **jar** file to our project.

Then we have to follow below steps:-

Step 1) Import the package {  import java.sql.* ;  }

Step 2) Load & Register the driver {  Class.forName("com.mysql.jdbc.Driver");  }

Step 3) Establish the Connection  {  Connection con = DriverManager.getConnection("url", "username", "password");  }

Step 4) Create the Statement 

{ For fetching the data :   Statement st = con.createStatement();  }

{ For inseting & updating the data :  PrepareStatement st = con.prepareStatement(query); //query=> meand sql query }

Step 5) Execute the Query  {  ResultSet rs = st.executeQuery(query) ; //query=> meand sql query  }

Step 6) Process the Results

Step 7) Close the Connection

------This is how, the JDBC concept is used in our project.



**<<====================================  END  =======================================>>**


