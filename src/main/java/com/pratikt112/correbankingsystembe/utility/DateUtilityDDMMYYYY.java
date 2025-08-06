package com.pratikt112.correbankingsystembe.utility;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
}
