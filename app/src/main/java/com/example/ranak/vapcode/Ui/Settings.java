package com.example.ranak.vapcode.Ui;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ranak.vapcode.R;

/**
 * Created by ranak on 4/10/17.
 */

public class Settings {

    protected static boolean settingUpForSettings(View mview){
        ListView list = (ListView) mview.findViewById(R.id.Settings_z);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return true;
    }
}
