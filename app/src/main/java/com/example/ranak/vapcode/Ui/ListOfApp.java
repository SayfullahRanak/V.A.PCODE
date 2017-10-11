package com.example.ranak.vapcode.Ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ranak.vapcode.Activity.LOCK.LockActivity;
import com.example.ranak.vapcode.Adapter.AdapterForListOfApplication;
import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.Data.InstalledAppNameIcon;
import com.example.ranak.vapcode.Data.InstalledAppNameIconCheckbox;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Utility.CheckInstallApplication;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by ranak on 4/10/17.
 */

public class ListOfApp extends Activity {



    protected static boolean settingUpForLockApp(final View mview, final Context mContext){

        ButtonVisibility(mview,R.id.showallapp);

        ListView list = setListViewWithAdapter(mview,mContext,0);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                setCheckBoxActivity(check,position,mContext);
            }
        });



        Button allApp = (Button)mview.findViewById(R.id.showallapp);
        allApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ButtonVisibility(mview,R.id.showallapp);

                ListView list = setListViewWithAdapter(mview,mContext,R.id.showallapp);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                        setCheckBoxActivity(check,position,mContext);
                    }
                });

            }
        });


        Button lockedApp = (Button)mview.findViewById(R.id.showlockedallapp);
        lockedApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ListView list = setListViewWithAdapter(mview,mContext,R.id.showlockedallapp);
                if(list!=null){
                    ButtonVisibility(mview,R.id.showlockedallapp);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                            setCheckBoxActivity(check,position,mContext);
                        }
                    });
                }else{

                    Toast.makeText(mContext,"No Locked App",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button SetPassword = (Button)mview.findViewById(R.id.setPassword);
        SetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent passwordIntend = new Intent();
                passwordIntend.setFlags(FLAG_ACTIVITY_NEW_TASK);
                passwordIntend.setClass(mContext, LockActivity.class);
                ((Activity)mContext).startActivityForResult(passwordIntend,0);

                Log.d("Hello Hello Hello Hello", "yes yes yes yes");*/

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
            String uniqeId = insapp.getPackageName();
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
            String uniqeId = insapp.getPackageName();
            boolean checkboxstatus = getCheckBoxStatusAccordingToAppMoode(i,appContext);
            if(checkboxstatus) {
                InstalledAppNameIconCheckboxlist.add(new InstalledAppNameIconCheckbox(appname,appIcon,checkboxstatus));
            }

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

    @Nullable
    private static ListView setListViewWithAdapter(View view, Context appContext, int pressedViewId){

        List<InstalledAppNameIcon> installedAppList = CheckInstallApplication.getInstalledApplication(appContext);
        List<InstalledAppNameIconCheckbox> AppnameAppiconCheckboxstatuslist;
                if(pressedViewId==R.id.showlockedallapp){
                    AppnameAppiconCheckboxstatuslist = getListOfAppnameAppiconCheckboxstatusLockedapp(appContext,installedAppList);
                    if(AppnameAppiconCheckboxstatuslist.isEmpty()){
                        return null;
                    }
                }
                else {
                    AppnameAppiconCheckboxstatuslist = getListOfAppnameAppiconCheckboxstatus(appContext,installedAppList);
                }
                ListAdapter listAdapter = new AdapterForListOfApplication(appContext,AppnameAppiconCheckboxstatuslist);
                ListView list = (ListView)view.findViewById(R.id.listofapp);
                list.setAdapter(listAdapter);
                return list;
    }

    private static void setCheckBoxActivity(CheckBox chkbx,int position,Context appContext){
        if(chkbx.isChecked()){
            SetCheckBoxStatusAccordingToAppMoode(position,appContext,false);
            chkbx.setChecked(false);
        }
        else{
            SetCheckBoxStatusAccordingToAppMoode(position,appContext,true);
            chkbx.setChecked(true);
        }
    }



    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("Hello Hello Hello Hello", "yes yes yes yes");
        if(requestCode==0 && resultCode==RESULT_OK){

        }

    }*/
}
