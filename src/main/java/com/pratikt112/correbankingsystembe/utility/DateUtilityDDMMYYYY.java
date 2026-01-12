package com.pratikt112.correbankingsystembe.utility;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Component
public class DateUtilityDDMMYYYY {
    public String getCurrentDateInDDMMYYYY() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getSCurrentDateInDDMMYYYY() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getCurrentTimeHhMmSsWww(){
        LocalTime now = LocalTime.now();
        int micro = now.getNano()/1000;

        return String.format("%02d%02d%02d%03d",
                now.getHour(),
                now.getMinute(),
                now.getSecond(),
                micro
        );
    }
}
