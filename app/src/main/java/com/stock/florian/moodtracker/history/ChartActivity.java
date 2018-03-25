package com.stock.florian.moodtracker.history;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.stock.florian.moodtracker.MainActivity;
import com.stock.florian.moodtracker.Mood;
import com.stock.florian.moodtracker.R;

import java.util.ArrayList;


/**
 * Created by flori on 25/03/2018.
 */

public class ChartActivity extends AppCompatActivity
{

    private static ArrayList<Integer> colors = new ArrayList<Integer>();
    public static ArrayList<PieEntry> entries = new ArrayList<>();
    public  int counterMood[] = {0,0,0,0,0};
    private static PieChart pieChart;


    public ChartActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        counterRestart();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        pieChart = (PieChart)findViewById(R.id.piechart);

        colors.add(ContextCompat.getColor(this, R.color.faded_red));
        colors.add(ContextCompat.getColor(this, R.color.warm_grey));
        colors.add(ContextCompat.getColor(this, R.color.cornflower_blue_65));
        colors.add(ContextCompat.getColor(this, R.color.light_sage));
        colors.add(ContextCompat.getColor(this, R.color.banana_yellow));



        for(int i=0;i<Mood.moods_list_save.size();i++)
        {
            if(Mood.moods_list_save.get(i).mood == 0){counterMood[0]+=1;}
            if(Mood.moods_list_save.get(i).mood == 1){counterMood[1]+=1;}
            if(Mood.moods_list_save.get(i).mood == 2){counterMood[2]+=1;}
            if(Mood.moods_list_save.get(i).mood == 3){counterMood[3]+=1;}
            if(Mood.moods_list_save.get(i).mood == 4){counterMood[4]+=1;}
        }


        addEntry(counterMood[0],"Très mauvaise humeur",0);
        addEntry(counterMood[1],"Mauvaise humeur",1);
        addEntry(counterMood[2],"Humeur normale",2);
        addEntry(counterMood[3],"Bonne humeur",3);
        addEntry(counterMood[4],"Super bonne humeur",4);


        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setValueTextSize(10f);
        dataset.setValueTextSize(12f);
        dataset.setColors(colors);


        PieData data = new PieData(dataset);//getting error here
        data.setValueFormatter(new PercentFormatter());


        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(13);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);  l.setYOffset(-80f);


        //pieChart.setPadding(0,-50,0,0);
        pieChart.setExtraOffsets(5, 50, 5, 5);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(20f);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(20);
        pieChart.getDescription().setEnabled(false);
        pieChart.setTransparentCircleRadius(10);
        pieChart.setData(data);pieChart.invalidate();

    }

    public  void counterRestart()
    {
        counterMood[0]=0;counterMood[1]=0;counterMood[2]=0;counterMood[3]=0;counterMood[4]=0;
    }

    private void addEntry(int counter,String label,int index)
    {
        if(counter>0)
        {
            entries.add(new PieEntry(counter, label, index));

        }
    }







}


