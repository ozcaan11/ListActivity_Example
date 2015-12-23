package com.ozcaan11.listactivity_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        Intent intent = getIntent();
        String obje_id = intent.getStringExtra("obje_id");

        final TextView gonderen   = (TextView) findViewById(R.id.textView_gonderen_detay);
        final TextView mesaj      = (TextView) findViewById(R.id.textView_mesaj_detay);

        ParseQuery<ParseObject> query = new ParseQuery<>("Mesaj");
        query.getInBackground(obje_id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if(e == null){
                    mesaj.setText(parseObject.getString("mesaj"));
                    gonderen.setText(parseObject.getString("kullanici"));
                }
                else {
                    Toast.makeText(DetayActivity.this, "Bir hata oluştu !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button geri = (Button)findViewById(R.id.btn_detay_geri);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetayActivity.this,MainMenuActivity.class));
            }
        });
    }
}
