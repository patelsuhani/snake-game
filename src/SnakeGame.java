import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 400));

        CheckeredBackground backgroundPanel = new CheckeredBackground(20);
        Fruit fruit = new Fruit(0, 0);
        Snake snake = new Snake();

        // Set panel sizes and positions
        backgroundPanel.setBounds(0, 0, 400, 400);
        fruit.setBounds(0, 0, 400, 400);
        snake.setBounds(0, 0, 400, 400);

        // Add panels to the layered pane
        layeredPane.add(backgroundPanel, Integer.valueOf(0));
        layeredPane.add(fruit, Integer.valueOf(1));
        layeredPane.add(snake, Integer.valueOf(2));

        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);

        Timer timer = new Timer(200, e -> {
            /**
             * moveSnake moves the snake in the direction it is pointed at. The direction also must be passed into moveSnake.
             * That direction should be stored as a field on the Snake object (snake) itself, and updated accordingly
             * whenever a new keystroke (arrow keys) occurs.
             */
            snake.moveSnake();
            /**
             * pass fruit to moveSnake so that you can call a method over fruit to get its location and compare it with
             * the snake's head's location. if the locations coincide, increase the score by one and call a method on
             * fruit(not yet defined-so you will have to create it) to change its position to a random position on the
             * board other than the snake's body.
             * Therefore, that method on fruit will need to accept a list of cells that form the snake(because we will
             * need to exclude them.
             */
        });
        timer.start();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP: {
                        snake.setDirection(Direction.UP);
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        snake.setDirection(Direction.DOWN);
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        snake.setDirection(Direction.LEFT);
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        snake.setDirection(Direction.RIGHT);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
