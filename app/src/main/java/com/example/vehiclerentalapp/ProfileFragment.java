package com.example.vehiclerentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button driverbtn = (Button) view.findViewById(R.id.addDriver);
        Button cardbtn = (Button) view.findViewById(R.id.addCard);

        driverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() ,  com.example.vehiclerentalapp.DriverActivity.class);
                //intent.putExtra("some" , "some data");
                startActivity(intent);
            }
        });

        cardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity() , com.example.vehiclerentalapp.activity_card.class);
                startActivity(intent);

            }
        });

        return view ;


    }


}