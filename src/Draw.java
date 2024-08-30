import java.awt.*;

public class Draw {
    public void drawGrid(Graphics graphics) {
        for (int i = 0; i < Constants.BOARD_HEIGHT / Constants.TILE_SIZE; i++) {
            int position = i * Constants.TILE_SIZE;
            graphics.drawLine(0, position, Constants.BOARD_WIDTH, position);
            graphics.drawLine(position, 0, position, Constants.BOARD_HEIGHT);
        }
    }

    public void drawTiles(Graphics graphics, Snake snake, Tile food) {
        graphics.setColor(Constants.FOOD_COLOR);
        graphics.fill3DRect(food.x() * Constants.TILE_SIZE, food.y() * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE, true);

        graphics.setColor(Constants.SNAKE_COLOR);
        graphics.fill3DRect(snake.getSnakeHead().x() * Constants.TILE_SIZE, snake.getSnakeHead().y() * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE, true);
    }
}
