package com.example.ranak.vapcode.Ui.listofapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
 * **** Properties (Activities) of the class*****
 * get installed application installed in the device
 * create a list view with those installed application (each row contains image view app name checkbox status)
 * create a shared reference where each rows status (checked or unchecked) will be updated
 * show list of only checked (selected) App and all App by pressing separate buttons (All Application / Locked Application) .
 *
 */

public class ListOfApp extends Activity {

    /**
     * set up list view for each installed applications
     * set up listener for pressing each row
     * set up activties for for showing list of only checked (selected) App and all
        * App by pressing separate buttons (All Application / Locked Application).
        * If once a button(All Application / Locked Application) is pressed that button will remain inactive until
        * next button is pressed
     *
     * @param mview view of each row
     * @param mContext application context
     * @param ApplicationMode
     * @return
     */
    protected static boolean settingUpForLockApp(final View mview, final Context mContext, final String ApplicationMode){
        //set the (Locked Application button) first time
        ButtonVisibility(mview,R.id.showallapp);

        ListView list = setListViewWithAdapter(mview,mContext,0);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String packageNameAsUnId = view.getTag(R.id.customrowlistofapp)+"";
                CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                setCheckBoxActivity(check,packageNameAsUnId,mContext);
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
                        String packageNameAsUnId = view.getTag(R.id.customrowlistofapp)+"";
                        CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                        setCheckBoxActivity(check,packageNameAsUnId,mContext);

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
                            String packageNameAsUnId = view.getTag(R.id.customrowlistofapp)+"";
                            CheckBox check = (CheckBox)view.findViewById(R.id.customrowCheckbox);
                            setCheckBoxActivity(check,packageNameAsUnId,mContext);
                        }
                    });
                }else{

                    Toast.makeText(mContext,"No Locked App",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return true;
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
//        ListOfApp.listOfnameAppiconfortest =AppnameAppiconCheckboxstatuslist;
        ListAdapter listAdapter = new AdapterForListOfApplication(appContext,AppnameAppiconCheckboxstatuslist);
        ListView list = (ListView)view.findViewById(R.id.listofapp);
        list.setAdapter(listAdapter);
        return list;
    }

    private static List<InstalledAppNameIconCheckbox> getListOfAppnameAppiconCheckboxstatus(Context appContext,List<InstalledAppNameIcon> installedAppList){

        List<InstalledAppNameIconCheckbox> InstalledAppNameIconCheckboxlist = new ArrayList<>();


        for(int i=0;i<installedAppList.size();i++){
            InstalledAppNameIcon insapp = installedAppList.get(i);
            String appname = insapp.getAppName();
            Drawable appIcon = insapp.getIcon();
            String uniqeId = insapp.getPackageName();
            Log.d("package names : ",uniqeId+"");
            boolean checkboxstatus = getCheckBoxStatusAccordingToAppMoode(uniqeId,appContext);
            InstalledAppNameIconCheckboxlist.add(new InstalledAppNameIconCheckbox(appname,appIcon,checkboxstatus,uniqeId));
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
            boolean checkboxstatus = getCheckBoxStatusAccordingToAppMoode(uniqeId,appContext);
            if(checkboxstatus) {
                InstalledAppNameIconCheckboxlist.add(new InstalledAppNameIconCheckbox(appname,appIcon,checkboxstatus,uniqeId));
            }

        }

        return InstalledAppNameIconCheckboxlist;
    }

    public static boolean getCheckBoxStatusAccordingToAppMoode(String unID,Context appContext){

        boolean checkboxstatus;
        String appMood;

        SharedPreferences SPcheckBoxstatus = appContext.getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE,appContext.MODE_PRIVATE);
//        SharedPreferences.Editor editor = SPcheckBoxstatus.edit();

        checkboxstatus =SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS+unID,false);
//        editor.commit();
        Log.d("check box","status : "+unID+" : "+checkboxstatus);

        return checkboxstatus;
    }


    private static void ButtonVisibility(View view,int buttontobeinvalidid ){

        Button allApp = (Button)view.findViewById(R.id.showallapp);
        allApp.setEnabled(true);
        Button lockedApp = (Button)view.findViewById(R.id.showlockedallapp);
        lockedApp.setEnabled(true);

        Button CurrentButton = (Button)view.findViewById(buttontobeinvalidid);
        CurrentButton.setEnabled(false);


    }

    private static void setCheckBoxActivity(CheckBox chkbx,String packageNameAsUniqId,Context appContext){


        if(chkbx.isChecked()){
            SetCheckBoxStatusAccordingToAppMoode(packageNameAsUniqId,appContext,false);
            chkbx.setChecked(false);
        }
        else{
            SetCheckBoxStatusAccordingToAppMoode(packageNameAsUniqId,appContext,true);
            chkbx.setChecked(true);
        }
    }

    private static boolean SetCheckBoxStatusAccordingToAppMoode(String unId,Context appContext,boolean status){

        SharedPreferences SPcheckBoxstatus = appContext.getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE,appContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = SPcheckBoxstatus.edit();
        editor.putBoolean(ConstantVariables.EACHCHECKBOXSTATUS+unId,status);
        editor.commit();

        Log.d("check box setup","status : "+unId+" : "+SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS+unId,false));
        return true;
    }

}
