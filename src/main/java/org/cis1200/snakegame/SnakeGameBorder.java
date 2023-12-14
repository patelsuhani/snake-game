package org.cis1200.snakegame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakeGameBorder extends GameEntity {

    private BufferedImage borderTile;

    public SnakeGameBorder() {
        super();
        for (int i = 0; i < getPositions().length; i++) {
            getPositions()[i][0] = true;
            getPositions()[i][getPositions().length - 1] = true;
            getPositions()[0][i] = true;
            getPositions()[getPositions().length - 1][i] = true;
        }
        setOpaque(false);
        setPreferredSize(new Dimension(400, 400));

        try {
            borderTile = ImageIO.read(new File("res/wood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    protected void paintComponent(Graphics g) {
        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, 20, getHeight()); // Left border
        // g.fillRect(getWidth() - 20, 0, 20, getHeight()); // Right border
        // g.fillRect(0, 0, getWidth(), 20); // Top border
        // g.fillRect(0, getHeight() - 20, getWidth(), 20); // Bottom border

        for (int i = 0; i < getPositions().length; i++) {
            for (int j = 0; j < getPositions()[i].length; j++) {
                if (getPositions()[i][j]) {
                    g.drawImage(borderTile, j * 20, i * 20, 20, 20, null);
                }
            }
        }
    }
}