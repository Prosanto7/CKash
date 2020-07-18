package com.example.ckash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}