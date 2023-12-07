import javax.swing.*;
import java.awt.*;

public class Fruit extends JComponent {
    private int originX;

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    private int originY;

    public Fruit(int x, int y) {
        this.originX = x;
        this.originY = y;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);
        g.setColor(Color.RED);
        g.fillOval(originX, originY, 20, 20);
    }

    @Override
    public void setLocation(int x, int y) {
        this.originX = x;
        this.originY = y;
        repaint();
    }


}
