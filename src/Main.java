import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SnakeGame");

        frame.setVisible(true);
        frame.setSize(Constants.boardWidth, Constants.boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeWindow snakeWindow = new SnakeWindow(Constants.boardWidth, Constants.boardHeight);
        frame.add(snakeWindow);
        frame.pack();
        frame.requestFocus();
    }
}