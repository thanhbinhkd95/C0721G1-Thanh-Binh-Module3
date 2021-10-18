package employee.model.repository;

import employee.model.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    BaseRepository baseRepository = new BaseRepository();
    DivisionRepository divisionRepository = new DivisionRepository();
    EducationDegreeRepository educationDegreeRepository = new EducationDegreeRepository();
    PositonRepository positonRepository = new PositonRepository();
    UserRepository userRepository = new UserRepository();
    private static final String SELECT_ALL_EMPLOYEE = "select * from employee";

    private static final String INSERT_EMPLOYEE_SQL = "insert into employee" + "(employee_name,employee_birthday,employee_id_card,employee_salary,employee_phone,employee_email,employee_address,position_id,education_degree_id,division_id,username) values"
            + "(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_EMPLOYEE = "delete from employee where employee_id =?";

    private static final String UPDATE_EMPLOYEE = "update employee set employee_name=?,employee_birthday=?,employee_id_card=?,employee_salary=?,employee_phone=?,employee_email=?,employee_address=?,position_id=?,education_degree_id=?,division_id=?,username=? where employee_id=?";

    private static final String SELECT_BY_EMPLOYEE_ID = "select * from employee where employee_id =?";

    private static final String SEARCH_BY_NAME_EMPLOYEE = "select * from employee where employee_name like ?";


    public List<Employee> selectAllEmployee() {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<Employee> employees = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String name = rs.getString("employee_name");
                String birthday = rs.getString("employee_birthday");
                String idCard = rs.getString("employee_id_card");
                double salary = rs.getDouble("employee_salary");
                String phone = rs.getString("employee_phone");
                String email = rs.getString("employee_email");
                String address = rs.getString("employee_address");
                Position idPos = positonRepository.selectPosition(rs.getInt("position_id"));
                EducationDegree idEdu = educationDegreeRepository.selectEducation(rs.getInt("education_degree_id"));
                Division idDivi = divisionRepository.selectDivision(rs.getInt("division_id"));
                User userName = userRepository.selectUser(rs.getString("username"));

                employees.add(new Employee(id, name, birthday, idCard, salary, phone, email, address, idPos, idEdu, idDivi, userName));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    public void insertEmployee(Employee employee) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeBirthday());
            statement.setString(3, employee.getEmployeeIdCard());
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setString(5, employee.getEmployeePhone());
            statement.setString(6, employee.getEmployeeEmail());
            statement.setString(7, employee.getEmployeeAddress());
            statement.setInt(8, employee.getPositionId().getPositionId());
            statement.setInt(9, employee.getEducationDegreeId().getEducationDegreeId());
            statement.setInt(10, employee.getDivisionId().getDivisionId());
            statement.setString(11, employee.getUserName().getUserName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteEmployee(int id) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        boolean check = false;
        try {
            statement = connection.prepareStatement(DELETE_EMPLOYEE);
            statement.setInt(1, id);
            check = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    public boolean editEmployee(Employee employee) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        boolean check = false;

        try {
            statement = connection.prepareStatement(UPDATE_EMPLOYEE);
            statement.setString(1, employee.getEmployeeName());
            statement.setString(2, employee.getEmployeeBirthday());
            statement.setString(3, employee.getEmployeeIdCard());
            statement.setDouble(4, employee.getEmployeeSalary());
            statement.setString(5, employee.getEmployeePhone());
            statement.setString(6, employee.getEmployeeEmail());
            statement.setString(7, employee.getEmployeeAddress());
            statement.setInt(8, employee.getPositionId().getPositionId());
            statement.setInt(9, employee.getEducationDegreeId().getEducationDegreeId());
            statement.setInt(10, employee.getDivisionId().getDivisionId());
            statement.setString(11, employee.getUserName().getUserName());
            statement.setInt(12,employee.getEmployeeId());
            check = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return check;
    }

    public Employee selectEmployee(int id) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        Employee employee = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_EMPLOYEE_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("employee_name");
                String birthday = rs.getString("employee_birthday");
                String idCard = rs.getString("employee_id_card");
                double salary = rs.getDouble("employee_salary");
                String phone = rs.getString("employee_phone");
                String email = rs.getString("employee_email");
                String address = rs.getString("employee_address");
                Position positionID = positonRepository.selectPosition(rs.getInt("position_id"));
                EducationDegree educationDegreeId = educationDegreeRepository.selectEducation(rs.getInt("education_degree_id"));
                Division divisionId = divisionRepository.selectDivision(rs.getInt("division_id"));
                User userName = userRepository.selectUser(rs.getString("username"));
                employee = new Employee(id, name, birthday, idCard, salary, phone, email, address, positionID, educationDegreeId, divisionId, userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public List<Employee> searchEmployee(String name) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SEARCH_BY_NAME_EMPLOYEE);
            statement.setString(1, "%"+name+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String nameEml = rs.getString("employee_name");
                String birthday = rs.getString("employee_birthday");
                String idCard = rs.getString("employee_id_card");
                double salary = rs.getDouble("employee_salary");
                String phone = rs.getString("employee_phone");
                String email = rs.getString("employee_email");
                String address = rs.getString("employee_address");
                Position positionID = positonRepository.selectPosition(rs.getInt("position_id"));
                EducationDegree educationDegreeId = educationDegreeRepository.selectEducation(rs.getInt("education_degree_id"));
                Division divisionId = divisionRepository.selectDivision(rs.getInt("division_id"));
                User userName = userRepository.selectUser(rs.getString("username"));
               Employee employee =new Employee(id, nameEml, birthday, idCard, salary, phone, email, address, positionID, educationDegreeId, divisionId, userName);
               employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeList;
    }

}
