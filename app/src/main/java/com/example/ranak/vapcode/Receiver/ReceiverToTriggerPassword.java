package com.example.ranak.vapcode.Receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ranak.vapcode.Activity.LOCK.LockActivity;
import com.example.ranak.vapcode.Data.ConstantVariables;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by ranak on 20/10/17.
 * This receiver class will launch the V.a.p Code Activity (LockActivity)
 */

public class ReceiverToTriggerPassword extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("Size of password",ConstantVariables.listOfApp.size()+"");
        Intent passwordIntend = new Intent();

        passwordIntend.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        passwordIntend.setClass(context, LockActivity.class);
        passwordIntend.putExtra(ConstantVariables.APPLICATION_MOOD_KEY_INTENT,ConstantVariables.APP_STATUS_AUTHENTICATE);


        context.getApplicationContext().startActivity(passwordIntend);

    }
}
