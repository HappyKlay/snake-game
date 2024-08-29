import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeWindow extends JPanel implements KeyListener, ActionListener {
    private GameLogic gameLogic;

    SnakeWindow(int boardWidth, int boardHeight) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        gameLogic = new GameLogic(new Random());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            gameLogic.setVelocityX(Constants.NONE);
            gameLogic.setVelocityY(Constants.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gameLogic.setVelocityX(Constants.NONE);
            gameLogic.setVelocityY(Constants.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameLogic.setVelocityX(Constants.LEFT);
            gameLogic.setVelocityY(Constants.NONE);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameLogic.setVelocityX(Constants.RIGHT);
            gameLogic.setVelocityY(Constants.NONE);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
