package com.stock.florian.moodtracker.Models;


public class MoodObjectSave
{


    public String date;
    public String comment;
    public int position;

    public MoodObjectSave(String date, String comment, int position)
    {
        this.date=date;
        this.comment=comment;
        this.position=position;
    }
}