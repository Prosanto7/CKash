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

public class signup extends AppCompatActivity {

    private EditText name_edittext,mobile_number_edittext,address_edittext,password_edittext;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name_edittext = (EditText) findViewById(R.id.name_edittext_signup);
        mobile_number_edittext = (EditText) findViewById(R.id.mobilenumber_edittext_signup);
        address_edittext = (EditText) findViewById(R.id.address_edittext_signup);
        password_edittext = (EditText) findViewById(R.id.password_edittext_signup);
        signup_button = (Button) findViewById(R.id.signup_button);



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_edittext.getText().toString();
                String mobilenumber = mobile_number_edittext.getText().toString();
                String address = address_edittext.getText().toString();
                String password = password_edittext.getText().toString();

                if(name.equals("")||mobilenumber.equals("")||address.equals("")||password.equals(""))
                {
                    Toast.makeText(signup.this,"দয়া করে সব তথ্য দিন",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(mobilenumber.length()!=11)
                    {
                        mobile_number_edittext.setText("");
                        Toast.makeText(signup.this,"আপনার মোবাইল নম্বরটি সঠিক নয়",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("namekey", name);
                        editor.putString("mobilenumberkey",mobilenumber);
                        editor.putString("addresskey",address);
                        editor.putString("passwordkey", password);
                        editor.commit();
                        Toast.makeText(signup.this,"সাইন আপ সফল হয়েছে",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(signup.this,home.class);
                        startActivity(intent);
                        finish();
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
            Intent intent = new Intent(signup.this,feedback.class);
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