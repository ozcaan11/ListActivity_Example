package com.ozcaan11.listactivity_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    private Button      giris_yap,misafir_giris;
    private EditText    kullanici_adi,kullanici_sifresi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kullanici_adi       = (EditText)findViewById(R.id.editText_kullaniciadi);
        kullanici_sifresi   = (EditText)findViewById(R.id.editText_sifre);
        giris_yap           = (Button)  findViewById(R.id.btn_login_giris);
        misafir_giris       = (Button)  findViewById(R.id.btn_login_misafir);

        //Parse.initialize(this,your_app_id,your_client_id);

        giris_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(kullanici_adi.getText().toString(), kullanici_sifresi.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e != null) {
                            Toast.makeText(LoginActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            ///intents
                            Toast.makeText(LoginActivity.this, "sa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        /*****************/
        misafir_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.yardim:
                Toast.makeText(LoginActivity.this, "Help butonu sonra yapılacaktır ..", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
