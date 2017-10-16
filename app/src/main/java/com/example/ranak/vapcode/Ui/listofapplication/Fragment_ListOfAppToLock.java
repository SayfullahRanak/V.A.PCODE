package com.example.ranak.vapcode.Ui.listofapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.R;

/**
 * Created by ranak on 29/9/17.
 */

public class Fragment_ListOfAppToLock extends Fragment {
    private Context mainActivityContext;
    private static View view;
    private String applicationmode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        view=inflater.inflate(R.layout.fragment_listofapptolock,container,false );
        Bundle receivedbundle = getArguments();
        String ApplicationMode = receivedbundle.getString(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,null);
        ListOfApp.settingUpForLockApp(view,mainActivityContext,ApplicationMode);
        return view;
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        this.mainActivityContext=context;
//        mainActivityContext.
    }

    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        this.mainActivityContext=activity.getApplicationContext();
    }
}
