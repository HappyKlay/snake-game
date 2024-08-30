import java.util.Random;

public class GameLogic  {
    private final Random random = new Random();
    private final Tile food = new Tile(random.nextInt(Constants.BOARD_WIDTH/Constants.TILE_SIZE),
                                 random.nextInt(Constants.BOARD_HEIGHT/Constants.TILE_SIZE));
    private final Snake snake = new Snake();
    private int velocityX;
    private int velocityY;

    public Tile getFood() {
        return food;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public Snake getSnake() {
        return snake;
    }
}

