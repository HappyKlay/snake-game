package main;


import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startGame);
    }

    private static void startGame() {
        JFrame frame = createGameFrame();

        GameLogic gameLogic = new GameLogic();
        SnakeWindow snakeWindow = new SnakeWindow(gameLogic, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.add(snakeWindow);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        snakeWindow.requestFocusInWindow();

        startGameTimer(snakeWindow);
    }

    private static JFrame createGameFrame() {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        return frame;
    }

    private static void startGameTimer(ActionListener gameListener) {
        Timer timer = new Timer(130, gameListener);
        timer.start();
    }
}

