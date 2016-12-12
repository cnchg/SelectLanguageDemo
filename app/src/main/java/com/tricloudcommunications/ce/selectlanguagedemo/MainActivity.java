package com.tricloudcommunications.ce.selectlanguagedemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView mainTV;
    ImageView mainIV;
    ImageView clearPrefIV;
    String selectedLanguage;

    public void clearSharedPreferences(View view){

        sharedPreferences.edit().remove("userLanguage").apply();

        Log.i("Preferences Status", "Cleared");

    }

    public void languageAlert() {

        mainIV.setVisibility(View.INVISIBLE);

         if (selectedLanguage.equals("")){

             new AlertDialog.Builder(this)
                 .setIcon(android.R.drawable.ic_dialog_alert)
                 .setTitle("Select Language")
                 .setMessage("What language would you like?")
                 .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {

                        sharedPreferences.edit().putString("userLanguage", "English").apply();
                        mainTV.setText(selectedLanguage);
                        Log.i("Language selected: ", "English");

                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {

                        sharedPreferences.edit().putString("userLanguage", "Spanish").apply();
                        mainTV.setText(selectedLanguage);
                        Log.i("Language selected: ", "Spanish");

                    }
                })
                .show();

             Log.i("preferenceStatus", "EMPTY-AND WAS-ADDED");

         }else{

             mainTV.setText(selectedLanguage);
             Log.i("preferenceStatus", "WAS-NOT-EMPTY");
         }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainTV = (TextView) findViewById(R.id.languageTextView);
        mainIV = (ImageView) findViewById(R.id.imageView);
        clearPrefIV = (ImageView) findViewById(R.id.imageView2);

        sharedPreferences = this.getSharedPreferences("com.tricloudcommunications.ce.selectlanguagedemo", Context.MODE_PRIVATE);
        selectedLanguage = sharedPreferences.getString("userLanguage", "");
        mainIV.setVisibility(View.VISIBLE);
        //sharedPreferences.edit().remove("userLanguage").apply();

        languageAlert();

        if (!selectedLanguage.equals("")){

            clearPrefIV.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.language_settings) {

            if (selectedLanguage.equals("English")){
                //if the language is English we are going to switch it to Spanish
                sharedPreferences.edit().putString("userLanguage", "Spanish").apply();

                //Then update the mainTV
                mainTV.setText(selectedLanguage);

                Log.i("Switch Status", "switch to Spanish");


            }else if (selectedLanguage.equals("Spanish")){
                //if the language is Spanish we are going to switch it to English
                sharedPreferences.edit().putString("userLanguage", "English").apply();

                //Then update the mainTV
                mainTV.setText(selectedLanguage);

                Log.i("Switch Status", "switch to English");

            }else{

                //Then update the mainTV
                //mainTV.setText("Please Select a Language");

                languageAlert();
                Log.i("Switch Status", "No Switch was made it");

            }

            Log.i("Language setting", "Lanuage Changed Requested");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
