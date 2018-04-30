package com.stock.florian.moodtracker.Model;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.stock.florian.moodtracker.Controler.MoodType;
import com.stock.florian.moodtracker.Controler.Mood_record;
import com.stock.florian.moodtracker.R;
import java.util.ArrayList;


/**
 * Created by florian stock on 25/03/2018.
 *
 * This class  is extends of AppCompatActivity and contains the layout  " PieCHart " . PieChart is an class of the library "MpAndroidChart" for create different charts easily.
 *
 */

public class ChartActivity extends AppCompatActivity
{

    private static ArrayList<Integer> colors = new ArrayList<>(); // This is my collection of colours to show the chart
    public static ArrayList<PieEntry> entries = new ArrayList<>(); // and my collection of pieEntry, who represent the different mood
    public  int counterMood[] = {0,0,0,0,0}; // But we need to count the numbers of  same mood reference to add a Pieentry
    private static PieChart pieChart; // Initialize the PieChart class


    public ChartActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        counterRestart(); //When the scene is created, we set the counters to zero

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity); // set the view of file xml
        pieChart = findViewById(R.id.piechart); // we find the layout in the main layout

        // I add the colors to the arrays
        colors.add(ContextCompat.getColor(this, MoodType.moods.get(0).color));
        colors.add(ContextCompat.getColor(this, MoodType.moods.get(1).color));
        colors.add(ContextCompat.getColor(this, MoodType.moods.get(2).color));
        colors.add(ContextCompat.getColor(this, MoodType.moods.get(3).color));
        colors.add(ContextCompat.getColor(this, MoodType.moods.get(4).color));


        //   we count the number of moods and add in the array counter

        for(int i=0;i<Mood_record.moods_list_save.size();i++)
        {
            if(Mood_record.moods_list_save.get(i).mood == 0){counterMood[0]+=1;}
            if(Mood_record.moods_list_save.get(i).mood == 1){counterMood[1]+=1;}
            if(Mood_record.moods_list_save.get(i).mood == 2){counterMood[2]+=1;}
            if(Mood_record.moods_list_save.get(i).mood == 3){counterMood[3]+=1;}
            if(Mood_record.moods_list_save.get(i).mood == 4){counterMood[4]+=1;}
        }

        // addEntry is a simple function, who add a PieEntry in the entries array with parameters.

        addEntry(counterMood[0],MoodType.moods.get(0).description,0);
        addEntry(counterMood[1],MoodType.moods.get(1).description,1);
        addEntry(counterMood[2],MoodType.moods.get(2).description,2);
        addEntry(counterMood[3],MoodType.moods.get(3).description,3);
        addEntry(counterMood[4],MoodType.moods.get(4).description,4);

        //To add  entries in the chart, we need to create a PieDataSet class, and for this class, we send the array of entries
        //and the array of colors

        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setValueTextSize(10f);
        dataset.setValueTextSize(12f);
        dataset.setColors(colors);

        // Now we can set the data to the object PieData
        PieData data = new PieData(dataset);
        // data.setValueFormatter(new PercentFormatter()); To show pourcent values

        //We add a legend with parameters of placement and size

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(13);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);  l.setYOffset(-80f);


        //We can set parameters of the layout, show or hide the labels, ect ect...

        pieChart.setExtraOffsets(5, 50, 5, 5);
        pieChart.setEntryLabelColor(Color.BLACK); // The color of the text
        pieChart.setEntryLabelTextSize(20f);
        pieChart.setDrawEntryLabels(false); // Hide the text of label in the piechart
        pieChart.setDrawHoleEnabled(true); // Show the circle in center;
        pieChart.setHoleRadius(20); // The cercle in the center
        pieChart.getDescription().setEnabled(false);
        pieChart.setTransparentCircleRadius(10);

        // Now we set the object data  PieData to the class PieChart and show the result
        pieChart.setData(data);

    }

    public  void counterRestart()
    {
        for(int c=0;c<counterMood.length;c++) // set all counters to 0
        {
            counterMood[c]=0;
        }

    }

    private void addEntry(int counter,String label,int index)
    {
        if(counter>0) // It's not necessary to show the moods type who are empty
        {
            entries.add(new PieEntry(counter, label, index));

        }
    }







}


