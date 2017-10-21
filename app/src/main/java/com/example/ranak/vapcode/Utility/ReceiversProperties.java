package com.example.ranak.vapcode.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.ranak.vapcode.Receiver.ReceiverToTriggerPassword;

/**
 * Created by ranak on 20/10/17.
 */

public class ReceiversProperties {
    Context appContext;
    BroadcastReceiver receiver;
    public ReceiversProperties(Context contx){
        this.appContext=contx;
        receiver = new ReceiverToTriggerPassword();
    }
    public boolean  InitializeReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("my.custom.action.tag.vapcode");
        appContext.registerReceiver(receiver,intentFilter);
        return true;
    }

    public boolean  SendingBroadCast(){
        Intent intent = new Intent();
        intent.setAction("my.custom.action.tag.vapcode");
        appContext.sendBroadcast(intent);
        return true;
    }

    public boolean  UnRegisteringBroadCast(){
        appContext.unregisterReceiver(receiver);
        return true;
    }



}
