package vian.mobile.gamana;

import static vian.mobile.gamana.BookingDetailsDB.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingsCustomAdapter extends RecyclerView.Adapter<BookingsCustomAdapter.ViewHolder> {

    private ArrayList<BookingDataModel> dataSet;
    private Context context;
    SQLiteDatabase sqLiteDatabase;
    int dataItem;
    public static Boolean edit;

    public static int id, image, days;
    public static String name, colour, start, end;
    public static  double capacity, price, total;

    public BookingsCustomAdapter(Context context, int dataItem, ArrayList<BookingDataModel> data, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.dataItem = dataItem;
        this.dataSet = data;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vehicle_list_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        BookingDataModel data = dataSet.get(position);

        // setting data to views
        holder.textViewVehicleName.setText(data.getVehicleName());
        holder.textViewVehicleCapacity.setText(String.valueOf("Engine Capacity: " + data.getEngineCapacity() + " L"));
        holder.textViewVehicleColour.setText("Vehicle Colour: " + data.getVehicleColour());
        holder.textViewVehiclePrice.setText(String.valueOf("Cost Per Day: LKR " + data.getVehiclePrice()));
        holder.textViewPickUpDate.setText(String.valueOf(data.getPickUpDate()));
        holder.textViewDropOffDate.setText(String.valueOf(data.getDropOffDate()));
        holder.textViewTotalNoOfDays.setText(String.valueOf("No. of Days: " + data.getNoOfDays()));
        holder.textViewTotalPrice.setText(String.valueOf("Total Cost: LKR " + data.getTotalCost()));
        holder.vehicleImageView.setImageResource(data.getVehicleImage());

        Log.d("*******************************", String.valueOf(getItemCount()));

        //flow menu
        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context, holder.editIcon);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit_menu:
                                //edit operation
                                edit = true;
                                try {
                                    id = data.getVehicleID();
                                    image = data.getVehicleImage();
                                    name = data.getVehicleName();
                                    colour = data.getVehicleColour();
                                    capacity = data.getEngineCapacity();
                                    price = data.getVehiclePrice();
                                    start = data.getPickUpDate();
                                    end = data.getDropOffDate();
                                    days = data.getNoOfDays();
                                    total = data.getTotalCost();
                                    Log.d("*************************", String.valueOf(id));
                                    Log.d("*************************", String.valueOf(price));
                                    Intent intent = new Intent(context, UpdateBookingDetailsActivity.class);
                                    context.startActivity(intent);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.delete_menu:
                                // delete operation
                                BookingDetailsDB bookingDetailsDB = new BookingDetailsDB(context);
                                sqLiteDatabase = bookingDetailsDB.getReadableDatabase();
                                long recdelete = sqLiteDatabase.delete(TABLENAME,"vehicleID="+ data.getVehicleID(),null);
                                if (recdelete!=-1){
                                    Toast.makeText(context, "Booking Cancelled Successfully", Toast.LENGTH_SHORT).show();
                                    //remove position after deleted
                                    dataSet.remove(position);
                                    //update data
                                    notifyDataSetChanged();
                                }
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                //display menu
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // Declarig variables for views
        TextView textViewVehicleName, textViewVehicleCapacity, textViewVehicleColour, textViewVehiclePrice;
        TextView textViewTotalNoOfDays, textViewTotalPrice, textViewPickUpDate, textViewDropOffDate;
        ImageView vehicleImageView;
        ImageButton editIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            // initializing views
            textViewVehicleName = (TextView) itemView.findViewById(R.id.textViewVehicleName);
            textViewVehicleCapacity = (TextView) itemView.findViewById(R.id.textViewVehicleCapacity);
            textViewVehicleColour = (TextView) itemView.findViewById(R.id.textViewVehicleColour);
            textViewVehiclePrice = (TextView) itemView.findViewById(R.id.textViewVehiclePrice);
            vehicleImageView = (ImageView) itemView.findViewById(R.id.vehicleImageView);

            textViewPickUpDate = (TextView)  itemView.findViewById(R.id.textViewPickUpDate);
            textViewDropOffDate = (TextView)  itemView.findViewById(R.id.textViewDropOffDate);
            textViewTotalNoOfDays = (TextView)  itemView.findViewById(R.id.textViewTotalNoOfDays);
            textViewTotalPrice = (TextView)  itemView.findViewById(R.id.textViewTotalPrice);

            editIcon = (ImageButton)itemView.findViewById(R.id.editIcon);

            textViewVehicleName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    BookingDataModel fuelQueue = dataSet.get(position);

                }

            });
        }

    }

}