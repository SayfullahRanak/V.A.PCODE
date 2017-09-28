package com.example.ranak.vapcode.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranak on 29/9/17.
 */

public class CheckInstallApplication {

    public static List<String> getInstalledApplication(Context appContext){
        List<String> installedApplication = new ArrayList<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<PackageInfo> packs = appContext.getPackageManager().getInstalledPackages(0);
        //PackageInfo pac = packs.get(0);
        //PackageManager pm = appContext.getPackageManager();


        int i=0;
        for( PackageInfo pk : packs ){
            PackageInfo pac = packs.get(i);
            String x = pac.applicationInfo.loadLabel(appContext.getPackageManager()).toString();
            Log.d("test",x);
            i++;
        }

        return  installedApplication;
    }
}
