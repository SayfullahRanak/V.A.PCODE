package com.example.ranak.vapcode.Service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.Utility.GetForgroundApp;
import com.example.ranak.vapcode.Utility.ReceiversProperties;
import com.example.ranak.vapcode.permissions.PermissionToUsageAccess;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This background service fire the "v.a.p code password activity (LockActivity)"if the running application is one of
    * the selected applications by user
 */

public class CheckInstalledAppAndLaunchPassword extends Service {

//    private String RunningApp="";


    /**
     * Background Service starts from here
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        RunChecking();
        super.onCreate();
    }

    /**
     * with the help of a timer after each 500 msec , the code checks whether the current running Application is
        * one of the selected applications or not?
        * If yes, the receiver is called from where the v.a.p code(Lock App) is triggered
     * @return
     */
    private boolean RunChecking(){
        final ReceiversProperties receiversProperties  = new ReceiversProperties(getApplicationContext());
        receiversProperties.InitializeReceiver();


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                if(PermissionToUsageAccess.hasPermissionTocheckAppData(getApplicationContext())) {
                    // Current Apllication on the foreground
                    String CurrentApp = GetForgroundApp.forgroundAppAccordingFromUsageState(getApplicationContext());

                    // Check in the listview in the row the applications is checked or not
                    SharedPreferences SPcheckBoxstatus = getApplicationContext().getSharedPreferences(ConstantVariables.CHECKBOXSTATUSPREFERENCE, getApplicationContext().MODE_PRIVATE);

                    // if checkboxstatus is true then current application is on of the selected app, if false then not
                    boolean checkboxstatus = SPcheckBoxstatus.getBoolean(ConstantVariables.EACHCHECKBOXSTATUS + CurrentApp, false);

                    //checking if users correctly given password ("IsPasswordCorrectlyGiven") state
                    SharedPreferences SPCorrectpasswordState = getApplicationContext().getSharedPreferences(ConstantVariables.FINAL_PASSWORD_SHARED_PREF, getApplicationContext().MODE_PRIVATE);

                    boolean IsPasswordCorrectlyGiven = SPCorrectpasswordState.getBoolean(ConstantVariables.FINAL_PASSWORD_IsAuth_KEY_SH, false);

                    /**
                     * CurrentApp will go to null position if any application is opened for a long time
                     */
                    if(CurrentApp!=null){
                        //if The app is selected by user and "IsPasswordCorrectlyGiven" state is false
                        if (checkboxstatus && !IsPasswordCorrectlyGiven) {
                            receiversProperties.SendingBroadCast();
                            //In "receiversProperties.SendingBroadCast()" "IsPasswordCorrectlyGiven"
                            // state will became true if password is correctly given

                        }else{
                            //if current app is not v.a.p code and the app is not selected by user,
                            // then make the IsPasswordCorrectlyGiven false
                            if(!CurrentApp.matches(getPackageName()) && !checkboxstatus){

                                SharedPreferences.Editor editor = SPCorrectpasswordState.edit();
                                editor.putBoolean(ConstantVariables.FINAL_PASSWORD_IsAuth_KEY_SH, false);
                                editor.commit();

                            }
                        }
                    }

                }

            }
        };
    timer.schedule(timerTask, 0, 100);

    return true;
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

    /*private void SetruningApp(String app){ this.RunningApp=app; }

    private String getruningApp(){ return this.RunningApp; }*/


















































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





