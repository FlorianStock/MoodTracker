package com.stock.florian.moodtracker;

import com.stock.florian.moodtracker.history.HistoryActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;




/**
 * Created by florian on 17/03/2018.
 */

public class Gestion_buttons implements ImageButton.OnClickListener
{

    Context current_context;
    private  EditText inputEditText;



    public Gestion_buttons(Context view)
        {

            current_context = view;
        }

    @Override
    public void onClick(View view)
    {
        if(view.getTag()== "comment") {dialog_comment();}

        if(view.getTag()== "history")
        {
            Intent intent = new Intent(current_context, HistoryActivity.class);
            current_context.startActivity(intent);
        }



    }

    private void dialog_comment()
    {

        AlertDialog.Builder dialog = new AlertDialog.Builder(current_context);
        dialog.setTitle("Commentaire");

        inputEditText = new EditText(current_context);

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

                File_json_gestion.Save_file(current_context,inputEditText.getText().toString());








            }
        });

        dialog.show();
    }



}



