package model.service.Impl;

import model.bean.User;
import model.repository.UserRepository;
import model.service.IUserService;


import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    UserRepository reponsitory = new UserRepository();

    @Override
    public void insertUser(User user) throws SQLException {
        reponsitory.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return reponsitory.selectUser(id);
    }

    @Override
    public List<User> selectAllUser() {
        return reponsitory.selectAllUser();
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return reponsitory.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return reponsitory.updateUser(user);
    }

    @Override
    public List<User> searchByCountry(String search) {
        return reponsitory.searchByCountry(search);
    }

    @Override
    public List<User> sortByName() {
        return reponsitory.sortByName();
    }

    @Override
    public List<User> viewUserStore() {
        return reponsitory.viewUserStore();
    }

    @Override
    public boolean updateUserStore(User user) {
        return reponsitory.updateUserStore(user);
    }

    @Override
    public boolean deleteUserStore(int id) {
      return   reponsitory.deleteUserStore(id);
    }

    @Override
    public void addUserTransaction(User user, int[] permision) {
        reponsitory.addUserTransaction(user,permision);
    }
    @Override
    public void insertUpdateUseTransaction() {
        reponsitory.insertUpdateUseTransaction();
    }
}
