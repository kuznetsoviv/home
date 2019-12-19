package ru.kuznetsoviv.zip;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"), new Locale("en"));
        System.out.println("Установка и получение времени:");
        //calendar.set(Calendar.HOUR_OF_DAY, 0);
        System.out.println(calendar.getTime());
        System.out.println("Получение параметров календаря:");
        System.out.println("------------------------------");
        System.out.println(calendar);
    }

}
