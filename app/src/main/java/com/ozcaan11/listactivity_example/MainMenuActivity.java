package com.ozcaan11.listactivity_example;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainMenuActivity extends ListActivity {

    protected List<ParseObject>     mMesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        /*if(ParseUser.getCurrentUser() == null){
            startActivity(new Intent(this,LoginActivity.class));
        }*/

        ParseQuery<ParseObject> query = new ParseQuery<>("Mesaj");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> mesaj, ParseException e) {
                if (e == null) {
                    mMesaj = mesaj;
                    CustomAdapter adapter = new CustomAdapter(getListView().getContext(), mMesaj);
                    setListAdapter(adapter);
                } else {
                    Toast.makeText(MainMenuActivity.this, "Hata " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button yeni_konu = (Button) findViewById(R.id.btn_yeni_konu);
        yeni_konu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YeniKonu();
            }
        });
    }

    public void YeniKonu(){
        if (ParseUser.getCurrentUser() == null){

            AlertDialog.Builder builder =new AlertDialog.Builder(MainMenuActivity.this);
            builder.setTitle("Yeni konu ekleyebilmek için giriş yapmalısınız");

            builder.setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {
            Intent intent = new Intent(MainMenuActivity.this,KullaniciIcinAnaMenuKayitliActivity.class);
            startActivity(intent);
        }

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
                Toast.makeText(MainMenuActivity.this, "Yardım butonu sonra yapılacak", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yeni_konu:
                if (ParseUser.getCurrentUser() == null){
                    AlertDialog.Builder builder =new AlertDialog.Builder(MainMenuActivity.this);
                    builder.setTitle("Yeni konu ekleyebilmek için giriş yapmalısınız");

                    builder.setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                            startActivity(intent);
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
                else {
                    Intent intent = new Intent(MainMenuActivity.this,KullaniciIcinAnaMenuKayitliActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(MainMenuActivity.this, ""+position, Toast.LENGTH_SHORT).show();
    }
}
