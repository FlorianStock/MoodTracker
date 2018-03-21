package com.stock.florian.moodtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Movie;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by florian on 17/03/2018.
 */

public class Gestion_buttons implements ImageButton.OnClickListener
{

    Context current_context;

    String json;
    Gson gson;
    File dir, saveLocation;
    FileWriter writer;

    public Gestion_buttons(Context view)
        {

            current_context = view;
        }

    @Override
    public void onClick(View view)
    {
        if(view.getTag()== "comment") {dialog_comment();}
        if(view.getTag()== "history") {Intent intent = new Intent(current_context, HistoryActivity.class);
            current_context.startActivity(intent);}



    }

    private void dialog_comment()
    {

        AlertDialog.Builder dialog = new AlertDialog.Builder(current_context);
        dialog.setTitle("Commentaire");

        final EditText input = new EditText(current_context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        dialog.setView(input);

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

                File_json_gestion.Save_file(current_context,input.getText().toString());








            }
        });

        dialog.show();
    }



}



