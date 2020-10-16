import view.UISwingImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class MooOriginal {
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	static UISwingImpl ui;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		ui = new UISwingImpl("Moo");
		int answer = JOptionPane.YES_OPTION;

		// login
		ui.addString("Enter your user name:\n");
		String name = ui.getString();
		int id = 0;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Moo","root","test1234");
		stmt = connection.createStatement();		
		rs = stmt.executeQuery("select id,name from players where name = '" + name + "'");
		if (rs.next()) {
			id = rs.getInt("id");
		} else {
			ui.addString("User not in database, please register with admin");
			Thread.sleep(5000);
			ui.exit();
		}
		
		while (answer == JOptionPane.YES_OPTION) {
			String goal = makeGoal();
			ui.clear();
			ui.addString("New game:\n");
			//comment out or remove next line to play real games!
			ui.addString("For practice, number is: " + goal + "\n");
			String guess = ui.getString();
			ui.addString(guess +"\n");
			int nGuess = 1;
			String bbcc = checkBC(goal, guess);
			ui.addString(bbcc + "\n");
			while ( ! bbcc.equals("BBBB,")) {
				nGuess++;
				guess = ui.getString();
				ui.addString(guess +"\n");
				bbcc = checkBC(goal, guess);
				ui.addString(bbcc + "\n");
			}
			int ok = stmt.executeUpdate("INSERT INTO results " + 
					"(result, player) VALUES (" + nGuess + ", " +	id + ")" );
			showTopTen();
			answer = JOptionPane.showConfirmDialog(null, "Correct, it took " + nGuess
					+ " guesses\nContinue?");
		
		}
		ui.exit();
	}
	
	public static String makeGoal(){
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
		return goal;
	}
	
	public static String checkBC(String goal, String guess) {
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
	
	static class PlayerAverage {
		String name;
		double average;
		public PlayerAverage(String name, double average) {
			this.name = name;
			this.average = average;
		}	
	}
	
	static void showTopTen() throws SQLException {
		ArrayList<PlayerAverage> topList = new ArrayList<>();
		Statement stmt2 = connection.createStatement();
		ResultSet rs2;
		rs = stmt.executeQuery("select * from players");
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			rs2 = stmt2.executeQuery("select * from results where player = "+ id );
			int nGames = 0;
			int totalGuesses = 0;
			while (rs2.next()) {
				nGames++;
				totalGuesses += rs2.getInt("result");
			}
			if (nGames > 0) {
				topList.add(new PlayerAverage(name, (double)totalGuesses/nGames));
			}
			
		}
		ui.addString("Top Ten List\n    Player     Average\n");
		int pos = 1;
		topList.sort((p1,p2)->(Double.compare(p1.average, p2.average)));
		for (PlayerAverage p : topList) {
			ui.addString(String.format("%3d %-10s%5.2f%n", pos, p.name, p.average));
			if (pos++ == 10) break;
		}

	}
}