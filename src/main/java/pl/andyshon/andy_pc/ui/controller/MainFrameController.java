package pl.andyshon.andy_pc.ui.controller;

import pl.andyshon.andy_pc.CalcYearsAndMonth;
import pl.andyshon.andy_pc.GetShowAllData;
import pl.andyshon.andy_pc.PdfCreator;
import pl.andyshon.andy_pc.ReadFromFile;
import pl.andyshon.andy_pc.ui.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainFrameController {
    private ReadFromFile readFromFile;
    private MainFrame mainFrame;
    private JButton btnShowAll, btnCount, btnSearch, btnRemoveChild, btnAbout, btnMinus, btnPlus, btnPdfCreate;
    private JTextField etChild, dateOfBirth, etSearch;
    private JTextArea textArea;

    int years, numTop, month2;
    Font font;
    float fontSize = 12.0f;
    boolean showBtnSearch=false;
    String childName, childDateOfBirth, searchName;
    ArrayList childList, childDateList, childDateNum, childGetFullInfo;
    File sourceFile, outputFile, file;
    String dirPath, dirPathNew;


    public MainFrameController() {
        initComponents();
        initListeners();
        if (!showBtnSearch){
            btnSearch.setEnabled(false);
            etSearch.setEnabled(false);
        }
    }

    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }

    // Инициализация компонентов
    private void initComponents(){
        String myJarPath = MainFrameController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        dirPath = new File(myJarPath).getParent();
        System.out.println("DIR1 = " + dirPath);

        try {
            dirPathNew = URLDecoder.decode(dirPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        sourceFile = new File(dirPathNew + "//ChildrenList.txt");
        outputFile = new File(dirPathNew + "//noteNew2.txt");
        file = new File(dirPathNew + "//noteNew2.txt");

        mainFrame = new MainFrame();
        readFromFile = new ReadFromFile();

        childList = new ArrayList();
        childDateList = new ArrayList();
        childDateNum = new ArrayList();
        childGetFullInfo = new ArrayList();

        btnShowAll = mainFrame.getBtnShowAll();
        btnCount = mainFrame.getBtnCount();
        btnSearch = mainFrame.getBtnSearch();
        btnRemoveChild = mainFrame.getBtnRemoveChild();
        btnAbout = mainFrame.getBtnAbout();
        btnMinus = mainFrame.getBtnMinus();
        btnPlus = mainFrame.getBtnPlus();
        btnPdfCreate = mainFrame.getBtnPdfCreate();

        etChild = mainFrame.getTfName();
        dateOfBirth = mainFrame.getTfDate();
        etSearch = mainFrame.getTfSearch();
        textArea = mainFrame.getTextArea();
        textArea.setEditable(false);

        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        etChild.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        dateOfBirth.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        etSearch.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
    }

    private void formKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                System.out.println("F1");
                btnShowAll.doClick();
                break;
            case KeyEvent.VK_F2:
                System.out.println("F2");
                btnCount.doClick();
                break;
            case KeyEvent.VK_F3:
                System.out.println("F3");
                btnSearch.doClick();
                break;
            case KeyEvent.VK_F4:
                System.out.println("F4");
                btnRemoveChild.doClick();
                break;
            case KeyEvent.VK_F5:
                System.out.println("F5");
                btnPdfCreate.doClick();
                break;
            default:
        }
    }

    // Инициализация кнопок
    private void initListeners() {
        btnShowAll.addActionListener(new btnShowAllListener());
        btnCount.addActionListener(new btnCountListener());
        btnSearch.addActionListener(new btnSearchListener());
        btnRemoveChild.addActionListener(new btnRemoveChildListener());
        btnAbout.addActionListener(new btnAboutListener());
        btnMinus.addActionListener(new btnMinusLisntener());
        btnPlus.addActionListener(new btnPlusLisntener());
        btnPdfCreate.addActionListener(new btnPdfCreateLisnter());
    }

    private class btnPdfCreateLisnter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PdfCreator pdfCreator = new PdfCreator();
            pdfCreator.createPDF();
        }
    }

    // Уменьшить масштаб
    private class btnMinusLisntener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (fontSize <= 10.0f)
                fontSize = 10.0f;
            else
                fontSize = font.getSize() - 1.0f;
            btnShowAll.doClick();
        }
    }
    // Увеличить масштаб
    private class btnPlusLisntener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (fontSize >= 25.0f)
                fontSize = 25.0f;
            else
                fontSize = font.getSize() + 1.0f;
            btnShowAll.doClick();
        }
    }

    // Нажатие на кнопку О программе
    private class btnAboutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "1. Чтобы добавить ребёнка в список, введите ФИО, год рождения и нажмите кнопку 'Считать возраст.'\n" +
                            "2. Чтобы найти ребёнка в списке, введити полное ФИО и нажмите кнопку 'Поиск.'\n" +
                            "3. Чтобы удалить ребёнка из списка, нажмите кнопку 'Удалить ребёнка из списка, введите ФИО \nребёнка, " +
                            "которого хотите удалить и нажмите OK." +
                            "Быстрые клавиши:\nF1 - Список детей, F2 - Считать возраст, F3 - Поиск, F4 - Удалить рёбенка," +
                            " F5 - Сохранить в PDF" +

                            "\nЭта программа предназначенна для личного использования.\nРазработал: Андрей Шкатула.",
                    "О программе", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Нажатие на кнопку Удалить ребёнка из списка
    private class btnRemoveChildListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(mainFrame, "Имя ребёнка, которого следует удалить из списка",
                    "Удалить ребёнка из списка", JOptionPane.INFORMATION_MESSAGE);

            String getString = readFromFile.getStrFromFile();

            ArrayList arrayListFull = new ArrayList();
            ArrayList arrayListNames = new ArrayList();

            if (!getString.isEmpty()) {
                btnSearch.setEnabled(true);
                etSearch.setEnabled(true);
                String[] str_res = getString.split("\\)");

                arrayListFull.clear();
                for (int n = 0; n < str_res.length; n++) {
                    arrayListFull.add(n, str_res[n]);
                }
                ArrayList arrayListDates = new ArrayList();
                int k;
                numTop=0;
                for (k = 0; k < arrayListFull.size(); k++) {
                    String st = arrayListFull.get(k).toString(); // полное Ф.И.О. и дата ребенка
                    String st_date = st.substring(st.length() - 10, st.length()); // только дата ребенка
                    System.out.println("st_date = " + st_date);
                    String st2 = arrayListFull.get(k).toString(); // полное Ф.И.О. и дата ребенка
                    String[] str_res2 = st2.split(":"); // массив имен детей

                    arrayListNames.add(str_res2[0]);
                    arrayListDates.add(str_res2[1]);
                }

                if (name == null){/*do nothing*/}
                else if (arrayListNames.contains(name)){
                    int index = arrayListNames.indexOf(name);
                    arrayListNames.remove(index);
                    arrayListDates.remove(index);
                    int index2 = childList.indexOf(name);
                    childList.remove(index2);

                    String stToFile = "";
                    for (int i=0; i<arrayListNames.size(); i++){
                        stToFile += arrayListNames.get(i).toString() + ":" + arrayListDates.get(i).toString()+")";
                    }
                    System.out.println("stToFile = " + stToFile);

                    try {
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                        try {
                            out.print(stToFile);
                        } finally {
                            out.close();
                        }
                    } catch(IOException rr) {
                        throw new RuntimeException(rr);
                    }
                    sourceFile.delete();
                    outputFile.renameTo(sourceFile);

                    JOptionPane.showMessageDialog(mainFrame,
                            "Ребёнок " + name + " был удален из списка.\nОбновите список детей.",
                            "Внимание", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame,
                            "Такого ребёнка нет в списке.","Внимание", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Такого ребёнка нет в списке.");
                }
            }
            else{
                showBtnSearch=false;
                btnSearch.setEnabled(false);
                etSearch.setEnabled(false);
            }
        }
    }

    // Нажатие на кнопку Поиск
    private class btnSearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            searchName = etSearch.getText();
            System.out.println("label = " + searchName);
            if (!searchName.isEmpty()){
                SearchChild(searchName);
            }
            else{
                System.out.println("Введите Ф.И.О. для поиска!");
                JOptionPane.showMessageDialog(mainFrame,
                        "Введите Ф.И.О. для поиска!",
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Поиск ребенка в списке
    public void SearchChild(String searchName){
        int num = 0;
        System.out.println("childGetFullInfo size = " + childGetFullInfo.size());
        for (int i=0; i<childGetFullInfo.size(); i++) {
            String str2 = childGetFullInfo.get(i).toString();
            String[] str_res = str2.split(":");

            for (int n=0; n<str_res.length; n+=2){
                if (searchName.equals(str_res[n])){
                    num = 1;
                    textArea.setText("");
                    textArea.setText(searchName + ": " + str_res[n+1] + ")");
                }
                else{}
            }
        }
        System.out.println("choice = " + num);
        if (num==0){
            JOptionPane.showMessageDialog(mainFrame,
                    "Ребёнка с таким Ф.И.О. в списке нет!\nРебёнок добавится в список,\n" +
                            "когда вы посчитаете его возраст.","Внимание", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ребёнка с такими Ф.И.О. в списке нет!!!");
        }
    }

    // Нажатие на кнопку Список детей
    private class btnShowAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            GetShowAllData.numTop=0;

            font = textArea.getFont();

            textArea.setText("");

            String str2 = readFromFile.getStrFromFile();

            childList.clear();

            if (!str2.isEmpty()) {
                btnSearch.setEnabled(true);
                etSearch.setEnabled(true);
                String[] str_res = str2.split("\\)");

                childGetFullInfo.clear();
                for (int n = 0; n < str_res.length; n++) {
                    childGetFullInfo.add(n, str_res[n]);
                }
                ArrayList arrayListNames = new ArrayList();
                int k;
                numTop=0;
                for (k = 0; k < childGetFullInfo.size(); k++) {
                    String st = childGetFullInfo.get(k).toString(); // полное Ф.И.О. и дата ребенка
                    String st_date = st.substring(st.length() - 10, st.length()); // только дата ребенка

                    System.out.println("st_date = " + st_date);
                    String st2 = childGetFullInfo.get(k).toString(); // полное Ф.И.О. и дата ребенка
                    String[] str_res2 = st2.split(":"); // массив имен детей

                    System.out.println("st2 = " + st2);
                    System.out.println("childGetFullInfo size = " + childGetFullInfo.size());
                    System.out.println("str_res2 length = " + str_res2.length);
                    for (int i=0; i<str_res2.length; i++) {
                        childList.add(str_res2[i]);
                        System.out.println("LIST: " + childList.get(i));
                    }

                    System.out.println("str_res2[0] = " + str_res2[0]);
                    System.out.println("str_res2[1] = " + str_res2[1]);
                    for (int i = 0; i < str_res2.length; i += 2) {
                        arrayListNames.add(i, str_res2[i]);
                        System.out.println("arrayListNames = " + arrayListNames.get(i));

                        //funct2(arrayListNames.get(i), st_date);
                        GetShowAllData getShowAllData = new GetShowAllData();
                        getShowAllData.printAllData(arrayListNames.get(i), st_date, textArea);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(mainFrame,
                        "Список пуст! Добавьте нового ребёнка.","Внимание", JOptionPane.INFORMATION_MESSAGE);
                showBtnSearch=false;
                btnSearch.setEnabled(false);
                etSearch.setEnabled(false);
            }
        }
    }

    // Нажатие на кнопку Считать возраст
    private class btnCountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showBtnSearch = true;
            if (showBtnSearch){
                btnSearch.setEnabled(true);
                etSearch.setEnabled(true);
            }
            childName = etChild.getText();
            childDateOfBirth = dateOfBirth.getText();
            System.out.println("childDateOfBirth: " + childDateOfBirth);
            System.out.println("childName: " + childName);
            if ((childDateOfBirth.length()!=0) && (childName.length()!=0)) {
                textArea.setText("");
                funct(childDateOfBirth);
            }
            else{
                System.out.println("childDateOfBirth = null");
                JOptionPane.showMessageDialog(mainFrame,
                        "Введите Ф.И.О. и год рождения,\nили же - воспользуйтесь поиском.",
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        public void funct(String str_date){
            textArea.removeAll();
            System.out.println("child11111 = " + childName);
            System.out.println("size = " + childList.size());
            for (int i=0; i<childList.size(); i++){
                System.out.println("cc = " + childList.get(i));
            }
            System.out.println("childGetFullInfo size = " + childGetFullInfo.size());
            if (childGetFullInfo.contains(childName)){
                System.out.println("YES!!!");
            }

            if (!childList.contains(childName)) {
                childList.add(childName);
                childDateNum.add(childDateOfBirth); //childDateOfBirth = 24.06.1998

                System.out.println("Date = " + str_date);
                String[] str_result = str_date.split("\\.");

                int year = Integer.parseInt(str_result[2]);
                int month = Integer.parseInt(str_result[1]);
                int day = Integer.parseInt(str_result[0]);
                System.out.println("month == " + month);

                Date todayYear = new Date();
                todayYear.getYear();
                String todYear = String.valueOf(todayYear);
                String str = todYear.substring(todYear.length() - 4, todYear.length());
                int yearNew = Integer.parseInt(str);

                String todDay = String.valueOf(todayYear);
                String str2 = todDay.substring(8, 10);
                int todayDay = Integer.parseInt(str2);

                Date todayMonth = new Date();
                todayMonth.getDate();
                String todMonth = String.valueOf(todayMonth);
                String str3 = todMonth.substring(4, 7);
                System.out.println("cur month1: " + str3);
                int monthNew = 0;
                GetShowAllData getMonth = new GetShowAllData();
                monthNew = getMonth.getMonth(str3, monthNew);

                System.out.println("cur year = " + yearNew);
                System.out.println("cur month2 = " + monthNew);
                System.out.println("cur day = " + todayDay);

                GregorianCalendar birthDay = new GregorianCalendar(year, month, day);
                GregorianCalendar curDay = new GregorianCalendar(yearNew, monthNew, todayDay);

                CalcYearsAndMonth calcYearsAndMonth = new CalcYearsAndMonth();
                years = calcYearsAndMonth.calc(birthDay, month, year, curDay);
                month2 = calcYearsAndMonth.month2;
                System.out.println(years + " лет ии " + month2 + " месяцев");
                String st="", st1="", st2="";
                if (years == 0)
                    st1 = " лет и ";
                else if (years == 1)
                    st1 = " год и ";
                else if ((years > 1) && (years < 5))
                    st1 = " года и ";
                else if (years > 4)
                    st1 = " лет и ";
                if (month2 == 0)
                    st2 = " месяцев";
                else if (month2 == 1)
                    st2 = " месяц";
                else if ((month2 > 1) && (month2 < 5))
                    st2 = " месяца";
                else if (month2 > 4)
                    st2 = " месяцев";

                st = years + st1 + month2 + st2;
                childDateList.add(st);
                st = years + "," + monthNew;


                textArea.setText(childName + ": " + st + "(" + childDateOfBirth + ")");

                writeFile(textArea.getText().toString());

                etChild.setText("");
                dateOfBirth.setText("");
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "Ребенок с таким Ф.И.О. уже существует!\nВоспользуйтесь поиском.",
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Запись данных в файл
    public void writeFile(String st1) {
        try(FileWriter writer = new FileWriter(dirPathNew + "//ChildrenList.txt", true)) {
            String str = "";
            str += st1;
            writer.write(str);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}