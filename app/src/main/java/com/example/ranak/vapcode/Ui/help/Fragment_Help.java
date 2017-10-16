package com.example.ranak.vapcode.Ui.help;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranak.vapcode.R;


public class Fragment_Help extends android.app.Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    /*if (doubleBackToExitPressedOnce) {

//            super.onBackPressed();

    }



        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);*/

    /**
     * Created by ranak on 4/10/17.
     */

    public static class HELP {

        protected static boolean settingUpForHelp(View mview){
            return true;
        }
    }
}
