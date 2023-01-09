package ru.vsu.sps.gui;

import ru.vsu.sps.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    private static final int INFO_PANEL_SIZE = 180;

    private static final int SHUFFLE_BUTTON_RIGHT_MARGIN = 20;
    private static final int SHUFFLE_BUTTON_TOP_MARGIN = 50;
    private static final int SHUFFLE_BUTTON_WIDTH = 140;
    private static final int SHUFFLE_BUTTON_HEIGHT = 30;
    private static final String SHUFFLE_BUTTON_TEXT = "Shuffle";

    private static final int COMPLETED_COLUMNS_LABEL_RIGHT_MARGIN = 20;
    private static final int COMPLETED_COLUMNS_LABEL_TOP_MARGIN = 90;
    private static final int COMPLETED_COLUMNS_LABEL_WIDTH = 140;
    private static final int COMPLETED_COLUMNS_LABEL_HEIGHT = 30;

    private static final String gameOverText = "You win! Next level?";

    private final JButton shuffleButton;
    private final JLabel completedColumnsLabel;
    private final RenderGameBoardPanel renderGameBoardPanel;

    private final Game game;

    public MainFrame() {
        game = new Game();
        renderGameBoardPanel = new RenderGameBoardPanel(game);
        shuffleButton = new JButton();
        completedColumnsLabel = new JLabel();

        initializeUi();
        updateCompletedColumnsLabel();
        renderGameBoardPanel.requestFocus();

        shuffleButton.addActionListener(e -> {
            game.shuffleBoard();
            updateGameInfoAndRenderBoard();
            renderGameBoardPanel.requestFocus();
        });

        renderGameBoardPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> game.moveRotationAreaUp();
                    case KeyEvent.VK_DOWN -> game.moveRotationAreaDown();
                    case KeyEvent.VK_RIGHT -> game.moveRotationAreaRight();
                    case KeyEvent.VK_LEFT -> game.moveRotationAreaLeft();
                    case KeyEvent.VK_Z -> game.rotateLeft();
                    case KeyEvent.VK_X -> game.rotateRight();
                }
                updateGameInfoAndRenderBoard();

                if (game.isGameOver()) {
                    JOptionPane.showConfirmDialog(null, gameOverText, "",
                            JOptionPane.DEFAULT_OPTION);
                    game.nextLevel();
                    updateGameInfoAndRenderBoard();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void updateGameInfoAndRenderBoard() {
        renderGameBoardPanel.repaint();
        updateCompletedColumnsLabel();
    }

    private void updateCompletedColumnsLabel() {
        completedColumnsLabel.setText("Columns " + game.getCompletedColumnsCount() + "/" + Game.BOARD_SIZE);
    }

    private void initializeUi() {
        setTitle("Quadro");

        renderGameBoardPanel.setLayout(null);
        Dimension windowsSize = getContentPaneSize();
        renderGameBoardPanel.setPreferredSize(windowsSize);
        setContentPane(renderGameBoardPanel);

        shuffleButton.setBounds(windowsSize.width - SHUFFLE_BUTTON_WIDTH - SHUFFLE_BUTTON_RIGHT_MARGIN,
                SHUFFLE_BUTTON_TOP_MARGIN,
                SHUFFLE_BUTTON_WIDTH, SHUFFLE_BUTTON_HEIGHT);
        shuffleButton.setText(SHUFFLE_BUTTON_TEXT);


        renderGameBoardPanel.add(completedColumnsLabel);
        renderGameBoardPanel.add(shuffleButton);

        completedColumnsLabel.setBounds(windowsSize.width -
                COMPLETED_COLUMNS_LABEL_RIGHT_MARGIN - COMPLETED_COLUMNS_LABEL_WIDTH,
                COMPLETED_COLUMNS_LABEL_TOP_MARGIN, COMPLETED_COLUMNS_LABEL_WIDTH, COMPLETED_COLUMNS_LABEL_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private Dimension getContentPaneSize() {
        Dimension dimension = new Dimension();
        dimension.setSize(Game.BOARD_SIZE * RenderGameBoardPanel.CELL_SIZE + INFO_PANEL_SIZE,
                Game.BOARD_SIZE * RenderGameBoardPanel.CELL_SIZE);
        return dimension;
    }
}
