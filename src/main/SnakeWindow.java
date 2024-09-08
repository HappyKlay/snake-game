package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeWindow extends JPanel implements KeyListener, ActionListener {
    private final GameLogic gameLogic;
    private final Draw draw;
    private static JFrame frame;
    private Score score;


    private void initializeWindow(int boardWidth, int boardHeight) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        addKeyListener(this);
        setFocusable(true);
        this.requestFocusInWindow();
    }

    public SnakeWindow(GameLogic gameLogic, int boardWidth, int boardHeight) {
        initializeWindow(boardWidth,boardHeight);
        this.gameLogic = gameLogic;
        this.draw = new Draw();
        frame = new JFrame();
        score = new Score();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameLogic.wasMoved) {
            gameLogic.wasMoved = false;
            repaint();
            return;
        }
        gameLogic.move();
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        draw.drawGameWindow(g2d);
        draw.drawTiles(g2d, gameLogic.getSnake(), gameLogic.getFood());
        draw.drawScore(g2d, score);

        if (gameLogic.isGameOver()) {
            draw.drawGameOver(g2d);
        }
    }

    public static void exit() {
        System.out.println("Exiting game...");
        if (frame != null) {
            frame.dispose();
        }
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        gameLogic.handleKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
