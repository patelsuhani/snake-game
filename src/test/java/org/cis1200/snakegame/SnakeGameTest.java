package org.cis1200.snakegame;

import java.util.List;

import org.junit.Test;

import org.cis1200.snakegame.savehelpers.SnakeCellState;

import static org.junit.Assert.*;

public class SnakeGameTest {
    @Test
    public void instantiatesVerticalSnakeFacingDown() {
        Snake snake = new Snake();
        assertNotNull(snake);
        assertTrue(snake.getPositions()[5][5]);
        assertTrue(snake.getPositions()[4][5]);
        assertTrue(snake.getPositions()[3][5]);
        assertTrue(snake.getPositions()[2][5]);
        assertEquals((Integer) snake.getHeadRowCol().get(0), Integer.valueOf(5));
        assertEquals((Integer) snake.getHeadRowCol().get(1), Integer.valueOf(5));
        assertEquals((Integer) snake.getTailRowCol().get(0), Integer.valueOf(2));
        assertEquals((Integer) snake.getTailRowCol().get(1), Integer.valueOf(5));
        assertEquals(snake.getDirection(), Direction.DOWN);
    }

    @Test
    public void moves() {
        Snake snake = new Snake();
        snake.move(new GameEntity[0]);
        assertTrue(snake.getPositions()[6][5]);
        assertTrue(snake.getPositions()[5][5]);
        assertTrue(snake.getPositions()[4][5]);
        assertTrue(snake.getPositions()[3][5]);
        assertFalse(snake.getPositions()[2][5]);
        assertEquals(Integer.valueOf(6), (Integer) snake.getHeadRowCol().get(0));
        assertEquals(Integer.valueOf(5), (Integer) snake.getHeadRowCol().get(1));
        assertEquals(Integer.valueOf(3), (Integer) snake.getTailRowCol().get(0));
        assertEquals(Integer.valueOf(5), (Integer) snake.getTailRowCol().get(1));
        assertEquals(Direction.DOWN, snake.getDirection());
    }

    @Test
    public void checksCollisionWithFruit() {
        Snake snake = new Snake();
        SnakeGameBorder border = new SnakeGameBorder();
        Fruit fruit = new Fruit(new GameEntity[] { snake, border });
        fruit.setRowCol(List.of(6, 5));
        assertTrue(snake.willCollideWith(fruit));
        fruit.setRowCol(List.of(17, 5));
        assertFalse(snake.willCollideWith(fruit));
    }

    @Test
    public void collisionDetectionWorks() {
        Snake snake = new Snake();
        snake.setBody(
                List.of(
                        new SnakeCellState(List.of(10, 10), Direction.LEFT.toString()),
                        new SnakeCellState(List.of(9, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(8, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(7, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(6, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(5, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(5, 9), Direction.RIGHT.toString()),
                        new SnakeCellState(List.of(5, 8), Direction.RIGHT.toString()),
                        new SnakeCellState(List.of(6, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(7, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(8, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(9, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(10, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(11, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(12, 8), Direction.UP.toString())
                )
        );
        snake.setHeadRowCol(List.of(10, 10));
        snake.setTailRowCol(List.of(12, 8));
        snake.move(new GameEntity[0]);
        assertTrue(snake.willCollideWith(snake));
    }

    @Test
    public void collisionDetectionEdgeCaseDoesntBiteTail() {
        Snake snake = new Snake();
        snake.setBody(
                List.of(
                        new SnakeCellState(List.of(10, 10), Direction.LEFT.toString()),
                        new SnakeCellState(List.of(9, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(8, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(7, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(6, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(5, 10), Direction.DOWN.toString()),
                        new SnakeCellState(List.of(5, 9), Direction.RIGHT.toString()),
                        new SnakeCellState(List.of(5, 8), Direction.RIGHT.toString()),
                        new SnakeCellState(List.of(6, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(7, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(8, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(9, 8), Direction.UP.toString()),
                        new SnakeCellState(List.of(10, 8), Direction.UP.toString())
                )
        );
        snake.setHeadRowCol(List.of(10, 10));
        snake.setTailRowCol(List.of(10, 8));
        snake.move(new GameEntity[0]);
        assertFalse(snake.willCollideWith(snake));
    }
}
