package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahd on 17/12/2016.
 */

public class MenuAdapter extends ArrayAdapter<Title> {

    private Context context;

    public MenuAdapter(Context context, ArrayList<Title> titles) {
        super(context,0, titles);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_cell_menu,parent,false);

            ViewHolder holder = new ViewHolder();
            holder.txtTitle = (TextView)convertView.findViewById(R.id.titleRow);
            String fontPath = "fonts/noor.ttf";
            Typeface face = Typeface.createFromAsset(context.getAssets(), fontPath);
            holder.txtTitle.setTypeface(face);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();

        final Title title = getItem(position);

        holder.txtTitle.setText(title.getTitle());
        return convertView;
    }


    class  ViewHolder {

        TextView txtTitle;

    }



}
