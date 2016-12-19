package dao;

import entities.*;
import factories.ConnectionToDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ParametersDaoImpl implements ParametersDao {

    Connection connection;

    public ParametersDaoImpl() {
        try {
            connection = ConnectionToDb.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Finds*/

    @Override
    public Father findFather(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        Father.Builder builder = new Father.Builder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM fathers NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder
                        .setSurname(rs.getString("father_surname"))
                        .setName(rs.getString("father_name"))
                        .setPatronymic(rs.getString("father_patronymic"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    @Override
    public Mother findMother(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        Mother.Builder builder = new Mother.Builder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM mothers NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder
                        .setSurname(rs.getString("mother_surname"))
                        .setName(rs.getString("mother_name"))
                        .setPatronymic(rs.getString("mother_patronymic"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    @Override
    public List<Child> findChildren(String userName) {
        List<Child> children = new LinkedList<>();
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM children NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                children.add(
                        (new Child.Builder())
                        .setSurname(rs.getString("child_surname"))
                        .setName(rs.getString("child_name"))
                        .setPatronymic("child_patronymic")
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return children;
    }

    @Override
    public Wish findWish(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        Wish.Builder builder = new Wish.Builder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM wishes NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder
                        .setRoomsCount(rs.getInt("rooms_count"))
                        .setKindergarden(rs.getBoolean("kindergarden"))
                        .setSchool(rs.getBoolean("school"))
                        .setDistrict(rs.getString("district"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    @Override
    public Housing findHousing(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        Housing.Builder builder = new Housing.Builder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM housing NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder
                        .setApplicationDate(rs.getDate("date_application"))
                        .setCondition(rs.getInt("condition"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    @Override
    public Promotions findPromotions(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        Promotions.Builder builder = new Promotions.Builder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM promotions NATURAL JOIN (SELECT users.user_id FROM users WHERE users.user_name = ?) AS us;"
            );

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                builder
                        .setPromotions(rs.getBoolean("promotions"))
                        .setFirstOfQueue(rs.getBoolean("first_of_queue"))
                        .setOutOfQueue(rs.getBoolean("out_of_queue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return builder.build();
    }

    /*Saves*/

    @Override
    public void saveFather(String userName, Father father) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT father_ins(?, ?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setString(2, father.getSurname());
            statement.setString(3, father.getName());
            statement.setString(4, father.getPatronymic());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные отца!");
        }
    }

    @Override
    public void saveMother(String userName, Mother mother) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT mother_ins(?, ?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setString(2, mother.getSurname());
            statement.setString(3, mother.getName());
            statement.setString(4, mother.getPatronymic());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные матери!");
        }
    }

    @Override
    public void saveChild(String userName, Child child) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT child_ins(?, ?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setString(2, child.getSurname());
            statement.setString(3, child.getName());
            statement.setString(4, child.getPatronymic());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные ребенка!");
        }
    }

    @Override
    public void saveWishes(String userName, Wish wish) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT wish_ins(?, ?, ?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setInt(2, wish.getRoomsCount());
            statement.setBoolean(3, wish.isKindergarden());
            statement.setBoolean(4, wish.isSchool());
            statement.setString(5, wish.getDistrict());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные пожеланий!");
        }
    }

    @Override
    public void saveHousing(String userName, Housing housing) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT housing_ins(?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setDate(2, housing.getApplicationDate());
            statement.setInt(3, housing.getCondition());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные жилищных условий!");
        }
    }

    @Override
    public void savePromotions(String userName, Promotions promotions) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT promotions_ins(?, ?, ?, ?)"
            );
            statement.setString(1, userName);
            statement.setBoolean(2, promotions.isPromotions());
            statement.setBoolean(3, promotions.isOutOfQueue());
            statement.setBoolean(4, promotions.isFirstOfQueue());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно ввели данные льгот!");
        }
    }

    /*Updates*/

    @Override
    public void updateFather(String userName, Father father) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE fathers SET father_surname = ?, father_name = ?, father_patronymic = ? " +
                            "WHERE fathers.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setString(1, father.getSurname());
            statement.setString(2, father.getName());
            statement.setString(3, father.getPatronymic());
            statement.setString(4, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные отца!");
        }
    }

    @Override
    public void updateMother(String userName, Mother mother) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE mothers SET mother_surname = ?, mother_name = ?, mother_patronymic = ? " +
                            "WHERE mothers.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setString(1, mother.getSurname());
            statement.setString(2, mother.getName());
            statement.setString(3, mother.getPatronymic());
            statement.setString(4, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные матери!");
        }
    }

    @Override
    public void updateChild(String userName, Child child) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE children SET child_surname = ?, child_name = ?, child_patronymic = ? " +
                            "WHERE children.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setString(1, child.getSurname());
            statement.setString(2, child.getName());
            statement.setString(3, child.getPatronymic());
            statement.setString(4, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные ребенка!");
        }
    }

    @Override
    public void updateWishes(String userName, Wish wish) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE wishes SET rooms_count = ?, kindergarden = ?, school = ?, district = ? " +
                            "WHERE wishes.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setInt(1, wish.getRoomsCount());
            statement.setBoolean(2, wish.isKindergarden());
            statement.setBoolean(3, wish.isSchool());
            statement.setString(4, wish.getDistrict());
            statement.setString(5, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные пожеланий!");
        }
    }

    @Override
    public void updateHousing(String userName, Housing housing) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE housing SET date_application = ?, condition = ? " +
                            "WHERE housing.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setDate(1, housing.getApplicationDate());
            statement.setInt(2, housing.getCondition());
            statement.setString(3, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные жилищных условий!");
        }
    }

    @Override
    public void updatePromotions(String userName, Promotions promotions) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE promotions SET promotions = ?, out_of_queue = ?, first_of_queue = ? " +
                            "WHERE promotions.user_id = (SELECT user_id FROM users WHERE user_name = ?);"
            );
            statement.setBoolean(1, promotions.isPromotions());
            statement.setBoolean(2, promotions.isOutOfQueue());
            statement.setBoolean(3, promotions.isFirstOfQueue());
            statement.setString(4, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Не верно введены данные льгот!");
        }
    }

    /*Deletes*/

    @Override
    public void deleteFather(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM fathers WHERE fathers.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMother(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM mothers WHERE mothers.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChild(String userName, Child child) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM children WHERE children.user_id = (SELECT user_id FROM users WHERE user_name = ?) " +
                            "AND children.child_surname = ? AND children.child_name = ? AND children.child_patronymic = ?;"
            );
            statement.setString(1, userName);
            statement.setString(2, child.getSurname());
            statement.setString(3, child.getName());
            statement.setString(4, child.getPatronymic());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChildren(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM children WHERE children.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWishes(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM wishes WHERE wishes.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHousing(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM housing WHERE housing.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePromotions(String userName) {
        if (userName != null && !userName.equals("")){
        }else {
            throw new IllegalArgumentException("Не введены данные пользователя!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM promotions WHERE promotions.user_id = (SELECT user_id FROM users WHERE user_name = ?)"
            );
            statement.setString(1, userName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
