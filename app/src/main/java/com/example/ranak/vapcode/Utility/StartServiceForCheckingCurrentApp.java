package com.example.ranak.vapcode.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.WindowManager;

import com.example.ranak.vapcode.Service.CheckInstalledAppAndLaunchPassword;
import com.example.ranak.vapcode.permissions.PermissionToUsageAccess;

/**
 * Created by ranak on 19/10/17.
 */

public class StartServiceForCheckingCurrentApp {

    /**
     * here one dialog box will apear, which will take you Settings->usage access- > this application (V.A.P Code)
     * @param appContext-> to use some system api
     * @return true if has permission, false if don't
     */
    public static boolean SartServiceForCheckingForgroundAppAndInitializingListener(final Context appContext){ //, final Activity appActivity

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){

            if(!PermissionToUsageAccess.hasPermissionTocheckAppData(appContext)){
                AlertDialog alertDialog = new AlertDialog.Builder(appContext).setTitle("Usage access")
                        .setMessage("App will not run without usage access permissions.")
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                appContext.getApplicationContext().startActivity(intent);
                                StartServiceFinally(appContext); //appActivity,

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert).create();

                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
                alertDialog.show();
            }
        }
        else {

        }
        return true;
    }

    private static boolean StartServiceFinally(Context appContext){ //Activity appActivity,

        Intent intent = new Intent();
        intent.setClass(appContext, CheckInstalledAppAndLaunchPassword.class);
        appContext.getApplicationContext().startService(intent);
//        appActivity.startService(intent);
        return true;
    }

}
