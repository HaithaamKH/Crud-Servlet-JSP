/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet.crud.dao;

import com.servlet.crud.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String username = "student";
        String password = "student";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
    
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name = ?, username = ?, password = ?, role = ?, country = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, emp.getName());
            stat.setString(2, emp.getUsername());
            stat.setString(3, emp.getPassword());
            stat.setString(4, emp.getRole());
            stat.setString(5, emp.getCountry());
            stat.setInt(6, emp.getId());
            stat.executeUpdate();
            System.out.println("Update Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, id);
            stat.executeUpdate();
            System.out.println("Employee with id: " + id + " deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employee(id, name, username, password, role, country) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setInt(1, getMaxId());
            stat.setString(2, emp.getName());
            stat.setString(3, emp.getUsername());
            stat.setString(4, emp.getPassword());
            stat.setString(5, emp.getRole());
            stat.setString(6, emp.getCountry());
            stat.executeUpdate();
            System.out.println("Employee added: " + emp.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Employee getById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("country"));
                }
            }
        }
        return employee;
    }
    
    public List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("country"));
                employees.add(employee);
            }
        }
        return employees;
    }
    
    public Employee getUserByCredentials(String username, String password) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        Employee employee = null;
        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, username);
            stat.setString(2, password);
            try (ResultSet res = stat.executeQuery()) {
                if (res.next()) {
                    employee = new Employee();
                    employee.setId(res.getInt("id"));
                    employee.setName(res.getString("name"));
                    employee.setUsername(username);
                    employee.setPassword(password);
                    employee.setRole(res.getString("role"));
                    employee.setCountry(res.getString("country"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    private int getMaxId() throws SQLException, ClassNotFoundException {
        int id = 0;
        for (Employee e : this.getAllEmployees()) {
            id = id > e.getId() ? id : e.getId();
        }
        return ++id;
    }
    
    public List<Employee> searchByNameAndCountry(String name, String country) {
        String sql = "SELECT * FROM employee WHERE name LIKE ? AND country LIKE ?";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(sql)) {

            stat.setString(1, "%" + name + "%");
            stat.setString(2, "%" + country + "%");

            try (ResultSet res = stat.executeQuery()) {
                while (res.next()) {
                    Employee emp = new Employee();
                    emp.setId(res.getInt("id"));
                    emp.setName(res.getString("name"));
                    emp.setUsername(res.getString("username"));
                    emp.setPassword(res.getString("password"));
                    emp.setRole(res.getString("role"));
                    emp.setCountry(res.getString("country"));

                    // Add each Employee object to the list
                    employees.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

}
