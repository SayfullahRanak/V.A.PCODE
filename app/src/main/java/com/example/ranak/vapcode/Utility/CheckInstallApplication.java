package com.example.ranak.vapcode.Utility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import com.example.ranak.vapcode.Data.*;

/**
 * Created by ranak on 29/9/17.
 */

public class CheckInstallApplication {


    /**
     *
     * @param appContext
     * @return List(type : com.example.ranak.vapcode.Data.InstalledAppNameIcon) of installed application (names & icons)
     *
     */
    public static List<InstalledAppNameIcon> getInstalledApplication(Context appContext){


        List<InstalledAppNameIcon> installedApplication = new ArrayList<>();
        String appName;
        Drawable appicon;
        float Id ;

        List<PackageInfo> packs = appContext.getPackageManager().getInstalledPackages(0);
        PackageManager pm = appContext.getPackageManager();
        for(int i=0;i<packs.size();i++){


            PackageInfo packageInfo = packs.get(i);
            if(pm.getLaunchIntentForPackage(packageInfo.packageName)!=null){

                appName = packageInfo.applicationInfo.loadLabel(appContext.getPackageManager()).toString();
                appicon = packageInfo.applicationInfo.loadIcon(appContext.getPackageManager());

                installedApplication.add(new InstalledAppNameIcon(appName,appicon));

            }
        }

        return  installedApplication;
    }
}
