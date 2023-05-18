import javax.swing.*;
import java.awt.*;

public class CollisionView extends JLabel {
    CollisionView(){
        super();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
    }
}

