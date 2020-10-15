package dao;

import java.util.List;

public interface GameDAO {
    int getPlayerId(String name);
    void saveResult(int result, int playerId);
    List<NameAndAverage> getTopTen();
}
