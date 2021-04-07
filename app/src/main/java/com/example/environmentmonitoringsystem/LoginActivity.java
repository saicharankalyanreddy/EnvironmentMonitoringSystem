package com.example.environmentmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText un,pass;
    MaterialButton login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        un=findViewById(R.id.un);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usern = un.getText().toString();
                String passw = pass.getText().toString();


                if(usern.isEmpty())
                    un.setError("Username Can not be Empty");
                else if(passw.isEmpty())
                    pass.setError("Password can not be Empty");

               else if(usern.equals("user")&&passw.equals("pass")){
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }
                else
                    Toast.makeText(LoginActivity.this,"Login Failed Check the Credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }
}