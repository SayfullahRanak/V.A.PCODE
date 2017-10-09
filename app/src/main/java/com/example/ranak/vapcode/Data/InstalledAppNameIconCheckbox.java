package com.example.ranak.vapcode.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

/**
 * Created by ranak on 29/9/17.
 * cluster for installed applications name and icons
 */

public class InstalledAppNameIconCheckbox {
    private String appName;
    private Drawable appIcone;
    private boolean checkBoxStatus;

    /*public  String appName;
    public  Drawable appIcone;*/

    public InstalledAppNameIconCheckbox(String name, Drawable icone, boolean checkStatus){

        this.appIcone=icone;
        this.appName =name;
        this.checkBoxStatus=checkStatus;
    }

    public  String getAppName(){
        return appName;
    }

    public  Drawable getIcon(){
        return appIcone;
    }

    public  boolean getCheckboxStatus(){
        return checkBoxStatus;
    }

    public  void setCheckboxStatus( boolean status){ this.checkBoxStatus =status ; }
}
