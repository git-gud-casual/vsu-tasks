package ru.vsu.sps.game;


import java.util.Random;

public class Game {
    public static final int BOARD_SIZE = 5;
    public static final int ROTATION_AREA_SIZE = 3;
    public final static int MAX_COLORS_COUNT = 5;
    public final static int MAX_SHUFFLES_COUNT = 200;

    private final int[][] board;
    private Point rotationAreaCoordinates;
    private final Random random = new Random();
    private int colorsCount;

    public Game() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        colorsCount = 2;
        initGame();
    }

    private void initGame() {
        rotationAreaCoordinates = new Point(0, 0);
        fillBoard();
        shuffleBoard();
    }

    public void nextLevel() {
        if (colorsCount < MAX_COLORS_COUNT) {
            colorsCount++;
        }
        initGame();
    }

    private void fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = j % colorsCount;
            }
        }
    }

    public void shuffleBoard() {
        Point currentRotationAreaCoordinates = new Point(rotationAreaCoordinates);

        for (int shuffleCount = 0;
             shuffleCount < random.nextInt(MAX_SHUFFLES_COUNT) + 1;
             shuffleCount++) {

            int yOffset = random.nextInt(BOARD_SIZE - ROTATION_AREA_SIZE + 1) - rotationAreaCoordinates.getY();
            int xOffset = random.nextInt(BOARD_SIZE - ROTATION_AREA_SIZE + 1) - rotationAreaCoordinates.getX();

            moveRotationAreaByY(yOffset);
            moveRotationAreaByX(xOffset);
            rotateArea(random.nextInt(3) + 1);
        }
        rotationAreaCoordinates = currentRotationAreaCoordinates;
    }

    private void rotateArea(int by90DegreesRotationCount) {
        int xOffset = rotationAreaCoordinates.getX();
        int yOffset = rotationAreaCoordinates.getY();

        for (int i = 0; i < ROTATION_AREA_SIZE / 2; i++) {
            for (int j = i; j < ROTATION_AREA_SIZE - i - 1; j++) {
                Point topPoint = new Point(j + xOffset, i + yOffset);
                Point bottomPoint = new Point(ROTATION_AREA_SIZE - 1 - j + xOffset,
                        ROTATION_AREA_SIZE - 1 - i + yOffset);
                Point rightPoint = new Point(ROTATION_AREA_SIZE - 1 - i + xOffset, j + yOffset);
                Point leftPoint = new Point(i + xOffset, ROTATION_AREA_SIZE - 1 - j + yOffset);

                int rotateCount = by90DegreesRotationCount;
                while (rotateCount-- > 0) {
                    int swap = board[topPoint.getY()][topPoint.getX()];

                    board[topPoint.getY()][topPoint.getX()] = board[leftPoint.getY()][leftPoint.getX()];
                    board[leftPoint.getY()][leftPoint.getX()] = board[bottomPoint.getY()][bottomPoint.getX()];
                    board[bottomPoint.getY()][bottomPoint.getX()] = board[rightPoint.getY()][rightPoint.getX()];
                    board[rightPoint.getY()][rightPoint.getX()] = swap;
                }
            }
        }
    }

    public void rotateLeft() {
        rotateArea(3);
    }

    public void rotateRight() {
        rotateArea(1);
    }

    private void moveRotationAreaByY(int yOffset) {
        if (rotationAreaCoordinates.getY() + yOffset >= 0 &&
                rotationAreaCoordinates.getY() + ROTATION_AREA_SIZE + yOffset <= BOARD_SIZE) {
            rotationAreaCoordinates.setY(rotationAreaCoordinates.getY() + yOffset);
        }
    }

    public void moveRotationAreaUp() {
        moveRotationAreaByY(-1);
    }

    public void moveRotationAreaDown() {
        moveRotationAreaByY(1);
    }

    private void moveRotationAreaByX(int xOffset) {
        if (rotationAreaCoordinates.getX() + xOffset >= 0 &&
                rotationAreaCoordinates.getX() + ROTATION_AREA_SIZE + xOffset <= BOARD_SIZE) {
            rotationAreaCoordinates.setX(rotationAreaCoordinates.getX() + xOffset);
        }
    }

    public void moveRotationAreaLeft() {
        moveRotationAreaByX(-1);
    }

    public void moveRotationAreaRight() {
        moveRotationAreaByX(1);
    }

    public int[][] getBoard() {
        return board;
    }

    public Point getRotationAreaCoordinates() {
        return rotationAreaCoordinates;
    }

    public int getCompletedColumnsCount() {
        int count = BOARD_SIZE;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE - 1; j++) {
                if (board[j][i] != board[j + 1][i]) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

    public boolean isGameOver() {
        return getCompletedColumnsCount() == BOARD_SIZE;
    }
}
