package employee.model.service.Impl;

import employee.model.bean.*;
import employee.model.repository.*;
import employee.model.service.IEmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    DivisionRepository divisionRepository = new DivisionRepository();
    EducationDegreeRepository educationDegreeRepository = new EducationDegreeRepository();
    UserRepository userRepository = new UserRepository();
    PositonRepository positonRepository = new PositonRepository();

    @Override
    public List<Employee> selectAllEmployee() {
        return employeeRepository.selectAllEmployee();
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeRepository.insertEmployee(employee);
    }

    @Override
    public Position selectPosition(int id) {
        return positonRepository.selectPosition(id);
    }

    @Override
    public User selectUser(String name) {
        return userRepository.selectUser(name);
    }

    @Override
    public EducationDegree selectEducation(int id) {
        return educationDegreeRepository.selectEducation(id);
    }

    @Override
    public Division selectDivision(int id) {
        return divisionRepository.selectDivision(id);
    }

    @Override
    public List<Employee> searchEmployee(String name) {
        return employeeRepository.searchEmployee(name);
    }

    @Override
    public Employee selectEmployee(int id) {
        return employeeRepository.selectEmployee(id);
    }

    @Override
    public boolean editEmployee(Employee employee) {
        return employeeRepository.editEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    @Override
    public List<Division> selectAllDivision() {
        return divisionRepository.selectAllDivision();
    }

    @Override
    public List<EducationDegree> selectAllEducation() {
        return educationDegreeRepository.selectAllEducation();
    }

    @Override
    public List<Position> selectAllPosition() {
        return positonRepository.selectAllPosition();
    }

    @Override
    public List<User> selectAllUser() {
        return userRepository.selecAlltUser();
    }
}
