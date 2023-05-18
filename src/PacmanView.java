import javax.swing.*;
import java.awt.*;

public class PacmanView extends JLabel {

    private boolean isMouthOpen = true;
    private int mouthCounter;

    PacmanModel.Direction direction = PacmanModel.Direction.RIGHT;

    public PacmanView(PacmanModel model) {
        super();
        setOpaque(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);

        int cellSize = getWidth();

        int x = (getWidth() - cellSize) / 2;
        int y = (getHeight() - cellSize) / 2;


        int startAngle = switch(direction) {
            case RIGHT -> 45;
            case LEFT -> 225;
            case UP -> 135;
            case DOWN ->315;
        };

        if(isMouthOpen)g.fillArc(x, y, cellSize, cellSize, startAngle, 270);
        else g.fillArc(x,y,cellSize,cellSize,0,360);

        mouthCounter++;
        if(mouthCounter%3==0){
            isMouthOpen = !isMouthOpen;
            mouthCounter=0;
        }


    }

    public void setDirection(PacmanModel.Direction direction) {
        this.direction = direction;
    }

}