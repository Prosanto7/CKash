package com.example.ckash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("namekey")&&sharedPreferences.contains("mobilenumberkey")&&sharedPreferences.contains("addresskey")&&sharedPreferences.contains("passwordkey"))
        {
            Intent intent = new Intent(MainActivity.this,signin.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(MainActivity.this,signup.class);
            startActivity(intent);
            finish();
        }

    }


}