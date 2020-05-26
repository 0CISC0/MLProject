import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task2Add {
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

    static int getMonthNumber(String date){
        int num = Integer.parseInt(date.split("\\.")[1]);
        return num;
    }
    static int foundMin(int[] temp_value){
        int min_temp = Integer.MAX_VALUE;
        for(int i = 0; i < temp_value.length; i++)
            if(temp_value[i] < min_temp)
                min_temp = temp_value[i];
        return min_temp;
    }
    static int foundMax(int[] temp_value){
        int max_temp = Integer.MIN_VALUE;
        for(int i = 0; i < temp_value.length; i++)
            if(temp_value[i] > max_temp)
                max_temp = temp_value[i];
        return max_temp;
    }

    public static void main(String[] args) throws IOException {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new GridLayout(3, 12));

        String[][] data = readCSV();
        String[] month_header = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                                    "Июль", "Август","Сентябрь","Октябрь","Ноябрь", "Декабрь"};

        JLabel[] title_month = new JLabel[month_header.length];
        for(int i = 0; i < month_header.length; i++){
            title_month[i] = new JLabel();
            title_month[i].setText(month_header[i]);
        }

        int i = 0;
        for(int month_num = 1; month_num <= month_header.length; month_num++){
            JPanel current_pnl = new JPanel(new BorderLayout());
            JPanel min_max_pnl = new JPanel();
            DefaultTableModel monthDtm = new DefaultTableModel();
            JTable monthJtb;

            Object[] columnsHeader = new String[] {"Дата", "Температура"};
            ArrayList<String[]> al_month_date = new ArrayList<>();
            while((getMonthNumber(data[i][0]) == month_num) && (i < 363)){
                al_month_date.add(data[i]);
                i++;
            }
            int[] temp_arr = new int[al_month_date.size()];
            for(int j = 0; j < al_month_date.size(); j++)
                temp_arr[j] = Integer.parseInt(al_month_date.get(j)[1]);
            JLabel min_lbl = new JLabel("Min = " + String.valueOf(foundMin(temp_arr)));
            JLabel max_lbl = new JLabel("Max = " + String.valueOf(foundMax(temp_arr)));


            monthDtm.setColumnIdentifiers(columnsHeader);
            for(String[] item: al_month_date)
                monthDtm.addRow(item);

            monthJtb = new JTable(monthDtm);
            current_pnl.add(new JScrollPane(monthJtb));
            current_pnl.add(title_month[month_num - 1], BorderLayout.NORTH);
            min_max_pnl.add(min_lbl);
            min_max_pnl.add(max_lbl);
            current_pnl.add(min_max_pnl, BorderLayout.SOUTH);
            rootPane.add(current_pnl);
        }
        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
