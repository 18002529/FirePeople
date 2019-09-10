package com.example.firepeople;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FiredPeopleList extends AppCompatActivity {

    Person pulledPeople = new Person();
    ListView lv_pulledPeople;
    ArrayList<Person> people;
    ArrayAdapter<Person> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Person");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fired_people_list);

        lv_pulledPeople = findViewById(R.id.lv_firedPeople);
        people = new ArrayList<Person>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();


                for (DataSnapshot child:children){
                    pulledPeople = child.getValue(Person.class);
                    people.add(pulledPeople);
                }
                adapter = new ArrayAdapter<>(FiredPeopleList.this, android.R.layout.simple_list_item_1);
                lv_pulledPeople.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
