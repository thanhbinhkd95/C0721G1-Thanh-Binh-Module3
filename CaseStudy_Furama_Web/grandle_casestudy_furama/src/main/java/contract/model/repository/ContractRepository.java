package contract.model.repository;

import contract.model.bean.Contract;
import contract.model.bean.CustomerUseService;
import contract_detail.model.bean.AttachService;
import contract_detail.model.bean.ContractDetails;
import contract_detail.model.service.Impl.ContractDetailImpl;
import customers.model.bean.Customers;
import customers.model.service.Impl.CustomerServiceImpl;
import employee.model.bean.Employee;
import employee.model.service.Impl.EmployeeServiceImpl;
import services.model.bean.Service;
import services.model.service.Impl.ServiceServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository {
    BaseRepository baseRepository = new BaseRepository();
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();
    ServiceServiceImpl serviceService = new ServiceServiceImpl();
    private static final String INSERT_CONTRACT_SQL = "insert into contract" + "(contract_start_date,contract_end_date,contract_deposit,contract_total_money,employee_id,customer_id,service_id) values"
                                        + "(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_CONTRACT = "select * from contract";
    private static final String SELECT_CONTRACT = "select * from contract where contract_id =?";

    private static final String SELECT_CUSTOMER_USE_SERVICE = "select c.contract_id,cus.customer_id,cus.customer_name,c.contract_start_date,c.contract_end_date,att.attach_service_id,cd.quantity,att.attach_service_name\n" +
            "from customer cus join  contract c on cus.customer_id = c.customer_id\n" +
            "join contract_detail cd on c.contract_id = cd.contract_id\n" +
            "join attach_service att on att.attach_service_id = cd.attach_service_id;";

    public void insertContract(Contract contract){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_CONTRACT_SQL);
            statement.setString(1,contract.getContractStartDate());
            statement.setString(2,contract.getContractEndDate());
            statement.setDouble(3,contract.getDeposit());
            statement.setDouble(4,contract.getTotalMoney());
            statement.setInt(5,contract.getEmployeeId().getEmployeeId());
            statement.setInt(6,contract.getCustomersId().getCustomerId());
            statement.setInt(7,contract.getServiceId().getServiceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public Contract selectContract(int id){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        Contract contract = null;
        try {
            statement = connection.prepareStatement(SELECT_CONTRACT);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String start = rs.getString("contract_start_date");
                String end = rs.getString("contract_end_date");
                double deposit = rs.getDouble("contract_deposit");
                double totalMoney = rs.getDouble("contract_total_money");
                Employee employee = employeeService.selectEmployee(rs.getInt("employee_id"));
                Customers customers = customerService.selectCustomer(rs.getInt("customer_id"));
                Service service = serviceService.selectService(rs.getInt("service_id"));
                contract = new Contract(id,start,end,deposit,totalMoney,employee,customers,service);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return contract;
    }

    public List<Contract> selectAllContract(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<Contract> contract = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_CONTRACT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("contract_id");
                String start = rs.getString("contract_start_date");
                String end = rs.getString("contract_end_date");
                double deposit = rs.getDouble("contract_deposit");
                double totalMoney = rs.getDouble("contract_total_money");
                Employee employee = employeeService.selectEmployee(rs.getInt("employee_id"));
                Customers customers = customerService.selectCustomer(rs.getInt("customer_id"));
                Service service = serviceService.selectService(rs.getInt("service_id"));
                contract.add(new Contract(id,start,end,deposit,totalMoney,employee,customers,service));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return contract;
    }

    public List<CustomerUseService> selectAllCustomersUseService(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<CustomerUseService> customerUseServices = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_CUSTOMER_USE_SERVICE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int cusId = rs.getInt("customer_id");
                String cusName = rs.getString("customer_name");
                int contractId = rs.getInt("contract_id");
                String startDate = rs.getString("contract_start_date");
                String endDate = rs.getString("contract_end_date");
                int quantity = rs.getInt("quantity");
                String attachName = rs.getString("attach_service_name");
                int attachID = rs.getInt("attach_service_id");
                CustomerUseService cus = new CustomerUseService(cusId,cusName,contractId,startDate,endDate,quantity,attachID,attachName);
                customerUseServices.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerUseServices;
    }
}
