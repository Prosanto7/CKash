package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class others extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private ImageButton facebook_button,youtube_button;
    private Button prothomalo_button,kalerkontho_button,jugantor_button,thedailystar_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_of_othhers);

        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationbar_of_ohters);
        navigationview.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        facebook_button = (ImageButton) findViewById(R.id.facebook_button);
        youtube_button = (ImageButton) findViewById(R.id.youtube_button);

        prothomalo_button = (Button) findViewById(R.id.prothom_alo_button);
        jugantor_button = (Button) findViewById(R.id.jugantor_button);
        kalerkontho_button = (Button) findViewById(R.id.kaler_kontho_button);
        thedailystar_button = (Button) findViewById(R.id.the_daily_star_button);

        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.facebook.com/");
                startActivity(intent);
            }
        });

        youtube_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.youtube.com/");
                startActivity(intent);
            }
        });

        prothomalo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.prothomalo.com/");
                startActivity(intent);
            }
        });

        kalerkontho_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.kalerkantho.com/");
                startActivity(intent);
            }
        });

        jugantor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.jugantor.com/");
                startActivity(intent);
            }
        });

        thedailystar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(others.this,webview.class);
                intent.putExtra("website","https://www.thedailystar.net/");
                startActivity(intent);
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
            Intent intent = new Intent(others.this,feedback.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(others.this,aboutus.class);
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
            Intent intent = new Intent(others.this,userprofile.class);
            startActivity(intent);
        }

        if(menuItem.getItemId()==R.id.home_navigationbar)
        {
            Intent intent = new Intent(others.this,home.class);
            startActivity(intent);
        }

        return false;
    }
}