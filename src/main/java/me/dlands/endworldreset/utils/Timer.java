package me.dlands.endworldreset.utils;

import me.dlands.endworldreset.settings.Config;
import java.util.Date;

public class Timer {

    public static String getLongPeriod() {

        Date start_date = new Date();
        Date end_date = Config.getSettings().getNextReset().getTime();


        // SimpleDateFormat converts the
        // string format to date object
        // Try Block

        // parse method is used to parse
        // the text from a string to
        // produce the date

        // Calucalte time difference
        // in milliseconds
        long difference_In_Time = end_date.getTime() - start_date.getTime();

        // Calucalte time difference in
        // seconds, minutes, hours, years,
        // and days
        long difference_In_Seconds = (difference_In_Time / 1000) % 60;
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

        // Print the date difference in
        // years, in days, in hours, in
        // minutes, and in seconds

        String period =
                (difference_In_Days>0?difference_In_Days + " day(s), " : "")
                + (difference_In_Hours>0?difference_In_Hours + " hour(s), " : "")
                + (difference_In_Minutes>0? difference_In_Minutes + " minute(s) " : "")
                + difference_In_Seconds + " second(s)";

        return period;
    }

}