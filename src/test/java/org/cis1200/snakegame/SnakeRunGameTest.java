package org.cis1200.snakegame;

import org.junit.Test;
import static org.junit.Assert.*;

public class SnakeRunGameTest {
    @Test
    public void testSnakeGameInstantiatesToNonNull() {
        RunSnakeGame snakeGame = new RunSnakeGame();
        assertNotNull(snakeGame);
    }
}