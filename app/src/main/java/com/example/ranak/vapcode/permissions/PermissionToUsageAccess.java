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
import android.util.Log;
import android.view.WindowManager;

import java.util.List;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.app.AppOpsManager.OPSTR_GET_USAGE_STATS;



public class PermissionToUsageAccess {

    /**
     * check is this application (v.a.p code) has permission to get usage access
     * @param appContext to use system api
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean hasPermissionTocheckAppData(Context appContext){

        AppOpsManager appOps = (AppOpsManager) appContext.getSystemService(Context.APP_OPS_SERVICE);
        int mood = appOps.checkOp(OPSTR_GET_USAGE_STATS,android.os.Process.myUid(),appContext.getPackageName());
        //Log.d("appContextNoProblem",mood+"");
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
