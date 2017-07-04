package pl.andyshon.andy_pc;

import pl.andyshon.andy_pc.ui.controller.MainFrameController;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ReadFromFile {     //Класс, реализованный для получение данных из файла

    private File sourceFile, outputFile, file;
    private String dirPath, dirPathNew, special;

    public String getStrFromFile() {
        StringBuilder sb = new StringBuilder();

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

        try(FileReader reader = new FileReader(dirPathNew + "//ChildrenList.txt"))
        {
            int c;
            System.out.println("данные из файла: ");
            while((c=reader.read())!=-1){
                System.out.print((char)c);
                sb.append((char)c).toString();
            }
            special = sb.toString();
            System.out.println("\ndata from file: " + special.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("В программе нет ни одного ребёнка\nВведите Ф.И.О. и год рождения\nпервого ребёнка");
            JOptionPane.showMessageDialog(null,
                    "В программе нет ни одного ребёнка\nВведите Ф.И.О. и год рождения\nпервого ребёнка");
        }
        return special;
    }
}