package com.stock.florian.moodtracker.Controler;

import java.util.ArrayList;
import java.util.List;

/**
 * This is my object class for record  the data to the file gson
 */

public class Mood_record
{

    public static List<Mood_record> moods_list_save = new ArrayList<>();

    public String date;
    public String comment;
    public int mood;

    public Mood_record(String date, String comment, int mood)
    {
        this.date=date;
        this.comment=comment;
        this.mood=mood;
    }
}
