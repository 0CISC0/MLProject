import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;


public class Task1 {

    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(350, 200);
        myFrame.setLocation(400, 300);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Converter"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }
    public static void main(String[] args) {
        JFrame jfr = getFrame();

        JPanel gridConverter = new JPanel(new GridLayout(0, 2, 5, 4));
        JPanel textFieldPnl = new JPanel(new VerticalLayout(10, 5));
        JPanel labelPnl = new JPanel(new VerticalLayout(30, 5));

        JTextField dollarField = new JTextField(14);
        JTextField courseField = new JTextField(14);
        JTextField finalValueField = new JTextField(14);
        JButton result_btn = new JButton("Рассчитать");

        JLabel jlb1 = new JLabel("               $");
        JLabel jlb2 = new JLabel("Рублей за 1 доллар");
        JLabel jlb3 = new JLabel("            result");

        labelPnl.add(jlb1);
        labelPnl.add(jlb2);
        labelPnl.add(jlb3);

        textFieldPnl.add(dollarField);
        textFieldPnl.add(courseField);
        textFieldPnl.add(finalValueField);
        textFieldPnl.add(result_btn);

        gridConverter.add(labelPnl);
        gridConverter.add(textFieldPnl);

        result_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalValueField.setText(String.valueOf(Integer.parseInt(dollarField.getText()) * Integer.parseInt(courseField.getText())));
            }
        });

        jfr.add(gridConverter);
        gridConverter.updateUI();

    }
}
