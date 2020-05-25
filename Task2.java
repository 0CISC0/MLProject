import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(450, 250);
        myFrame.setLocation(400, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Генератор текста"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }

    static String[][] readCSV() throws IOException {
        String csvFile = "C:\\Users\\Сергей\\IdeaProjects\\Inf2Sem12\\src\\temp2.csv";
        String line = "";
        String cvsSplitBy = ";";
        String[][] data = new String[1000][10];
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                data[i] = line.split(cvsSplitBy);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    static double averageTemp(int[] temp){
        double av_temp = 0;
        for(int i = 0; i < temp.length; i++)
            av_temp += temp[i];
        return av_temp / temp.length;
    }

    public static void main(String[] args) throws IOException {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new BorderLayout());
        DefaultTableModel dtm = new DefaultTableModel();
        JTable jtb;

        Object[] columnsHeader = new String[] {"Дата", "Температура"};
        String[][] data = readCSV();
        String[][] jan_data = new String[30][2];
        for(int i = 0; i < 30; i++)
            jan_data[i] = data[i];


        dtm.setColumnIdentifiers(columnsHeader);
        for (int i = 0; i < jan_data.length; i++)
            dtm.addRow(jan_data[i]);

        int[] temp_arr = new int[dtm.getRowCount()];
        for(int i = 1; i < dtm.getRowCount(); i++)
            temp_arr[i] = Integer.parseInt(jan_data[i][1]);


        JLabel jlb = new JLabel();
        jlb.setText(String.valueOf(averageTemp(temp_arr)));
        jtb = new JTable(dtm);
        rootPane.add(new JScrollPane(jtb));
        rootPane.add(jlb, BorderLayout.SOUTH);
        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
