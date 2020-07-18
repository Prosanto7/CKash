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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {

    private EditText name_edittext,password_edittext;
    private Button signin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        name_edittext = (EditText) findViewById(R.id.name_edittext_signin);
        password_edittext = (EditText) findViewById(R.id.password_edittext_signin);
        signin_button = (Button) findViewById(R.id.signin_button);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_edittext.getText().toString();
                String password = password_edittext.getText().toString();

                if(name.equals("")||password.equals(""))
                {
                    Toast.makeText(signin.this,"দয়া করে সব তথ্য দিন",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

                    if(sharedPreferences.contains("namekey")&&sharedPreferences.contains("passwordkey"))
                    {
                        String stored_name = sharedPreferences.getString("namekey","Data Not Found");
                        String stored_password = sharedPreferences.getString("passwordkey","Data Not Found");

                        if(stored_name.equals(name)&&stored_password.equals(password))
                        {
                            Intent intent = new Intent(signin.this,home.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(signin.this,"আপানার দেয়া তথ্য সঠিক নয়",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });


    }

    @Override
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
            Intent intent = new Intent(signin.this,feedback.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutus_menu)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}