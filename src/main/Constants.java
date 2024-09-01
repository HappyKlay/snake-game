package main;

import java.awt.*;

public class Constants {
    public static final int BOARD_WIDTH  = 800;
    public static final int BOARD_HEIGHT = 800;
    public static final int TILE_SIZE    = 20;


    public static final int SNAKE_HEAD_START_X = 20;
    public static final int SNAKE_HEAD_START_Y = 20;

    public static final Color FOOD_COLOR    = Color.red;
    public static final Color SNAKE_COLOR   = Color.green;
//    public static final Color SNAKE_HEAD_COLOR =
    public static final int BOARD_COLUMNS   = BOARD_HEIGHT / BOARD_WIDTH;
    public static final int BOARD_ROWS      = BOARD_HEIGHT / BOARD_WIDTH;

    public enum Direction {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0), NONE(0, 0);
        private final int deltaX;
        private final int deltaY;

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public Direction opposite() {
            return switch (this) {
                case UP -> DOWN;
                case DOWN -> UP;
                case LEFT -> RIGHT;
                case RIGHT -> LEFT;
                case NONE -> NONE;
            };
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }
}
