package com.ozcaan11.listactivity_example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class DetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        Intent intent   = getIntent();
        String obje_id  = intent.getStringExtra("obje_id");

        final TextView gonderen   = (TextView) findViewById(R.id.textView_gonderen_detay);
        final TextView mesaj      = (TextView) findViewById(R.id.textView_mesaj_detay);

        ParseQuery<ParseObject> query = new ParseQuery<>("Mesaj");
        query.getInBackground(obje_id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if(e == null){
                    mesaj.setText("\" "+parseObject.getString("mesaj")+" \"");
                    gonderen.setText("Gönderen: "+parseObject.getString("kullanici"));
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
                onBackPressed();
                System.exit(0);
            }
        });
    }

    public void YeniKonu(){

            AlertDialog.Builder builder =new AlertDialog.Builder(DetayActivity.this);
            builder.setTitle("Yeni konu ekleyebilmek için giriş yapmalısınız");

            builder.setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(DetayActivity.this, LoginActivity.class);
                    startActivity(intent);
                    System.exit(0);
                }
            });

            builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
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
                Toast.makeText(DetayActivity.this, "Yardım butonu sonra yapılacak", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yeni_konu:
                if (ParseUser.getCurrentUser() == null){
                    YeniKonu();
                }
                else {
                    Intent intent = new Intent(DetayActivity.this,YeniKonuActivity.class);
                    startActivity(intent);
                    System.exit(0);
                }
                break;
            case R.id.cikis:
                System.exit(0);
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
