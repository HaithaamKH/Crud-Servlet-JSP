<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.servlet.crud.model.*"%>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    Employee loggedEmp = (Employee) session.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    </head>
    <body>
        <div class="center">
            <h1>Hello, <%= loggedEmp != null ? loggedEmp.getName() : "Guest" %></h1>
            
            <div class="button-container">
                <form method="post" action="EmpServlet" style="display:inline;">
                    <input type="hidden" name="action" value="logout">
                    <input type="submit" value="Logout" title="Logout">
                </form>
                <form method="post" action="EmpServlet" style="display:inline;">
                    <input type="hidden" name="action" value="showAddForm">
                    <input type="hidden" name="operation" value="add">
                    <input type="submit" value="Add New" title="Add New Employee">
                </form>
            </div>

            <!-- Employee list table -->
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Username</th>
                        <th>Salary</th>
                        <th>Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (employees != null) { %>
                        <% for (Employee emp : employees) { %>
                            <tr>
                                <td><%= emp.getId() %></td>
                                <td><%= emp.getName() %></td>
                                <td><%= emp.getUsername() %></td>
                                <td><%= emp.getRole() %></td>
                                <td><%= emp.getCountry() %></td>
                                <td>
                                   <form action="EmpServlet" method="post" style="display:inline;">
                                       <input type="hidden" name="action" value="delete">
                                       <input type="hidden" name="id" value="<%= emp.getId() %>">
                                       <input type="submit" value="Delete">
                                  </form>
                                  <form action="EmpServlet" method="post" style="display:inline;">
                                       <input type="hidden" name="action" value="showEditForm">
                                       <input type="hidden" name="id" value="<%= emp.getId() %>">
                                       <input type="hidden" name="operation" value="edit">
                                       <input type="submit" value="Edit">
                                  </form>
                                </td>
                            </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="6">No employees found.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
