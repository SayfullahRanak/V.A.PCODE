package com.example.ranak.vapcode.Data;

import android.graphics.drawable.Drawable;

/**
 * Created by ranak on 29/9/17.
 * cluster for installed applications name and icons
 */

public class InstalledAppNameIcon {
    private static String appName;
    private static Drawable appIcone;

    public InstalledAppNameIcon(String name,Drawable icone){
        this.appIcone=icone;
        this.appName =name;
    }

    protected static String getAppName(){
        return appName;
    }

    protected static Drawable getIcon(){
        return appIcone;
    }
}
