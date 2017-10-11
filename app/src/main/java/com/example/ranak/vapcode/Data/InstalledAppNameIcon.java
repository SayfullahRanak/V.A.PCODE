package com.example.ranak.vapcode.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

/**
 * Created by ranak on 29/9/17.
 * cluster for installed applications name and icons
 */

public class InstalledAppNameIcon {
    private String appName;
    private Drawable appIcone;
    private String packageName;

    /*public  String appName;
    public  Drawable appIcone;*/

    public InstalledAppNameIcon(String name, Drawable icone){

        this.appIcone=icone;
        this.appName =name;
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
