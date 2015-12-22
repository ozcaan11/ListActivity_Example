package com.ozcaan11.listactivity_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        Parse.initialize(this);
        //Parse.initialize(this,"your_app_id","your_client_key");


        Button btn = (Button)findViewById(R.id.btn_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StarterActivity.this, MainMenuActivity.class));
            }
        });

    }
}
