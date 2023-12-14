package org.cis1200.snakegame;

import java.awt.Graphics;
import javax.swing.JComponent;

public abstract class GameEntity extends JComponent implements Collidable {
    private boolean[][] positions;

    public boolean[][] getPositions() {
        return positions;
    }

    public void setPositions(boolean[][] positions) {
        this.positions = positions;
    }

    public GameEntity() {
        this.positions = new boolean[20][20];
    }

    public boolean[][] computeFuturePositions() {
        return positions;
    }

    public void handleCollision(Collidable other) {
    };

    public boolean willCollideWith(Collidable other) {
        boolean[][] otherNextPositions = other.computeFuturePositions();
        boolean[][] nextPositions = this.computeFuturePositions();
        int positionsLength = nextPositions.length;
        int otherPositionsLength = otherNextPositions.length;

        for (int i = 0; i < positionsLength; i++) {
            boolean[] positionsRow = nextPositions[i];
            int positionsRowLength = positionsRow.length;

            for (int j = 0; j < positionsRowLength; j++) {
                boolean currentPosition = nextPositions[i][j];

                for (int k = 0; k < otherPositionsLength; k++) {
                    boolean[] otherPositionsRow = otherNextPositions[k];
                    int otherPositionsRowLength = otherPositionsRow.length;

                    for (int l = 0; l < otherPositionsRowLength; l++) {
                        boolean otherPosition = otherNextPositions[k][l];

                        if (currentPosition && otherPosition && i == k && j == l) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Method to be implemented by subclasses to draw themselves
    protected abstract void paintComponent(Graphics g);
}