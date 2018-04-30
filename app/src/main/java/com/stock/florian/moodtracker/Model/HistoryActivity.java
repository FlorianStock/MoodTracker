package com.stock.florian.moodtracker.Model;

import android.support.v7.app.AppCompatActivity;
import com.stock.florian.moodtracker.*;
import com.stock.florian.moodtracker.Controler.Mood_record;
import com.stock.florian.moodtracker.View.BarMoodLayout;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


/**
 * Created by flori on 21/03/2018.
 */

public class HistoryActivity  extends AppCompatActivity
{
    public static int  number_show_mood=7;
    public static int size_case;
    public static int size_width_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        size_case = metrics.heightPixels / (number_show_mood+1);
        size_width_screen = metrics.widthPixels;

        Collections.sort(Mood_record.moods_list_save, new Comparator<Mood_record>()
        {
            public int compare(Mood_record s1, Mood_record s2)
            {
                Date results1 = new Date();
                Date results2 = new Date();

                SimpleDateFormat dateFormats1 = new SimpleDateFormat("dd - MM - yyyy");
                SimpleDateFormat dateFormats2 = new SimpleDateFormat("dd - MM - yyyy");

                try {
                     results1 = dateFormats1.parse(s1.date);
                     results2 = dateFormats2.parse(s2.date);


                } catch (Exception e)
                {
                    //Log.e("HistoryActivity  ","Parse Error");
                }

                return results2.compareTo(results1);

            }
        });


        try
        {
            int decrement=number_show_mood;

            for (int a = 0 ; a <= number_show_mood; a++)
            {


                BarMoodLayout layout_bar = new BarMoodLayout(this, Mood_record.moods_list_save.get(a).mood,  Mood_record.moods_list_save.get(a).date , Mood_record.moods_list_save.get(a).comment);
                layout_bar.setY((decrement * size_case)-size_case);
                this.addContentView(layout_bar, layout_bar.getLayoutParams());
                decrement--;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            Log.i("Error BarMoodLayout",e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.button_chart, menu);

        return true;
    }


    // Function when the user select a item in action bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_chart:
                ChartActivity.entries.clear();
                Intent intent = new Intent(this, ChartActivity.class);
                this.startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }




}


