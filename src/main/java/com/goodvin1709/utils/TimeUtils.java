package com.goodvin1709.utils;

import com.sun.javafx.binding.StringFormatter;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Goodvin1709 on 02.07.2016.
 */
public class TimeUtils {
    private static final Logger LOGGER = Logger.getLogger(TimeUtils.class);

    //get Current System time format 0-24 hours.
    public static String messageForCurrentTime(String message) {
        long systemTime = System.currentTimeMillis();
        return new SimpleDateFormat("[kk:mm:ss] ").format(new Date(systemTime)).concat(message);
    }

    //parse time with pattern hh:mm:ss.
    public static Date parseTime(String parseTime) {
        DateFormat format = new SimpleDateFormat("kk:mm:ss");
        Date parseDate;
        try {
            parseDate = format.parse(parseTime);
        } catch (ParseException e) {
            LOGGER.info("Error while parsing the date.");
            parseDate = new Date();
        }
        return parseDate;
    }
}
