package com.servlet.crud.servlets;

import com.servlet.crud.business.EmployeeBusiness;
import com.servlet.crud.model.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet{
    private EmployeeBusiness empBuis = new EmployeeBusiness();
    private boolean user;

    
    
    @Override
    public void service(HttpServletRequest request , HttpServletResponse response){
        String action = request.getParameter("action");
        
        try{
            switch(action){
                case "login":
                    login(request, response);
                    break;
                case "logout":
                    logout(request , response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "showEditForm":
                    showEditForm(request, response);
                    break;
                    
                    case "showAddForm":
                    showAddForm(request, response);
                    break;
                     case "operation":
                    operationOnEmp(request, response);
                    break;
                case "back":
                    list(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath()+"/index.html");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       
        Employee loggedEmp = this.empBuis.login(username, password);
        if(loggedEmp !=null){
            HttpSession session = request.getSession(true);
            session.setAttribute("employee", loggedEmp);
            if(loggedEmp.getRole().equals("admin")){
                request.setAttribute("employees", this.empBuis.getAllEmps());
                this.setUser(false);
                request.getRequestDispatcher("/protected/admin.jsp")
                        .forward(request, response);
            }
            else{
                request.setAttribute("employee", loggedEmp);
                this.setUser(true);
                request.getRequestDispatcher("/protected/form.jsp")
                        .forward(request, response);
                
            }
        }else{
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
            response.sendRedirect(request.getContextPath()+"/index.html");
        }else{
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.empBuis.deleteEmp(id);
        request.setAttribute("employees", this.empBuis.getAllEmps());
        request.getRequestDispatcher("/protected/admin.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.empBuis.getEmpById(id);
        String operation = request.getParameter("operation");
        if(operation.equals("edit")){
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("protected/form.jsp").
                forward(request, response);
            return;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {
        request.setAttribute("employees", this.empBuis.getAllEmps());
        request.getRequestDispatcher("/protected/admin.jsp")
                .forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ServletException, IOException {
        
        request.setAttribute("employee", new Employee());
        request.getRequestDispatcher("/protected/form.jsp")
                .forward(request, response);
    }

    private void operationOnEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        int id = Integer.parseInt(request.getParameter("id"));
        if (id>0){
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(request.getParameter("name"));
            employee.setUsername(request.getParameter("username"));
            employee.setPassword(request.getParameter("password"));
            employee.setRole(request.getParameter("role"));
            employee.setCountry(request.getParameter("country"));
            this.empBuis.updateEmployee(employee);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("protected/form.jsp")
                    .forward(request, response);
        }else if(id==0){
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(request.getParameter("name"));
            employee.setUsername(request.getParameter("username"));
            employee.setPassword(request.getParameter("password"));
            employee.setRole(request.getParameter("role"));
            employee.setCountry(request.getParameter("country"));
            this.empBuis.saveEmployee(employee);
            this.list(request, response);
        }
        //this.list(request, response);
    }
    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
    
}
