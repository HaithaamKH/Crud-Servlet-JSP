<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.servlet.crud.model.*"%>
<%@page import="com.servlet.crud.servlets.*"%>
<%
    Employee emp = (Employee) request.getAttribute("employee");
    Employee sessionEmp = (Employee) session.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
    <center>
        <h1>Hello, <%= sessionEmp != null ? sessionEmp.getName() : "Guest" %></h1><br>

        <!-- Logout Button -->
        <form method="post" action="EmpServlet" style="display:inline;">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" title="Logout">
        </form>

        <!-- Back Button -->
        <form method="post" action="EmpServlet" style="display:inline;">
            <input type="hidden" name="action" value="back">
            <input type="submit" value="Back" 
                   <%= sessionEmp != null && "user".equals(sessionEmp.getRole()) ? "disabled" : "" %>
                   title="Back To Home page">
        </form>

        <!-- Employee Form -->
        <form method="post" action="EmpServlet">
            <input type="hidden" name="id" value="<%= emp != null ? emp.getId() : "" %>">
            Name: <input type="text" name="name" 
                         value="<%= emp.getName() != null ? emp.getName() : "" %>"><br>
            Username: <input type="text" name="username"
                             value="<%= emp.getUsername() != null ? emp.getUsername() : "" %>"><br>
            Password: <input type="password" name="password" 
                             value="<%= emp.getPassword() != null ? emp.getPassword() : "" %>"><br>
            Role: <input type="text" name="role" 
                         value="<%= emp.getRole() != null ? emp.getRole() : "" %>"><br>
            Country: <input type="text" name="country" 
                            value="<%= emp.getCountry()!= null ? emp.getCountry() : "" %>"><br>
            <input type="hidden" name="action" value="operation">
            <input type="submit" value="Save" title="Save">
        </form>
    </center>
</body>
</html>
