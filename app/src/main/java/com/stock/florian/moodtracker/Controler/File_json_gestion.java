package com.stock.florian.moodtracker.Controler;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stock.florian.moodtracker.Model.MainActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


// Load a gson file and get the type Mood_record object

public class File_json_gestion
{
    public static void Load_file(Context context)
    {
        try {

            FileInputStream fis = context.openFileInput("MoodTracker.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            Type listType = new TypeToken<ArrayList<Mood_record>>(){}.getType();
            Mood_record.moods_list_save = new Gson().fromJson(sb.toString(), listType);


        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }

    // Function who save the static array  moods_list_save to the file json

    public static void Save_file(Context context,String text_comment)
    {


        for(int i = 0; i< MainActivity.Moods.length; i++)
        {
                    if(MainActivity.Moods[i].isactive)
                    {

                        Mood_record mood_save  = new Mood_record ( date_today(),text_comment,i);


                        for(int a=0;a<Mood_record.moods_list_save.size();a++)
                        {

                            if(mood_save.date.equals(Mood_record.moods_list_save.get(a).date)){Mood_record.moods_list_save.remove(a);}
                        }

                        Mood_record.moods_list_save.add(mood_save);

                     }

        }



        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<List<Mood_record>>() {}.getType();
        String json = gson.toJson(Mood_record.moods_list_save, type);


        try
        {
            FileOutputStream fos = context.openFileOutput("MoodTracker.json", Context.MODE_PRIVATE);
            fos.write(json.getBytes());fos.flush();fos.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String date_today()
    {
        Date date = new Date();
        SimpleDateFormat date_string = new  SimpleDateFormat("dd - MM - yyyy");
        return  date_string.format(date);
    }


   public static void writeTest(int number_entry)
   {

       for(int i=0;i<number_entry;i++)
       {

           Random  rnd;
           Date    dt;
           long    ms;

           rnd = new Random();
           ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
           dt = new Date(ms);

           SimpleDateFormat date_string = new  SimpleDateFormat("dd - MM - yyyy");


           Mood_record.moods_list_save.add(new Mood_record(date_string.format(dt),"This is a test "+i,0));
       }


   }


}
