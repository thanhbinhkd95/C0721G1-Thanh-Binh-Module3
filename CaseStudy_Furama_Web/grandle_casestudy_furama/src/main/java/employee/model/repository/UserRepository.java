package employee.model.repository;

import employee.model.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    BaseRepository baseRepository = new BaseRepository();
    private  static final String SELECT_USER = "select * from `user` where username =?";
    private  static final String SELECT_ALL_USER = "select * from `user`";


    public User selectUser(String name){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        User user = null;

        try {
            statement = connection.prepareStatement(SELECT_USER);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String pass = rs.getString("password");
                user = new User(name,pass);
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
        return user;
    }
    public List<User> selecAlltUser(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<User> userList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String name = rs.getString("username");
                String pass = rs.getString("password");
                userList.add(new User(name,pass));
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
        return userList;
    }

}
