package com.example.ranak.vapcode.Service;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Timer;
import java.util.TimerTask;

public class CheckInstalledAppAndLaunchPassword extends Service {

    private String RunningApp="";
    /*public CheckInstalledAppAndLaunchPassword() {
    }*/


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {

//        RunCheckingWithASeparatedThread();
        RunChecking();
        super.onCreate();

    }

    private boolean RunChecking(){
        final ReceiversProperties receiversProperties  = new ReceiversProperties(getApplicationContext());
        receiversProperties.InitializeReceiver();


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                //your code
//                Log.d("Accessed in the : ","Timer");
                if(PermissionToUsageAccess.hasPermissionTocheckAppData(getApplicationContext())) {
                    String CurrentApp = GetForgroundApp.forgroundAppAccordingFromUsageState(getApplicationContext());
                    SharedPreferences SPcheckBoxstatus = getApplicationContext().getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE, getApplicationContext().MODE_PRIVATE);
                    boolean checkboxstatus = SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS + CurrentApp, false);


                    SharedPreferences SPCorrectpasswordState = getApplicationContext().getSharedPreferences(ConstantVariables.FINAL_PASSWORD_SHARED_PREF, getApplicationContext().MODE_PRIVATE);
                    boolean IsPasswordCorrectlyGiven = SPCorrectpasswordState.getBoolean(ConstantVariables.FINAL_PASSWORD_IsAuth_KEY_SH, false);
//                    Log.d("is auth state",IsPasswordCorrectlyGiven+"");
                    if(CurrentApp!=null){

                        if (checkboxstatus && !IsPasswordCorrectlyGiven) {
//                            Log.d("Trigerring receiver : ", "vapcode");
                            receiversProperties.SendingBroadCast();

                        }else{
                            if(!CurrentApp.matches(getPackageName()) && !checkboxstatus){

//                                Log.d("Trigerring receiver : ",CurrentApp+"");
                                SharedPreferences.Editor editor = SPCorrectpasswordState.edit();
                                editor.putBoolean(ConstantVariables.FINAL_PASSWORD_IsAuth_KEY_SH, false);
                                editor.commit();

                            }
                        }
                    }

                }

            }
        };
    timer.schedule(timerTask, 0, 500);

    return true;
    }

    private void SetruningApp(String app){
        this.RunningApp=app;
    }

    private String getruningApp(){
        return this.RunningApp;
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

    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean RunCheckingWithASeparatedThread(){

        final ReceiversProperties receiversProperties  = new ReceiversProperties(getApplicationContext());
        receiversProperties.InitializeReceiver();

        final SharedPreferences SPcheckBoxstatus = this.getApplicationContext().getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE,this.getApplicationContext().MODE_PRIVATE);
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
                        Log.d("Current App",CurrentApp+"");

                        SharedPreferences SPAuthenticationstatus = getSharedPreferences(ConstantVariables.FINAL_PASSWORD_SHARED_PREF,MODE_PRIVATE);
                        boolean IsPassWordCorrectlyGiven = SPAuthenticationstatus.getBoolean(ConstantVariables.FINAL_PASSWORD_IsAuthenticated_KEY_SH,false);
//                        Log.d("IsPasswordCorrect : ",IsPassWordCorrectlyGiven+"");

                        if(CurrentApp!=null){
                            if(pickData){

                                boolean checkboxstatus =SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS+CurrentApp,false);

                                if(checkboxstatus){
                                    Log.d("Found my app",CurrentApp);
                                    RunningApp=CurrentApp;
                                    pickData = false;
                                    receiversProperties.SendingBroadCast();

                                }
                            }
                            if(!RunningApp.matches(CurrentApp) && !pickData && !IsPassWordCorrectlyGiven){

                                pickData=true;
                                SharedPreferences.Editor editor = SPAuthenticationstatus.edit();
                                editor.putBoolean(ConstantVariables.FINAL_PASSWORD_IsAuthenticated_KEY_SH,false);
                                editor.commit();


                            }

                        }

                    }
                }

            }
        };
        cheeckforApp.start();

        return true;
    }*/
}





