package main;

import java.awt.event.KeyEvent;
import java.util.*;

public class GameLogic  {
    private final Random random = new Random();
    private Tile food = new Tile(10, 8);
    private Snake snake = new Snake();
    private boolean gameOver = false;
    private Constants.Direction previousDirection = Constants.Direction.NONE;
    private Constants.Direction direction = Constants.Direction.NONE;

    public void move() {
        if (direction == Constants.Direction.NONE || gameOver) {
            return;
        }

        Tile newHead = calculateNewHeadPosition();

        if (isMovingIntoHimself(newHead)) {
            return;
        }

        if (isCollision(newHead)) {
            gameOver();
            return;
        }

        previousDirection = direction;
        snake.setHead(newHead);
        snake.removeLastPart();
    }


    private boolean isMovingIntoHimself(Tile head) {
        Iterator<Tile> iterator = snake.getBody().iterator();
        if (!iterator.hasNext()) {
            return false;
        }

        iterator.next();

        if (iterator.hasNext()) {
            Tile tile = iterator.next();
            return head.x() == tile.x() && head.y() == tile.y();
        }
        return false;
    }

    private Tile calculateNewHeadPosition() {
        return new Tile(
                snake.getHead().x() + direction.getDeltaX(),
                snake.getHead().y() + direction.getDeltaY()
        );
    }

    private void setDirection (Constants.Direction newDirection) {
        if (newDirection == null || newDirection == newDirection.opposite()) {
            return;
        }
        if (direction == Constants.Direction.NONE && newDirection == Constants.Direction.LEFT) {
            return;
        }
        if (previousDirection == direction.opposite() && direction != Constants.Direction.NONE) {
            return;
        }
        this.direction = newDirection;
    }
    public boolean wasMoved = false;
    public void handleKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                setDirection(Constants.Direction.UP);
                if (direction != previousDirection) {
                    move();
                    wasMoved = true;
                }
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                setDirection(Constants.Direction.DOWN);
                if (direction != previousDirection) {
                    move();
                    wasMoved = true;
                }
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                setDirection(Constants.Direction.LEFT);
                if (direction != previousDirection) {
                    move();
                    wasMoved = true;
                }
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                setDirection(Constants.Direction.RIGHT);
                if (direction != previousDirection) {
                    move();
                    wasMoved = true;
                }
            }
            case KeyEvent.VK_Q -> {
                if (isGameOver())
                    SnakeWindow.exit();
            }
            case KeyEvent.VK_ESCAPE -> SnakeWindow.exit();
            case KeyEvent.VK_R -> {
                if (isGameOver())
                    restartGame();
            }
        }
    }

    private void restartGame() {
        food = new Tile(10, 8);
        snake = new Snake();
        direction = Constants.Direction.NONE;
        gameOver = false;
    }
    private boolean isCollision(Tile newHead) {
        if (newHead.x() <= 0 || newHead.y() <= 1 || newHead.x() > Constants.BOARD_ROWS || newHead.y() > Constants.BOARD_COLUMNS + 1) {
            return true;
        }

        if (newHead.x() == food.x() && newHead.y() == food.y()) {
            grow();
            Score.increaseScore();
            food = generateRandomFood();
            return false;
        }
        return snake.getBody().stream().anyMatch(tile -> tile.x() == newHead.x() && tile.y() == newHead.y());
    }

    private Tile generateRandomFood() {
        int x, y;
        Tile newTile;

        do {
            x = random.nextInt(Constants.BOARD_COLUMNS - 1) + 1;
            y = random.nextInt(Constants.BOARD_ROWS ) + 2;
            newTile = new Tile(x, y);
        } while (isContainsFood(newTile));

        return newTile;
    }

    private boolean isContainsFood(Tile food) {
        return snake.getBody().stream().anyMatch(tile -> tile.x() == food.x() && tile.y() == food.y());
    }

    private void grow() {
        Tile tile = snake.getLastPart();
        snake.addTile(tile.x() - direction.getDeltaX(), tile.y() - direction.getDeltaY());
    }

    public void gameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Tile getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }
}

