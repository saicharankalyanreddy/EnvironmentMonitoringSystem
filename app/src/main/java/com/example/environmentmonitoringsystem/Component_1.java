package com.example.environmentmonitoringsystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.android.material.textfield.TextInputEditText;


public class Component_1 extends Fragment {
    View view;

    SeekBar s1;
    EditText li1;
    int pc=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_component_1, container, false);
        s1=view.findViewById(R.id.seekbar1);
        li1=view.findViewById(R.id.li1);


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
}