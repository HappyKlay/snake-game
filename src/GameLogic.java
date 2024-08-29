import java.util.Random;

public class GameLogic  {
    private Tile food = new Tile(Constants.NULL, Constants.NULL);
    private Snake snake = new Snake();
    private Random random;
    private int velocityX;
    private int velocityY;
    public GameLogic(Random random) {
        this.random = random;

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
}

