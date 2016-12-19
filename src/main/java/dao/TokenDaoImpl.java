package dao;

import factories.ConnectionToDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TokenDaoImpl implements TokenDao {

    private Connection connection;

    public TokenDaoImpl() {
        try {
            connection = ConnectionToDb.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToken(String userName, String token) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT token_ins(?, ?)"
            );
            statement.setString(1, userName);
            statement.setString(2, token);

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getTokensByUserName(String userName) {
        List<String> tokens = new LinkedList<>();

        try {
            PreparedStatement statement = ConnectionToDb.getInstance().getConnection().prepareStatement(
                    "SELECT token FROM tokens NATURAL JOIN (SELECT * FROM users WHERE user_name = ?) AS us;"
            );
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                tokens.add(rs.getString("token"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tokens;
    }
}
