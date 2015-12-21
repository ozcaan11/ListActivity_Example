package com.ozcaan11.listactivity_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        Parse.initialize(this,"your_app_id","your_client_id");

            Intent intent = new Intent(StarterActivity.this,MainMenuActivity.class);
            startActivity(intent);

    }

    @Override
    protected void onPause() {
        //Intent intent = new Intent(StarterActivity.this,MainMenuActivity.class);
        startActivity(getIntent());
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
