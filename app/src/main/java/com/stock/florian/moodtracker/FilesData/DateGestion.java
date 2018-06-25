package com.stock.florian.moodtracker.FilesData;



import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGestion
{

    public String getDateToday()
    {
        Date date = new Date();
        SimpleDateFormat date_string = new SimpleDateFormat("dd - MM - yyyy");
        return date_string.format(date);
    }



}
