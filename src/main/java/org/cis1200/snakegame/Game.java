package org.cis1200.snakegame;

public class Game implements Runnable {
    @Override
    public void run() {
        RunSnakeGame.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
