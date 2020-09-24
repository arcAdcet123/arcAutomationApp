package com.example.automation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button on,off;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on=findViewById(R.id.BUTTON_ON);
        off=findViewById(R.id.BUTTON_OFF);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String email = user.getEmail();
                int MACID=1234;
                String uid = user.getUid();
                DatabaseReference myRef = database.getReference(uid);
                myRef.child("EMAIL").setValue(email);
                myRef.child("MAC iD").setValue(MACID);
                myRef.child("WiFi name").setValue("Mark");
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("STATUS_OFF");
                myRef.setValue(0);
            }
        });
    }
}