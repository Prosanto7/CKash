package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private CardView balack_check_cardview,income_cost_cardview,previous_transaction_check_cardview,others_cardview;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        balack_check_cardview = (CardView) findViewById(R.id.balack_check_cardview);
        income_cost_cardview = (CardView) findViewById(R.id.income_cost_cardview);
        previous_transaction_check_cardview = (CardView) findViewById(R.id.previous_transaction_check_cardview);
        others_cardview = (CardView) findViewById(R.id.others_cardview);

        balack_check_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Balace Card View",Toast.LENGTH_SHORT).show();

            }
        });
        income_cost_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Income Card View",Toast.LENGTH_SHORT).show();

            }
        });
        previous_transaction_check_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Pervious Card View",Toast.LENGTH_SHORT).show();

            }
        });
        others_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Others Card View",Toast.LENGTH_SHORT).show();

            }
        });



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationbar);
        navigationview.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
            Intent intent = new Intent(home.this,feedback.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(home.this,aboutus.class);
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
            Intent intent = new Intent(home.this,userprofile.class);
            startActivity(intent);
        }

        return false;
    }


}