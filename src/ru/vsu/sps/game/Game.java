package ru.vsu.sps.game;

import java.awt.*;

public class Game {
    final static int BOARD_SIZE = 5;
    final static int ROTATION_AREA_SIZE = 3;
    private final static Color[] usedColors = {Color.YELLOW, Color.RED};

    private final Color[][] board;
    private final Point rotationAreaCoordinates;

    public Game() {
        board = new Color[BOARD_SIZE][BOARD_SIZE];
        rotationAreaCoordinates = new Point(0, 0);

        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = usedColors[i % usedColors.length];
            }
        }
    }

    private void shuffleBoard() {

    }

    private void rotateAreaOn90Degrees(int rotateOffset) {

    }
    public void rotateLeft() {
        rotateAreaOn90Degrees(-1);
    }

    public void rotateRight() {
        rotateAreaOn90Degrees(1);
    }

    private void moveRotationAreaByY(int yOffset) {
        if (rotationAreaCoordinates.getY() + yOffset > 0 &&
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
        if (rotationAreaCoordinates.getX() + xOffset > 0 &&
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
}
