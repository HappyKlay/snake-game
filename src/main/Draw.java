package main;

import java.awt.*;

public class Draw {
    private static final Color LIGHT_TILE_COLOR = new Color(170, 215, 81);
    private static final Color DARK_TILE_COLOR = new Color(162, 209, 73);
    private static final Color BORDER_COLOR = new Color(87, 138, 52);
    private static final Color SCORE_BACKGROUND_COLOR = new Color(59, 94, 35);
    private static final Font SCORE_FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 30);
    private static final Color GAME_OVER_BACKGROUND_COLOR = new Color(0, 0, 0, 150);
    private static final Font GAME_OVER_FONT = new Font("Serif", Font.BOLD, 80);
    private static final Font INSTRUCTION_FONT = new Font("Serif", Font.PLAIN, 30);
    private static final Color GAME_OVER_TEXT_COLOR = Color.RED;
    private static final Color INSTRUCTION_TEXT_COLOR = Color.WHITE;
    private static final float BACKGROUND_ALPHA = 0.6f;
    private Color getTileColor(int row, int col) {
        return (row + col) % 2 == 0 ? LIGHT_TILE_COLOR : DARK_TILE_COLOR;
    }

    private void drawGrid(Graphics2D g2d) {
        int rows = Constants.BOARD_HEIGHT / Constants.TILE_SIZE;
        int cols = Constants.BOARD_WIDTH / Constants.TILE_SIZE;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                g2d.setColor(getTileColor(row, col));
                g2d.fillRect(
                        col * Constants.TILE_SIZE + Constants.BORDER_WIDTH,
                        row * Constants.TILE_SIZE + Constants.BORDER_WIDTH + Constants.TILE_SIZE,
                        Constants.TILE_SIZE, Constants.TILE_SIZE
                );
            }
        }
    }

    public void drawTiles(Graphics graphics, Snake snake, Tile food) {
        Graphics2D g2d = (Graphics2D) graphics;

        // Draws food
        g2d.setColor(Constants.FOOD_COLOR);
        g2d.fillOval(
                food.x() * Constants.TILE_SIZE,
                food.y() * Constants.TILE_SIZE,
                Constants.TILE_SIZE,
                Constants.TILE_SIZE
        );

        // Draws snake's body
        for (Tile tile : snake.getBody()) {
            g2d.setColor(Color.blue);
            g2d.fillOval(tile.x() * Constants.TILE_SIZE, tile.y() * Constants.TILE_SIZE,
                                    Constants.TILE_SIZE, Constants.TILE_SIZE);
        }

//         Draws snake's head
        g2d.setColor(Constants.SNAKE_COLOR);
        g2d.fillOval(snake.getHead().x() * Constants.TILE_SIZE, snake.getHead().y() * Constants.TILE_SIZE,
                Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void drawScore(Graphics graphics, Score score) {
        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(SCORE_BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.TILE_SIZE);

        String scoreText = "SCORE: " + score.getScore();

        // Draw shadow for score text
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(SCORE_FONT);
        g2d.drawString(scoreText, 11, 31);

        // Draw main score text
        g2d.setColor(Color.WHITE);
        g2d.drawString(scoreText, 10, 30);
    }

    public void drawGameWindow(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(SCORE_BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.TILE_SIZE);

        g2d.setColor(BORDER_COLOR);
        g2d.fillRect(0, Constants.TILE_SIZE + Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.BOARD_HEIGHT);
        g2d.fillRect(0, Constants.SCREEN_HEIGHT - Constants.BORDER_WIDTH, Constants.SCREEN_WIDTH, Constants.TILE_SIZE / 2);
        g2d.fillRect(Constants.SCREEN_WIDTH - Constants.TILE_SIZE / 2, Constants.TILE_SIZE + Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.BOARD_HEIGHT);
        g2d.fillRect(0, Constants.TILE_SIZE, Constants.SCREEN_WIDTH, Constants.BORDER_WIDTH);

        drawGrid(g2d);
    }

    public void drawGameOver(Graphics2D g2d) {
        Composite originalComposite = g2d.getComposite();

        try {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, BACKGROUND_ALPHA));
            drawGameOverBackground(g2d);

            g2d.setComposite(originalComposite);

            drawCenteredText(g2d, "GAME OVER", GAME_OVER_FONT, GAME_OVER_TEXT_COLOR, Constants.SCREEN_HEIGHT / 2);

            drawCenteredText(g2d, "Press R to Restart or Q to Quit", INSTRUCTION_FONT, INSTRUCTION_TEXT_COLOR,
                    Constants.SCREEN_HEIGHT / 2 + 80);
        } finally {
            g2d.setComposite(originalComposite);
        }
    }

    private void drawGameOverBackground(Graphics2D g2d) {
        g2d.setColor(GAME_OVER_BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    private void drawCenteredText(Graphics2D g2d, String text, Font font, Color color, int yPosition) {
        g2d.setFont(font);
        g2d.setColor(color);
        
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = (Constants.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        
        g2d.drawString(text, x, yPosition);
    }
}
