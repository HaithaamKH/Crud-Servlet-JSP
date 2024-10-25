package com.servlet.crud.business;

import com.servlet.crud.dao.EmployeeDAO;
import com.servlet.crud.model.Employee;
import java.util.List;


public class EmployeeBusiness {
    private EmployeeDAO empDAO = new EmployeeDAO();
    
    public List<Employee> getAllEmps(){
        try{
            return this.empDAO.getAllEmployees();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Employee login(String username , String password){
        try{
            return this.empDAO.getUserByCredentials(username, password);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void deleteEmp(int id){
        try{
            this.empDAO.deleteEmployee(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Employee getEmpById(int id){
        try{
           return this.empDAO.getById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateEmployee(Employee emp ){
        try{
            this.empDAO.updateEmployee(emp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveEmployee(Employee employee) {
          try{
              this.empDAO.insertEmployee(employee);
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
}
