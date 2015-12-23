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
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {
    private EditText    kullanici_adi,kullanici_sifresi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Parse.initialize(this);

        kullanici_adi       = (EditText)findViewById(R.id.editText_kullaniciadi);
        kullanici_sifresi   = (EditText)findViewById(R.id.editText_sifre);
        Button giris_yap = (Button) findViewById(R.id.btn_login_giris);

        giris_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(kullanici_adi.getText().toString(), kullanici_sifresi.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e != null) {
                            Toast.makeText(LoginActivity.this, "Hata" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            ///intents
                            Toast.makeText(LoginActivity.this, "Başarılı", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, KullaniciIcinAnaMenuKayitliActivity.class);
                            startActivity(intent);
                            System.exit(0);
                        }
                    }
                });
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
            case R.id.yeni_kullanici:
                Toast.makeText(LoginActivity.this, "Şu an yeni kullanıcı kaydedilemiyor ..", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainMenuActivity.class));
        System.exit(0);
        super.onBackPressed();
    }
}
