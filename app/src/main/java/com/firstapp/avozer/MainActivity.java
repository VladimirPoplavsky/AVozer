package com.firstapp.avozer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);


        // admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                 //correct
                   Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
               }else
                   //incorrect
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();


            }
        });




    }
}