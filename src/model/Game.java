package model;

public class Game {

    private String goal;
    private String guess;
    private int guessCount = 0;
    GameStrategy gameType;

    public void setGameType(GameStrategy gameType) {
        this.gameType = gameType;
    }

    public String checkBullsAndCows() {
        return gameType.checkBullsAndCows(goal, guess);
    }

    public void generateRandomNumber() {
        this.goal = gameType.generateRandomNumber();
    }

    public boolean validateGuess(String guess) {
        return gameType.validateGuess(guess);
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public String getGuess() {
        return this.guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
        this.guessCount ++;
    }

    public String getGoal() {
        return this.goal;
    }
}
