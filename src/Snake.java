import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake extends JComponent {
    private List<Point> snakeSegments;
    private Direction direction;

    public Snake() {
        snakeSegments = new ArrayList<>();
        snakeSegments.add(new Point(100, 100));
        snakeSegments.add(new Point(80, 100));
        snakeSegments.add(new Point(60, 100));
        snakeSegments.add(new Point(40, 100));

        setOpaque(false);
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (Point segment : snakeSegments) {
            g.fillRect(segment.x, segment.y, 20, 20);
        }
    }

    public void moveSnake() {
//        if (!snakeSegments.isEmpty()) {
//            for (int i = snakeSegments.size() - 1; i > 0; i--) {
//                snakeSegments.get(i).x = snakeSegments.get(i - 1).x;
//                snakeSegments.get(i).y = snakeSegments.get(i - 1).y;
//            }
//            snakeSegments.get(0).x += 20;
//        }
        if(direction != null){
            switch (direction){
                case UP: {
                    snakeSegments.add(0,new Point( snakeSegments.get(0).x,snakeSegments.get(0).y -20));
                    break;
                }
                case DOWN: {
                    snakeSegments.add(0,new Point( snakeSegments.get(0).x,snakeSegments.get(0).y +20));
                    break;
                }
                case LEFT: {
                    snakeSegments.add(0,new Point( snakeSegments.get(0).x-20,snakeSegments.get(0).y));
                    break;
                }
                case RIGHT: {
                    snakeSegments.add(0, new Point(snakeSegments.get(0).x + 20, snakeSegments.get(0).y));
                    break;
                }
            }
            snakeSegments.remove(snakeSegments.size()-1);
            repaint();
        }
    }

    public void setDirection(Direction d){
        switch (d){
            case UP: {
                if(this.direction==Direction.DOWN){
                    return;
                }
                break;
            }
            case DOWN: {
                if(this.direction==Direction.UP){
                    return;
                }
                break;
            }
            case LEFT: {
                if(this.direction==Direction.RIGHT){
                    return;
                }
                break;
            }
            case RIGHT:{
                if(this.direction==Direction.LEFT){
                    return;
                }
                break;
            }
        }
        this.direction =d;
    }
}
