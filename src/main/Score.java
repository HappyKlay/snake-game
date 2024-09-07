package main;

public class Score {
    private static int score;

    public Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public static void increaseScore() {
        score += 10;
    }
}
