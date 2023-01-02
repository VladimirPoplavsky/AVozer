package com.firstapp.avozer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton registerBtn;
    private MaterialButton loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.emailReg);
        TextView password =(TextView) findViewById(R.id.password);

        loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
        registerBtn = (MaterialButton) findViewById(R.id.registerbtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });


        // admin

//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                 //correct
//                   Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//               }else
//                   //incorrect
//                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });




    }
}