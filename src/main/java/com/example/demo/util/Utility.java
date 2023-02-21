package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public static String getDate() {
        Date date = new Date();
        return convertEpochTime(date.getTime());
    }

    public static String convertEpochTime(Long epochTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(epochTime);
    }

}
