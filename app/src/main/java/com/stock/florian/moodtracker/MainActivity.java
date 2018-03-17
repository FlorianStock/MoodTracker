package com.stock.florian.moodtracker;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class MainActivity extends AppCompatActivity
{

    GestureDetector gesturedetector;
    Mood mood_sad;

    public static Mood Moods[] = new Mood[5];


   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Show the  main view

        Moods[0]=new Mood(this,R.color.faded_red,R.drawable.smiley_sad);
        Moods[1]=new Mood(this,R.color.warm_grey,R.drawable.smiley_disappointed);
        Moods[2]=new Mood(this,R.color.cornflower_blue_65,R.drawable.smiley_normal);
        Moods[3]=new Mood(this,R.color.light_sage,R.drawable.smiley_happy);
        Moods[4]=new Mood(this,R.color.banana_yellow,R.drawable.smiley_super_happy);


        for(int a=0; a<Moods.length;a++){this.addContentView(Moods[a],Moods[a].getLayoutParams());}

        Moods[2].setScaleY(1);
        Moods[2].isactive=true;

        // Initialisation of  layouts with a class extend relativelayout

        gesturedetector = new GestureDetector(this, new Gesture_event(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //method onTouchEvent of GestureDetector class Analyzes the given motion event
        //and if applicable triggers the appropriate callbacks on the GestureDetector.OnGestureListener supplied.
        //Returns true if the GestureDetector.OnGestureListener consumed the event, else false.

        boolean eventConsumed=gesturedetector.onTouchEvent(event);



        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            //Gesture_event.end_event();

            for(int i=0;i<Moods.length;i++)
            {
                if(Moods[i].isactive)
                {
                    try
                    {

                            if (Moods[i].getScaleY() > Moods[i + 1].getScaleY() && Gesture_event.scroll_direction == 0) {
                                Moods[i].setScaleY(1);
                                Moods[i + 1].setScaleY(0);
                            } else if (Moods[i].getScaleY() < Moods[i + 1].getScaleY() && Gesture_event.scroll_direction == 0) {
                                Moods[i].setScaleY(0);
                                Moods[i + 1].setScaleY(1);
                                Moods[i].isactive = false;
                                Moods[i + 1].isactive = true;
                            }

                    } catch ( IndexOutOfBoundsException e )
                {
                    if(Moods[i].getScaleY()>Moods[i-1].getScaleY())
                    {
                        Moods[i].setScaleY(1);
                    }
                    else
                    {
                        Moods[i].setScaleY(0);Moods[i-1].setScaleY(1);Moods[i].isactive = false;
                        Moods[i - 1].isactive = true;
                    }
                }

                   try
                   {
                       if (Moods[i].getScaleY() > Moods[i - 1].getScaleY() && Gesture_event.scroll_direction == 1)
                       {
                           Moods[i].setScaleY(1);
                           Moods[i - 1].setScaleY(0);
                       }
                       else if (Moods[i].getScaleY() < Moods[i - 1].getScaleY() && Gesture_event.scroll_direction == 1)
                       {
                           Moods[i].setScaleY(0);
                           Moods[i - 1].setScaleY(1);
                           Moods[i].isactive = false;
                           Moods[i - 1].isactive = true;
                       }
                   } catch ( IndexOutOfBoundsException e )
                {
                    if(Moods[i].getScaleY()>Moods[i+1].getScaleY())
                    {
                        Moods[i].setScaleY(1);
                    }
                    else
                    {
                        Moods[i].setScaleY(0);Moods[i+1].setScaleY(1);Moods[i].isactive = false;
                        Moods[i + 1].isactive = true;
                    }
                    //Moods[i].setScaleY(1);
                }


                }
            }
        }

        if (eventConsumed)
        {

            return true;
        }
        else
        {
            return false;
        }


    }

}
