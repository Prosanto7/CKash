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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.navigation.NavigationView;

public class webview extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_of_webview);

        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationbar_of_webview);
        navigationview.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle bundle = getIntent().getExtras();

        String website;

        if(bundle!=null)
        {
           website = bundle.getString("website");

           webView = (WebView) findViewById(R.id.webview);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient());

           webView.loadUrl(website);
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
            Intent intent = new Intent(webview.this,feedback.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(webview.this,aboutus.class);
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
            Intent intent = new Intent(webview.this,userprofile.class);
            startActivity(intent);
        }

        if(menuItem.getItemId()==R.id.home_navigationbar)
        {
            Intent intent = new Intent(webview.this,home.class);
            startActivity(intent);
        }

        return false;
    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}