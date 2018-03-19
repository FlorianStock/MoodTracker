package com.stock.florian.moodtracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flori on 19/03/2018.
 */

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

            Type listType = new TypeToken<ArrayList<Mood.Mood_record>>(){}.getType();
            Mood.moods_list_save = new Gson().fromJson(sb.toString(), listType);

            System.out.println("prem"+Mood.moods_list_save.get(0).date);
            System.out.println(Mood.moods_list_save.get(1).date);
        }
        catch(Exception o)
        {

        }
    }

    public static void Save_file(Context context,String text_comment)
    {
        for(int i=0;i<MainActivity.Moods.length;i++)
        {
            if(MainActivity.Moods[i].isactive)
            {

                Mood.Mood_record mood_save  = new Mood.Mood_record ( date_today(),text_comment,i);

                for(int a=0;a<Mood.moods_list_save.size();a++)
                {
                    if(mood_save.date.equals(Mood.moods_list_save.get(a).date)  ){Mood.moods_list_save.remove(a);System.out.println("re");}
                }

                Mood.moods_list_save.add(mood_save);
            }

        }


        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<List<Mood.Mood_record>>() {}.getType();
        String json = gson.toJson(Mood.moods_list_save, type);


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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dat = dateFormat.format(date);
        return  dat;
    }
}
