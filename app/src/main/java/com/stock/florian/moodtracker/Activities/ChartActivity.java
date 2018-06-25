package com.stock.florian.moodtracker.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.stock.florian.moodtracker.FilesData.FileSaveGestion;
import com.stock.florian.moodtracker.R;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {


    private  ArrayList<Integer> colors = new ArrayList<>(); // This is my collection of colours to show the chart
    private  ArrayList<PieEntry> entries = new ArrayList<>(); // and my collection of pieEntry, who represent the different mood
    private  int counterMood[] = {0,0,0,0,0}; // But we need to count the numbers of  same mood reference to add a Pieentry
    private  PieChart pieChart; // Initialize the PieChart class

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        counterRestart(); //When the scene is created, we set the counters to zero

        pieChart = findViewById(R.id.piechart); // we find the layout in the main layout

        // I add the colors to the arrays
       int[] colorsBank =  getResources().getIntArray(R.array.colorMoods);
        for(int a=0;a<colorsBank.length;a++) { colors.add(colorsBank[a]); }


        //   we count the number of moods and add in the array counter

        for(int i = 0; i< FileSaveGestion.getInstance().getListDataSave().size(); i++)
        {

            if(FileSaveGestion.getInstance().getListDataSave().get(i).position == 0){counterMood[0]+=1;}
            if(FileSaveGestion.getInstance().getListDataSave().get(i).position == 1){counterMood[1]+=1;}
            if(FileSaveGestion.getInstance().getListDataSave().get(i).position == 2){counterMood[2]+=1;}
            if(FileSaveGestion.getInstance().getListDataSave().get(i).position == 3){counterMood[3]+=1;}
            if(FileSaveGestion.getInstance().getListDataSave().get(i).position == 4){counterMood[4]+=1;}
        }

        // addEntry is a simple function, who add a PieEntry in the entries array with parameters.

        for(int i=0; i<getResources().getStringArray(R.array.MoodsName).length;i++ )
        {
            addEntry(counterMood[i],getResources().getStringArray(R.array.MoodsName)[i],i);
        }

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

    private   void counterRestart()
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

