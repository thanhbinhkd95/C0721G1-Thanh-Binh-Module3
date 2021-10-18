package services.model.repository;

import services.model.bean.RentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentTypeRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_BY_RENT_TYPEID = "select * from rent_type where rent_type_id=?";
    private static final String SELECT_ALL_RENT_TYPE = "select * from rent_type";

    public RentType findByRentType(int id){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        RentType rentType =null;

        try {
            statement = connection.prepareStatement(SELECT_BY_RENT_TYPEID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("rent_type_name");
                double cost = rs.getDouble("rent_type_cost");
                rentType = new RentType(id,name,cost);
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
        return rentType;
    }
    public List<RentType> selectAllRentType(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<RentType> rentType = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_RENT_TYPE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("rent_type_id");
                String name = rs.getString("rent_type_name");
                double cost = rs.getDouble("rent_type_cost");
                rentType.add(new RentType(id,name,cost));
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
        return rentType;
    }
}
