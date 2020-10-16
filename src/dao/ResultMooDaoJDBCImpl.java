package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultMooDaoJDBCImpl implements ResultDAO {

    Connection connection;
    PreparedStatement addResult, getTopTen;

    public ResultMooDaoJDBCImpl(ConnectionFactory connectionFactory) {
        connection = connectionFactory.getConnection();
        connection = connectionFactory.getConnection();
        try {
            addResult = connection.prepareStatement("INSERT INTO results_moo (result, player) VALUES (?,?)");
            getTopTen = connection.prepareStatement("select players.name, avg(results_moo.result) as average "
                    + "from players "
                    + "join results_moo on results_moo.player = players.id "
                    + "group by players.name "
                    + "order by average asc "
                    + "limit 10");
        } catch (SQLException e) {
            throw new RuntimeException("ResultDAO constructor problem: " + e);
        }
    }

    @Override
    public void saveResult(int result, int playerId) {
        try {
            addResult.setInt(1, result);
            addResult.setInt(2, playerId);
            addResult.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("ResultDAO saveResult problem: " + e);
        }
    }

    @Override
    public List<Result> getTopTen() {
        try {
            ResultSet resultSet = getTopTen.executeQuery();
            ArrayList<Result> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(new Result(resultSet.getString("name"), resultSet.getDouble("average")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("ResultDAO getTopTen problem: " + e);
        }
    }
}
