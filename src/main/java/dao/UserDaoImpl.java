package dao;

import entities.SecurityUser;
import entities.User;
import factories.ConnectionToDb;
import utils.MD5;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl() {
        try {
            connection = ConnectionToDb.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SecurityUser find(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные отца!");
        }
        SecurityUser.Builder secUserBuilder = new SecurityUser.Builder();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT user_name, date_registration, deleted FROM users WHERE user_name = ?;"
            );
            statement.setString(1, userName);
            ResultSet rs =  statement.executeQuery();

            if (rs.next()){
                secUserBuilder
                        .setUserName(rs.getString("user_name"))
                        .setDateRegistration(rs.getDate("date_registration"))
                        .setGranted(rs.getBoolean("deleted"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return secUserBuilder.build();
    }

    @Override
    public SecurityUser checkPassword(String userName, String password) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные отца!");
        }
        if (password != null && !password.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные отца!");
        }
        SecurityUser.Builder builder = new SecurityUser.Builder();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT user_name FROM users WHERE user_name = ? AND password = ?;"
            );
            statement.setString(1, userName);
            statement.setString(2, MD5.generateHash(password));

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder.setUserName(rs.getString("user_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return builder.build();
    }

    @Override
    public void save(User user) {
        if (user.getUser_name() != null & !user.getUser_name().equals("")
                | user.getPassword() != null | !user.getPassword().equals("")){
        }else{
            throw new IllegalArgumentException("Введены неудовлетворительные данные пользователя!");
        }

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, DEFAULT)"
            );
            statement.setString(1, user.getUser_name());
            statement.setString(2, MD5.generateHash(user.getPassword()));
            statement.setDate(3, user.getDateRegistration());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Имя пользователя не уникально!");
        }
    }

    @Override
    public void update(User user) {
        // не реализовано
    }

    @Override
    public void delete(String userName) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE user_name = ?;"
            );
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
