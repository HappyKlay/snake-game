import java.util.LinkedList;
import java.util.List;

public class Snake {
    private Tile snakeHead = new Tile(Constants.SNAKE_HEAD_START_X, Constants.SNAKE_HEAD_START_Y);
    private final List<Tile> snakeBody = new LinkedList<>();

    public Tile getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Tile snakeHead) {
        this.snakeHead = snakeHead;
    }

    public List<Tile> getSnakeBody() {
        return snakeBody;
    }
}
