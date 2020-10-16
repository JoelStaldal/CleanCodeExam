package view;

public interface UI {
    String getString();
    void addString(String s);
    void clear();
    void exit();
    int showOptionPane(int guessCount);
}
