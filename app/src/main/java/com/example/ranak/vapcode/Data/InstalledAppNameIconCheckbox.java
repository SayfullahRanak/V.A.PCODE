package com.example.ranak.vapcode.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

/**
 * Created by ranak on 29/9/17.
 * cluster for installed applications name and icons
 * This class store the
 * application name
 * Drawable object the image
 * Package name of the application
 * This class also set the checkbox status of this object
 */

public class InstalledAppNameIconCheckbox {
    private String appName;
    private Drawable appIcone;
    private String packageName;
    private boolean checkBoxStatus;


    public InstalledAppNameIconCheckbox(String name, Drawable icone, boolean checkStatus,String PakgNam){

        this.appIcone=icone;
        this.appName =name;
        this.checkBoxStatus=checkStatus;
        this.packageName = PakgNam;
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

    public  boolean getCheckboxStatus(){
        return checkBoxStatus;
    }


    /**
     * set checkbox status of this object
     * @param status
     */
    public  void setCheckboxStatus( boolean status){ this.checkBoxStatus =status ; }
}
