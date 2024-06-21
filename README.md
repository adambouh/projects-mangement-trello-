# Project Management Application
## Overview
This application is a project management tool similar to Trello. It helps manage projects, assign tasks to developers, and track progress. The application is built using Java servlets for the back end and JSP for the front end, and runs on an Apache Tomcat server.

## Features
User Authentication: Sign up, log in, and log out.
Project Management: Create, view, update, and delete projects.
Task Management: Create, assign, view, update, and delete tasks.
Developer Assignment: Assign tasks to developers.
Project and Task Tracking: View the status of projects and tasks.
Technologies Used
Front End: JSP (JavaServer Pages)
Back End: Java Servlets
Database: MySQL (or any preferred relational database)
Server: Apache Tomcat
Build Tool: Apache Maven
Version Control: Git
Setup and Installation
Prerequisites
Java Development Kit (JDK) 8 or higher
Apache Maven
Apache Tomcat 9 or higher
MySQL (or your preferred relational database)
Git
## Steps
Clone the Repository

cd project-management-app
Configure the Database

Create a new MySQL database.
Update the database connection details in the src/main/resources/database.properties file.
Build the Project


Deploy to Apache Tomcat

Copy the generated WAR file from target/project-management-app.war to the Tomcat webapps directory.
Start the Tomcat server.
Access the Application

Open your web browser and navigate to http://localhost:8080/project-management-app.
## Usage
Sign Up

Create a new account by signing up.
Log In

Log in with your credentials.
Create Projects

Navigate to the "Projects" section and create a new project.
Manage Tasks

Within a project, create tasks and assign them to developers.
Track Progress

View the status of your projects and tasks on the dashboard.
# Contributing
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Make your changes and commit them (git commit -m 'Add new feature').
Push to the branch (git push origin feature-branch).
Create a new Pull Request.
# License
This project is licensed under the MIT License. See the LICENSE file for details.

# Acknowledgements
This project was inspired by Trello.
Contact
For any questions or feedback, please reach out to adambouhni@gmail.com

