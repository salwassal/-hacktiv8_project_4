package com.hacktiv8.bux.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {

    public static void main(String[] args) {

        Long timeInMillis = Long.parseLong("1669775160000");
        System.out.println(timestampToLocalDate(timeInMillis));
    }

    public static String timestampToLocalDate(long ts) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts);
        java.util.Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(d);
    }

    public static String timestampToLocalDate2(long ts) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts);
        java.util.Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy");

        return sdf.format(d);
    }

    public static String timestampToLocalDate3(long ts) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts);
        java.util.Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");

        return sdf.format(d);
    }

    public static String timestampToLocalDate4(long ts) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts);
        java.util.Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

        return sdf.format(d);
    }

    public static String timestampToBookNo(long ts) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ts);
        java.util.Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");

        return sdf.format(d);
    }

}
