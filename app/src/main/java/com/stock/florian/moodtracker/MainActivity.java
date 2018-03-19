package com.stock.florian.moodtracker;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    GestureDetector gesturedetector;

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


        // Initialisation of  layouts with a class extend relativelayout
        gesturedetector = new GestureDetector(this, new Gesture_event(this));

            File_json_gestion.Load_file(this);

        for (Mood.Mood_record item : Mood.moods_list_save)
        {
           if(item.date.equals(File_json_gestion.date_today()))
           {
               Moods[item.mood].setScaleY(1);
               Moods[item.mood].isactive=true;
           }
           else
           {
               Moods[1].setScaleY(1);
               Moods[1].isactive=true;
           }
        }

            Moods[2].setScaleY(1);
            Moods[2].isactive=true;


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
