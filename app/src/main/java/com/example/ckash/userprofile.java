package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class userprofile extends AppCompatActivity {

    private TextView userprofile_name,userprofile_mobile_number,userprofile_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        userprofile_name = (TextView) findViewById(R.id.userprofile_name);
        userprofile_mobile_number = (TextView) findViewById(R.id.userprofile_mobile_number);
        userprofile_address = (TextView) findViewById(R.id.userprofile_address);

        SharedPreferences sharedPreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("namekey")&&sharedPreferences.contains("mobilenumberkey")&&sharedPreferences.contains("addresskey")&&sharedPreferences.contains("passwordkey"))
        {
            String name = sharedPreferences.getString("namekey","Data Not Found");
            String mobilenumber = sharedPreferences.getString("mobilenumberkey","Data Not Found");
            String address = sharedPreferences.getString("addresskey","Data Not Found");

            userprofile_name.setText("আপনার নাম "+name);
            userprofile_mobile_number.setText("আপনার মোবাইল নাম্বার "+mobilenumber);
            userprofile_address.setText("আপনার ঠিকানা "+address);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }

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
            Intent intent = new Intent(userprofile.this,feedback.class);
            startActivity(intent);
            finish();
            return true;
        }
        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(userprofile.this,aboutus.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}