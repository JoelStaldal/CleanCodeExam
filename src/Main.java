import controller.Controller;
import dao.GameDAO;
import dao.MooDaoJDBCImpl;
import model.GameStrategy;
import model.Game;
import view.UI;
import view.UISwingImpl;

public class Main {

    public static void main(String[] args) {
        UI simpleWindow = new UISwingImpl("Moo");
        GameStrategy mooGame = new Game();
        GameDAO mooDao = new MooDaoJDBCImpl();

        Controller controller = new Controller();
        controller.setUi(simpleWindow);
        controller.setLogic(mooGame);
        controller.setDao(mooDao);

        controller.run();
    }
}
