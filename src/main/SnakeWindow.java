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
    private Score score;

    private void initializeWindow(int boardWidth, int boardHeight) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        addKeyListener(this);
        setFocusable(true);
        this.requestFocusInWindow();
    }

    SnakeWindow(GameLogic gameLogic, int boardWidth, int boardHeight) {
        initializeWindow(boardWidth,boardHeight);
        this.gameLogic = gameLogic;
        this.draw = new Draw();
        score = new Score();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.move();
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw.drawGameWindow(graphics);
        draw.drawTiles(graphics, gameLogic.getSnake(), gameLogic.getFood());
        draw.drawScore(graphics, score);
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
