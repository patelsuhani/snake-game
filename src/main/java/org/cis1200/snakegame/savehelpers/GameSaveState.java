package org.cis1200.snakegame.savehelpers;

import java.util.List;

public record GameSaveState(SnakeState snake, int score, List<Integer> fruit) {
}