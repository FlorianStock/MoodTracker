package com.stock.florian.moodtracker.FilesData;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stock.florian.moodtracker.Models.MoodObjectSave;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FileSaveGestion  {

    private  SharedPreferences sharedPreferences;
    private static  List<MoodObjectSave> dataSave = new ArrayList<>();
    private static final String DATA_NAME_FILE = "Moods";
    private static FileSaveGestion INSTANCE = null;

    public static FileSaveGestion getInstance()
    {
        if (INSTANCE == null)
        {

            INSTANCE = new FileSaveGestion();

        }
        return INSTANCE;
    }

    public FileSaveGestion()
    {
        //sharedPreferences = getInstance().getSharedPreferences(DATA_NAME_FILE, MODE_PRIVATE);
    }




    public void save( String text_comment,int position,Context context)
    {


        if(dataSave==null){dataSave = new ArrayList<>();}

        MoodObjectSave dataToSave = new MoodObjectSave(new DateGestion().getDateToday(), text_comment, position);

       // for (int a = 0; a < dataSave.size(); a++) { System.out.print(dataSave.get(a).position); }


        for (int i = 0; i < dataSave.size(); i++)
        {
            if (dataToSave.date.equalsIgnoreCase(dataSave.get(i).date))
            {
                dataSave.remove(i);

            }
        }


        dataSave.add(dataToSave);

        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<List<MoodObjectSave>>() {}.getType();
        String json = gson.toJson(dataSave, type);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString("MoodObjects",json).apply();
        sharedPreferences.edit().commit();


    }



    public void load(Context context)
    {
        Type listType = new TypeToken<ArrayList<MoodObjectSave>>() {}.getType();


        dataSave = new ArrayList<>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String strJson = sharedPreferences.getString("MoodObjects","");

        System.out.println(strJson);

        if(strJson!=null)
        {

            dataSave = new Gson().fromJson(strJson, listType);

            //for (int a = 0; a < dataSave.size(); a++) { System.out.println(dataSave.get(a).position); }
        }





        //System.out.println(strJson);
        //System.out.println(dataSave.get(0).date);
       // for (int a = 0; a < dataSave.size(); a++) { System.out.println(dataSave.get(a).date); }
    }





    public List<MoodObjectSave> getListDataSave()
    {
        return dataSave;
    }
}
