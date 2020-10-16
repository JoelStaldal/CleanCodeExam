package controller;

import dao.PlayerDAO;
import dao.Result;
import dao.ResultDAO;
import model.Game;
import view.UI;

import java.util.List;

public class Controller {

    UI ui;
    Game logic;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;
    boolean newGame = false;

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setLogic(Game logic) {
        this.logic = logic;
    }

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void setResultDAO(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public void run() {
        int playerId = loginPlayer();
        do {
            playGame(playerId);
        }
        while (newGame == true);
    }

    private void playGame(int playerId) {

        generateRandomNumber();
        showGameWindow();
        userGuess();
        compareGoalWithGuess();
        saveResult(playerId);
        showScoreboard();
        showOptionPane();
    }

    private void generateRandomNumber() {
        logic.generateRandomNumber();
    }

    private int loginPlayer() {
        ui.addString("Enter your user name:\n");

        int playerId;
        do {
            String name = ui.getString();
            playerId = playerDAO.getPlayerId(name);
            if(playerId == 0) {
                ui.addString("User: " + name + " is not in database, try again\n");
            }
        } while (playerId == 0);

        return playerId;
    }

    private void userGuess() {
        String guess = "";
        do {
            guess = ui.getString();
            if(logic.validateGuess(guess) == true) {
                logic.setGuess(guess);
            } else {
                ui.addString("Not a valid guess: " + guess + "\n");
            }
        } while (logic.validateGuess(guess) == false);
    }

    private void showGameWindow() {
        ui.clear();
        ui.addString("New game:\n");
        //comment out or remove next line to play real games!
        ui.addString("For practice, number is: " + logic.getGoal() + "\n");
    }

    private void compareGoalWithGuess() {
        String guess = logic.getGuess();
        ui.addString(guess +"\n");
        String result = logic.checkBullsAndCows();

        ui.addString(result + "\n");
        while ( ! result.equals("BBBB,")) {
            userGuess();
            guess = logic.getGuess();
            ui.addString(guess +"\n");
            result = logic.checkBullsAndCows();
            ui.addString(result + "\n");
        }
    }

    private void showOptionPane() {
        int choice = ui.showOptionPane(logic.getGuessCount());
        if(choice == 0) {
            logic.setGuessCount(0);
            newGame = true;
        } else {
            newGame = false;
            ui.exit();
        }
    }

    private void showScoreboard() {
        List<Result> results = resultDAO.getTopTen();
        ui.addString("Top Ten List\n    Player     Average\n");

        int pos = 1;
        for(Result player : results) {
            ui.addString(String.format("%3d %-10s%5.2f%n", pos, player.getName(), player.getAverage()));
            pos ++;
        }
    }

    private void saveResult(int playerId) {
        resultDAO.saveResult(logic.getGuessCount(), playerId);
    }

}
