package com.stock.florian.moodtracker.MoodsViewPager;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.stock.florian.moodtracker.FilesData.FileSaveGestion;
import com.stock.florian.moodtracker.Activities.HistoryActivity;
import com.stock.florian.moodtracker.R;
import java.io.Serializable;


/**
 * Created by flori on 09/05/2018.
 */

public class MoodFragment extends Fragment implements  ImageButton.OnClickListener,Serializable
{
    private int position;
    public static EditText inputEditText;
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";
    private  final int[] mSmileyPath = {R.drawable.smiley_sad,R.drawable.smiley_disappointed,R.drawable.smiley_normal,R.drawable.smiley_happy,R.drawable.smiley_super_happy};

    private final FileSaveGestion fileSaveGestion = new FileSaveGestion();

    //public MoodFragment() { }

    public static MoodFragment newInstance(int position, int color)
    {

        //Create new fragment
        MoodFragment frag = new MoodFragment();

        //Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inputEditText = new EditText(this.getContext());

        position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        View result = inflater.inflate(R.layout.fragment_mood, container, false);

        RelativeLayout rootView= (RelativeLayout) result.findViewById(R.id.root);
        ImageView smileyImage = (ImageView)rootView.findViewById(R.id.imageView_smiley);
        ImageButton buttonAddComment = (ImageButton)rootView.findViewById(R.id.imageButton_comment);
        ImageButton buttonShowHistory = (ImageButton)rootView.findViewById(R.id.imageButton2_history);

        smileyImage.setImageResource(mSmileyPath[position]);

        buttonAddComment.setOnClickListener(this);
        buttonShowHistory.setOnClickListener(this);

        rootView.setBackgroundColor(color);




        return result;
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.imageButton_comment:

                dialog_comment(this.getContext());
                break;

            case R.id.imageButton2_history:


                Intent intent = new Intent(this.getContext(), HistoryActivity.class);


                //fileSaveGestion.save(inputEditText.getText().toString(),getArguments().getInt(KEY_POSITION, -1),this.getContext());
                //fileSaveGestion.load(this.getContext());
                this.getContext().startActivity(intent);

                break;
        }
    }



    private void dialog_comment(Context c)
    {

         final Context context = c;
        // inputEditText = new EditText(this.getContext());


        AlertDialog.Builder dialog = new AlertDialog.Builder(this.getContext());
        dialog.setTitle("Commentaire");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        inputEditText.setLayoutParams(lp);
        dialog.setView(inputEditText);

        dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }
        });

        dialog.setPositiveButton("Valider", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialoginterface, int ii)
            {


                fileSaveGestion.save(inputEditText.getText().toString(),getArguments().getInt(KEY_POSITION, -1), context);


            }
        });

        dialog.show();
    }

}
