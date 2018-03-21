package com.stock.florian.moodtracker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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


                Bar_mood layout_bar = new Bar_mood(this, Mood.moods_list_save.get(a).mood, Mood.moods_list_save.get(a).date, Mood.moods_list_save.get(a).comment);
                layout_bar.setY(a * size_case);
                this.addContentView(layout_bar, layout_bar.getLayoutParams());

            }
        }
        catch (IndexOutOfBoundsException e) {

        }

    }
}

class Bar_mood extends RelativeLayout
{
    private double width_bar;
    private  String comment;
    private  Context context;

    public Bar_mood(Context context,int mood_type,String date,String comment)
    {
        super(context);

        comment=comment;
        context=context;






        View view = new View(context);

        if(mood_type!=0)
        {

        }
        else
        {
            //view.setLayoutParams(new LayoutParams(HistoryActivity.size_width_screen / mood_type, HistoryActivity.size_case));

        }
        switch(mood_type)
        {
            case 0 :
                width_bar = HistoryActivity.size_width_screen*0.3;
                view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.faded_red));break;
            case 1 :
                width_bar = HistoryActivity.size_width_screen*0.4;
                view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.warm_grey));break;
            case 2 :
                width_bar = HistoryActivity.size_width_screen*0.6;
                view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.cornflower_blue_65));break;
            case 3 :
                width_bar = HistoryActivity.size_width_screen*0.8;
                view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.light_sage));break;
            case 4 :
                width_bar = HistoryActivity.size_width_screen;
                view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.banana_yellow));break;

        }


        ImageButton show_comment_button = new ImageButton(context);
        show_comment_button.setBackgroundResource(R.drawable.ic_comment_black_48px);
        RelativeLayout.LayoutParams param_button_show_comment = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param_button_show_comment.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        param_button_show_comment.rightMargin=15; param_button_show_comment.topMargin=15;
        show_comment_button.setLayoutParams(param_button_show_comment);

        



        TextView text_date = new TextView(context);
        text_date.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        text_date.setText(date);

       // view.setLayoutParams(status_bar);


        this.addView(view);
        this.addView(text_date);
        this.addView(show_comment_button);

        int i = (int) width_bar;
        RelativeLayout.LayoutParams status_bar = new RelativeLayout.LayoutParams(i,LinearLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(status_bar);


    }



}
