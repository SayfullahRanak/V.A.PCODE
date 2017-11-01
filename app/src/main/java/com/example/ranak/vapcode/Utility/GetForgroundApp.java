package com.example.ranak.vapcode.Utility;

import android.app.ActivityManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by ranak on 18/10/17.
 */

public class GetForgroundApp {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String forgroundAppAccordingFromUsageState(Context appContext){
        String packageNameByUsageStats = null;
        String classByUsageStats = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            UsageStatsManager mUsageStatsManager = (UsageStatsManager)appContext.getSystemService(Context.USAGE_STATS_SERVICE);
            final long INTERVAL = 10000;
            final long end = System.currentTimeMillis();
            final long begin = end - INTERVAL;
            final UsageEvents usageEvents = mUsageStatsManager.queryEvents(begin, end);
            while (usageEvents.hasNextEvent()){
                UsageEvents.Event event = new UsageEvents.Event();
                usageEvents.getNextEvent(event);
                if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND){

                    packageNameByUsageStats = event.getPackageName();
                    classByUsageStats = event.getClassName();
//                Log.d("Forground app", "packageNameByUsageStats is" + packageNameByUsageStats + ", classByUsageStats is " + classByUsageStats);
                }
            }
        }else {
            ActivityManager am = (ActivityManager) appContext.getSystemService(ACTIVITY_SERVICE);
// The first in the list of RunningTasks is always the foreground task.
            ActivityManager.RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(1).get(0);
            String foregroundTaskPackageName = foregroundTaskInfo .topActivity.getPackageName();
            PackageManager pm = appContext.getPackageManager();
            PackageInfo foregroundAppPackageInfo = null;
            try {
                foregroundAppPackageInfo = pm.getPackageInfo(foregroundTaskPackageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            packageNameByUsageStats = foregroundAppPackageInfo.applicationInfo.loadLabel(pm).toString();
        }

        return packageNameByUsageStats;
    }
}
