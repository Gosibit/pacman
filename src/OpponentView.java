import javax.swing.*;
import java.awt.*;

public class OpponentView extends JLabel {

    private Color color;
    private Color baseColor;
    OpponentView(Color color){
        super();
        setVisible(true);
        this.color = color;
        this.baseColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = getWidth();

        int x = (getWidth() - cellSize) / 2;
        int y = (getHeight() - cellSize) / 2;

        g.setColor(color);
        g.fillArc(x, y, cellSize, cellSize, 0, 180);
        g.fillRect(x, y + cellSize / 2, cellSize, cellSize/4*3 );
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBaseColor() {
        return baseColor;
    }
}
