package com.example.ranak.vapcode.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranak.vapcode.Data.InstalledAppNameIcon;
import com.example.ranak.vapcode.Data.InstalledAppNameIconCheckbox;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Ui.ListOfApp;

import java.util.List;

/**
 * Created by ranak on 2/10/17.
 */

public class AdapterForListOfApplication extends ArrayAdapter<InstalledAppNameIconCheckbox>{
    //Context baseContext;
    private List<InstalledAppNameIconCheckbox> appList;
    private Context appContext;

    public AdapterForListOfApplication(Context context, List<InstalledAppNameIconCheckbox> list) {
        super(context, R.layout.customrow_listofapp,list);
        this.appList =list;
        this.appContext = context;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){


        /*LayoutInflater inflator = LayoutInflater.from(appContext);
        convertView = inflator.inflate(R.layout.customrow_listofapp, null);
        TextView text = (TextView) convertView.findViewById(R.id.customrowText);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.customrowCheckbox);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.customrowImage);
        text.setText(getItem(position).getAppName());
        imageView.setImageDrawable(getItem(position).getIcon());
        checkBox.setChecked(getItem(position).getCheckboxStatus());*/


        ViewHolder holder;
        if(convertView==null) {

            LayoutInflater inflator = LayoutInflater.from(appContext);
            convertView = inflator.inflate(R.layout.customrow_listofapp, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.customrowText);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.customrowCheckbox);
            holder.imgeIcon = (ImageView) convertView.findViewById(R.id.customrowImage);
            convertView.setTag(holder);
            convertView.setTag(R.id.customrowText, holder.text);
            convertView.setTag(R.id.customrowCheckbox, holder.checkbox);
            convertView.setTag(R.id.customrowImage, holder.imgeIcon);
            holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();
                    appList.get(getPosition).setCheckboxStatus(buttonView.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkbox.setTag(position); // This line is important.

        holder.text.setText(appList.get(position).getAppName());
        holder.checkbox.setChecked(appList.get(position).getCheckboxStatus());
        holder.imgeIcon.setImageDrawable(appList.get(position).getIcon());




        return convertView;

    }

    static class ViewHolder{

        protected TextView text;
        protected ImageView imgeIcon;
        protected CheckBox checkbox;
    }

}










































/*
if(convertView==null) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.customrow_listofapp, parent, false);
//            convertView = inflater.inflate(R.layout.customrow_listofapp, parent, false);
        holder = new ViewHolder();
        holder.checkbox = (CheckBox)(CheckBox) convertView.findViewById(R.id.customrowCheckbox);
        holder.text = (TextView) convertView.findViewById(R.id.customrowText);
        holder.imgeIcon = (ImageView) convertView.findViewById(R.id.customrowImage);
//            final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.customrowCheckbox);
//            ImageView image = (ImageView) customView.findViewById(R.id.customrowImage);
//            TextView text = (TextView) customView.findViewById(R.id.customrowText);
        convertView.setTag(holder);
        convertView.setTag(R.id.customrowText,holder.text);
        convertView.setTag(R.id.customrowCheckbox,holder.checkbox);
        InstalledAppNameIconCheckbox item = getItem(position);

//            image.setImageDrawable(item.getIcon());
//            text.setText(item.getAppName());
//        if(convertView==null){
//            checkBox.setChecked(item.getCheckboxStatus());
        }else {
        holder = (ViewHolder)convertView.getTag();
        }
        holder.checkbox.setTag(position);
        holder.text.setText(appList.get(position).getAppName());
        holder.checkbox.setChecked(appList.get(position).getCheckboxStatus());

//customView.setTag(new ListViewHolder(checkBox));
//        }
        else{
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.customrowCheckbox);
            checkBox.setChecked(ListOfApp.getCheckBoxStatusAccordingToAppMoode(position,getContext()));
        }
*/
