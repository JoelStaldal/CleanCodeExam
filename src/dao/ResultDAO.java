package dao;

import java.util.List;

public interface ResultDAO {
    void saveResult(int result, int playerId);
    List<Result> getTopTen();
}
