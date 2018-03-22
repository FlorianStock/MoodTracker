package com.stock.florian.moodtracker;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flooo on 15/03/2018.
 */
public class Mood extends RelativeLayout // changer nom classe MoodLayout
{


    public static List<Mood_record> moods_list_save = new ArrayList<>();

    public Boolean isactive=false;

    public  Mood(Context context, int background, int image_smiley)
    {

        super(context);

        //Creation of the layout in the current context
        //RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(param);

        //Creation and set the color of the background for the relativelayout
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, background));

        //Create the image of the smiley
        ImageView iv = new ImageView(context);

        LinearLayout.LayoutParams param_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param_image.topMargin=100;
        param_image.gravity = Gravity.CENTER_VERTICAL ;
        iv.setLayoutParams(param_image);
        iv.setImageResource(image_smiley);
        iv.setScaleX(0.8f);iv.setScaleY(0.8f);

        ImageButton comment_button = new ImageButton(context);
        comment_button.setBackgroundResource(R.drawable.ic_note_add_black);
        RelativeLayout.LayoutParams param_button_add_note = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param_button_add_note.bottomMargin=70;
        param_button_add_note.leftMargin=50;
        param_button_add_note.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        param_button_add_note.alignWithParent=true;
        comment_button.setLayoutParams(param_button_add_note);

        ImageButton history_button = new ImageButton(context);
        history_button.setBackgroundResource(R.drawable.ic_history_black);
        RelativeLayout.LayoutParams param_button_history = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param_button_history.bottomMargin=70;
        param_button_history.rightMargin=50;
        param_button_history.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        param_button_history.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        param_button_history.alignWithParent=true;
        history_button.setLayoutParams(param_button_history);

        comment_button.setOnClickListener(new Gestion_buttons(context));comment_button.setTag("comment");
        history_button.setOnClickListener(new Gestion_buttons(context));history_button.setTag("history");

        this.addView(view);
        this.addView(iv);
        this.addView(comment_button);
        this.addView(history_button);
        this.setScaleY(0);
        //////////////////////////////////////////////////





        //this.setScaleY(0.4f);
    }

    public static class Mood_record
    {
        String date;
        String comment;
        int mood;

        public Mood_record(String date,String comment,int mood)
        {
            this.date=date;
            this.comment=comment;
            this.mood=mood;
        }
    }






}
