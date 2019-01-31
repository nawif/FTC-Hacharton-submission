package com.arrawdah.enjoy;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> array1 = new ArrayList<String>();
    private ArrayList<Integer> array2 = new ArrayList<Integer>();
    View lineColor;
    public CustomList(Activity context,
                      ArrayList<String> array1, ArrayList<Integer> array2) {
        super(context, R.layout.list_single, array1);
        this.context = context;
        this.array1 = array1;
        this.array2 = array2;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView img = (ImageView) rowView.findViewById(R.id.imageList);
        txtTitle.setText(array1.get(position));
        img.setImageResource(array2.get(position));
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");
        txtTitle.setTypeface(face);

        return rowView;
    }
}