package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameLogic gameLogic = new GameLogic();

        SnakeWindow snakeWindow = new SnakeWindow(gameLogic, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.add(snakeWindow);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        snakeWindow.requestFocusInWindow();

        Timer timer = new Timer(100, snakeWindow);
        timer.start();
    }
}