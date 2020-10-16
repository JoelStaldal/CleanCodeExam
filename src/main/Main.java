package main;

import controller.Controller;
import dao.*;
import model.Game;
import model.GameStrategy;
import model.MasterMindGame;
import model.MooGame;
import view.UI;
import view.UIScannerImpl;
import view.UISwingImpl;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();

        Game logic = new Game();
        logic.setGameType(new MooGame());
        //logic.setGameType(new MasterMindGame());
        controller.setLogic(logic);

        controller.setUi(new UISwingImpl("GameName"));
        //controller.setUi(new UIScannerImpl());

        controller.setPlayerDAO(new PlayerDaoJDBCImpl(new ConnectionFactory()));

        controller.setResultDAO(new ResultMooDaoJDBCImpl(new ConnectionFactory()));
        //controller.setResultDAO(new ResultMasterMindDaoJDBCImpl(new ConnectionFactory()));

        controller.run();
    }
}
