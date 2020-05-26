//Программа работает с файлами .txt, пример DATA_Stud.txt


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Task3 {
    static JFrame getFrame(){
        JFrame myFrame = new JFrame();
        myFrame.setSize(600, 350);
        myFrame.setLocation(350, 250);// обязательно - указать размер окна
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // обязательно - действия при выходе
        myFrame.setTitle("Таблица оценок студентов"); // заголовок окна
        myFrame.setVisible(true);

        return myFrame;
    }

    static ArrayList<String[]> readFile(String filePath) throws IOException {
        String line = "";
        String splitBy = ";";
        ArrayList<String[]> data = new ArrayList<>();
        int i = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "CP1251"))) {
            while ((line = br.readLine()) != null) {
                data.add(line.split(splitBy));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    static String joinStringArr(String[] arr){
        String joinedStr = "";
        for(int i = 0; i < arr.length; i++)
            if(joinedStr == "")
                joinedStr += arr[i];
            else
                joinedStr += ";" + arr[i];
            return joinedStr;
    }
    static void writeInFile(String[][] data, String fileName){
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "CP1251")){
            for (String[] strings : data) {
                for (int i = 0; i < strings.length; i++) {
                    writer.write(strings[i]);
                    if(i < (strings.length-1))
                        writer.append(";");
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void cleanTable(DefaultTableModel dtm){
        int i = dtm.getRowCount() - 1;
        while(dtm.getRowCount() != 0) {
            dtm.removeRow(i);
            i--;
        }
    }
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static void main(String[] args) throws IOException {
        JFrame jfr = getFrame();
        JPanel rootPane = new JPanel(new BorderLayout());
        JPanel tools_pnl = new JPanel(new GridLayout(0, 6));
        JButton load_file_jbt = new JButton("Загрузить таблицу");
        JButton add_row = new JButton("Добавить строку");
        JButton save_jbt = new JButton("Сохранить в файл");
        JButton delete_sel_row = new JButton("Удалить строку");
        JButton calc_total = new JButton("Итоги");
        JButton calc_average_mark = new JButton("Средняя оценка");

        JPanel total_pnl = new JPanel(new GridLayout(2 , 4, 10, 10));
        JTextField[] tf_mark_arr = new JTextField[4];
        JTextField first_tf = new JTextField();
        JTextField sec_tf = new JTextField();
        JTextField fird_tf = new JTextField();
        JTextField four_tf = new JTextField();
        tf_mark_arr[0] = first_tf;
        tf_mark_arr[1] = sec_tf;
        tf_mark_arr[2] = fird_tf;
        tf_mark_arr[3] = four_tf;
        JLabel first_lbl = new JLabel("История");
        JLabel sec_lbl = new JLabel("Физика");
        JLabel fird_lbl = new JLabel("Химия");
        JLabel four_lbl = new JLabel("Средний балл");

        total_pnl.add(first_tf);
        total_pnl.add(sec_tf);
        total_pnl.add(fird_tf);
        total_pnl.add(four_tf);

        total_pnl.add(first_lbl);
        total_pnl.add(sec_lbl);
        total_pnl.add(fird_lbl);
        total_pnl.add(four_lbl);

        DefaultTableModel main_dtm = new DefaultTableModel();
        JTable main_jtb = new JTable(main_dtm);

        JFileChooser jloadFileChooser = new JFileChooser();
        String[] stud_table_headers = {"Фамилия И.О" , "История", "Физика", "Химия", "Средний балл"};
        load_file_jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                jloadFileChooser.setDialogTitle("Выбор файла");
                jloadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = jloadFileChooser.showOpenDialog(jfr);
                if (result == JFileChooser.APPROVE_OPTION ){
                    ArrayList<String[]> stud_data = null;
                    try {
                        stud_data = readFile(jloadFileChooser.getSelectedFile().getPath());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if(main_dtm.getRowCount() != 0)
                        cleanTable(main_dtm);
                    main_dtm.setColumnIdentifiers(stud_table_headers);
                    for(int i = 0; i < stud_data.size(); i++) {
                        main_dtm.addRow(stud_data.get(i));
                    }
                }
            }
        });

        add_row.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sel_row_index = main_jtb.getSelectedRow() + 1;
                    main_dtm.insertRow(sel_row_index, new String[]{"", "", "", "", ""});
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(jfr, "Выберите строку, после которой нужно добавить новую.");
                }
            }
        });
        delete_sel_row.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sel_row_index = main_jtb.getSelectedRow();
                    main_dtm.removeRow(sel_row_index);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(jfr, "Выберите строку, которую нужно удалить.");
                }
            }
        });

        JFileChooser jsaveFileChooser = new JFileChooser();
        save_jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directory_path = null;
                File save_file;
                String[][] saving_data = new String[main_dtm.getRowCount()][main_dtm.getColumnCount()];
                for(int i = 0; i < main_dtm.getRowCount(); i++)
                    for(int j = 0; j < main_dtm.getColumnCount(); j++)
                        saving_data[i][j] = (String) main_dtm.getValueAt(i, j);
                String in_file_name = JOptionPane.showInputDialog(jfr, "Введите название файла");

                jsaveFileChooser.setDialogTitle("Выбор папки сохранения");
                jsaveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = jsaveFileChooser.showOpenDialog(jfr);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    directory_path = jsaveFileChooser.getSelectedFile().getPath() + "\\" + in_file_name + ".txt";
                }
                save_file = new File(directory_path);
                try {
                    if(save_file.createNewFile()) {
                        System.out.println(directory_path + " Файл создан");
                        writeInFile(saving_data, directory_path);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        boolean[] is_all_mark_calculated = new boolean[1];
        calc_average_mark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double average_mark;
                for(int i = 0; i < main_dtm.getRowCount(); i++) {
                    average_mark = Integer.parseInt((String) main_dtm.getValueAt(i, 1)) +
                            Integer.parseInt((String) main_dtm.getValueAt(i, 2)) +
                            Integer.parseInt((String) main_dtm.getValueAt(i, 3));
                    average_mark = average_mark / 3;
                    main_dtm.setValueAt(String.valueOf(roundAvoid(average_mark, 1)), i, 4);
                }
                is_all_mark_calculated[0] = true;
            }
        });

        calc_total.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(is_all_mark_calculated[0]) {
                    double total_group_mark;
                    for (int i = 1; i < main_dtm.getColumnCount(); i++) {
                        total_group_mark = 0;
                        for (int j = 0; j < main_dtm.getRowCount(); j++)
                            total_group_mark += Double.parseDouble((String) main_dtm.getValueAt(j, i));
                        total_group_mark = total_group_mark / main_dtm.getRowCount();
                        tf_mark_arr[i - 1].setText(String.valueOf(total_group_mark));
                    }
                    rootPane.add(total_pnl, BorderLayout.SOUTH);
                    rootPane.updateUI();
                }
                else
                    JOptionPane.showMessageDialog(jfr, "Чтобы вывести итоги нужно рассчитать средний балл по каждому предмету!");
            }
        });


        tools_pnl.add(save_jbt);
        tools_pnl.add(load_file_jbt);
        tools_pnl.add(add_row);
        tools_pnl.add(delete_sel_row);
        tools_pnl.add(calc_total);
        tools_pnl.add(calc_average_mark);
        rootPane.add(new JScrollPane(main_jtb), BorderLayout.CENTER);
        rootPane.add(tools_pnl, BorderLayout.NORTH);
        jfr.add(rootPane);
        rootPane.updateUI();
    }
}
