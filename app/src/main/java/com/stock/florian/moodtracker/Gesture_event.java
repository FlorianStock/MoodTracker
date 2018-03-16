package com.stock.florian.moodtracker;


import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.io.IOError;

/**
 * Created by Flooo on 15/03/2018.
 */
public class Gesture_event extends GestureDetector.SimpleOnGestureListener
{


    public static int scroll_direction;
    private Context current_context;

    public Gesture_event(Context context)
        {
            current_context=context;
        }



    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        // Do scrolling in onTouchEvent() since onScroll() are not call immediately
        //  when user touch and move the spinnerwheel
        WindowManager wm = (WindowManager) current_context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        //System.out.println(e1.getY()+" "+size.y);
        for(int i=0;i<MainActivity.Moods.length;i++) // For any Mood
        {
            if (MainActivity.Moods[i].isactive)
            {
                if (e1.getY() < (metrics.heightPixels / 2))
                {

                    scroll_direction = 1;


                        MainActivity.Moods[i].setPivotY(MainActivity.Moods[i].getHeight());
                        MainActivity.Moods[i].setScaleY(MainActivity.Moods[i].getScaleY() + (distanceY / MainActivity.Moods[i].getHeight()));

                        if(MainActivity.Moods[i] != MainActivity.Moods[0] )
                        {
                            MainActivity.Moods[i - 1].setPivotY(MainActivity.Moods[i - 1].getY());
                            MainActivity.Moods[i - 1].setScaleY(MainActivity.Moods[i - 1].getScaleY() - (distanceY / MainActivity.Moods[i - 1].getHeight()));
                        }



                }
                else
                {
                    scroll_direction = 0;

                    try
                    {
                        MainActivity.Moods[i].setPivotY(MainActivity.Moods[i].getY());
                        MainActivity.Moods[i].setScaleY(MainActivity.Moods[i].getScaleY() - (distanceY / MainActivity.Moods[i-1].getHeight()));

                    } catch ( IndexOutOfBoundsException e )
                    {

                    }


                    try
                    {
                        MainActivity.Moods[i + 1].setPivotY(MainActivity.Moods[i + 1].getHeight());
                        MainActivity.Moods[i + 1].setScaleY(MainActivity.Moods[i + 1].getScaleY() + (distanceY / MainActivity.Moods[i].getHeight()));

                    }catch ( IndexOutOfBoundsException e )
                    {

                    }

                 }
        }


        }



        return true;
    }




}
