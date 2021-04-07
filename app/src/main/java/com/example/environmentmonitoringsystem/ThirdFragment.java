package com.example.environmentmonitoringsystem;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ThirdFragment extends Fragment {
    View view;
    SeekBar s3;
    MaterialTextView li3;
    int pc=0;
    MaterialTextView h3;
    MaterialTextView t3;
    FirebaseDatabase database;
    Button b3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_third, container, false);
        s3=view.findViewById(R.id.seekbar3);
        li3=view.findViewById(R.id.li3);

        h3=view.findViewById(R.id.humidity3);
        t3=view.findViewById(R.id.temp3);
        database=FirebaseDatabase.getInstance();

        b3=view.findViewById(R.id.button3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lv = s3.getProgress();

               // database.getReference().child("li3").setValue(lv);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        database.getReference().child("humidity3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                    h3.setText("Humidity : "+snapshot.getValue()+" %");
                else
                    h3.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("temperature3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                    t3.setText("Temperature : "+snapshot.getValue()+" °C");
                else
                    t3.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.getReference().child("li3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) {
                    //s3.setProgress(Integer.parseInt(snapshot.getValue().toString()));
                    li3.setText(snapshot.getValue().toString()+" LUX");
                }
                else{
                    s3.setProgress(0);
                    li3.setText(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}