package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class income_cost extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private EditText amount_edittext,cause_edittext;
    private RadioButton income_radio_button,cost_radio_button;
    private Button submit_button;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_cost);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_of_income_cost);

        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationbar_of_income_cost);
        navigationview.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        amount_edittext = (EditText) findViewById(R.id.ammount_edittext);
        cause_edittext = (EditText) findViewById(R.id.cause_edittext);
        income_radio_button = (RadioButton) findViewById(R.id.income_radiobutton);
        cost_radio_button = (RadioButton) findViewById(R.id.cost_radiobutton);
        submit_button = (Button) findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String amount = amount_edittext.getText().toString();
                final String cause = cause_edittext.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy তারিখ hh:mm:ss a", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());

                if(!amount.equals("")&&!cause.equals(""))
                {
                    if(income_radio_button.isChecked()||cost_radio_button.isChecked())
                    {
                         if(income_radio_button.isChecked())
                         {
                             final String income_messase = "আপনি "+currentDateandTime+" এই সময়ে ("+cause+") এই কারণে "+amount+" টাকা আয় করেছেন";

                             AlertDialog.Builder alertdailogebuilder = new AlertDialog.Builder(income_cost.this);
                             alertdailogebuilder.setTitle("আবার দেখুন");
                             alertdailogebuilder.setMessage(income_messase);
                             alertdailogebuilder.setIcon(R.drawable.ic_baseline_add_alert_24);

                             alertdailogebuilder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                     SharedPreferences sharedPreferences = getSharedPreferences("transaction_database",Context.MODE_PRIVATE);
                                     SharedPreferences.Editor editor = sharedPreferences.edit();

                                     if(sharedPreferences.contains("lastid"))
                                     {
                                         int lastid = Integer.parseInt(sharedPreferences.getString("lastid","Data Not Found"));
                                         int lastBalance = Integer.parseInt(sharedPreferences.getString("lastBalance","Data Not Found"));
                                         int newid = lastid+1;
                                         int newBalace = lastBalance + Integer.parseInt(amount);
                                         editor.putString("lastBalance",Integer.toString(newBalace));
                                         editor.commit();
                                         editor.putString("lastid",Integer.toString(newid));
                                         editor.commit();
                                         editor.putString(Integer.toString(newid),income_messase);
                                         editor.commit();
                                     }
                                     else
                                     {
                                         editor.putString("lastBalance",amount);
                                         editor.commit();
                                         editor.putString("lastid","1");
                                         editor.commit();
                                         editor.putString("1",income_messase);
                                         editor.commit();
                                     }

                                     Toast.makeText(income_cost.this,"সাবমিশন সফল হয়েছে",Toast.LENGTH_SHORT).show();

                                         amount_edittext.setText("");
                                         cause_edittext.setText("");
                                         income_radio_button.setChecked(false);
                                         cost_radio_button.setChecked(false);

                                 }
                             });

                             alertdailogebuilder.setNegativeButton("না", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                 }
                             });

                             alertdailogebuilder.setNeutralButton("বাতিল করুন", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                     amount_edittext.setText("");
                                     cause_edittext.setText("");
                                     income_radio_button.setChecked(false);
                                     cost_radio_button.setChecked(false);

                                 }
                             });

                             AlertDialog alertDialog = alertdailogebuilder.create();
                             alertDialog.show();


                         }
                         else
                         {
                             final String cost_messase = "আপনি "+currentDateandTime+" এই সময়ে ("+cause+") এই কারণে "+amount+" টাকা ব্যয় করেছেন";

                             AlertDialog.Builder alertdailogebuilder = new AlertDialog.Builder(income_cost.this);
                             alertdailogebuilder.setTitle("আবার দেখুন");
                             alertdailogebuilder.setMessage(cost_messase);
                             alertdailogebuilder.setIcon(R.drawable.ic_baseline_add_alert_24);

                             alertdailogebuilder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                     SharedPreferences sharedPreferences = getSharedPreferences("transaction_database",Context.MODE_PRIVATE);
                                     SharedPreferences.Editor editor = sharedPreferences.edit();

                                     if(sharedPreferences.contains("lastid"))
                                     {
                                        int lastid = Integer.parseInt(sharedPreferences.getString("lastid","Data Not Found"));
                                        int lastBalance = Integer.parseInt(sharedPreferences.getString("lastBalance","Data Not Found"));
                                        int newid = lastid+1;
                                        int newBalace = lastBalance - Integer.parseInt(amount);
                                        editor.putString("lastBalance",Integer.toString(newBalace));
                                        editor.commit();
                                        editor.putString("lastid",Integer.toString(newid));
                                        editor.commit();
                                        editor.putString(""+newid,cost_messase);
                                        editor.commit();
                                     }
                                     else
                                     {
                                         int newBalance = 0 - Integer.parseInt(amount);
                                         editor.putString("lastBalance",Integer.toString(newBalance));
                                         editor.commit();
                                         editor.putString("lastid","1");
                                         editor.commit();
                                         editor.putString("1",cost_messase);
                                         editor.commit();
                                     }




                                     Toast.makeText(income_cost.this,"সাবমিশন সফল হয়েছে",Toast.LENGTH_SHORT).show();

                                     amount_edittext.setText("");
                                     cause_edittext.setText("");
                                     income_radio_button.setChecked(false);
                                     cost_radio_button.setChecked(false);

                                 }
                             });

                             alertdailogebuilder.setNegativeButton("না", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                 }
                             });

                             alertdailogebuilder.setNeutralButton("বাতিল করুন", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {

                                     amount_edittext.setText("");
                                     cause_edittext.setText("");
                                     income_radio_button.setChecked(false);
                                     cost_radio_button.setChecked(false);

                                 }
                             });

                             AlertDialog alertDialog = alertdailogebuilder.create();
                             alertDialog.show();

                         }
                    }
                    else
                    {
                        Toast.makeText(income_cost.this,"দয়া করে আয়-ব্যয় নির্ণয় করুন",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(income_cost.this,"দয়া করে সব তথ্য দিন",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.share_menu)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "আমার ক্যাশ অ্যাপ";
            String body = "আপনার দৈনন্দিন জীবনের সকল হিসাব-নিকাশ, আয়-ব্যায় সবকিছুর সমাধান পাবেন এই অ্যাপে";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(intent.createChooser(intent,"Share With"));

            return true;
        }


        if(item.getItemId()==R.id.feedback_menu)
        {
            Intent intent = new Intent(income_cost.this,feedback.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(income_cost.this,aboutus.class);
            startActivity(intent);
            return true;
        }

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.share_navigationbar)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "আমার ক্যাশ অ্যাপ";
            String body = "আপনার দৈনন্দিন জীবনের সকল হিসাব-নিকাশ, আয়-ব্যায় সবকিছুর সমাধান পাবেন এই অ্যাপে";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(intent.createChooser(intent,"Share With"));
        }


        if(menuItem.getItemId()==R.id.profile_navigationbar)
        {
            Intent intent = new Intent(income_cost.this,userprofile.class);
            startActivity(intent);
        }

        if(menuItem.getItemId()==R.id.home_navigationbar)
        {
            Intent intent = new Intent(income_cost.this,home.class);
            startActivity(intent);
        }

        return false;
    }
}