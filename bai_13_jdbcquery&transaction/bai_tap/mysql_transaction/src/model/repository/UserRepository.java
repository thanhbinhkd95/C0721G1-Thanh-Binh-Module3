package model.repository;

import model.bean.User;
import sun.dc.pr.PRError;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    BaseRepository baseRepository = new BaseRepository();


    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    private static final String SEARCH_USER_BY_COUNTRY = "select id,name,email,country from users where country like ?";

    private static final String SORT_USER_BY_NAME = "SELECT * FROM users\n" +
            "ORDER BY `name` ASC;";

    private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";

    private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE NAME=?";

    private static final String SQL_TABLE_CREATE = "CREATE TABLE EMPLOYEE"

            + "("

            + " ID serial,"

            + " NAME varchar(100) NOT NULL,"

            + " SALARY numeric(15, 2) NOT NULL,"

            + " CREATED_DATE timestamp,"

            + " PRIMARY KEY (ID)"

            + ")";

    private static final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE";


    public UserRepository() {
    }

    public void insertUser(User user) throws SQLException{
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement=connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public User selectUser(int id){
        Connection connection =baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return user;
    }

    public List<User> selectAllUser(){
        Connection connection =baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        List<User> user = new ArrayList<>();
        try {
            preparedStatement  = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user.add(new User(id,name,email,country));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public boolean deleteUser(int id){
        Connection connection =baseRepository.getConnection();
        PreparedStatement statement = null;
        boolean rowDeleted = false;
        try  {
            statement  = connection.prepareStatement(DELETE_USERS_SQL);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowDeleted;
    }

    public boolean updateUser(User user){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        boolean rowUpdated = false;
        try{
            statement = connection.prepareStatement(UPDATE_USERS_SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return rowUpdated;
    }

    public List<User> searchByCountry(String search){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<User> usersList = new ArrayList<>();
        try{
            statement  = connection.prepareStatement(SEARCH_USER_BY_COUNTRY);
            statement.setString(1,"%"+search+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                usersList.add(new User(id,name,email,country));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return usersList;
    }

    public List<User> sortByName(){
        Connection connection = baseRepository.getConnection();
        List<User> usersList = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SORT_USER_BY_NAME);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                usersList.add(new User(id,name,email,country));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersList;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public List<User> viewUserStore() {
        Connection connection = baseRepository.getConnection();
       List<User> userList = new ArrayList<>();
       String query = "{CALL view_list_user_procedure()}";
       try{
           CallableStatement callableStatement = connection.prepareCall(query);
           ResultSet resultSet = callableStatement.executeQuery();

           while (resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");

               String email = resultSet.getString("email");

               String country = resultSet.getString("country");

              userList.add(new User(id,name,email,country));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return userList;
    }


    public boolean updateUserStore(User user) {
        Connection connection = baseRepository.getConnection();
        String query = "{CALL update_user_procedure(?,?,?,?)}";
        boolean check = false;
        try{
            CallableStatement callableStatement = connection.prepareCall(query);

            callableStatement.setInt(1, user.getId());

            callableStatement.setString(2, user.getName());

            callableStatement.setString(3, user.getEmail());

            callableStatement.setString(4, user.getCountry());

            check = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }


    public boolean deleteUserStore(int id) {
        Connection connection =baseRepository.getConnection();
        String query = "{CALL delete_user_procedure(?)}";
        boolean check = false;
        try  {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            check = callableStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }


    public void addUserTransaction(User user, int[] permisions) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtAssignment = null;


        ResultSet rs = null;

        try {

            conn = baseRepository.getConnection();

            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(INSERT_USERS_SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getName());

            pstmt.setString(2, user.getEmail());

            pstmt.setString(3, user.getCountry());

            int rowAffected = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            int userId = 0;

            if (rs.next())
                userId = rs.getInt(1);
            if (rowAffected == 1) {
                String sqlPivot = "INSERT INTO user_permision(user_id,permision_id) "
                        + "VALUES(?,?)";
                pstmtAssignment = conn.prepareStatement(sqlPivot);
                for (int permisionId : permisions) {
                    pstmtAssignment.setInt(1, userId);
                    pstmtAssignment.setInt(2, permisionId);
                    pstmtAssignment.executeUpdate();

                }
                conn.commit();
            } else {

                conn.rollback();
            }
        } catch (SQLException ex) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (pstmtAssignment != null) pstmtAssignment.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public void insertUpdateUseTransaction() {
        Connection connection = baseRepository.getConnection();
        PreparedStatement preparedStatement = null;
        String INSERT_CUSTOMERS = "insert into customers(id_cus,cus_name)" +
                                    "values (?,?) ";
        String INSERT_CUSTOMERS_TYPE = "insert into customers_type(id_cus,type_name)" +
                                            "values (?,?) ";

            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS);
                preparedStatement.setInt(1,3);
                preparedStatement.setString(2,"Long");

                int row = preparedStatement.executeUpdate();

                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_TYPE);
                preparedStatement.setInt(1,6);
                preparedStatement.setString(2,"Minh");

                row += preparedStatement.executeUpdate();

                if(row == 2){
                    System.out.println("Commit thanh công");
                    connection.commit();
                }else {
                    connection.rollback();
                }



            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    System.out.println("Thât Bại");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    }
}
