package ru.vsu.sps.gui;


import javax.swing.*;
import java.awt.*;

import ru.vsu.sps.game.Game;
import ru.vsu.sps.game.Point;

public class RenderGameBoardPanel extends JPanel {
    public static final int CELL_SIZE = 100;
    public static final int MARGIN = 25;

    private final Game game;
    private final Color[] usedColors = {Color.YELLOW, Color.RED, Color.ORANGE, Color.CYAN, Color.BLUE};

    public RenderGameBoardPanel(Game game) {
        super();

        this.game = game;
    }

    private boolean shouldRenderRotateArea(int x, int y) {
        int xOffset = x - game.getRotationAreaCoordinates().getX();
        int yOffset = y - game.getRotationAreaCoordinates().getY();

        return yOffset >= 0 && xOffset >= 0 && yOffset < Game.ROTATION_AREA_SIZE && xOffset < Game.ROTATION_AREA_SIZE;
    }

    private Color getColorByInt(int num) {
        return usedColors[num];
    }

    private void render(Graphics graphics) {
        int size = CELL_SIZE - MARGIN * 2;

        for (int i = 0; i < Game.BOARD_SIZE; i++) {
            for (int j = 0; j < Game.BOARD_SIZE; j++) {
                graphics.setColor(getColorByInt(game.getBoard()[i][j]));

                int x = j * CELL_SIZE + MARGIN;
                int y = i * CELL_SIZE + MARGIN;

                if (shouldRenderRotateArea(j, i)) {
                    graphics.fillOval(x, y, size, size);
                }
                else {
                    graphics.fillRect(x, y, size, size);
                }
            }

            Point rectCoordinates = game.getRotationAreaCoordinates();
            graphics.setColor(Color.BLACK);
            graphics.drawRect(rectCoordinates.getX() * CELL_SIZE, rectCoordinates.getY() * CELL_SIZE,
                    Game.ROTATION_AREA_SIZE * CELL_SIZE, Game.ROTATION_AREA_SIZE * CELL_SIZE);
        }

        graphics.setColor(Color.GRAY);
    }

    private void paintBorders(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, CELL_SIZE * Game.BOARD_SIZE, CELL_SIZE * Game.BOARD_SIZE);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        render(graphics);
        paintBorders(graphics);
    }
}
