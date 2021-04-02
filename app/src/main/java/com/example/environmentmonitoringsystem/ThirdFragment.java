package com.example.environmentmonitoringsystem;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;


public class ThirdFragment extends Fragment {
    View view;
    SeekBar s3;
    EditText li3;
    int pc=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_third, container, false);
        s3=view.findViewById(R.id.seekbar3);
        li3=view.findViewById(R.id.li3);

        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pc=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                li3.setText(String.valueOf(pc));

            }
        });
        return view;
    }
}