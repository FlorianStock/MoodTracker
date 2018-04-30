package com.stock.florian.moodtracker.Controler;

import com.stock.florian.moodtracker.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by flori on 17/04/2018.
 */

public class MoodType
{

    public String description;
    public int color;
    public int smiley_index;
    public double factor;

    public static List<MoodType> moods  = new ArrayList<>();

    public MoodType(int color, int drawble, String description,Double factor)
    {
        this.color=color;
        this.description=description;
        this.smiley_index=drawble;
        this.factor=factor;
    }

    public static void init()
    {
        moods.add(new MoodType(R.color.faded_red,R.drawable.smiley_sad,"Très mauvaise humeur",0.2));
        moods.add(new MoodType(R.color.warm_grey,R.drawable.smiley_disappointed,"Mauvaise humeur",0.4));
        moods.add(new MoodType(R.color.cornflower_blue_65,R.drawable.smiley_normal,"Humeur normale",0.6));
        moods.add(new MoodType(R.color.light_sage,R.drawable.smiley_happy,"Bonne humeur",0.8));
        moods.add(new MoodType(R.color.banana_yellow,R.drawable.smiley_super_happy,"Super bonne humeur",1.0));

    }


}
