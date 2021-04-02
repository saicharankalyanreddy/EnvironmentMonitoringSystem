package com.example.environmentmonitoringsystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Secondfragment extends Fragment {
    View view;
    SeekBar s2;
    EditText li2;
    int pc=0;


    MaterialTextView h2;
    MaterialTextView t2;

    FirebaseDatabase database;

    Button b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_second, container, false);
        s2=view.findViewById(R.id.seekbar2);
        li2=view.findViewById(R.id.li2);

        h2=view.findViewById(R.id.humidity2);

        t2=view.findViewById(R.id.temp2);

        database = FirebaseDatabase.getInstance();

        b2=view.findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lv = Float.parseFloat(li2.getText().toString());

                database.getReference().child("li2").setValue(lv);
            }
        });

        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pc=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                li2.setText(String.valueOf(pc));
            }
        });




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        database.getReference().child("humidity2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                h2.setText("Humidity : "+snapshot.getValue()+" %");
                else
                    h2.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("temperature2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                    t2.setText("Temperature : "+snapshot.getValue()+" °C");
                else
                    t2.setText("Data not Present in Database");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.getReference().child("li2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) {
                    s2.setProgress(Integer.parseInt(snapshot.getValue().toString()));
                    li2.setText(snapshot.getValue().toString());
                }
                else{
                    s2.setProgress(0);
                    li2.setText(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
