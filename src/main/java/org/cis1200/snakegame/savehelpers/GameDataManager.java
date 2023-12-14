package org.cis1200.snakegame.savehelpers;

import java.io.File;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cis1200.snakegame.Fruit;
import org.cis1200.snakegame.Snake;

public class GameDataManager {
    public static void saveGameState(Snake snake, Fruit fruit, int score) {
        ObjectMapper objectMapper = new ObjectMapper();
        GameSaveState gameSaveState = new GameSaveState(
                new SnakeState(snake), score, fruit.getRowCol()
        );
        try {
            objectMapper.writeValue(new File("target/save.json"), gameSaveState);
        } catch (Exception e) {
            System.out.println("Error saving game state");
        }
    }

    public static GameSaveState loadGameState() {
        ObjectMapper objectMapper = new ObjectMapper();
        GameSaveState gameSaveState = null;
        try {
            File saveFile = new File("target/save.json");
            if (!saveFile.exists()) {
                JOptionPane.showMessageDialog(
                        null, "Save file not found", "Failed to load saved game",
                        JOptionPane.ERROR_MESSAGE
                );
                return gameSaveState;
            }
            gameSaveState = objectMapper.readValue(saveFile, GameSaveState.class);
        } catch (Exception e) {
            System.out.println("Error loading game state");
        }
        return gameSaveState;
    }
}
