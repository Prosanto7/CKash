package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        /*
        SharedPreferences sharedPreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("namekey")&&sharedPreferences.contains("mobilenumberkey")&&sharedPreferences.contains("addresskey")&&sharedPreferences.contains("passwordkey"))
        {
            String name = sharedPreferences.getString("namekey","Data Not Found");
            String mobilenumber = sharedPreferences.getString("mobilenumberkey","Data Not Found");
            String adrress = sharedPreferences.getString("addresskey","Data Not Found");
            String password = sharedPreferences.getString("passwordkey","Data Not Found");
        }*/


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}