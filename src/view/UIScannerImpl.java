package view;

import java.util.Scanner;

public class UIScannerImpl implements UI {

    static Scanner sc = new Scanner(System.in);

    @Override
    public String getString() {
        return sc.nextLine().trim();
    }

    @Override
    public void addString(String s) {
        System.out.println(s);
    }

    @Override
    public void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public int showOptionPane(int guessCount) {
        System.out.println("Correct, it took " + guessCount + " guesses\nContinue? [y/n]");
        String answer = getString();
        if(answer.equalsIgnoreCase("y")) {
            return 0;
        } else {
            return 1;
        }
    }
}
