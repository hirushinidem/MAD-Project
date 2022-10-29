package com.example.vehiclerentalapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class VehicleActivity extends AppCompatActivity {

    VehicleDB myDb;
    EditText editVehicleNo, editVehicleType, editModelNo, editPassengers;
    Button addVehicleBtn, updateBtn,viewBtn, deleteBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        myDb = new VehicleDB(this);
        editVehicleNo = (EditText) findViewById(R.id.editTextVehicleNo);
        editVehicleType = (EditText) findViewById(R.id.editTextVehicleType);
        editModelNo = (EditText) findViewById(R.id.editTextVehicleModel);
        editPassengers = (EditText) findViewById(R.id.editTextNoOfPassengers);
        addVehicleBtn = (Button) findViewById(R.id.btnAddVehicle);
        viewBtn = (Button) findViewById(R.id.btnView);
        updateBtn = (Button) findViewById(R.id.btnUpdate);
        deleteBtn = (Button) findViewById(R.id.btnDelete);

        addVehicle();
        viewVehicles();
        updateData();
        deleteData();
    }

    public void addVehicle() {
        addVehicleBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String vno = editVehicleNo.getText().toString();
                        String vType = editVehicleType.getText().toString();
                        String model =  editModelNo.getText().toString();
                        String pass = editPassengers.getText().toString();

                        boolean isInserted = myDb.insertVehicle(vno, vType, model, pass);

                        if (isInserted == true){
                            Toast.makeText(VehicleActivity.this, "Vehicle added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(VehicleActivity.this, "Vehicle not added", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public  void viewVehicles(){
        viewBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllVehicles();

                        if(res.getCount() == 0){
                            showMessage("Error", "No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Vehicle No: " +res.getString(0) +"\n");
                            buffer.append("Vehicle Type: " +res.getString(1) +"\n");
                            buffer.append("Vehicle Model: " +res.getString(2) +"\n");
                            buffer.append("No of Passengers: " +res.getString(3) +"\n\n");
                        }

                        //show all data
                        showMessage("Vehicles", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updateData(){
        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String vno = editVehicleNo.getText().toString();
                        String vType = editVehicleType.getText().toString();
                        String model =  editModelNo.getText().toString();
                        String pass = editPassengers.getText().toString();

                        boolean isUpdated = myDb.updateVehicle(vno, vType, model, pass);

                        if(isUpdated == true){
                            Toast.makeText(VehicleActivity.this, "Vehicle updated", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(VehicleActivity.this, "Vehicle not updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void deleteData(){
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDb.deleteData(editVehicleNo.getText().toString());
                        if(deletedRows > 0){
                            Toast.makeText(VehicleActivity.this, "Vehicle deleted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(VehicleActivity.this, "Vehicle updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


}
