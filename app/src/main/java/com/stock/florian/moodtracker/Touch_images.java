package com.stock.florian.moodtracker;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by florian on 17/03/2018.
 */

public class Touch_images extends RelativeLayout
{

    public Touch_images(Context context)
    {
        super(context);

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


        this.setLayoutParams(param);

        ImageButton button_comment = new ImageButton(context);
        button_comment.setImageResource(R.drawable.ic_note_add_black);

        this.addView(button_comment);

        RelativeLayout.LayoutParams param_button_history = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);



        ImageButton button_history = new ImageButton(context);
        button_history.setId(0);
        button_comment.setImageResource(R.drawable.ic_history_black);
        param_button_history.addRule(RelativeLayout.RIGHT_OF, button_history.getId());
        button_history.setLayoutParams(param_button_history);
        this.addView(button_history);

       //button_comment.setOnClickListener(onclick());
    }



}
