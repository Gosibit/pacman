import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(()->{
            MyFrame myFrame = new MyFrame();
            MenuView mv = new MenuView(myFrame);
            myFrame.getContentPane().add(mv, BorderLayout.CENTER);
        });


    }
}
