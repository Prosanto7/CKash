package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class previous_transaction extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private ListView previous_transaction_listview;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_transaction);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_of_presvious_transaction);

        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationbar_of_previous_transaction);
        navigationview.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {

            SharedPreferences sharedPreferences = getSharedPreferences("transaction_database", Context.MODE_PRIVATE);

            int lastid = Integer.parseInt(sharedPreferences.getString("lastid", "Data Not Found"));

            String[] total_result_array = new String[lastid];
            String[] total_result_array_reverse = new String[lastid];

            if (sharedPreferences.contains("lastid")) {

                for (int i = 0; i < lastid; i++) {
                    String result = sharedPreferences.getString(Integer.toString(i + 1), "Data " + i + 1 + " Not Found") + "\n";
                    total_result_array[i] = result;
                }

            }

            int i = 0;
            int j = lastid - 1;
            while(i<lastid)
            {
                total_result_array_reverse[j] = total_result_array[i];
                i++;
                j--;
            }


            previous_transaction_listview = (ListView) findViewById(R.id.previous_transaction_listview);
            searchView = (SearchView) findViewById(R.id.searchview);

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(previous_transaction.this, R.layout.sampleview, R.id.sampleview_textview, total_result_array_reverse);
            previous_transaction_listview.setAdapter(adapter);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });

        }catch (Exception e)
        {
            Toast.makeText(previous_transaction.this," আপনি কোনো লেনদেন করেন নি ",Toast.LENGTH_SHORT).show();
        }



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
            Intent intent = new Intent(previous_transaction.this,feedback.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(previous_transaction.this,aboutus.class);
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
            Intent intent = new Intent(previous_transaction.this,userprofile.class);
            startActivity(intent);
        }

        if(menuItem.getItemId()==R.id.home_navigationbar)
        {
            Intent intent = new Intent(previous_transaction.this,home.class);
            startActivity(intent);
        }

        return false;
    }
}