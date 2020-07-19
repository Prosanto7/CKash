package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {

    private TextView feedback_name_edittext,feedback_messege_edittext;
    private Button feedback_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        feedback_name_edittext = (EditText) findViewById(R.id.feedback_name_edittext);
        feedback_messege_edittext = (EditText) findViewById(R.id.feedback_message_edittext);
        feedback_button = (Button) findViewById(R.id.feedback_button);

        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {

                    String feedback_name = feedback_name_edittext.getText().toString();
                    String feedback_message = feedback_messege_edittext.getText().toString();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/email");

                    intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"prosantodeb7@gmail.com"});

                    intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback From APP");
                    intent.putExtra(Intent.EXTRA_TEXT,"Name : "+feedback_name+"\n Message : "+feedback_message);

                    startActivity(Intent.createChooser(intent,"Feedback With"));

                }catch (Exception e){

                    Toast.makeText(feedback.this,"দয়া করে সম্পূর্ণ তথ্য দিন",Toast.LENGTH_SHORT).show();
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

        if(item.getItemId()==R.id.aboutus_menu)
        {
            Intent intent = new Intent(feedback.this,aboutus.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}