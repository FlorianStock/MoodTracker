package com.stock.florian.moodtracker.Activities;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stock.florian.moodtracker.FilesData.DateGestion;
import com.stock.florian.moodtracker.FilesData.FileSaveGestion;
import com.stock.florian.moodtracker.MoodsViewPager.MoodPageAdapter;
import com.stock.florian.moodtracker.MoodsViewPager.VerticalViewPager;
import com.stock.florian.moodtracker.R;

public class MainActivity extends AppCompatActivity
{




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FileSaveGestion.getInstance().load(this);





        this.setConfigViewPager(this.getApplicationContext());
    }




    private void setConfigViewPager(final Context c)
    {

        VerticalViewPager mViewPager = findViewById(R.id.activity_main_viewpager);

        mViewPager.setAdapter(new MoodPageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorMoods)) {});


        //set default mood
        
        if(FileSaveGestion.getInstance().getListDataSave()==null)
        {
            mViewPager.setCurrentItem(3);
        }
        else
        {
            //Get the last mood recorded by research with date

            for(int i=0; i<FileSaveGestion.getInstance().getListDataSave().size();i++)
            {
                if(FileSaveGestion.getInstance().getListDataSave().get(i).date.equalsIgnoreCase(new DateGestion().getDateToday()))
                {
                    mViewPager.setCurrentItem(FileSaveGestion.getInstance().getListDataSave().get(i).position);
                }
            }
        }
       // mViewPager.set
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                   FileSaveGestion.getInstance().save("", position, c);
            }

            @Override
            public void onPageSelected(int position)
            {
                    FileSaveGestion.getInstance().save("", position, c);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

}
