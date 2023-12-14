package org.cis1200.snakegame;

public interface Collidable {
    void handleCollision(Collidable other);

    boolean[][] computeFuturePositions();

    boolean willCollideWith(Collidable other);
}