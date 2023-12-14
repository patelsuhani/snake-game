package org.cis1200.snakegame;

import javax.swing.*;
import java.awt.*;

public class CheckeredBackground extends JComponent {
    private final int squareSize;

    public CheckeredBackground(int squareSize) {
        this.squareSize = squareSize;
        setPreferredSize(
                new Dimension(
                        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                )
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor((i + j) % 2 == 0 ? new Color(176, 247, 185) : new Color(216, 247, 176));
                g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
    }
}
