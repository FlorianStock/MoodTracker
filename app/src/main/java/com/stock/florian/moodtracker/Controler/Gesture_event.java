package com.stock.florian.moodtracker.Controler;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.stock.florian.moodtracker.Model.MainActivity;

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
        // I search the dimension of the screen
        WindowManager wm = (WindowManager) current_context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        // For any Mood
        for(int i = 0; i< MainActivity.Moods.length; i++)
        {
            // If the mood is show on the screen with her variable.
            if (MainActivity.Moods[i].isactive)
            {
                // If the user touch the screen below of middle

                if (e1.getY() < (metrics.heightPixels / 2))
                {

                    scroll_direction = 1; // We set the scroll

                    // If the mood is not equal to 0, because, we use the previous mood, and the next mood in this function.
                    // So we have a Error "BoundsArrayIndex" without this exeption !

                    if(MainActivity.Moods[i] != MainActivity.Moods[0] )
                    {
                        //We set the pivot to the top.
                        MainActivity.Moods[i].setPivotY(MainActivity.Moods[i].getHeight());

                        //The scale is the distance Y of the gesture divide by the height of the image, with the current scaleY.
                        MainActivity.Moods[i].setScaleY(MainActivity.Moods[i].getScaleY() + (distanceY / MainActivity.Moods[i].getHeight()));

                        // The mood who is under the active mood is scalling too
                        MainActivity.Moods[i - 1].setPivotY(MainActivity.Moods[i - 1].getY());
                        MainActivity.Moods[i - 1].setScaleY(MainActivity.Moods[i - 1].getScaleY() - (distanceY / MainActivity.Moods[i - 1].getHeight()));
                    }



                }
                // If the user touch the screen on the down of screen
                else
                {

                    // It is the same logic but the pivotY of the scale is to the down.

                    scroll_direction = 0;

                    if(MainActivity.Moods[i] != MainActivity.Moods[MainActivity.Moods.length-1] ) // I added a exception for the out of index array
                    {
                        MainActivity.Moods[i].setPivotY(MainActivity.Moods[i].getY());
                        MainActivity.Moods[i].setScaleY(MainActivity.Moods[i].getScaleY() - (distanceY / MainActivity.Moods[i + 1].getHeight()));

                        MainActivity.Moods[i + 1].setPivotY(MainActivity.Moods[i + 1].getHeight());
                        MainActivity.Moods[i + 1].setScaleY(MainActivity.Moods[i + 1].getScaleY() + (distanceY / MainActivity.Moods[i].getHeight()));

                    }

                 }
        }


        }



        return true;
    }




}
