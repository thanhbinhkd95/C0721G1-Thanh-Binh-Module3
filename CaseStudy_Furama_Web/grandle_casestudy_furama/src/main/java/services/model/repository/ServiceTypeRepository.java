package services.model.repository;

import services.model.bean.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_BY_TYPE_SERVICEID = "select * from service_type where service_type_id =?";
    private static final String SELECT_ALL_TYPE_SERVICE = "select * from service_type";

    public ServiceType findByIdType(int id){
        Connection connection = baseRepository.getConnection();
        ServiceType serviceType = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SELECT_BY_TYPE_SERVICEID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("service_type_name");
                serviceType = new ServiceType(id,name);
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
        return serviceType;
    }
    public List<ServiceType> selectAllServiceTyype(){
        Connection connection = baseRepository.getConnection();
        List<ServiceType> serviceType = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SELECT_ALL_TYPE_SERVICE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("service_type_id");
                String name = rs.getString("service_type_name");
                serviceType.add(new ServiceType(id,name));
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
        return serviceType;
    }
}
