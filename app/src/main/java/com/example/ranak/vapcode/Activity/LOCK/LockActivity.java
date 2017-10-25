package com.example.ranak.vapcode.Activity.LOCK;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Ui.vap.Fragment_VAPCODE;
import com.example.ranak.vapcode.Utility.StartServiceForCheckingCurrentApp;

import java.util.ArrayList;
import java.util.List;


public class LockActivity extends AppCompatActivity implements Fragment_VAPCODE.OnFragmentInteractionListener{

    private boolean passwordGivenFirstTime=false;
    private String AppMode;
    private static boolean RegistrationPhaseOne=true;
    private static List<String> StoredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        this.AppMode = intent.getStringExtra(ConstantVariables.APPLICATION_MOOD_KEY_INTENT);
        setContentView(R.layout.activity_lock);

        Fragment fragment = new Fragment_VAPCODE();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_lock,fragment, ConstantVariables.LOCKFRAGMENTTAG);
        ft.commit();


    }

    @Override
    public void onVAPCODEFragmentInteraction(List<String> passwordList,View parentView) {
        ActivitiesOfPasswordWordLock(passwordList,parentView);
    }

    private void ActivitiesOfPasswordWordLock(List<String> passwordList,View parentView){

        TextView textView = (TextView)this.findViewById(R.id.patternstatus);

        if(this.AppMode.equals(ConstantVariables.APP_STATUS_AUTHENTICATE)){

            List<String> accessPassword = new ArrayList<>();

            SharedPreferences SPAuthenticationstatus = getSharedPreferences(ConstantVariables.FINAL_PASSWORD_SHARED_PREF,MODE_PRIVATE);
            int accessPasswordSize = SPAuthenticationstatus.getInt(ConstantVariables.FINAL_PASSWORD_SIZE_KEY_SH,0);

            for(int i=0;i<accessPasswordSize;i++){
                accessPassword.add(SPAuthenticationstatus.getString(ConstantVariables.FINAL_PASSWORD_KEY_SH+i,null));
            }

            if(this.matchPassword(accessPassword,passwordList)){

                SharedPreferences.Editor editor = SPAuthenticationstatus.edit();
                editor.putBoolean(ConstantVariables.FINAL_PASSWORD_IsAuth_KEY_SH,true);
                editor.commit();
                //Matched
//                Log.d("Authentication state",SPAuthenticationstatus.getBoolean(ConstantVariables.FINAL_PASSWORD_IsAuthenticated_KEY_SH,false)+"");
                finish();
            }
            else {
                textView.setText("Incorrect, try again");
            }

        }else{
            if(this.RegistrationPhaseOne){

                textView.setText("Confirm again");

                this.StoredPassword=passwordList;
                this.RegistrationPhaseOne=false;

            }else{

                if(this.matchPassword(this.StoredPassword,passwordList)){

                    this.RegistrationPhaseOne=true;
                    textView.setText("Password matched");

                    FinalizePassword(passwordList);
                    StartServiceForCheckingCurrentApp.SartServiceForCheckingForgroundAppAndInitializingListener(this,this);

                }else {

                    textView.setText("Password didn't match");
                }
            }

        }
    }

    private boolean matchPassword(List<String> leftSide, List<String> rightSide) {

        for (int i = 0; i < rightSide.size(); i++) {

            if (!rightSide.get(i).equals(leftSide.get(i))) {
                Log.d("new one",leftSide.get(i));
                Log.d("Old one",rightSide.get(i));
                return false;
            }
        }
        return true;
    }

    private boolean FinalizePassword(List<String> FinalPassword) {

        SharedPreferences SPcheckBoxstatus = getSharedPreferences(ConstantVariables.FINAL_PASSWORD_SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = SPcheckBoxstatus.edit();
        editor.putInt(ConstantVariables.FINAL_PASSWORD_SIZE_KEY_SH,FinalPassword.size());
        for(int i=0;i<FinalPassword.size();i++){
            editor.putString(ConstantVariables.FINAL_PASSWORD_KEY_SH+i,FinalPassword.get(i));
        }
        editor.commit();

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!AppMode.matches(ConstantVariables.APP_STATUS_AUTHENTICATE)){
            super.onBackPressed();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
