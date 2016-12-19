package services;

import dao.UserDao;
import entities.User;
import factories.DaoFactory;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.getInstance().getUserDao();
    }

    @Override
    public boolean isRegistered(String userName) {
        if (userDao.find(userName).getUser_name() != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkPassword(String userName, String password) {
        if (userDao.checkPassword(userName, password).getUser_name() != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void save(User user) {
        try {
            userDao.save(user);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String userName) {
        userDao.delete(userName);
    }
}
