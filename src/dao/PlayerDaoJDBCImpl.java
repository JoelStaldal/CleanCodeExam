package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDaoJDBCImpl implements PlayerDAO {

    Connection connection;
    PreparedStatement findUser;

    public PlayerDaoJDBCImpl(ConnectionFactory connectionFactory) {
        connection = connectionFactory.getConnection();
        try {
            findUser = connection.prepareStatement("select id, name from players where name = ?");
        } catch (SQLException e) {
            throw new RuntimeException("PlayerDAO constructor problem: " + e);
        }
    }

    @Override
    public int getPlayerId(String name) {
        int id = 0;
        try {
            findUser.setString(1, name);
            ResultSet resultSet = findUser.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException("GameDAO getPlayerId problem: " + e);
        }
    }
}
