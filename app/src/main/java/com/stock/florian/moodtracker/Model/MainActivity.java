package com.stock.florian.moodtracker.Model;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.stock.florian.moodtracker.Controler.File_json_gestion;
import com.stock.florian.moodtracker.Controler.Gesture_event;
import com.stock.florian.moodtracker.Controler.MoodType;
import com.stock.florian.moodtracker.Controler.Mood_record;
import com.stock.florian.moodtracker.R;
import com.stock.florian.moodtracker.View.MoodLayout;



public class MainActivity extends AppCompatActivity
{

    GestureDetector gesturedetector;

    public static MoodLayout Moods[]  = new MoodLayout[5]; // The array of "Moods"  by default with length to 5

   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main); // Show the  main view


        MoodType.init();


        Moods[0]=new MoodLayout(this,MoodType.moods.get(0).color,MoodType.moods.get(0).smiley_index);
        Moods[1]=new MoodLayout(this,MoodType.moods.get(1).color,MoodType.moods.get(1).smiley_index);
        Moods[2]=new MoodLayout(this,MoodType.moods.get(2).color,MoodType.moods.get(2).smiley_index);
        Moods[3]=new MoodLayout(this,MoodType.moods.get(3).color,MoodType.moods.get(3).smiley_index);
        Moods[4]=new MoodLayout(this,MoodType.moods.get(4).color,MoodType.moods.get(4).smiley_index);



        // I add the content to the view

        for(int a=0; a<Moods.length;a++){this.addContentView(Moods[a],Moods[a].getLayoutParams());}



        gesturedetector = new GestureDetector(this, new Gesture_event(this));

        // Static function to load the file gson and set the static array of mood list
        File_json_gestion.Load_file(this);

        //FOR TEST
        //File_json_gestion.writeTest(20);


        // By default on the screen the mood happy is active
        Moods[3].setScaleY(1);
        Moods[3].isactive=true;


        //Try to search if the last mood recorded is the date of today

        for (Mood_record item : Mood_record.moods_list_save)
        {

           if(File_json_gestion.date_today().equals(item.date) && item.mood!=3)
           {
               Moods[item.mood].setScaleY(1);
               Moods[item.mood].isactive=true;
               Moods[3].setScaleY(0);
               Moods[3].isactive=false;
           }





        }




    }

    // I have a listener when the user touch the screen of the activity and return true

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        //send true for the listener gesture if the use touch the screen
        boolean eventConsumed=gesturedetector.onTouchEvent(event);


        // The action when the user is upping the finger, i replace the different moods, compared with their scaleY
        if(event.getAction()==MotionEvent.ACTION_UP)
        {


            for(int i=0;i<Moods.length;i++)
            {
                if(Moods[i].isactive)
                {

                    try
                    {
                            // If the scroll is beginning to the up of screen

                            if (Moods[i].getScaleY() > Moods[i + 1].getScaleY() && Gesture_event.scroll_direction == 0) {
                                Moods[i].setScaleY(1);
                                Moods[i + 1].setScaleY(0);
                            } else if (Moods[i].getScaleY() < Moods[i + 1].getScaleY() && Gesture_event.scroll_direction == 0) {
                                Moods[i].setScaleY(0);
                                Moods[i + 1].setScaleY(1);
                                Moods[i].isactive = false;
                                Moods[i + 1].isactive = true;
                                File_json_gestion.Save_file(this,"");
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
                        File_json_gestion.Save_file(this,"");
                    }
                }
                    // If the scroll is beginning to the down of screen

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
                           File_json_gestion.Save_file(this,"");
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
