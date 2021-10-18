package employee.model.service;


import employee.model.bean.*;

import java.util.List;

public interface IEmployeeService {
     List<Employee> selectAllEmployee();
     void insertEmployee(Employee employee);
     Position selectPosition(int id);
   User selectUser(String name);
     EducationDegree selectEducation(int id);
     Division selectDivision(int id);
    List<Employee> searchEmployee(String name);
    Employee selectEmployee(int id);
     boolean editEmployee(Employee employee);
     boolean deleteEmployee(int id);
    public List<Division> selectAllDivision();
    public List<EducationDegree> selectAllEducation();
    public List<Position> selectAllPosition();
    public List<User> selectAllUser();
}
