package com.example.ranak.vapcode.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

import java.util.List;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.app.AppOpsManager.OPSTR_GET_USAGE_STATS;

/**
 * Created by ranak on 17/10/17.
 * This class get permission to read any applications usage state (when the application open, when closed .. etc..)
 * 2 types of codes will be written for permission, 1 for after LOLLIPOP version, 1 for before Lolipop version
 */

public class PermissionToUsageAccess {

    /**
     * here one dialog box will apear, which will take you Settings->usage access- > this application (V.A.P Code)
     * @param appContext-> to use some system api
     * @param activity -> needed to start the dialog box
     * @return true if has permission, false if don't
     */
    public static boolean getusageaccess(Context appContext, final Activity activity){

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){

            //Check if the this application (V.A.P CODE) has permission or not
            if(hasPermissionTocheckAppData(appContext)){

                // The dialog will give you two option
                // 1. settings -> if you want to go to Settings->usage access- > this application (V.A.P Code)
                // 2. cancel -> if you don't want to go
                AlertDialog alertDialog = new AlertDialog.Builder(appContext).setTitle("Usage access")
                        .setMessage("App will not run without usage access permissions.")
                        // If you press "Settings"
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // This intent s for setting up the path to Settings->usage access- > this application (V.A.P Code)
                                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                // This activity will take you to Settings->usage access- > this application (V.A.P Code)
                                activity.startActivityForResult(intent,0);

                            }
                        })
                        // If you press "Cancel"
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert).create();

                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);

                alertDialog.show();

            }
        }

        return true;
    }

    /**
     * check is this application (v.a.p code) has permission to get usage access
     * @param appContext to use system api
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean hasPermissionTocheckAppData(Context appContext){

        AppOpsManager appOps = (AppOpsManager) appContext.getSystemService(Context.APP_OPS_SERVICE);
        int mood = appOps.checkOp(OPSTR_GET_USAGE_STATS,android.os.Process.myUid(),appContext.getPackageName());
        //return true is mood = MODE_ALLOWED, false if not
        return (mood == MODE_ALLOWED);

    }
}





























/*@SuppressWarnings("WrongConstant")
    UsageStatsManager usm = (UsageStatsManager) appContext.getSystemService("usagestats");
    long time = System.currentTimeMillis();
    // 100 is for, it will get the usage state of last 100 seconds and store it in appList list
    List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
            time - 1000 * 1000, time);

    if(appList.size()==0){

            }*/
