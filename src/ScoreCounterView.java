import javax.swing.*;
import java.awt.*;

public class ScoreCounterView extends JLabel {
    ScoreCounterView(){
        new JLabel();
        setText("Score: 0");
        setFont(new Font("Arial", Font.BOLD, 24));
    }
}
