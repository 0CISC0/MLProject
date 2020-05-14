import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(350, 400);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Factorial"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }

    public static void main(String[] args) {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new VerticalLayout(70, 50));
        JLabel header = new JLabel("<html>Эта программа вычисляет<br>значение факториала целого числа</html>");

        JPanel insertValuePnl = new JPanel();
        JLabel valueLbl = new JLabel("Введите целое число: ");
        JTextField insertValueTf = new JTextField(6);
        insertValuePnl.add(valueLbl);
        insertValuePnl.add(insertValueTf);

        JPanel outValuePnl = new JPanel();
        JLabel outLbl = new JLabel("Ответ: ");
        JTextField outValueTf = new JTextField(14);
        outValuePnl.add(outLbl);
        outValuePnl.add(outValueTf);

        JPanel jbtPnl = new JPanel();
        JButton jbt = new JButton("Вычислить факториал");
        jbtPnl.add(new JLabel(""));
        jbtPnl.add(jbt);


        rootPane.add(header);
        rootPane.add(insertValuePnl);
        rootPane.add(outValuePnl);
        rootPane.add(jbtPnl);

        jfr.add(rootPane);

        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = 1;
                for(int i = 1; i <= Integer.parseInt(insertValueTf.getText()); i++)
                    temp *= i;
                outValueTf.setText(String.valueOf(temp));
            }
        });
    }
}
