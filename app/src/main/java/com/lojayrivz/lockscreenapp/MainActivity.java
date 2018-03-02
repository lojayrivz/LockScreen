package com.lojayrivz.lockscreenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button log = (Button) findViewById(R.id.loginn);
        Button reg = (Button) findViewById(R.id.register);

        log.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.loginn:
                Toast.makeText(this, "Log in",Toast.LENGTH_SHORT).show();
                openLogIn();
                break;
            case R.id.register:
                Toast.makeText(this, "Register",Toast.LENGTH_SHORT).show();
                openRegister();
                break;
        }

    }

    public void openLogIn(){
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
