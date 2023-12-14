package org.cis1200.snakegame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import org.cis1200.snakegame.savehelpers.GameDataManager;
import org.cis1200.snakegame.savehelpers.GameSaveState;

public class RunSnakeGame {
    private static int score;
    private static GameEntity[] gameEntities;
    private static Direction selectedDirection = Direction.DOWN;
    private static boolean gamePaused = true;
    private static boolean gameOver = false;

    public static void start() {
        JFrame frame = new JFrame("Snake RunSnakeGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel cards = new JPanel(new CardLayout());

        JPanel gameplayScreen = new JPanel(new BorderLayout());
        JLayeredPane gameArea = new JLayeredPane();
        gameArea.setPreferredSize(new Dimension(400, 400));
        CheckeredBackground backgroundPanel = new CheckeredBackground(20);
        Snake snake = new Snake();
        SnakeGameBorder border = new SnakeGameBorder();
        Fruit fruit = new Fruit(new GameEntity[] { snake, border });
        gameEntities = new GameEntity[] { fruit, border, snake };

        // Set panel sizes and positions (gameArea contents)
        backgroundPanel.setBounds(0, 0, 400, 400);
        border.setBounds(0, 0, 400, 400);
        fruit.setBounds(0, 0, 400, 400);
        snake.setBounds(0, 0, 400, 400);

        // Add panels to the layered pane (gameArea)
        gameArea.add(backgroundPanel, Integer.valueOf(0));
        gameArea.add(border, Integer.valueOf(1));
        gameArea.add(fruit, Integer.valueOf(2));
        gameArea.add(snake, Integer.valueOf(3));

        JPanel gameAreaWrapper = new JPanel(new GridBagLayout());
        gameAreaWrapper.add(gameArea);
        gameplayScreen.add(gameAreaWrapper, BorderLayout.CENTER);

        // Gameplay header
        JPanel gameplayHeader = new JPanel();
        gameplayHeader.setLayout(new BoxLayout(gameplayHeader, BoxLayout.Y_AXIS));

        // Gameplay header score (will show up below the buttons, but needs to be
        // created first so it can be used with the button handlers)
        JLabel scoreLabel = new JLabel("Score: " + score);

        // Gameplay header buttons
        JPanel gameplayHeaderBtnRow = new JPanel(new FlowLayout());
        JButton backBtn = new JButton("< Back to Menu (B)");

        JButton pauseBtn = new JButton("Pause (or press ESC)");
        pauseBtn.addActionListener(e -> {
            gamePaused = !gamePaused;
            pauseBtn.setText(gamePaused ? "Resume (or press a key)" : "Pause (or press ESC)");
            gameArea.requestFocusInWindow();
        });

        JButton resetBtn = new JButton("Restart (R)");
        resetBtn.addActionListener(e -> {
            restartGame(gameArea, snake, scoreLabel, pauseBtn);
        });

        backBtn.addActionListener(e -> {
            if (!gamePaused) {
                pauseBtn.doClick();
            }
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "menu");
        });

        JButton helpBtn = new JButton("Help (H)");
        helpBtn.addActionListener(e -> {
            if (!gamePaused) {
                pauseBtn.doClick();
            }
            HelpPopup.show();
        });

        JButton saveButton = new JButton("Save (S)");
        saveButton.addActionListener(e -> {
            if (!gamePaused) {
                pauseBtn.doClick();
            }
            if (!gameOver) {
                GameDataManager.saveGameState(snake, fruit, score);
                JOptionPane.showMessageDialog(
                        null, "RunSnakeGame saved!", "Save successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        null, "RunSnakeGame not saved because the game is over!",
                        "Save unsuccessful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JButton saveAndQuitBtn = new JButton("Save and Quit");
        saveAndQuitBtn.addActionListener(e -> {
            GameDataManager.saveGameState(snake, fruit, score);
            System.exit(0);
        });
        JButton quitBtn = new JButton("Quit (Q)");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });

        gameplayHeaderBtnRow.add(backBtn);
        gameplayHeaderBtnRow.add(resetBtn);
        gameplayHeaderBtnRow.add(quitBtn);
        gameplayHeaderBtnRow.add(pauseBtn);
        gameplayHeaderBtnRow.add(helpBtn);
        gameplayHeaderBtnRow.add(saveButton);
        gameplayHeaderBtnRow.add(saveAndQuitBtn);
        gameplayHeader.add(gameplayHeaderBtnRow);

        gameplayHeader.add(scoreLabel);

        gameplayScreen.add(gameplayHeader, BorderLayout.NORTH);

        gameArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gamePaused) {
                    pauseBtn.doClick();
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        RunSnakeGame.selectedDirection = Direction.UP;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        RunSnakeGame.selectedDirection = Direction.DOWN;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        RunSnakeGame.selectedDirection = Direction.LEFT;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        RunSnakeGame.selectedDirection = Direction.RIGHT;
                        break;
                    }
                    case KeyEvent.VK_ESCAPE: {
                        pauseBtn.doClick();
                        break;
                    }
                    case KeyEvent.VK_S: {
                        saveButton.doClick();
                        break;
                    }
                    case KeyEvent.VK_Q: {
                        quitBtn.doClick();
                        break;
                    }
                    case KeyEvent.VK_H: {
                        helpBtn.doClick();
                        break;
                    }
                    case KeyEvent.VK_R: {
                        resetBtn.doClick();
                        break;
                    }
                    case KeyEvent.VK_B: {
                        backBtn.doClick();
                        break;
                    }
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        MenuScreen menuScreen = new MenuScreen(
                (CardLayout) cards.getLayout(), cards,
                new Runnable() {
                    @Override
                    public void run() {
                        restartGame(gameArea, snake, scoreLabel, pauseBtn);
                    }
                },
                new Consumer<GameSaveState>() {
                    @Override
                    public void accept(GameSaveState t) {
                        gameArea.requestFocusInWindow();
                        snake.setBody(t.snake().cells());
                        snake.setHeadRowCol(t.snake().head());
                        snake.setTailRowCol(t.snake().tail());
                        selectedDirection = snake.getDirection();
                        fruit.setRowCol(t.fruit());
                        setScore(scoreLabel, t.score());
                        if (!gamePaused) {
                            pauseBtn.doClick();
                        }
                        gameOver = false;
                        JOptionPane.showMessageDialog(
                                null,
                                "Saved game loaded successfully. After closing this" +
                                        " pop-up, press any"
                                        +
                                        "arrow-key to get right into the game!",
                                "Load successful", JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
        );

        // Card first added is the one that is shown first
        cards.add(menuScreen, "menu");
        cards.add(gameplayScreen, "game");

        frame.add(cards);
        frame.setLocation(500, 500);
        frame.setVisible(true);

        Timer timer = new Timer(200, e -> {
            if (gamePaused || gameOver) {
                return;
            }
            snake.setDirection(selectedDirection);
            if (snake.willCollideWith(fruit)) {
                snake.handleCollision(fruit);
                fruit.handleCollision(snake);
                setScore(scoreLabel, score + 1);
            } else if (snake.willCollideWith(border)) {
                snake.handleCollision(border);
                handleGameOver(pauseBtn, scoreLabel);
            } else if (snake.willCollideWith(snake)) {
                snake.handleCollision(snake);
                handleGameOver(pauseBtn, scoreLabel);
            }
            if (!gameOver) {
                snake.move(gameEntities);
            }
        });
        timer.start();
    }

    protected static void setScore(JLabel scoreLabel, int newScore) {
        scoreLabel.setText("Score: " + newScore);
        score = newScore;
    }

    private static void handleGameOver(JButton pauseBtn, JLabel scoreLabel) {
        scoreLabel.setText("RunSnakeGame Over! Final Score: " + score);
        JOptionPane.showMessageDialog(
                null, "RunSnakeGame over! Your final score was: " + score + ".",
                "RunSnakeGame over",
                JOptionPane.INFORMATION_MESSAGE
        );
        pauseBtn.doClick();
        gameOver = true;
    }

    private static void restartGame(
            JLayeredPane gameArea, Snake snake, JLabel scoreLabel, JButton pauseBtn
    ) {
        gameArea.requestFocusInWindow();
        snake.reset();
        RunSnakeGame.selectedDirection = Direction.DOWN;
        setScore(scoreLabel, 0);
        if (gamePaused) {
            pauseBtn.doClick();
        }
        gameOver = false;
    }
}