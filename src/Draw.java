import java.awt.*;

public class Draw {
    public void drawGrid(Graphics graphics) {
        for (int i = 0; i < Constants.boardHeight/Constants.tileSize; i++) {
            int position = i * Constants.tileSize;
            graphics.drawLine(0, position, Constants.boardWidth, position);
            graphics.drawLine(position, 0, position, Constants.boardHeight);
        }
    }
}
