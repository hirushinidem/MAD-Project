package vian.mobile.gamana;

import static vian.mobile.gamana.BookingDetailsDB.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewBookingsListActivity extends AppCompatActivity {

    BookingDetailsDB bookingDetailsDB;
    SQLiteDatabase sqLiteDatabase;

    RecyclerView recyclerView;
    BookingsCustomAdapter bookingsCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings_list);

        bookingDetailsDB = new BookingDetailsDB(getApplicationContext());

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        sqLiteDatabase= bookingDetailsDB.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLENAME+"",null);
        ArrayList<BookingDataModel> bookingDataModels =new ArrayList<>();
        while (cursor.moveToNext()){
            bookingDataModels.add(new BookingDataModel(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getDouble(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8),
                    cursor.getDouble(9)
                    ));
        }
        cursor.close();
        bookingsCustomAdapter = new BookingsCustomAdapter(this, R.layout.vehicle_list_layout, bookingDataModels, sqLiteDatabase);
        recyclerView.setAdapter(bookingsCustomAdapter);

    }
}