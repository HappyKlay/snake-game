import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeWindow extends JPanel implements KeyListener, ActionListener {
    private final GameLogic gameLogic;
    private final Draw draw;

    private void initializeWindow(int boardWidth, int boardHeight) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
    }

    SnakeWindow(GameLogic gameLogic, int boardWidth, int boardHeight) {
        initializeWindow(boardWidth,boardHeight);
        this.gameLogic = gameLogic;
        this.draw = new Draw();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw.drawGrid(graphics);
        draw.drawTiles(graphics, gameLogic.getSnake(), gameLogic.getFood());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        gameLogic.handleKey(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
