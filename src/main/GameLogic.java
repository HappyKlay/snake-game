package main;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class GameLogic  {
    private final Random random = new Random();
    private Tile food = new Tile(random.nextInt(Constants.BOARD_COLUMNS),
                                 random.nextInt(Constants.BOARD_ROWS));
    private final Snake snake = new Snake();
    private Constants.Direction direction = Constants.Direction.UP;

    public void move() {
        snake.setHead(new Tile(snake.getHead().x() + direction.getDeltaX(),
                                  snake.getHead().y() + direction.getDeltaY()));

        Tile previous = snake.getHead();

        for (Tile bodyPiece:
             snake.getBody()) {
            Tile temp = new Tile(previous.x(), previous.y());
            bodyPiece.x(temp.x());
            bodyPiece.y(temp.y());
            previous = temp;
        }

    }

    public void setDirection (Constants.Direction direction) {
        if (direction == direction.opposite()) {
            return;
        }
        this.direction = direction;
    }

    public void handleKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP      -> setDirection(Constants.Direction.UP);
            case KeyEvent.VK_DOWN    -> setDirection(Constants.Direction.DOWN);
            case KeyEvent.VK_LEFT    -> setDirection(Constants.Direction.LEFT);
            case KeyEvent.VK_RIGHT   -> setDirection(Constants.Direction.RIGHT);
        }
        System.out.println(e.toString());
    }

    public void calculateCollision() {
        if (snake.getHead().x() == food.x() && snake.getHead().y() == food.y()) {
            snake.grow();
            updateFood(random.nextInt(Constants.BOARD_COLUMNS),
                       random.nextInt(Constants.BOARD_ROWS));
        }

        Deque<Tile> body = snake.getBody();

        for (Tile bodyPiece:
             body) {
            if (snake.getHead().x() == bodyPiece.x() &&
                snake.getHead().y() == bodyPiece.y()) {
                gameOver();
            }
        }
    }

    private void updateFood(int x, int y) {
        this.food = new Tile(x, y);
    }

    public void gameOver() {

    }

    public Tile getFood() {
        return food;
    }

   public int getDeltaX() {
       return direction.getDeltaX();
   }

   public int getDeltaY() {
        return direction.getDeltaY();
   }

    public Snake getSnake() {
        return snake;
    }
}

