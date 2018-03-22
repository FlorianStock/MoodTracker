package com.stock.florian.moodtracker;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by flori on 21/03/2018.
 */

public class HistoryActivity  extends AppCompatActivity
{
    public static int  number_show_mood=8;
    public static int size_case;
    public static int size_width_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);





       // his.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, android.R.layout.h);


        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        size_case = metrics.heightPixels / number_show_mood;
        size_width_screen = metrics.widthPixels;

        Collections.sort(Mood.moods_list_save, new Comparator<Mood.Mood_record>() {
            public int compare(Mood.Mood_record s1, Mood.Mood_record s2) {
                return s1.date.compareTo(s2.date);

            }
        });


        try
        {
            for (int a = 0; a <= number_show_mood; a++)
            {


                BarMood layout_bar = new BarMood(this, Mood.moods_list_save.get(a).mood, Mood.moods_list_save.get(a).date, Mood.moods_list_save.get(a).comment);
                layout_bar.setY(a * size_case);
                this.addContentView(layout_bar, layout_bar.getLayoutParams());

            }
        }
        catch (IndexOutOfBoundsException e) {

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.button_chart, menu);
        return true;
    }




}


