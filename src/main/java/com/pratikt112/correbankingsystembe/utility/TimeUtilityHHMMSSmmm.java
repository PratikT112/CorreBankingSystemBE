package com.pratikt112.correbankingsystembe.utility;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeUtilityHHMMSSmmm {
    public String getCurrentTimeInHHMMSSSSS() {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
        Date date = new Date();
        return formatter.format(date);
    }
}
