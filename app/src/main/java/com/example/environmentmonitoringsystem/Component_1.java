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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Component_1 extends Fragment {
    View view;
    MaterialTextView h1;
    MaterialTextView t1;
    SeekBar s1;
    EditText li1;
    int pc=0;
    FirebaseDatabase database;
    Button b1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_component_1, container, false);
        s1=view.findViewById(R.id.seekbar1);
        li1=view.findViewById(R.id.li1);

        h1=view.findViewById(R.id.humidity1);
        t1=view.findViewById(R.id.temp1);

        b1=view.findViewById(R.id.button1);



        database = FirebaseDatabase.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lv = Float.parseFloat(li1.getText().toString());

                database.getReference().child("li1").setValue(lv);
            }
        });


        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pc=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                li1.setText(String.valueOf(pc));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        database.getReference().child("humidity1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                h1.setText("Humidity : "+snapshot.getValue()+" %");
                else
                    h1.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.getReference().child("temperature1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                    t1.setText("Temperature : "+snapshot.getValue()+" °C");
                else
                    t1.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.getReference().child("li1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) {
                    s1.setProgress(Integer.parseInt(snapshot.getValue().toString()));
                    li1.setText(snapshot.getValue().toString());
                }
                else{
                    s1.setProgress(0);
                    li1.setText(0);
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}