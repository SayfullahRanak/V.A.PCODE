package com.example.ranak.vapcode.Ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ranak.vapcode.Adapter.AdapterForListOfApplication;
import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.Data.InstalledAppNameIcon;
import com.example.ranak.vapcode.Data.InstalledAppNameIconCheckbox;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Utility.CheckInstallApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranak on 4/10/17.
 */

public class ListOfApp extends AppCompatActivity {

    protected static boolean settingUpForLockApp(final View mview, final Context mContext){

        ButtonVisibility(mview,R.id.showallapp);
        List<InstalledAppNameIcon> installedAppList = CheckInstallApplication.getInstalledApplication(mContext);
        List<InstalledAppNameIconCheckbox> AppnameAppiconCheckboxstatuslist = getListOfAppnameAppiconCheckboxstatus(mContext,installedAppList);
        ListAdapter listAdapter = new AdapterForListOfApplication(mContext,AppnameAppiconCheckboxstatuslist);
        ListView list = (ListView)mview.findViewById(R.id.listofapp);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);

                if(check.isChecked()){
                    SetCheckBoxStatusAccordingToAppMoode(position,mContext,false);
                    check.setChecked(false);
                }
                else{
                    SetCheckBoxStatusAccordingToAppMoode(position,mContext,true);
                    check.setChecked(true);
                }
            }
        });



        Button allApp = (Button)mview.findViewById(R.id.showallapp);
        allApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ButtonVisibility(mview,R.id.showallapp);
                List<InstalledAppNameIcon> installedAppList = CheckInstallApplication.getInstalledApplication(mContext);
                List<InstalledAppNameIconCheckbox> AppnameAppiconCheckboxstatuslist = getListOfAppnameAppiconCheckboxstatus(mContext,installedAppList);
                ListAdapter listAdapter = new AdapterForListOfApplication(mContext,AppnameAppiconCheckboxstatuslist);
                ListView list = (ListView)mview.findViewById(R.id.listofapp);
                list.setAdapter(listAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);

                        if(check.isChecked()){
                            SetCheckBoxStatusAccordingToAppMoode(position,mContext,false);
                            check.setChecked(false);
                        }
                        else{
                            SetCheckBoxStatusAccordingToAppMoode(position,mContext,true);
                            check.setChecked(true);
                        }
                    }
                });


            }
        });


        Button lockedApp = (Button)mview.findViewById(R.id.showlockedallapp);
        lockedApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<InstalledAppNameIcon> installedAppList = CheckInstallApplication.getInstalledApplication(mContext);
                List<InstalledAppNameIconCheckbox> AppnameAppiconCheckboxstatuslist = getListOfAppnameAppiconCheckboxstatusLockedapp(mContext,installedAppList);
                if(AppnameAppiconCheckboxstatuslist.size()>0){
                    ButtonVisibility(mview,R.id.showlockedallapp);
                    ListAdapter listAdapter = new AdapterForListOfApplication(mContext,AppnameAppiconCheckboxstatuslist);
                    ListView list = (ListView)mview.findViewById(R.id.listofapp);

                    list.setAdapter(listAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);

                            if(check.isChecked()){
                                SetCheckBoxStatusAccordingToAppMoode(position,mContext,false);
                                check.setChecked(false);
                            }
                            else{
                                SetCheckBoxStatusAccordingToAppMoode(position,mContext,true);
                                check.setChecked(true);
                            }
                        }
                    });
                }else{

                    Toast.makeText(mContext,"No Locked App",Toast.LENGTH_SHORT).show();
                }



            }
        });

        return true;
    }

    private static List<InstalledAppNameIconCheckbox> getListOfAppnameAppiconCheckboxstatus(Context appContext,List<InstalledAppNameIcon> installedAppList){

        List<InstalledAppNameIconCheckbox> InstalledAppNameIconCheckboxlist = new ArrayList<>();


        for(int i=0;i<installedAppList.size();i++){
            InstalledAppNameIcon insapp = installedAppList.get(i);
            String appname = insapp.getAppName();
            Drawable appIcon = insapp.getIcon();
            boolean checkboxstatus = getCheckBoxStatusAccordingToAppMoode(i,appContext);
            InstalledAppNameIconCheckboxlist.add(new InstalledAppNameIconCheckbox(appname,appIcon,checkboxstatus));
        }

        return InstalledAppNameIconCheckboxlist;
    }


    private static List<InstalledAppNameIconCheckbox> getListOfAppnameAppiconCheckboxstatusLockedapp(Context appContext,List<InstalledAppNameIcon> installedAppList){

        List<InstalledAppNameIconCheckbox> InstalledAppNameIconCheckboxlist = new ArrayList<>();


        for(int i=0;i<installedAppList.size();i++){
            InstalledAppNameIcon insapp = installedAppList.get(i);
            String appname = insapp.getAppName();
            Drawable appIcon = insapp.getIcon();
            boolean checkboxstatus = getCheckBoxStatusAccordingToAppMoode(i,appContext);
            if(checkboxstatus) InstalledAppNameIconCheckboxlist.add(new InstalledAppNameIconCheckbox(appname,appIcon,checkboxstatus));

        }

        return InstalledAppNameIconCheckboxlist;
    }

    public static boolean getCheckBoxStatusAccordingToAppMoode(int pos,Context appContext){

        boolean checkboxstatus;
        String appMood;

        SharedPreferences SPcheckBoxstatus = appContext.getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE,appContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = SPcheckBoxstatus.edit();

        checkboxstatus =SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS+pos,false);
        editor.commit();
        Log.d("check box","status : "+pos+" : "+checkboxstatus);

        return checkboxstatus;
    }

    private static boolean SetCheckBoxStatusAccordingToAppMoode(int pos,Context appContext,boolean status){
        SharedPreferences SPcheckBoxstatus = appContext.getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE,appContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = SPcheckBoxstatus.edit();
        editor.putBoolean(ConstantVariables.EACHCHECKBOXSTATUS+pos,status);
        editor.commit();

        Log.d("check box setup","status : "+pos+" : "+SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS+pos,false));
        return true;
    }

    private static void ButtonVisibility(View view,int buttontobeinvalidid ){

        Button allApp = (Button)view.findViewById(R.id.showallapp);
        allApp.setEnabled(true);
        Button lockedApp = (Button)view.findViewById(R.id.showlockedallapp);
        lockedApp.setEnabled(true);

        Button CurrentButton = (Button)view.findViewById(buttontobeinvalidid);
        CurrentButton.setEnabled(false);


    }


}
