package com.stock.florian.moodtracker.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.stock.florian.moodtracker.FilesData.FileSaveGestion;
import com.stock.florian.moodtracker.Models.MoodObjectSave;
import com.stock.florian.moodtracker.R;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class HistoryActivity extends AppCompatActivity
{

    private final int NUMBER_MOODS_TO_SCREEN = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        FileSaveGestion fileSaveGestion = FileSaveGestion.getInstance();
        fileSaveGestion.load(this);

        change_order_array(fileSaveGestion.getListDataSave());
        loadLayouts(fileSaveGestion.getListDataSave());



    }

    private void change_order_array(List<MoodObjectSave> array)
    {
        Collections.sort(array, new Comparator<MoodObjectSave>()
        {
            public int compare(MoodObjectSave s1, MoodObjectSave s2)
            {
                Date results1 = new Date();
                Date results2 = new Date();

                SimpleDateFormat dateFormats1 = new SimpleDateFormat("dd - MM - yyyy");
                SimpleDateFormat dateFormats2 = new SimpleDateFormat("dd - MM - yyyy");

                try {
                    results1 = dateFormats1.parse(s1.date);
                    results2 = dateFormats2.parse(s2.date);


                } catch (Exception e)
                {
                    //Log.e("HistoryActivity  ","Parse Error");
                }

                return results2.compareTo(results1);

            }
        });
    }

    private void loadLayouts(final List<MoodObjectSave> arrayMoodsCounts)
    {

        LayoutInflater layoutInflator = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout insertPoint = (LinearLayout) findViewById(R.id.linearPoint);

        DisplayMetrics ecran = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ecran);


        double[] factor = {0.3,0.4,0.6,0.8,1};

        
        for(int i=0;i<arrayMoodsCounts.size();i++)
        {

            View view = layoutInflator.inflate(R.layout.mood_bar, null);
            insertPoint.addView(view);


            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = ecran.heightPixels /NUMBER_MOODS_TO_SCREEN;
            double width = factor[arrayMoodsCounts.get(i).position]*ecran.widthPixels;
            params.width = (int) width;

            TextView dateText = view.findViewById(R.id.textDate);
            dateText.setText(arrayMoodsCounts.get(i).date);

            int[] color = getResources().getIntArray(R.array.colorMoods);
            view.setBackgroundColor(color[arrayMoodsCounts.get(i).position]);


            ImageButton imageButton = view.findViewById(R.id.imageButtonComment);
            ViewGroup.LayoutParams buttonParam = imageButton.getLayoutParams();


            if(! arrayMoodsCounts.get(i).comment.equalsIgnoreCase(""))
            {
                imageButton.setVisibility(View.VISIBLE);imageButton.setClickable(false);

                final String comment = arrayMoodsCounts.get(i).comment;

                imageButton.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View arg0)
                    {

                        Toast.makeText(HistoryActivity.this, comment, Toast.LENGTH_SHORT).show();

                    }

                });

            }






        }
        insertPoint.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return true;
    }


    // Function when the user select a item in action bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_chart:
               // ChartActivity.entries.clear();
                Intent intent = new Intent(this, ChartActivity.class);
                this.startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
