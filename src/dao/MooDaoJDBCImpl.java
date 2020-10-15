package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MooDaoJDBCImpl implements GameDAO {

    Connection connection;
    PreparedStatement findUser, addResult, getTopTen;

    public MooDaoJDBCImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Moo","root","test1234");
            findUser = connection.prepareStatement("select id, name from players where name = ?");
            addResult = connection.prepareStatement("INSERT INTO results (result, player) VALUES (?,?)");
            getTopTen = connection.prepareStatement("select players.name, avg(results.result) as average "
                    + "from players "
                    + "join results on results.player = players.id "
                    + "group by players.name "
                    + "order by average asc "
                    + "limit 10");

        } catch (SQLException e) {
            throw new RuntimeException("MooDAO constructor problem: " + e);
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

    @Override
    public void saveResult(int result, int playerId) {
        try {
            addResult.setInt(1, result);
            addResult.setInt(2, playerId);
            addResult.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("GameDAO saveResult problem: " + e);
        }
    }

    @Override
    public List<NameAndAverage> getTopTen() {
        try {
            ResultSet resultSet = getTopTen.executeQuery();
            ArrayList<NameAndAverage> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(new NameAndAverage(resultSet.getString("name"), resultSet.getDouble("average")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("GameDAO getTopTen problem: " + e);
        }
    }

}
