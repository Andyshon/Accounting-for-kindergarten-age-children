package pl.andyshon.andy_pc.ui.view;

import javax.swing.*;

public class MainFrame extends JFrame{

    public static final int WIDTH = 800, HEIGHT = 500;

    private JPanel mainPanel;
    private JTextField tfName;
    private JTextField tfDate;
    private JTextField tfSearch;
    private JButton btnShowAll, btnCount;
    private JTextArea textArea;
    private JButton btnSearch;
    private JButton btnRemoveChild;
    private JButton btnAbout;
    private JButton btnMinus;
    private JButton btnPlus;
    private JButton btnPdfCreate;
    private JButton btnF1;

    public MainFrame() {
        setTitle("Возраст детей");
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon("src/main/resources/icon.png");
        setIconImage(icon.getImage());
    }

    public JButton getBtnF1(){
        return btnF1;
    }
    public JButton getBtnPdfCreate(){
        return btnPdfCreate;
    }
    public JButton getBtnMinus(){
        return btnMinus;
    }
    public JButton getBtnPlus(){
        return btnPlus;
    }
    public JButton getBtnRemoveChild(){
        return btnRemoveChild;
    }
    public JButton getBtnAbout(){
        return btnAbout;
    }
    public JButton getBtnSearch(){
        return btnSearch;
    }
    public JButton getBtnCount(){
        return btnCount;
    }
    public JTextArea getTextArea() {
        return textArea;
    }
    public JTextField getTfName() {
        return tfName;
    }
    public JTextField getTfDate() {
        return tfDate;
    }
    public JButton getBtnShowAll() {
        return btnShowAll;
    }
    public JTextField getTfSearch(){
        return tfSearch;
    }

}
