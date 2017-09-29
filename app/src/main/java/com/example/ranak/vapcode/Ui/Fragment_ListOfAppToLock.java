package com.example.ranak.vapcode.Ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranak.vapcode.R;

/**
 * Created by ranak on 29/9/17.
 */

public class Fragment_ListOfAppToLock extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.content_main,container,false );
        return view;
    }

}
