package model;

public interface GameStrategy {
    String generateRandomNumber();
    String checkBullsAndCows(String goal, String guess);
    boolean validGuess(String guess);
}
