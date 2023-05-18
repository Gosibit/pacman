import javax.swing.*;
import java.awt.*;

public class GameBoardView extends JTable {

       public GameBoardView(GameBoardModel model) {
              super(model);

              setLayout(new BorderLayout());
              setCellSelectionEnabled(false);
              setRowSelectionAllowed(false);
              setColumnSelectionAllowed(false);
              setBackground(Color.black);
              setGridColor(Color.black);
              setIntercellSpacing(new Dimension(0, 0));
              getTableHeader().setVisible(false);
              setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
              setDefaultRenderer(Object.class, new Renderer());

       }
}