package dao;

import entities.FullEntity;
import factories.ConnectionToDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    Connection connection;

    public AdminDaoImpl() {
        try {
            connection = ConnectionToDb.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FullEntity> getNormalOutOfQueue() {
        List<FullEntity> outOfQueue = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM sorted_out_of_queue;"
            );
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                outOfQueue.add((new FullEntity.Builder()).setUserName(rs.getString("user_name"))
                        .setRoomsCount(rs.getInt("rooms_count"))
                        .setKindergarden(rs.getBoolean("kindergarden"))
                        .setSchool(rs.getBoolean("school"))
                        .setDistrict(rs.getString("district"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outOfQueue;
    }

    @Override
    public List<FullEntity> getNormalFirstOfQueue() {
        List<FullEntity> firstOfQueue = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM sorted_first_of_queue;"
            );
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                firstOfQueue.add((new FullEntity.Builder()).setUserName(rs.getString("user_name"))
                        .setRoomsCount(rs.getInt("rooms_count"))
                        .setKindergarden(rs.getBoolean("kindergarden"))
                        .setSchool(rs.getBoolean("school"))
                        .setDistrict(rs.getString("district"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstOfQueue;
    }

    @Override
    public List<FullEntity> getNormalUsualQueue() {
        List<FullEntity> usualQueue = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM sorted_usual_queue;"
            );
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                usualQueue.add((new FullEntity.Builder()).setUserName(rs.getString("user_name"))
                        .setRoomsCount(rs.getInt("rooms_count"))
                        .setKindergarden(rs.getBoolean("kindergarden"))
                        .setSchool(rs.getBoolean("school"))
                        .setDistrict(rs.getString("district"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usualQueue;
    }

    @Override
    public List<FullEntity> getDeletedQueue() {
        List<FullEntity> deletedQueue = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM deleted_queue;"
            );
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                deletedQueue.add((new FullEntity.Builder()).setUserName(rs.getString("user_name"))
                                    .setRoomsCount(rs.getInt("rooms_count"))
                                    .setKindergarden(rs.getBoolean("kindergarden"))
                                    .setSchool(rs.getBoolean("school"))
                                    .setDistrict(rs.getString("district"))
                                    .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedQueue;
    }


    @Override
    public void deleteFromNormalQueue(String userName) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET deleted = TRUE WHERE user_name = ?"
            );
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromDeletedQueue(String userName) {
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
