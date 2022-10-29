package com.example.vehiclerentalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar rating1;
    RatingBar rating2;
    EditText editTxtFB;
    Button btnFB;
    //    boolean valid = true;
    FeedbackDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratings);

        rating1 = (RatingBar) findViewById(R.id.ratingBar1);
        rating2 = (RatingBar) findViewById(R.id.ratingBar2);
        editTxtFB = (EditText) findViewById(R.id.editTextFeedback);
        btnFB = (Button) findViewById(R.id.btnFeedback);
        DB = new FeedbackDB(this);
        addFeedback();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("feedback") != null){

                Toast.makeText(getApplicationContext() , "data :" + bundle.getString("feedback") , Toast.LENGTH_LONG).show();

            }
        }

    }

    public void addFeedback() {
        btnFB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted = DB.insertFeedback(String.valueOf(rating2.getRating()) ,String.valueOf(rating2.getRating()) , editTxtFB.getText().toString());

                        if (isInserted == true){
                            Toast.makeText(FeedbackActivity.this, "Thank you for your feedback :)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(FeedbackActivity.this, "Please give a feedback :)", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}


