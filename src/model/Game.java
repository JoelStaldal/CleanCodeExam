package model;

public class Game implements GameStrategy {

    private String goal;
    private String guess;
    private int guessCount = 0;

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    @Override
    public String getGuess() {
        return this.guess;
    }

    @Override
    public void setGuess(String guess) {
        this.guess = guess;
        this.guessCount ++;
    }

    @Override
    public String getGoal() {
        return this.goal;
    }

    @Override
    public void setGoal() {
        String goal = "";
        for (int i = 0; i < 4; i++){
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (goal.contains(randomDigit)){
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            goal = goal + randomDigit;
        }
        this.goal = goal;
    }

    @Override
    public String checkBullsAndCows() {
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++ ) {
                if (goal.charAt(i) == guess.charAt(j)){
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++){
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++){
            result = result + "C";
        }
        return result;
    }
}
