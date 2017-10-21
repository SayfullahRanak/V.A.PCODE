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
 */

public class PermissionToUsageAccess {

    public static boolean getusageaccess(Context appContext, final Activity activity){

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            @SuppressWarnings("WrongConstant")
            UsageStatsManager usm = (UsageStatsManager) appContext.getSystemService("usagestats");
            long time = System.currentTimeMillis();
            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                    time - 1000 * 1000, time);
            if(appList.size()==0){


                AlertDialog alertDialog = new AlertDialog.Builder(appContext).setTitle("Usage access")
                        .setMessage("App will not run without usage access permissions.")
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivityForResult(intent,0);

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert).create();

                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
                alertDialog.show();

                //alertDialog.create();

            }
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean hasPermissionTocheckAppData(Context appContext){

        AppOpsManager appOps = (AppOpsManager) appContext.getSystemService(Context.APP_OPS_SERVICE);
        int mood = appOps.checkOp(OPSTR_GET_USAGE_STATS,android.os.Process.myUid(),appContext.getPackageName());

        return (mood == MODE_ALLOWED);

    }
}
