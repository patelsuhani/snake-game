package org.cis1200.snakegame;

import java.awt.CardLayout;

import javax.swing.*;
import java.awt.BorderLayout;

import org.cis1200.snakegame.savehelpers.GameDataManager;
import org.cis1200.snakegame.savehelpers.GameSaveState;

import java.util.function.Consumer;

public class MenuScreen extends JPanel {
    public MenuScreen(
            CardLayout cardLayout, JPanel cards, Runnable startGame,
            Consumer<GameSaveState> loadGame
    ) {
        JButton startButton = new JButton("Start RunSnakeGame");
        startButton.addActionListener(e -> {
            cardLayout.show(cards, "game");
            startGame.run();
        });
        add(startButton);

        JButton loadButton = new JButton("Load RunSnakeGame");
        loadButton.addActionListener(e -> {
            GameSaveState g = GameDataManager.loadGameState();
            if (g != null) {
                cardLayout.show(cards, "game");
                loadGame.accept(g);
            }
        });
        add(loadButton);

        JButton helpBtn = new JButton("Help");
        helpBtn.addActionListener(e -> {
            HelpPopup.show();
        });
        add(helpBtn);

        JButton quitButton = new JButton("Quit RunSnakeGame");
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        add(quitButton);

        JPanel mainMenuImage = new JPanel();
        // add title image from res/main.png
        JLabel mainMenuLabel = new JLabel(new ImageIcon("res/main.png"));
        mainMenuImage.add(mainMenuLabel);
        add(mainMenuImage, BorderLayout.CENTER);
    }
}