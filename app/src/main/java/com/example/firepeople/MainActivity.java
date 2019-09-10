package com.example.firepeople;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    String firstName, lastName, reasonFired;
    Button btnPush;
    EditText txtFirstName, txtLastName, txtReasonFired;
    Person firedPerson;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Person");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPush.findViewById(R.id.btnPush);
        txtFirstName.findViewById(R.id.fireName);
        txtLastName.findViewById(R.id.fireSurname);
        txtReasonFired.findViewById(R.id.fireReason);

        firedPerson = new Person();

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            firstName = txtFirstName.getText().toString();
            lastName = txtLastName.getText().toString();
            reasonFired = txtReasonFired.getText().toString();

            //Method 1
            firedPerson.firstName = firstName;

            //Method 2
            firedPerson.setLastName(lastName);
            firedPerson.setReasonFired(reasonFired);

            myRef.push().setValue(firedPerson);

            Intent i = new Intent(MainActivity.this, FiredPeopleList.class);
            startActivity(i);
            }
        });
    }
}
