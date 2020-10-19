package model;

public interface GameStrategy {
    String generateRandomNumber();
    String checkBullsAndCows(String goal, String guess);
    boolean validateGuess(String guess);
}
