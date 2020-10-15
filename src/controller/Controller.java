package controller;

import dao.GameDAO;
import dao.NameAndAverage;
import model.GameStrategy;
import view.UI;

import javax.swing.*;
import java.util.List;

public class Controller {

    UI ui;
    GameDAO dao;
    GameStrategy logic;
    boolean newGame = false;

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setDao(GameDAO dao) {
        this.dao = dao;
    }

    public void setLogic(GameStrategy logic) {
        this.logic = logic;
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
        showJOptionPane();
    }

    private void generateRandomNumber() {
        logic.setGoal();
    }

    private void showJOptionPane() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Correct, it took " + logic.getGuessCount() + " guesses\nContinue?");
        if(choice == 0) {
            logic.setGuessCount(0);
            newGame = true;
        } else {
            newGame = false;
            ui.exit();
        }
    }

    private int loginPlayer() {
        ui.addString("Enter your user name:\n");

        int playerId;
        do {
            String name = ui.getString();
            playerId = dao.getPlayerId(name);
            if(playerId == 0) {
                ui.addString("User: " + name + " is not in database, try again\n");
            }
        } while (playerId == 0);

        return playerId;
    }

    private void userGuess() {

        boolean validGuess = false;
        do {
            String guess = ui.getString();
            if((guess.matches("[0-9]+")) && guess.length() == 4) {
                logic.setGuess(guess);
                validGuess = true;
            } else {
                ui.addString("Not a valid guess: " + guess + "\n");
            }
        } while (validGuess == false);
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

    private void showScoreboard() {
        List<NameAndAverage> topList = dao.getTopTen();
        ui.addString("Top Ten List\n    Player     Average\n");

        int pos = 1;
        for(NameAndAverage player : topList) {
            ui.addString(String.format("%3d %-10s%5.2f%n", pos, player.getName(), player.getAverage()));
            pos ++;
        }
    }

    private void saveResult(int playerId) {
        dao.saveResult(logic.getGuessCount(), playerId);
    }

}
