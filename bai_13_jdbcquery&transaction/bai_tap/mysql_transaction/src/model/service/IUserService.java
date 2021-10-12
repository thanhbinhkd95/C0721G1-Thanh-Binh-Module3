package model.service;

import model.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    void insertUser(User user) throws SQLException;

    User selectUser(int id);

    List<User> selectAllUser();

    boolean deleteUser(int id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    List<User> searchByCountry(String search);

    List<User> sortByName();

    List<User> viewUserStore();

    boolean updateUserStore(User user);

    boolean deleteUserStore(int id);

    void addUserTransaction(User user, int[] permision);

    public void insertUpdateUseTransaction();
}
