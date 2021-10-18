package employee.model.repository;

import employee.model.bean.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositonRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_POSITION = "select position_name from `position` where position_id =?";
    private static final String SELECT_ALL_POSITION = "select * from `position` ";

    public Position selectPosition(int id){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        Position position = null;

        try {
            statement = connection.prepareStatement(SELECT_POSITION);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String name = rs.getString("position_name");
                position = new Position(id,name);
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
        return position;
    }
    public List<Position> selectAllPosition(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<Position> position = new ArrayList<>();

        try {
            statement = connection.prepareStatement(SELECT_ALL_POSITION);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                position.add(new Position(id,name));
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
        return position;
    }
}
