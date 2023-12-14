package org.cis1200.snakegame.savehelpers;

import java.util.List;

public record SnakeCellState(List<Integer> position, String direction) {
    public SnakeCellState() {
        this(List.of(0, 0), "right");
    }
}