package main;

import java.util.*;

public class Snake {
    private final Deque<Tile> body = new ArrayDeque<Tile>();

    public Snake() {
        body.add(new Tile(Constants.SNAKE_HEAD_START_X, Constants.SNAKE_HEAD_START_Y));
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

    public void grow() {
        Tile tail = body.getLast();
        body.add(new Tile(tail.x(), tail.y()));
    }

    public Tile getLastPart() {
        return body.getLast();
    }

    public int getSnakeBodyLength() {
        return body.size();
    }

}
