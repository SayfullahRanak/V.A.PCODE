package com.example.ranak.vapcode.Ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ranak.vapcode.Adapter.AdapterForListOfApplication;
import com.example.ranak.vapcode.Data.InstalledAppNameIcon;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Utility.CheckInstallApplication;

import java.util.List;

/**
 * Created by ranak on 29/9/17.
 */

public class Fragment_ListOfAppToLock extends Fragment {
    private Context mainActivityContext;
    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        view=inflater.inflate(R.layout.fragment_listofapptolock,container,false );

        settingUpForEachViews(view,mainActivityContext);

        return view;
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        this.mainActivityContext=context;
    }

    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        this.mainActivityContext=activity.getApplicationContext();
    }

    public static boolean setLayoutVisibity(int id){

        setAllVisibilityGone();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(id);
        linearLayout.setVisibility(View.VISIBLE);
        return true;
    }

    private static boolean setAllVisibilityGone(){
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.Setting_mainlayout);
        linearLayout.setVisibility(View.GONE);
        linearLayout = (LinearLayout) view.findViewById(R.id.listofapp_mainlayout);
        linearLayout.setVisibility(View.GONE);
        linearLayout = (LinearLayout) view.findViewById(R.id.Help_mainlayout);
        linearLayout.setVisibility(View.GONE);
        linearLayout = (LinearLayout) view.findViewById(R.id.privacy_policy_mainlayout);
        linearLayout.setVisibility(View.GONE);
        return true;
    }

    private boolean settingUpForEachViews(View mview,Context mContext){

        ListOfApp.settingUpForLockApp(mview,mContext);
        Settings.settingUpForSettings(mview);
        Help.settingUpForHelp(mview);
        PrivacyPolicy.settingUpForPrivacyPolicy(mview);
        return true;
    }


}
