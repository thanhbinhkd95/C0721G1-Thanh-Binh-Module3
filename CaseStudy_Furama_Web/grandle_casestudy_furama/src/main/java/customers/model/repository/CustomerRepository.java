package customers.model.repository;

import customers.model.bean.CustomerType;
import contract.model.bean.CustomerUseService;
import customers.model.bean.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    BaseRepository baseRepository = new BaseRepository();
    CustomerTypeRepository customerTypeRepository = new CustomerTypeRepository();
    private static final String SELECT_ALL_CUSTOMERS = "select * from customer";
    private static final String INSERT_CUSTOMERS_SQL = "insert into customer" + "(customer_type_id,customer_name,customer_birthday,customer_gender,customer_id_card,customer_phone,customer_email,customer_address,customer_code) values" +
            "(?,?,?,?,?,?,?,?,?);";
    private static final String DELETE_CUSTOMER = "delete from customer where customer_id = ?";
    private static final String SELECT_CUSTOMER = "select * from customer where customer_id = ?";
    private static final String SEARCH_NAME_CUSTOMER = "select * from customer where customer_name like ?";

    private static final String UPDATE_CUSTOMER = "update customer set customer_type_id =?,customer_name =?,customer_birthday=?,customer_gender=?,customer_id_card=?,customer_phone=?,customer_email=?,customer_address=?,customer_code=? where customer_id=?";


    public List<Customers> selectAllCustomers() {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        List<Customers> customers = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("customer_id");
                CustomerType id_type = customerTypeRepository.selectCustomerType(rs.getInt("customer_type_id"));
                String name = rs.getString("customer_name");
                String dayOfBirth = rs.getString("customer_birthday");
                String gender = rs.getString("customer_gender");
                String idCard = rs.getString("customer_id_card");
                String phone = rs.getString("customer_phone");
                String email = rs.getString("customer_email");
                String address = rs.getString("customer_address");
                String cusCode = rs.getString("customer_code");
                customers.add(new Customers(id,id_type,name,dayOfBirth,gender,idCard,phone,email,address,cusCode));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }
    public boolean insertCustomer(Customers customers) throws SQLException {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        boolean check =false;

        try{
            preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL);
            preparedStatement.setInt(1,customers.getCustomerTypeId().getCustomerTypeId());
            preparedStatement.setString(2,customers.getCustomerName());
            preparedStatement.setString(3,customers.getCustomerBirthday());
            preparedStatement.setString(4,customers.getCustomerGender());
            preparedStatement.setString(5,customers.getCustomerIdCard());
            preparedStatement.setString(6,customers.getCustomerPhone());
            preparedStatement.setString(7,customers.getCustomerEmail());
            preparedStatement.setString(8,customers.getCustomerAddress());
            preparedStatement.setString(9,customers.getCustomerCode());
            check = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return check;
    }

    public boolean deleteCustomer(int id) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        boolean check = false;
            try {
                preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
                preparedStatement.setInt(1,id);
                check = preparedStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        return check;
    }
    public Customers selectCustomer(int id) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        Customers customers = null;

            try {
                preparedStatement = connection.prepareStatement(SELECT_CUSTOMER);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    CustomerType id_typer = customerTypeRepository.selectCustomerType(resultSet.getInt("customer_type_id"));
                    String name = resultSet.getString("customer_name");
                    String dayOfBirth = resultSet.getString("customer_birthday");
                    String gender = resultSet.getString("customer_gender");
                    String idCard = resultSet.getString("customer_id_card");
                    String phone = resultSet.getString("customer_phone");
                    String email = resultSet.getString("customer_email");
                    String address = resultSet.getString("customer_address");
                    String code = resultSet.getString("customer_code");
                    customers = new Customers(id,id_typer,name,dayOfBirth,gender,idCard,phone,email,address,code);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return customers;

    }

    public boolean updateCustomer(Customers customer) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        boolean check = false;
            try {
                preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
                preparedStatement.setInt(1,customer.getCustomerTypeId().getCustomerTypeId());
                preparedStatement.setString(2,customer.getCustomerName());
                preparedStatement.setString(3,customer.getCustomerBirthday());
                preparedStatement.setString(4,customer.getCustomerGender());
                preparedStatement.setString(5,customer.getCustomerIdCard());
                preparedStatement.setString(6,customer.getCustomerPhone());
                preparedStatement.setString(7,customer.getCustomerEmail());
                preparedStatement.setString(8,customer.getCustomerAddress());
                preparedStatement.setString(9,customer.getCustomerCode());
                preparedStatement.setInt(10,customer.getCustomerId());
                check = preparedStatement.executeUpdate() >0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return check;
    }

    public List<Customers> searchByName(String name){
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        List<Customers> customersList = new ArrayList<>();
            try {
                preparedStatement = connection.prepareStatement(SEARCH_NAME_CUSTOMER);
                preparedStatement.setString(1,"%"+name+"%");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("customer_id");
                    CustomerType id_type = customerTypeRepository.selectCustomerType(rs.getInt("customer_type_id"));
                    String nameCus = rs.getString("customer_name");
                    String dayOfBirth = rs.getString("customer_birthday");
                    String gender = rs.getString("customer_gender");
                    String idCard = rs.getString("customer_id_card");
                    String phone = rs.getString("customer_phone");
                    String email = rs.getString("customer_email");
                    String address = rs.getString("customer_address");
                    String code = rs.getString("customer_code");
                    customersList.add(new Customers(id,id_type,nameCus,dayOfBirth,gender,idCard,phone,email,address,code));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        return customersList;
    }


}
