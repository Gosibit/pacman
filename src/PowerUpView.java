import javax.swing.*;
import java.awt.*;

public class PowerUpView extends JLabel {

    PowerUpView(){
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        int size = Math.min(getWidth()/2,getHeight()/2);
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        g.fillArc(x, y, size, size,0,360);
    }
}
