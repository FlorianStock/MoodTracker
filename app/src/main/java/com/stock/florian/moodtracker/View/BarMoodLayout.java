package com.stock.florian.moodtracker.View;

import com.stock.florian.moodtracker.Controler.MoodType;
import com.stock.florian.moodtracker.Model.HistoryActivity;
import com.stock.florian.moodtracker.R;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by flori on 22/03/2018.
 */

public class BarMoodLayout extends RelativeLayout
{
    private double width_bar;

    private View view;
    private ImageButton showCommentButton;

    public BarMoodLayout(final Context context, int mood_type, String date, String comment)
    {
        super(context);


        view = new View(context);
        this.addView(view);

        designMood(MoodType.moods.get(mood_type).factor,MoodType.moods.get(mood_type).color,context);


    if(!comment.equals(""))
    {
        showCommentButton = new ImageButton(context);
        showCommentButton.setBackgroundResource(R.drawable.ic_comment_black_48px);
        RelativeLayout.LayoutParams param_button_show_comment = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // param_button_show_comment.addRule(Gravity.BOTTOM);
        param_button_show_comment.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        param_button_show_comment.rightMargin = 15;
        param_button_show_comment.topMargin = 50;
        showCommentButton.setLayoutParams(param_button_show_comment);

        final String comment_show = comment;
        showCommentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, comment_show,
                        Toast.LENGTH_SHORT).show();
            }
        });

        this.addView(showCommentButton);
    }

        TextView text_date = new TextView(context);
        text_date.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        text_date.setText(date);
        this.addView(text_date);



        int i = (int) width_bar;
        RelativeLayout.LayoutParams status_bar = new RelativeLayout.LayoutParams(i, LinearLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(status_bar);

    }

    // Function to personnalize the design with parameters

    private void designMood(double factor, int color, Context context)
    {
        width_bar = HistoryActivity.size_width_screen*factor;
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, HistoryActivity.size_case));
        view.setBackgroundColor(ContextCompat.getColor(context, color));
    }


}