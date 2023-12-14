package org.cis1200.snakegame.savehelpers;

import java.util.List;
import org.cis1200.snakegame.Snake;

public record SnakeState(List<SnakeCellState> cells, List<Integer> head, List<Integer> tail) {
    public SnakeState(Snake snake) {
        this(snake.getCells(), snake.getHeadRowCol(), snake.getTailRowCol());
    }
}