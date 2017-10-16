package com.example.ranak.vapcode.Ui.vap;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranak.vapcode.R;

import java.util.List;


public class Fragment_VAPCODE extends android.app.Fragment implements VAPCODE.mainFragmentListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onVAPCODEFragmentInteraction(List<String > passwordList,View parentView);
    }

    private List<String> passList;

    private Context mContext;
    View mview;
    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_vapcode, container, false);

        VAPCODE.SetUpPasswordAreaGettingResult(mview,this,mContext);

        return mview;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void getPassword(List<String> password) {
        this.passList = password;
        mListener.onVAPCODEFragmentInteraction(password,mview);
//        TextView txt = mview.findViewById(R.id.)

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




}