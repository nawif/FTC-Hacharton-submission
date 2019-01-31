package com.alsahirAndroid.merth;

import android.com.merath.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahd on 17/12/2016.
 */

public class AlertAdapter extends ArrayAdapter<Alert> {

    private Context context;

    public AlertAdapter(Context context, ArrayList<Alert> alerts) {
        super(context,0, alerts);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_cell_alert,parent,false);

            ViewHolder holder = new ViewHolder();
            holder.txtTitle = (TextView)convertView.findViewById(R.id.titleRowAlert);
            holder.txtSubTitle = (TextView)convertView.findViewById(R.id.subTitleRowAlert);
            holder.aSwitch = (Switch) convertView.findViewById(R.id.switchRowAlert);

            String fontPath = "fonts/noor.ttf";
            Typeface face = Typeface.createFromAsset(context.getAssets(), fontPath);

            holder.txtTitle.setTypeface(face);

            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();

        final Alert alert = getItem(position);

        holder.txtTitle.setText(alert.getTitle());
        holder.txtSubTitle.setText(getSubTitle(alert.getRepeat(),alert.getTime()));
        holder.aSwitch.setChecked(alert.getActive());

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                final DSDatabase dsDatabase = new DSDatabase(context);

                String data = "is_active=" + "'" + isChecked + "'";

                dsDatabase.updateCoulmnByID("alerts",data,alert.getId());

            }
        });
        return convertView;
    }


    class  ViewHolder {

        TextView txtTitle;
        TextView txtSubTitle;
        Switch aSwitch;
    }

    private String getSubTitle(String repeat, String time)
    {
        
        if (Integer.parseInt(repeat) == 0)
        {
            repeat = "ينبهك كل نصف ساعة عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 1)
        {
            repeat = "ينبهك كل ساعة عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 2)
        {
            repeat = "ينبهك كل ثلاثة ساعات عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 3)
        {
            repeat = "ينبهك كل يوم عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 4)
        {
            repeat = "ينبهك كل أحد عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 5)
        {
            repeat = "ينبهك كل أثنين عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 6)
        {
            repeat = "ينبهك كل ثلاثاء عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 7)
        {
            repeat = "ينبهك كل أربعاء عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 8)
        {
            repeat = "ينبهك كل خميس عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 9)
        {
            repeat = "ينبهك كل جمعة عند الساعة " + time;
        }
            else if (Integer.parseInt(repeat) == 10)
        {
            repeat = "ينبهك كل سبت عند الساعة " + time;
        }

        return repeat;
    }



}
