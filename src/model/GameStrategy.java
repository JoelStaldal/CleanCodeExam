package model;

public interface GameStrategy {
    String checkBullsAndCows();
    int getGuessCount();
    void setGuessCount(int guessCount);
    void setGoal();
    String getGoal();
    void setGuess(String guess);
    String getGuess();
}
