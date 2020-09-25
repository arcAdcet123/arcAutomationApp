package com.example.automation_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Hall extends Fragment   {

    public Hall() {
        // Required empty public constructor
    }
    View view;
    Button button2;
    ToggleButton button3,button4,button5;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =inflater.inflate(R.layout.fragment_hall, container, false);

        button2=(Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseCall("Hall_App1",1,"Off");
                DatabaseCall("Hall_App2",2,"Off");
                DatabaseCall("Hall_App3",3,"Off");
                Intent intent=new Intent(getActivity(),Sign_in_Activity.class);
                startActivity(intent);
            }
        });
        button3= (ToggleButton) view.findViewById(R.id.button3);

        button3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Hall_App1",1,"On");
                }else {
                    DatabaseCall("Hall_App1",1,"Off");
                }
            }
        });

        button4= (ToggleButton) view.findViewById(R.id.button4);
        button4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Hall_App2",2,"On");
                }else {
                    DatabaseCall("Hall_App2",2,"Off");
                }
            }
        });

        button5= (ToggleButton) view.findViewById(R.id.button5);
        button5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Hall_App3",3,"On");
                }else {
                    DatabaseCall("Hall_App3",3,"Off");
                }
            }
        });
        return view;
    }


    public void DatabaseCall(String appID,int i,String status){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String email = user.getEmail();
        String uid = user.getUid();
        DatabaseReference myRef = database.getReference(uid);
        myRef.child("EMAIL").setValue(email);
        if(appID.equals("Hall_App1"))
            myRef.child("APPLIANCE").setValue("HALL_1_"+status);
        else if(appID=="Hall_App2")
            myRef.child("APPLIANCE").setValue("HALL_2_"+status);
        else if(appID=="Hall_App3")
            myRef.child("APPLIANCE").setValue("HALL_3_"+status);


     }


}