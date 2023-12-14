package org.cis1200.snakegame;

import javax.swing.*;

public class HelpPopup {
    public static void show() {
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
        helpPanel.add(
                new JLabel(
                        "In this game, you" +
                                "play as a snake, trying to get as big " +
                                "as you can until you"
                                +
                                "fill the world!"
                )
        );
        helpPanel.add(
                new JLabel(
                        "1. Use the UP, DOWN, LEFT and RIGHT arrow keys on your keyboard to move."
                )
        );
        helpPanel.add(
                new JLabel(
                        "2. You can't stop eating things in your way, not" +
                                "even yourself - so beware of biting"
                                +
                                "yourself! Also, don't hit the walls of the world!"
                )
        );
        helpPanel.add(
                new JLabel("3. You can pause the game by pressing the ESCAPE key on your keyboard.")
        );
        helpPanel.add(new JLabel("4. You can save the game by pressing the Save and Quit button."));
        helpPanel.add(new JLabel("5. You can quit the game by pressing the Quit button."));
        helpPanel.add(
                new JLabel(
                        "6. You can go back to the menu by pressing the < Back to Menu" +
                                "button while the game is being played."
                )
        );

        JOptionPane.showMessageDialog(
                null, helpPanel, "About Snake RunSnakeGame", JOptionPane.INFORMATION_MESSAGE
        );
    }
}