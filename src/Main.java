import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SnakeGame");

        frame.setVisible(true);
        frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameLogic gameLogic = new GameLogic();

        SnakeWindow snakeWindow = new SnakeWindow(gameLogic, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        frame.add(snakeWindow);
        frame.pack();
        frame.requestFocus();
    }
}