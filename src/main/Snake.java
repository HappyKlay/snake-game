package main;

import java.util.*;

public class Snake {
    private final Deque<Tile> body = new ArrayDeque<Tile>();

    public Snake() {
        body.add(new Tile(Constants.SNAKE_HEAD_START_X, Constants.SNAKE_HEAD_START_Y));
        body.add((new Tile(Constants.SNAKE_HEAD_START_X, Constants.SNAKE_HEAD_START_Y + 1)));
        body.add((new Tile(Constants.SNAKE_HEAD_START_X, Constants.SNAKE_HEAD_START_Y + 2)));
    }

    public void removeLastPart() {
        body.removeLast();
    }

    public Tile getHead() {
        return body.getFirst();
    }

    public void setHead(Tile snakeHead) {
        body.addFirst(snakeHead);
    }

    public Deque<Tile> getBody() {
        return body;
    }

    public void addTile(Tile tile) {
        body.add(new Tile(tile.x(), tile.y()));
    }

    public void addTile(int x, int y) {
        body.add(new Tile(x, y));
    }

    public Tile getLastPart() {
        return body.getLast();
    }

    public int getSnakeBodyLength() {
        return body.size();
    }

}
