package driverManage.It21086984.DriverManage_S.A.T.P.Senadheera.main.java.com.example.gamana;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DriverActivity extends AppCompatActivity {

    driverDB myDB ;
    EditText name , address , age , id ;
    Button addDriverbtn ;
    Button viewData ;
    Button updateBtn ;
    Button deleteBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        myDB = new driverDB(this);

        id = (EditText) findViewById(R.id.driverID) ;
        name = (EditText) findViewById(R.id.driverName);
        address = (EditText) findViewById(R.id.driverAddress) ;
        age = (EditText) findViewById(R.id.driverAge) ;
        addDriverbtn = (Button) findViewById(R.id.addDriverBtn) ;
        viewData = (Button) findViewById(R.id.viewDriversBtn) ;
        updateBtn = (Button)  findViewById(R.id.updateDriverBtn);
        deleteBtn = (Button) findViewById(R.id.deleteDriverBtn) ;
        addDriver();
        viewDrivers();
        updateDrivers();
        deleteData();



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("some") != null){

                Toast.makeText(getApplicationContext() , "data :" + bundle.getString("some") , Toast.LENGTH_LONG).show();

            }
        }
    }

    public void addDriver(){
        addDriverbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted = myDB.insertData(name.getText().toString() , address.getText().toString() , age.getText().toString());

                       if (isInserted = true){
                           Toast.makeText(DriverActivity.this, "Driver inserted", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(DriverActivity.this, "Driver not inserted", Toast.LENGTH_SHORT).show();
                       }
                    }
                }
        );
    }

    public void viewDrivers(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error" , "No drivers");
                    return ;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Address: " + res.getString(2) + "\n");
                    buffer.append("Age : " + res.getString(3) + "\n\n");
                }

                showMessage("Drivers" , buffer.toString());


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

    public void updateDrivers(){
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(id.getText().toString() , name.getText().toString() , address.getText().toString() , age.getText().toString());
                if (isUpdated == true){
                    Toast.makeText(DriverActivity.this, "Driver Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DriverActivity.this, "Driver Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData(){
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows =  myDB.deleteData(id.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(DriverActivity.this, "Driver Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DriverActivity.this, "Driver Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}