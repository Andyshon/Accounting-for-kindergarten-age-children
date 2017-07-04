package pl.andyshon.andy_pc;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetShowAllData {

    public static int numTop;
    private int years;
    private Font font;
    private float fontSize = 12.0f;

    public void printAllData(Object str_name, Object str_date, JTextArea textArea){
        String strName = str_name.toString();
        System.out.println("Date = " + str_date);
        String str_date2 = str_date.toString();
        String[] str_result = str_date.toString().split("\\.");

        int year = Integer.parseInt(str_result[2]);
        int month = Integer.parseInt(str_result[1]);
        int day = Integer.parseInt(str_result[0]);

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
        int monthNew = 0;
        monthNew = getMonth(str3, monthNew);

        System.out.println("cur year = " + yearNew);
        System.out.println("cur month = " + monthNew);
        System.out.println("cur day = " + todayDay);

        GregorianCalendar birthDay = new GregorianCalendar(year, month, day);
        GregorianCalendar curDay = new GregorianCalendar(yearNew, monthNew, todayDay);

        CalcYearsAndMonth calcYearsAndMonth = new CalcYearsAndMonth();
        years = calcYearsAndMonth.calc(birthDay, month, year, curDay);
        System.out.println(years + " лет и " + calcYearsAndMonth.month2 + " месяцев");
        System.out.println(years + "," + calcYearsAndMonth.month2);
        String st="";

        st = years + "," + calcYearsAndMonth.month2;
        numTop += 1;

        font = textArea.getFont();
        textArea.setFont(font.deriveFont(fontSize));
        textArea.append(numTop + ". " + strName + ": " + st + "(" + str_date2 + ")\n");
    }

    // Получаем текущий месяц в числовой системе счисления
    public int getMonth(String str, int month){
        if (str.equals("Jan"))
            month = 1;
        else if (str.equals("Feb"))
            month = 2;
        else if (str.equals("Mar"))
            month = 3;
        else if (str.equals("Apr"))
            month = 4;
        else if (str.equals("May"))
            month = 5;
        else if (str.equals("Jun"))
            month = 6;
        else if (str.equals("Jul"))
            month = 7;
        else if (str.equals("Aug"))
            month = 8;
        else if (str.equals("Sep"))
            month = 9;
        else if (str.equals("Oct"))
            month = 10;
        else if (str.equals("Nov"))
            month = 11;
        else if (str.equals("Dec"))
            month = 12;
        return month;
    }

}