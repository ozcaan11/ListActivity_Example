package com.ozcaan11.listactivity_example;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainMenuActivity extends ListActivity {

    protected List<ParseObject>     mMesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

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
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(MainMenuActivity.this,DetayActivity.class);
        intent.putExtra("obje_id",mMesaj.get(position).getObjectId());
        startActivity(intent);
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

}
