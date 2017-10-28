package com.example.ranak.vapcode.Ui.settings;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ranak.vapcode.Activity.LOCK.LockActivity;
import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.R;

/**
 * Created by ranak on 4/10/17.
 */

public class Settings {

    protected static boolean settingUpForSettings(View mview, final Context appContext){
        ListView list = (ListView) mview.findViewById(R.id.Settings_z);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("ENtered in the"," list");

                if(position==1){
                    Intent passwordIntend = new Intent();

                    passwordIntend.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    passwordIntend.setClass(appContext, LockActivity.class);
                    passwordIntend.putExtra(ConstantVariables.APPLICATION_MOOD_KEY_INTENT,ConstantVariables.FINAL_PASSWORD_RESET_PASSWORD);
                    appContext.startActivity(passwordIntend);
                }

            }
        });

        return true;
    }
}
