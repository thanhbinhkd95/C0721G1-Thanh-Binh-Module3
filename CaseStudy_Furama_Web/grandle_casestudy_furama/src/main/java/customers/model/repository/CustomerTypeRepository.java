package customers.model.repository;

import customers.model.bean.CustomerType;
import services.model.bean.RentType;
import services.model.bean.Service;
import services.model.bean.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepository {
    static BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_CUSTOMER_TYPE = "select customer_type_name from customer_type where customer_type_id = ?";
    private static final String SELECT_ALL_CUSTOMER_TYPE = "select * from customer_type";

    public CustomerType selectCustomerType(int id) {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        CustomerType customerType = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_TYPE);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("customer_type_name");
                customerType = new CustomerType(id, name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customerType;
    }

    public List<CustomerType> selectAllCustomerType() {
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<CustomerType> customerTypeList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_CUSTOMER_TYPE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("customer_type_id");
                String name = rs.getString("customer_type_name");
                customerTypeList.add(new CustomerType(id, name));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerTypeList;

    }
}
