import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task5 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(450, 250);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Генератор текста"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }
    public static void main(String[] args) {
        String[] arr1 = {"Кузнечик","Вася","Крокодил","Орел","Трактор","Боинг 747","Слоник"};
        String[] arr2 = {"ушел","полетел","побежал","залез","уполз","упрыгал","умчался"};
        String[] arr3 = {"в кусты","на дискотеку","домой","за пивом","на Чукотку","по грибы","на охоту"};
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new VerticalLayout(5, 10));
        JPanel cb_pnl = new JPanel();

        JComboBox<String> firstBox = new JComboBox<String>(arr1);
        JComboBox<String> secBox = new JComboBox<String>(arr2);
        JComboBox<String> freeBox = new JComboBox<String>(arr3);

        JPanel text_a_pnl = new JPanel(new GridLayout(0, 2, 1, 1));
        JPanel input_pnl = new JPanel(new VerticalLayout(5, 10));
        JTextArea mainTA = new JTextArea(5, 30);
        text_a_pnl.add(mainTA);
        text_a_pnl.add(input_pnl);

        JButton ph_jbt = new JButton("Добавить фразу");
        JButton exit_jbt = new JButton("Закрыть");

        ph_jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainTA.append(firstBox.getSelectedItem() + " " + secBox.getSelectedItem() + " " + freeBox.getSelectedItem());
                mainTA.append("\n");
            }
        });

        input_pnl.add(ph_jbt);
        input_pnl.add(exit_jbt);

        cb_pnl.add(firstBox);
        cb_pnl.add(secBox);
        cb_pnl.add(freeBox);

        rootPane.add(cb_pnl);
        rootPane.add(text_a_pnl);
        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
