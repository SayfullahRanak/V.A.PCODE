package com.example.ranak.vapcode.Ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranak.vapcode.R;

public class Fragment_Settings extends android.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview= inflater.inflate(R.layout.fragment_settings, container, false);
//        Settings.settingUpForSettings(mview);
        Log.d("inserted","settings settings");
        return mview;
    }

}
