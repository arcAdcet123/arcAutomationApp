package com.example.automation_app;

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

public class Kitchen extends Fragment {

    Button button2;
    ToggleButton button7,button8,button9;
    View view;

    public Kitchen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_kitchen, container, false);

        button2=(Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseCall("Kitchen_App1",1,"Off");
                DatabaseCall("Kitchen_App2",2,"Off");
                DatabaseCall("Kitchen_App3",3,"Off");
                Intent intent=new Intent(getActivity(),Sign_in_Activity.class);
                startActivity(intent);
            }
        });

        button7= (ToggleButton) view.findViewById(R.id.button7);
        button7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Kitchen_App1",1,"On");
                }else {
                    DatabaseCall("Kitchen_App1",1,"Off");
                }
            }
        });

        button8= (ToggleButton) view.findViewById(R.id.button8);
        button8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Kitchen_App2",2,"On");
                }else {
                    DatabaseCall("Kitchen_App2",2,"Off");
                }
            }
        });

        button9= (ToggleButton) view.findViewById(R.id.button9);
        button9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Kitchen_App3",3,"On");
                }else {
                    DatabaseCall("Kitchen_App3",3,"Off");
                }
            }
        });
        return view;
    }


    public void DatabaseCall(String appID,int i,String status) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String email = user.getEmail();
        String uid = user.getUid();
        DatabaseReference myRef = database.getReference(uid);
        myRef.child("EMAIL").setValue(email);
        if(appID.equals("Kitchen_App1"))
            myRef.child("APPLIANCE").setValue("KITCHEN_1_"+status);
        else if(appID=="Kitchen_App2")
            myRef.child("APPLIANCE").setValue("KITCHEN_2_"+status);
        else if(appID=="Kitchen_App3")
            myRef.child("APPLIANCE").setValue("KITCHEN_3_"+status);
    }
}