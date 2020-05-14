import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Task4 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(450, 350);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Решение квадратного уравнения"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }

    static private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static void main(String[] args) {
        JFrame jfr = getFrame();
        ImageIcon icon = new ImageIcon("C:\\Users\\Сергей\\IdeaProjects\\Inf2Sem11\\src\\Icon.png");
        ImageIcon thumbnailIcon = new ImageIcon(getScaledImage(icon.getImage(), 200, 200));

        JPanel rootPane = new JPanel(new VerticalLayout(5, 10));
        JPanel mainPnl = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel inputPnl = new JPanel(new VerticalLayout(5, 10));

        JLabel iconLb = new JLabel(thumbnailIcon);
        JLabel header = new JLabel("Координаты точки на плоскости:");

        JPanel x_panel = new JPanel();
        x_panel.add(new JLabel("X = "));
        JTextField input_x_tf = new JTextField(10);
        x_panel.add(input_x_tf);

        JPanel y_panel = new JPanel();
        y_panel.add(new JLabel("Y = "));
        JTextField input_y_tf = new JTextField(10);
        y_panel.add(input_y_tf);

        JLabel resultLb = new JLabel();
        resultLb.setHorizontalAlignment(JLabel.RIGHT);

        JButton jbt = new JButton("Проверить");
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean temp;
                double x = Double.parseDouble(input_x_tf.getText());
                double y = Double.parseDouble(input_y_tf.getText());

                if(x >= 0){
                    if((x <= 3) && (x*x + y*y <= 36))
                        temp = true;
                    else
                        temp = false;
                }else if(y < 0)
                    temp = false;
                else if(y <= x + 6)
                    temp = true;
                else temp = false;

                if(temp) {
                    resultLb.setForeground(Color.blue);
                    resultLb.setText("Точка (" + x + " ; " + y + ") принадлежит этой области");
                }
                else {
                    resultLb.setForeground(Color.RED);
                    resultLb.setText("Точка (" + x + " ; " + y + ") не принадлежит этой области");
                }
            }
        });


        inputPnl.add(header);
        inputPnl.add(x_panel);
        inputPnl.add(y_panel);
        inputPnl.add(jbt);
        mainPnl.add(iconLb);
        mainPnl.add(inputPnl);
        rootPane.add(mainPnl);
        rootPane.add(resultLb);
        jfr.add(rootPane);

        rootPane.updateUI();

    }
}
