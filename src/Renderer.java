import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Renderer extends DefaultTableCellRenderer {

    public void fillColor(JTable t, JLabel l){
            l.setBackground(t.getBackground());
            l.setForeground(t.getForeground());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column)
    {
        if(value instanceof JLabel){
            JLabel label = (JLabel)value;
            fillColor(table,label);
            return label;
        }
        else
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}