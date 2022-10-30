package vian.mobile.gamana;

import static vian.mobile.gamana.BookingDetailsDB.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EnterBookingDetailsActivity extends AppCompatActivity {

    TextView textViewVehicleName, textViewVehicleCapacity, textViewVehicleColour, textViewVehiclePrice;
    ImageView vehicleImageView;
    TextView pickUpTime, dropOffTime, textViewTotalNoOfDays, textViewTotalPrice, textViewPickUpDate, textViewDropOffDate;
    Button calculateCost, confirmBooking;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    int noOfDays;
    double totalPrice;
    Calendar calendar;
    String startDay, endDay = null;
    int flag = 0;

    BookingDetailsDB bookingDetailsDB;
    SQLiteDatabase sqLiteDatabase;

    Boolean editP = false;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_booking_details);

        bookingDetailsDB = new BookingDetailsDB(getApplicationContext());

        // initializing views
        textViewVehicleName = (TextView) findViewById(R.id.textViewVehicleName);
        textViewVehicleCapacity = (TextView) findViewById(R.id.textViewVehicleCapacity);
        textViewVehicleColour = (TextView) findViewById(R.id.textViewVehicleColour);
        textViewVehiclePrice = (TextView) findViewById(R.id.textViewVehiclePrice);
        textViewPickUpDate = (TextView) findViewById(R.id.textViewPickUpDate);
        textViewDropOffDate = (TextView) findViewById(R.id.textViewDropOffDate);
        textViewTotalNoOfDays = (TextView) findViewById(R.id.textViewTotalNoOfDays);
        textViewTotalPrice = (TextView) findViewById(R.id.textViewTotalPrice);
        vehicleImageView = (ImageView) findViewById(R.id.vehicleImageView);
        pickUpTime = findViewById(R.id.pickUpTime);
        dropOffTime = findViewById(R.id.dropOffTime);
        calculateCost = findViewById(R.id.calculateCost);
        confirmBooking = findViewById(R.id.confirmBooking);

            editP = false;
            confirmBooking.setText(R.string.confirmBooking);
            pickUpTime.setText("Select Pick-up Date");
            dropOffTime.setText("Select Drop-off Date");
            // set the details of the vehicle selected
            vehicleImageView.setImageResource(VehicleListActivity.image);
            textViewVehicleName.setText(VehicleListActivity.name);
            textViewVehicleCapacity.setText(String.valueOf(VehicleListActivity.capacity));
            textViewVehicleColour.setText(VehicleListActivity.colour);
            textViewVehiclePrice.setText("LKR " + String.valueOf(VehicleListActivity.price) + " /Day");


        // show date picker when pickUpTime is clicked
        pickUpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPickUpDate.setText("");
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EnterBookingDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textViewPickUpDate.setVisibility(View.VISIBLE);
                                textViewPickUpDate.setText("Pick-up Date: " + day + "/" + (month + 1) + "/" + year);
                                startDay =  (month + 1) + "/" + day + "/" +year;
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        // show date picker when dropOffTime is clicked
        dropOffTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDropOffDate.setText("");
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EnterBookingDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textViewDropOffDate.setVisibility(View.VISIBLE);
                                textViewDropOffDate.setText("Drop-off Date: " + day + "/" + (month + 1) + "/" + year);
                                endDay =  (month + 1) + "/" + day + "/" +year;
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });

        calculateCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDay == null) {
                    Toast.makeText(EnterBookingDetailsActivity.this, "Please select pick-up date", Toast.LENGTH_SHORT).show();
                }
                else if(endDay == null) {
                    Toast.makeText(EnterBookingDetailsActivity.this, "Please select drop-off date", Toast.LENGTH_SHORT).show();
                }
                else {
                    flag = 1;
                    noOfDays = calculateNoOfDays(startDay, endDay); // calculating the number of days

                    totalPrice = calculateTotalPrice(noOfDays); // calculating the total price for booking


                    Log.d("*********************", String.valueOf(id));

                    textViewTotalNoOfDays.setVisibility(View.VISIBLE);
                    textViewTotalNoOfDays.setText("No. of Days: " + noOfDays);

                    textViewTotalPrice.setVisibility(View.VISIBLE);
                    textViewTotalPrice.setText("Total Price: LKR " + totalPrice);
                }
                
            }
        });


        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    insertData();
                }
                else {
                    Toast.makeText(EnterBookingDetailsActivity.this, "Calculate cost before confirming booking", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    // calculating the number of days
    private int calculateNoOfDays(String startDay, String endDay) {

        long timeDiff, daysDiff = 0;
        try {
            // Convert `String` to `Date`
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date dateBefore = sdf.parse(startDay);
            Date dateAfter = sdf.parse(endDay);

            // Calculate the number of days between dates
            timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
            daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
           
        }catch(Exception e){
            e.printStackTrace();
        }

        return (int) daysDiff;
    }

    // calculating the total price for booking
    private double calculateTotalPrice(int noOfDays) {
            return VehicleListActivity.price * noOfDays;
    }

    // inserting data to SQLite database
    public void insertData() {
        ContentValues cv = new ContentValues();
        cv.put("vehicleID", VehicleListActivity.id);
        cv.put("vehicleImage", VehicleListActivity.image);
        cv.put("vehicleName", VehicleListActivity.name);
        cv.put("vehicleColour", VehicleListActivity.colour);
        cv.put("engineCapacity", VehicleListActivity.capacity);
        cv.put("vehiclePrice", VehicleListActivity.price);
        cv.put("pickUpDate", textViewPickUpDate.getText().toString());
        cv.put("dropOffDate",textViewDropOffDate.getText().toString());
        cv.put("noOfDays", noOfDays);
        cv.put("totalCost", totalPrice);

        sqLiteDatabase = bookingDetailsDB.getWritableDatabase();

        Long recinsert = sqLiteDatabase.insert(TABLENAME,null, cv);

        if (recinsert != null){
            finish();
            startActivity(new Intent(getApplicationContext(), BookingConfirmedActivity.class));
        }
    }


}