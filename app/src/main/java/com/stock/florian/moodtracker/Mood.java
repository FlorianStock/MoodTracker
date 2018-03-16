package com.stock.florian.moodtracker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Flooo on 15/03/2018.
 */
public class Mood extends RelativeLayout
{


    Boolean isactive=false;

    public  Mood(Context context, int background, int image_smiley)
    {

        super(context);

        //Creation of the layout in the current context
        //RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(param);

        //Creation and set the color of the background for the relativelayout
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, background));

        //Create the image of the smiley
        ImageView iv = new ImageView(context);

        LinearLayout.LayoutParams param_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        param_image.topMargin=100;
        param_image.gravity = Gravity.CENTER_VERTICAL ;
        iv.setLayoutParams(param_image);
        iv.setImageResource(image_smiley);


        this.addView(view);
        this.addView(iv);
        this.setScaleY(0);
        //////////////////////////////////////////////////





        //this.setScaleY(0.4f);
    }






}
