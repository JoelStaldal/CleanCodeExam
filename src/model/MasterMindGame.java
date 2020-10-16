package model;

public class MasterMindGame implements GameStrategy {

    @Override
    public String generateRandomNumber() {
        String goal = "";
        for (int i = 0; i < 4; i++){
            int random = (int) (Math.floor(Math.random() * 6) + 1);
            String randomDigit = "" + random;
            goal = goal + randomDigit;
        }
        return goal;
    }

    @Override
    public String checkBullsAndCows(String goal, String guess) {
        /*int cows = 0, bulls = 0;
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
        return result;*/
        return "BBBB,";
    }

    @Override
    public boolean validGuess(String guess) {
        return ((guess.matches("[1-6]+")) && guess.length() == 4);
    }
}
