import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(){

        Dimension minimumSize = new Dimension(600, 600);
        setMinimumSize(minimumSize);

        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pacman");
        setVisible(true);
    }
}
