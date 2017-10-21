package com.example.ranak.vapcode.Service;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.Utility.GetForgroundApp;
import com.example.ranak.vapcode.Utility.ReceiversProperties;
import com.example.ranak.vapcode.permissions.PermissionToUsageAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckInstalledAppAndLaunchPassword extends Service {

    String targetName="com.soundcloud.android";
    List<String> checkOnly = new ArrayList<>();

    public CheckInstalledAppAndLaunchPassword() {
    }


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {

        final ReceiversProperties receiversProperties  = new ReceiversProperties(getApplicationContext());
        receiversProperties.InitializeReceiver();

        checkOnly.add(targetName);
        checkOnly.add("com.lazada.android");
        checkOnly.add("com.google.android.music");
        final Context appContext = this;


            final Thread cheeckforApp = new Thread(){

                @Override
                public void run() {
                    super.run();
                    String RunningApp="";
                    boolean pickData=true;
                    while (true){

                        if(PermissionToUsageAccess.hasPermissionTocheckAppData(appContext)) {
                            String CurrentApp = GetForgroundApp.forgroundAppAccordingFromUsageState(appContext);
                            //Log.d("Current App",CurrentApp+"");


                            if(CurrentApp!=null){
                                /*Log.d("Current App",CurrentApp+"");
                                Log.d("Running App",RunningApp+"");*/

                                if(pickData){

                                    if(checkOnly.contains(CurrentApp)){
                                        RunningApp=CurrentApp;
                                        pickData = false;
                                        receiversProperties.SendingBroadCast();
                                        Log.d("Found my app",CurrentApp);
                                    }
                                }

                                if(!RunningApp.matches(CurrentApp) && !pickData){
                                    pickData=true;
                                }

                            }

                        }
                    }

                }
            };
            cheeckforApp.start();

        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        final ReceiversProperties receiversProperties  = new ReceiversProperties(this);
        receiversProperties.UnRegisteringBroadCast();
    }
}





