package com.arrawdah.enjoy;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListFav extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> array1 = new ArrayList<String>();

    public CustomListFav(Activity context,
                         ArrayList<String> array1) {
        super(context, R.layout.list_fav, array1);
        this.context = context;
        this.array1 = array1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_fav, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtFavTitle);
        txtTitle.setText(array1.get(position));
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");
        txtTitle.setTypeface(face);

        return rowView;
    }
}