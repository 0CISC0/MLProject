import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Task1 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(450, 250);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("pifagor"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }
    public static void main(String[] args) {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new BorderLayout());
        JTable jtb = new JTable(10, 10);

        for(int i = 0; i < jtb.getRowCount(); i++)
            jtb.setValueAt((i + 1), 0, i);

        for(int i = 0; i < jtb.getRowCount(); i++)
            jtb.setValueAt((i + 1), i, 0);

        for(int i = 1; i < jtb.getRowCount(); i++)
            for(int j = 1; j < jtb.getColumnCount(); j++){
                jtb.setValueAt((i+1)*(j+1), i, j);
            }
        rootPane.add(jtb);
        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
