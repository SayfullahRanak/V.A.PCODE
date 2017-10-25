package com.example.ranak.vapcode.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranak.vapcode.Data.InstalledAppNameIconCheckbox;
import com.example.ranak.vapcode.R;

import java.util.List;

/**
 * Created by ranak on 2/10/17.
 */

/**
 *  ************Properties (Activities)***************
 *  Set adapter for The  List View of "Lock App" fragment
 *  Maintaining the check box selection state
    * If checkbox is selected , and the list view scrolled up or down, some logic and written in this
    * class to make those checkbox kept selected
    *
 * This class needs the application context and list of App which will get appeared in the  listview
 */

public class AdapterForListOfApplication extends ArrayAdapter<InstalledAppNameIconCheckbox>{

    private List<InstalledAppNameIconCheckbox> appList;
    private Context appContext;

    /**
     *
     * @param context (Application context)
     * @param list  (List of app which will get appeared in the listview)
     */
    public AdapterForListOfApplication(Context context, List<InstalledAppNameIconCheckbox> list) {
        super(context, R.layout.customrow_listofapp,list);
        this.appList =list;
        this.appContext = context;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        /**
         * Everytime when The listView is get Scrolled down or up, it comes up with a new view, that means if you check a
                *unchecked checkbox box of a row  then scroll down in a way that the row is not visible , then you again come to
                * that row by scrolling up, the checkbox will again became unchceked. (These issue can
                * be happen vise varsa also ***uncheck to check*** ***scroll up to scroll down*** ) beacuse in listview everytime
                * every row get visible with a new view object(row get newly created)
         * To Solve these issue , we have to take the view object by setTag, so that , if the row get visible again after scrolling down
            * or up, we will get that row object by getTag, and show this object in the view.
         */

        ViewHolder holder;
        //When the row of listview appear first time
        if(convertView==null) {

            LayoutInflater inflator = LayoutInflater.from(appContext);
            convertView = inflator.inflate(R.layout.customrow_listofapp, null);
            //The holder object will hold the objects of the row component (image, name, checkboxstatus)
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.customrowText);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.customrowCheckbox);
            holder.imgeIcon = (ImageView) convertView.findViewById(R.id.customrowImage);
            //Attach this holder object with the view object, so that next time we can get the object next time
            convertView.setTag(holder);
            //when the checkbox status changes, get the status(checked/unchecked) which was set previously by the Tag.
            holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();
                    // set the status according to which status (checked/unchecked) was set last time
                    appList.get(getPosition).setCheckboxStatus(buttonView.isChecked());
                }
            });
        }
        //When the row of listview appear again
        else {

            holder = (ViewHolder) convertView.getTag();
        }
        //setting a tag(position) of the checkbox, so that next time we get the status of this checkbox
        holder.checkbox.setTag(position); // This line is important.

        holder.text.setTag(appList.get(position).getAppName());

        holder.text.setText(appList.get(position).getAppName());
        holder.checkbox.setChecked(appList.get(position).getCheckboxStatus());
        holder.imgeIcon.setImageDrawable(appList.get(position).getIcon());

        // This tag is for "com.example.ranak.vapcode.Ui.listofapplication" class where we need to identify a row
            //when the row is clicked
        convertView.setTag(R.id.customrowlistofapp,appList.get(position).getPackageName());
        return convertView;

    }

    /**
     * holds the textview, imgeIcon, checkbox for future
     */
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
