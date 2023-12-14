package org.cis1200.snakegame;

import java.awt.*;
import java.util.List;

public class Fruit extends GameEntity {
    private int originX;
    private int originY;
    private GameEntity[] gameEntities;

    public Fruit(GameEntity[] gameEntities) {
        resetLocation(gameEntities);
        this.gameEntities = gameEntities;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        g.setColor(Color.RED);
        g.fillOval(originX, originY, 20, 20);
    }

    private void resetLocation(GameEntity[] gameEntities) {
        int row = (int) (Math.random() * 20);
        int col = (int) (Math.random() * 20);
        boolean[][] pos = new boolean[20][20];
        pos[row][col] = true;
        setPositions(pos);

        while (true) {
            boolean collision = false;
            for (GameEntity gameEntity : gameEntities) {
                if (gameEntity.willCollideWith(this)) {
                    collision = true;
                    break;
                }
            }
            if (!collision) {
                break;
            }

            col++;
            if (col >= 20) {
                col = 0;
                row++;
                if (row >= 20) {
                    row = 0;
                }
            }
            pos = new boolean[20][20];
            pos[row][col] = true;
            setPositions(pos);
        }

        originX = col * 20;
        originY = row * 20;
        repaint();
    }

    @Override
    public boolean[][] computeFuturePositions() {
        return this.getPositions();
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other instanceof Snake) {
            resetLocation(this.gameEntities);
        }
    }

    public List<Integer> getRowCol() {
        return List.of(originY / 20, originX / 20);
    }

    public void setRowCol(List<Integer> fruit) {
        this.originY = fruit.get(0) * 20;
        this.originX = fruit.get(1) * 20;
        boolean[][] pos = new boolean[20][20];
        pos[fruit.get(0)][fruit.get(1)] = true;
        this.setPositions(pos);
        repaint();
    }
}
