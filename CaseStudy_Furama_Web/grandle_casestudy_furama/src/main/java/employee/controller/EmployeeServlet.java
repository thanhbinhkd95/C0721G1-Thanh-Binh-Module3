package employee.controller;

import employee.model.bean.*;
import employee.model.service.IEmployeeService;
import employee.model.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet",urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {
    IEmployeeService employeeService = new EmployeeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createEmployee(request,response);
                break;
            case "edit" :
                editEmployee(request,response);
                break;
            case "delete" :
                deleteEmployee(request,response);
                break;
            case "search" :
                searchEmployee(request,response);
                break;

        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                showCreateEmployeeForm(request,response);
                break;
            case "edit" :
                showEditEmployeeForm(request,response);
            default:
                listEmployee(request,response);
        }
    }

    private void showEditEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.selectEmployee(id);
        request.setAttribute("employee",employee);
        List<Division> divisionList = employeeService.selectAllDivision();
        List<EducationDegree> educationDegreeList = employeeService.selectAllEducation();
        List<Position> positionList = employeeService.selectAllPosition();
        List<User> userList = employeeService.selectAllUser();

        request.setAttribute("divisionList",divisionList);
        request.setAttribute("educationDegreeList",educationDegreeList);
        request.setAttribute("positionList",positionList);
        request.setAttribute("userList",userList);
        try {
            request.getRequestDispatcher("view/employee/edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        List<Division> listDivion = employeeService.selectAllDivision();
        List<EducationDegree> educationDegreeList = employeeService.selectAllEducation();
        List<Position> positions = employeeService.selectAllPosition();
        List<User> userList = employeeService.selectAllUser();
        request.setAttribute("divitionList",listDivion);
        request.setAttribute("eduList",educationDegreeList);
        request.setAttribute("positionList",positions);
        request.setAttribute("userList",userList);

        try {
            request.getRequestDispatcher("view/employee/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = employeeService.selectAllEmployee();
        request.setAttribute("employeeList",employeeList);
        try {
            request.getRequestDispatcher("view/employee/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void createEmployee(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("employee_name");
        String birthday = request.getParameter("employee_birthday");
        String idCard = request.getParameter("employee_id_card");
        double salary = Double.parseDouble(request.getParameter("employee_salary"));
        String phone = request.getParameter("employee_phone");
        String email = request.getParameter("employee_email");
        String address = request.getParameter("employee_address");
        Position positionID = employeeService.selectPosition(Integer.parseInt(request.getParameter("position_id")));
        EducationDegree educationDegreeId = employeeService.selectEducation(Integer.parseInt(request.getParameter("education_degree_id")));
        Division divisionId = employeeService.selectDivision(Integer.parseInt(request.getParameter("division_id")));
        User userName = employeeService.selectUser(request.getParameter("username"));
        Employee employee = new Employee(name,birthday,idCard,salary,phone,email,address,positionID,educationDegreeId,divisionId,userName);
        employeeService.insertEmployee(employee);

        try {
            response.sendRedirect("/employee");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void editEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("employee_id"));
        String name = request.getParameter("employee_name");
        String birthday = request.getParameter("employee_birthday");
        String idCard = request.getParameter("employee_id_card");
        double salary = Double.parseDouble(request.getParameter("employee_salary"));
        String phone = request.getParameter("employee_phone");
        String email = request.getParameter("employee_email");
        String address = request.getParameter("employee_address");
        Position positionID = employeeService.selectPosition(Integer.parseInt(request.getParameter("position_id")));
        EducationDegree educationDegreeId = employeeService.selectEducation(Integer.parseInt(request.getParameter("education_degree_id")));
        Division divisionId = employeeService.selectDivision(Integer.parseInt(request.getParameter("division_id")));
        User userName = employeeService.selectUser(request.getParameter("username"));
        Employee employee = new Employee(id,name,birthday,idCard,salary,phone,email,address,positionID,educationDegreeId,divisionId,userName);
        employeeService.editEmployee(employee);

        try {
            response.sendRedirect("/employee");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Employee> employeeList = employeeService.searchEmployee(name);
        request.setAttribute("employeeList",employeeList);
        try {
            request.getRequestDispatcher("view/employee/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("employee_id"));
        employeeService.deleteEmployee(id);
        try {
            response.sendRedirect("/employee");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
