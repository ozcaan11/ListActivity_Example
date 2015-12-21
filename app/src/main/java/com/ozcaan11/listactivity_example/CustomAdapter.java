package com.ozcaan11.listactivity_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<ParseObject> {

    protected Context               mContext;
    protected List<ParseObject>     mMesaj;

    public CustomAdapter(Context context, List<ParseObject> mesaj) {
        super(context,R.layout.custom,mesaj);

        mContext = context;
        mMesaj   = mesaj;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom, null);

            holder  = new ViewHolder();

            holder.kullanici_adi = (TextView)convertView.findViewById(R.id.textView_kullanici_adi);
            holder.kullanici_mesaj = (TextView)convertView.findViewById(R.id.textView_mesaj);
            holder.tarih = (TextView)convertView.findViewById(R.id.textView_tarih);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        ParseObject mesajObject = mMesaj.get(position);

        String kullaniciadi = mesajObject.getString("kullanici");
        holder.kullanici_adi.setText(kullaniciadi);

        String mesaj = mesajObject.getString("mesaj");
        holder.kullanici_mesaj.setText(mesaj);

        return convertView;
    }

    public static class ViewHolder{
        TextView kullanici_adi;
        TextView kullanici_mesaj;
        TextView tarih;
    }

}
