
package com.servlet.crud.model;

public class Employee {
    
    private int id;
    private String name , username ,password , role , country;

    public Employee() {
    }

    public Employee(int id, String name, String username, String password, String role, String country) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", role=" + role + ", country=" + country + '}';
    }
    
    
}
