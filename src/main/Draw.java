package main;

import java.awt.*;
import java.util.Deque;


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

        drawFood(g2d, food);
        drawSnake(g2d, snake);
    }

    private void drawFood(Graphics2D g2d, Tile food) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int foodX = food.x() * Constants.TILE_SIZE;
        int foodY = food.y() * Constants.TILE_SIZE;

        g2d.setColor(new Color(231, 71, 29));
        g2d.fillOval(foodX - Constants.BORDER_WIDTH + 8, foodY - Constants.BORDER_WIDTH + 8, (int) (Constants.TILE_SIZE / 1.5), (int) (Constants.TILE_SIZE / 1.5));

        g2d.setColor(new Color(236, 115, 90));
        g2d.fillArc(foodX - 10, foodY - 11, 10, 20, 50, 150);

        g2d.setColor(new Color(151, 110, 76));
        g2d.fillRect(foodX - 1, foodY - 23, 4, 10);

        g2d.setColor(new Color(72, 196, 39));
        g2d.fillOval(foodX - 1, foodY - 23, 10, 6);
    }

    private void drawSnake(Graphics2D g2d, Snake snake) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Deque<Tile> body = snake.getBody();

        boolean isHead = true;
        for (Tile tile : body) {
            int tileX = tile.x() * Constants.TILE_SIZE - Constants.BORDER_WIDTH;
            int tileY = tile.y() * Constants.TILE_SIZE - Constants.BORDER_WIDTH;

            if (isHead) {
                g2d.setColor(Color.RED);
                g2d.fillOval(tileX, tileY, Constants.TILE_SIZE, Constants.TILE_SIZE);

                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillOval(tileX + Constants.TILE_SIZE / 4, tileY + Constants.TILE_SIZE / 4,
                        Constants.TILE_SIZE / 2, Constants.TILE_SIZE / 2);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(tileX, tileY, Constants.TILE_SIZE, Constants.TILE_SIZE);

                isHead = false;
            } else {
                GradientPaint gradient = new GradientPaint(tileX, tileY, Color.BLUE,
                        tileX + Constants.TILE_SIZE, tileY + Constants.TILE_SIZE, Color.CYAN);
                g2d.setPaint(gradient);
                g2d.fillOval(tileX, tileY, Constants.TILE_SIZE, Constants.TILE_SIZE);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));  // Border thickness
                g2d.drawOval(tileX, tileY, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }
    }

    public void drawScore(Graphics graphics, Score score) {
        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(SCORE_BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.TILE_SIZE);

        String scoreText = "SCORE: " + score.getScore();

        // Draw shadow for score text
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(SCORE_FONT);
        g2d.drawString(scoreText, 25, 36);

        // Draw main score text
        g2d.setColor(Color.WHITE);
        g2d.drawString(scoreText, 24, 35);
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
