package vian.mobile.gamana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VehicleListActivity extends AppCompatActivity {

    CardView vehicleCardView1, vehicleCardView2, vehicleCardView3, vehicleCardView4;
    public static int id, image;
    public static String name, colour;
    public static  double capacity, price;
    Button viewBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        vehicleCardView1 = findViewById(R.id.vehicleCardView1);
        vehicleCardView2 = findViewById(R.id.vehicleCardView2);
        vehicleCardView3 = findViewById(R.id.vehicleCardView3);
        vehicleCardView4 = findViewById(R.id.vehicleCardView4);

        viewBookings = findViewById(R.id.viewBookings);

        viewBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewBookingsListActivity.class));
            }
        });

        vehicleCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = VehicleData.id[0];
                image = VehicleData.vehicleImageArray[0];
                name = VehicleData.vehicleNamesArray[0];
                colour = VehicleData.vehicleColoursArray[0];
                capacity = VehicleData.engineCapacityArray[0];
                price = VehicleData.vehiclePriceArray[0];
                startActivity(new Intent(getApplicationContext(), EnterBookingDetailsActivity.class));
            }
        });

        vehicleCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = VehicleData.id[1];
                image = VehicleData.vehicleImageArray[1];
                name = VehicleData.vehicleNamesArray[1];
                colour = VehicleData.vehicleColoursArray[1];
                capacity = VehicleData.engineCapacityArray[1];
                price = VehicleData.vehiclePriceArray[1];
                startActivity(new Intent(getApplicationContext(), EnterBookingDetailsActivity.class));
            }
        });

        vehicleCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = VehicleData.id[2];
                image = VehicleData.vehicleImageArray[2];
                name = VehicleData.vehicleNamesArray[2];
                colour = VehicleData.vehicleColoursArray[2];
                capacity = VehicleData.engineCapacityArray[2];
                price = VehicleData.vehiclePriceArray[2];
                startActivity(new Intent(getApplicationContext(), EnterBookingDetailsActivity.class));
            }
        });

        vehicleCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = VehicleData.id[3];
                image = VehicleData.vehicleImageArray[3];
                name = VehicleData.vehicleNamesArray[3];
                colour = VehicleData.vehicleColoursArray[3];
                capacity = VehicleData.engineCapacityArray[3];
                price = VehicleData.vehiclePriceArray[3];
                startActivity(new Intent(getApplicationContext(), EnterBookingDetailsActivity.class));
            }
        });

    }
}
