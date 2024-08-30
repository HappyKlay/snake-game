import java.awt.event.KeyEvent;
import java.util.Random;

public class GameLogic  {
    private final Random random = new Random();
    private final Tile food = new Tile(random.nextInt(Constants.BOARD_WIDTH/Constants.TILE_SIZE),
                                 random.nextInt(Constants.BOARD_HEIGHT/Constants.TILE_SIZE));
    private final Snake snake = new Snake();
    private Constants.Direction direction = Constants.Direction.NONE;

    public void move() {
//  TODO
    }

    public void setDirection (Constants.Direction direction) {
        this.direction = direction;
    }

    public void handleKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> setDirection(Constants.Direction.UP);
            case KeyEvent.VK_DOWN -> setDirection(Constants.Direction.DOWN);
            case KeyEvent.VK_LEFT -> setDirection(Constants.Direction.LEFT);
            case KeyEvent.VK_RIGHT -> setDirection(Constants.Direction.RIGHT);
        }
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

