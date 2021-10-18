package customers.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseRepository {
    private String jdbcURL = "jdbc:mysql://localhost:3306/casestudy_furama_web?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";



    public BaseRepository() {
    }

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
