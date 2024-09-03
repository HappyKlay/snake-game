package main;

import java.awt.event.KeyEvent;
import java.util.Deque;
import java.util.Random;

public class GameLogic  {
    private final Random random = new Random();
    private Tile food = new Tile(10, 8);
    private final Snake snake = new Snake();
    private Constants.Direction direction = Constants.Direction.NONE;

    public void move() {
        if (direction == Constants.Direction.NONE) {
            return;
        }

        Tile newHead = calculateNewHeadPosition();

        if (isCollision(newHead)) {
            gameOver();
            return;
        }

        snake.setHead(newHead);
        snake.removeLastPart();
    }

    private Tile calculateNewHeadPosition() {
        return new Tile(
                snake.getHead().x() + direction.getDeltaX(),
                snake.getHead().y() + direction.getDeltaY()
        );
    }

    public void setDirection (Constants.Direction newDirection) {
        if (newDirection == null || newDirection == newDirection.opposite()) {
            return;
        }
        this.direction = newDirection;
    }

    public void handleKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> setDirection(Constants.Direction.UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> setDirection(Constants.Direction.DOWN);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> setDirection(Constants.Direction.LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> setDirection(Constants.Direction.RIGHT);
        }
    }

    private boolean isCollision(Tile newHead) {
        if (newHead.x() == food.x() && newHead.y() == food.y()) {
            System.out.println("s");
            grow();
            food = generateRandomFood();
            return false;
        }
        return snake.getBody().stream().anyMatch(newHead::equals);
    }

    private Tile generateRandomFood() {
        return new Tile(random.nextInt(Constants.BOARD_COLUMNS), random.nextInt(Constants.BOARD_ROWS));
    }

    private void grow() {
        Tile tile = snake.getLastPart();
        snake.addTile(tile.x() - direction.getDeltaX(), tile.y() - direction.getDeltaY());
    }

    private void updateFood(int x, int y) {
        this.food = new Tile(x, y);
    }

    public void gameOver() {
        System.out.println("game over");
    }

    public Tile getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }
}

