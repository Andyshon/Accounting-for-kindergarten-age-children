package pl.andyshon.andy_pc;

import java.util.GregorianCalendar;

public class CalcYearsAndMonth {

    public int month2;

    // Считаем разницу в годах и месяцах между двумя датами
    public int calc(GregorianCalendar birthDay, int month, int year, GregorianCalendar curDay) {
        System.out.println("curDay.get(GregorianCalendar.YEAR) = " + curDay.get(GregorianCalendar.YEAR));
        System.out.println("birthDay.get(GregorianCalendar.YEAR) = " + birthDay.get(GregorianCalendar.YEAR));
        int years = curDay.get(GregorianCalendar.YEAR) - year;
        System.out.println("years = " + years);

        int curMonth = curDay.get(GregorianCalendar.MONTH);
        int birthMonth = month;
        System.out.println("curMonth = " + curMonth);
        System.out.println("birthMonth = " + birthMonth);

        if (curMonth < birthMonth) {
            System.out.println("^1");
            years --;
            System.out.println("DAY_OF_MONTH curDay = " + curDay.get(GregorianCalendar.DAY_OF_MONTH));
            System.out.println("DAY_OF_MONTH birthDay = " + birthDay.get(GregorianCalendar.DAY_OF_MONTH));
            if (curDay.get(GregorianCalendar.DAY_OF_MONTH) > birthDay.get(GregorianCalendar.DAY_OF_MONTH)) {
                month2 = 12 - birthMonth + curMonth;
            }
            else if (curDay.get(GregorianCalendar.DAY_OF_MONTH) < birthDay.get(GregorianCalendar.DAY_OF_MONTH)) {
                month2 = 12 - birthMonth + curMonth - 1;
            }
        }
        else if (curMonth > birthMonth){
            System.out.println("^2");
            month2 = curMonth - birthMonth;
        }
        else if (curMonth == birthMonth
                && curDay.get(GregorianCalendar.DAY_OF_MONTH) > birthDay.get(GregorianCalendar.DAY_OF_MONTH)) {
            System.out.println("^3");
            month2 = 0;
        }
        else if (curMonth == birthMonth
                && curDay.get(GregorianCalendar.DAY_OF_MONTH) < birthDay.get(GregorianCalendar.DAY_OF_MONTH)) {
            System.out.println("^4");
            years --;
            month2 = 11;
        }
        else if (curMonth == birthMonth
                && curDay.get(GregorianCalendar.DAY_OF_MONTH) == birthDay.get(GregorianCalendar.DAY_OF_MONTH)) {
            month2=0;
        }
        return years;
    }

}