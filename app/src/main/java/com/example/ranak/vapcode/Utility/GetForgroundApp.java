package com.example.ranak.vapcode.Utility;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by ranak on 18/10/17.
 */

public class GetForgroundApp {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String forgroundAppAccordingFromUsageState(Context appContext){
        String packageNameByUsageStats = null;
        String classByUsageStats = null;
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
        return packageNameByUsageStats;
    }
}
