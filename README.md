# CRUD Application with Java Servlets and JSP

This is a simple CRUD (Create, Read, Update, Delete) web application built using Java Servlets and JSP. The application uses MySQL as its database and runs on a Tomcat server. It features a login system with role-based access for employees, allowing different functionalities based on the user's role.

## Features
- **User Authentication**: Employees can log in using their username and password, verified against the database.
- **Role-Based Access Control**:
  - **Admin**: Has full CRUD access to employee data (create, edit, view, delete).
  - **User**: Can only view and edit their own information.
- **CRUD Operations**: Admins can manage employee records, including creating new entries, editing, viewing, and deleting.

## Technologies Used
- **Java Servlets**: For handling backend logic and requests.
- **JSP**: For the frontend and rendering views.
- **MySQL**: To store employee data.
- **Tomcat Server**: As the web server.

## Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/HaithaamKH/Crud-Servlet-JSP.git
