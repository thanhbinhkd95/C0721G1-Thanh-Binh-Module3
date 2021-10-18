package login.model.repository;

import employee.model.bean.User;
import login.model.bean.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ACCOUNT = "select * from view_user where username = ? and `password` = ? ";
    public Account login(String user, String pass){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        Account account = null;
        try {
            statement = connection.prepareStatement(SELECT_ACCOUNT);
            statement.setString(1,user);
            statement.setString(2,pass);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String userName = rs.getString("username");
                String password = rs.getString("password");
                int  role = rs.getInt("role_id");
                account = new Account(userName,password,role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
