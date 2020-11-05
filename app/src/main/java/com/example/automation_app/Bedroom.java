package com.example.automation_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

public class Bedroom extends Fragment {
    ToggleButton button11,button12,button13;
    Button button2;
    View view;
    public Bedroom() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReceiveData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_bedroom, container, false);
        ReceiveData();
        button2=(Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseCall("Bedroom_App1",1,"Off");
                DatabaseCall("Bedroom_App2",2,"Off");
                DatabaseCall("Bedroom_App3",3,"Off");
                Intent intent=new Intent(getActivity(),Sign_in_Activity.class);
                startActivity(intent);
            }
        });

        button11= (ToggleButton) view.findViewById(R.id.button11);
        button11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Bedroom_App1",1,"On");
                }else {
                    DatabaseCall("Bedroom_App1",1,"Off");
                }
            }
        });

        button12= (ToggleButton) view.findViewById(R.id.button12);
        button12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Bedroom_App2",2,"On");
                }else {
                    DatabaseCall("Bedroom_App2",2,"Off");
                }
            }
        });

        button13= (ToggleButton) view.findViewById(R.id.button13);
        button13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DatabaseCall("Bedroom_App3",3,"On");
                }else {
                    DatabaseCall("Bedroom_App3",3,"Off");
                }
            }
        });
        return view;
    }

    public void DatabaseCall(String appID,int i,String status){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String name = user.getDisplayName();
        String uid = user.getUid();
        DatabaseReference myRef = database.getReference(uid);
        myRef.child(name);
        if(appID.equals("Bedroom_App1"))
            myRef.child(name).child("Bedroom_App1").setValue(status);
        else if(appID=="Bedroom_App2")
            myRef.child(name).child("Bedroom_App2").setValue(status);
        else if(appID=="Bedroom_App3")
            myRef.child(name).child("Bedroom_App3").setValue(status);
        ReceiveData();

    }

    public void ReceiveData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
        String uid = user.getUid();
        final DatabaseReference ref1 = database.getReference().child(uid).child(name).child("Bedroom_App1");
        final DatabaseReference ref2 = database.getReference().child(uid).child(name).child("Bedroom_App2");
        final DatabaseReference ref3 = database.getReference().child(uid).child(name).child("Bedroom_App3");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    System.out.println(snapshot1.getValue());
                }*/
                System.out.println(snapshot.getValue());
                if(snapshot.getValue().equals("Off")){
                    button11.setText("Device Off");
                }if(snapshot.getValue().equals("On")) {
                    button11.setText("Device On");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed");
            }
        });

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    System.out.println(snapshot1.getValue());
                }*/
                System.out.println(snapshot.getValue());
                if(snapshot.getValue().equals("Off")){
                    button12.setText("Device Off");
                }if(snapshot.getValue().equals("On")) {
                    button12.setText("Device On");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed");
            }
        });

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    System.out.println(snapshot1.getValue());
                }*/
                System.out.println(snapshot.getValue());
                if(snapshot.getValue().equals("Off")){
                    button13.setText("Device Off");
                }if(snapshot.getValue().equals("On")) {
                    button13.setText("Device On");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed");
            }
        });
    }
}

