import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Task3 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(450, 300);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Решение квадратного уравнения"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }

    public static void main(String[] args) {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new VerticalLayout(15, 30));

        JLabel header = new JLabel("Введите коэффициэнты");

        JPanel coeffPnl = new JPanel();
        JTextField firstCf = new JTextField(3);
        JLabel firstLbl = new JLabel("X^2");
        JTextField secCf = new JTextField(3);
        JLabel secLbl = new JLabel("X");
        JTextField nullCf = new JTextField(3);
        JLabel nullLbl = new JLabel(" = 0");
        coeffPnl.add(firstCf);
        coeffPnl.add(firstLbl);
        coeffPnl.add(secCf);
        coeffPnl.add(secLbl);
        coeffPnl.add(nullCf);
        coeffPnl.add(nullLbl);

        JLabel jlb1 = new JLabel();
        JLabel jlb2 = new JLabel();
        JLabel jlb3 = new JLabel();
        JLabel jlbDc = new JLabel();

        JPanel outPnl = new JPanel();
        JPanel outDPnl = new JPanel();
        outPnl.add(jlb1);
        outPnl.add(jlb2);
        outPnl.add(jlb3);
        outDPnl.add(jlbDc);


        JButton jbt = new JButton("Вычислить");
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double a = Double.parseDouble(firstCf.getText());
                Double b = Double.parseDouble(secCf.getText());
                Double c = Double.parseDouble(nullCf.getText());
                double Dc, R_x1, R_x2, C_x1, C_x2;
                String x1, x2;
                if(a == 0){
                    JOptionPane.showMessageDialog(jfr, "Коэффициент при старшей степени х равен 0, ур-ние принимает вид линейного");
                    x1 = x2 = " ";
                    Dc = 0;
                }else{
                    Dc = b*b - 4*a*c;

                    if(Dc == 0)
                        x1 = x2 =  String.valueOf((-b + Math.sqrt(Dc)) / 2);
                    else
                        if(Dc > 0) {
                            x1 = String.valueOf((-b + Math.sqrt(Dc)) / 2);
                            x2 = String.valueOf((-b - Math.sqrt(Dc)) / 2);
                        }else{
                            R_x1 = R_x2 = -b / 2;
                            C_x1 = Math.sqrt(Math.abs(Dc)) / 2;
                            C_x2 = -Math.sqrt(Math.abs(Dc)) / 2;

                            x1 = new DecimalFormat("#0.0").format(R_x1) + " + " + new DecimalFormat("#0.0").format(C_x1) +  "i";
                            x2 = new DecimalFormat("#0.0").format(R_x2) + new DecimalFormat("#0.0").format(C_x2) +  "i";
                        }
                }
                if(a != 0) {
                    jlb1.setText("Корни: ");
                    jlb2.setText("x1: " + x1);
                    jlb3.setText("x2: " + x2);

                    jlbDc.setText("D = " + new DecimalFormat("#0.0").format(Dc));
                    rootPane.updateUI();
                }
            }
        });

        rootPane.add(header);
        rootPane.add(coeffPnl);
        rootPane.add(outPnl);
        rootPane.add(outDPnl);
        rootPane.add(jbt);

        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
