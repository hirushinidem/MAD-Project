package com.example.vehiclerentalapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class activity_card extends AppCompatActivity {

    cardDB myDB ;
    EditText cardNumber , expDate , cvc , cid ;
    Button saveCardBtn ;
    Button viewCardsBtn ;
    Button editCardBtn ;
    Button removeCardBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        myDB = new cardDB(this);

        cid = (EditText) findViewById(R.id.cardID) ;
        expDate = (EditText) findViewById(R.id.expireDate);
        cvc = (EditText) findViewById(R.id.Cvc) ;
        cardNumber = (EditText) findViewById(R.id.CardNumber) ;
        saveCardBtn = (Button) findViewById(R.id.saveCardBtn) ;
        viewCardsBtn = (Button) findViewById(R.id.viewCardsBtn) ;
        editCardBtn = (Button)  findViewById(R.id.editCardBtn);
        removeCardBtn = (Button) findViewById(R.id.removeCardBtn) ;
        addCard();
        viewCard();
        updatecard();
        deletecard();



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("some") != null){

                Toast.makeText(getApplicationContext() , "data :" + bundle.getString("some") , Toast.LENGTH_LONG).show();

            }
        }
    }

    public void addCard(){
        saveCardBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertData(cardNumber.getText().toString() , expDate.getText().toString() , cvc.getText().toString());

                        if (isInserted = true){
                            Toast.makeText(activity_card.this, "Card saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(activity_card.this, "Card not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void viewCard(){
        viewCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error" , "No cards. please insert a card");
                    return ;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Card ID : " + res.getString(0) + "\n");
                    buffer.append("Card Number : " + res.getString(1) + "\n");
                    buffer.append("Expire Date: " + res.getString(2) + "\n");
                    buffer.append("CVC : " + res.getString(3) + "\n\n");
                }

                showMessage("Cards" , buffer.toString());


            }
        });
    }

    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updatecard(){
        editCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(cid.getText().toString() , cardNumber.getText().toString() , expDate.getText().toString() , cvc.getText().toString());
                if (isUpdated == true){
                    Toast.makeText(activity_card.this, "Card Update Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(activity_card.this, "Card Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deletecard(){
        removeCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows =  myDB.deleteData(cid.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(activity_card.this, "Card Removed", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity_card.this, "Card Not Removed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}