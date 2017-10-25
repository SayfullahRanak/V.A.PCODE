package com.example.ranak.vapcode.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

/**
 *
 * Created by ranak on 29/9/17.
 * cluster for installed applications name and icons
 * This class store the
 * application name
 * Drawable object the image
 * Package name of the application
 *
 */

public class InstalledAppNameIcon {
    private String appName;
    private Drawable appIcone;
    private String packageName;


    public InstalledAppNameIcon(String name, Drawable icone , String pakgnm){

        this.appIcone=icone;
        this.appName =name;
        this.packageName=pakgnm;
    }

    public  String getAppName(){
        return appName;
    }

    public  String getPackageName(){
        return packageName;
    }

    public  Drawable getIcon(){
        return appIcone;
    }


}
