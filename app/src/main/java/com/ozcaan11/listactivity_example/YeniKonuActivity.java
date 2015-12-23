package com.ozcaan11.listactivity_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class YeniKonuActivity extends AppCompatActivity {

    private EditText    mesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_konu);

        if (ParseUser.getCurrentUser() == null){
            Intent intent = new Intent(YeniKonuActivity.this,LoginActivity.class);
            startActivity(intent);
            System.exit(0);
        }

        mesaj           = (EditText)findViewById(R.id.editText_anamenu_mesaj);
        Button gonder   = (Button)  findViewById(R.id.btn_anamenu_gonder);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mesaj.getText() != null) {
                    final ParseObject parseObject = new ParseObject("Mesaj");
                    parseObject.put("mesaj", mesaj.getText().toString());
                    parseObject.put("kullanici", ParseUser.getCurrentUser().getUsername());

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(YeniKonuActivity.this, "İşlem başarıyla gerçekleşti !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(YeniKonuActivity.this, MainMenuActivity.class));
                                System.exit(0);
                            } else {
                                Toast.makeText(YeniKonuActivity.this, "Hata " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(YeniKonuActivity.this, "Göndereceğiniz mesaj boş olamaz !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainMenuActivity.class));
        System.exit(0);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_application,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.yardim:
                Toast.makeText(YeniKonuActivity.this, "Help sonra yapılacaktır", Toast.LENGTH_SHORT).show();
            case R.id.yeni_konu:
                Toast.makeText(YeniKonuActivity.this, "Yeni konu ekleyin lütfen ", Toast.LENGTH_SHORT).show();
            case R.id.cikis:
                ParseUser.logOut();
                Toast.makeText(YeniKonuActivity.this, "Çıkış yapılıyor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainMenuActivity.class));
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

}
