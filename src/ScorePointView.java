import javax.swing.*;
import java.awt.*;

public class ScorePointView extends JLabel {
    ScorePointView(){
        super();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        int size = Math.min(getWidth()/5,getHeight()/5);
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        g.fillRect(x, y, size, size);
    }
}
