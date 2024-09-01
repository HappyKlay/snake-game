package main;

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
        // food
        graphics.setColor(Constants.FOOD_COLOR);
        graphics.fill3DRect(food.x() * Constants.TILE_SIZE, food.y() * Constants.TILE_SIZE,
                            Constants.TILE_SIZE, Constants.TILE_SIZE, true);

        for (Tile tile:
                snake.getBody()) {
            System.out.println(tile.x() + "    " + tile.y());
        }
        System.out.println("\n\n");

        // body
        Tile temp = snake.getHead();
        for (Tile tile :
                snake.getBody()) {

//            tile = new Tile(temp.x() + , temp.y())


            graphics.setColor(Color.blue);
            graphics.fill3DRect(tile.x() * Constants.TILE_SIZE, tile.y() * Constants.TILE_SIZE,
                                    Constants.TILE_SIZE, Constants.TILE_SIZE, true);
        }



//         head
        graphics.setColor(Constants.SNAKE_COLOR);
        graphics.fill3DRect(snake.getHead().x() * Constants.TILE_SIZE, snake.getHead().y() * Constants.TILE_SIZE,
                Constants.TILE_SIZE, Constants.TILE_SIZE, true);
    }
}
