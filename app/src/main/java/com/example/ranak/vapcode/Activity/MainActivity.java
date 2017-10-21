package com.example.ranak.vapcode.Activity;

import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.ranak.vapcode.Data.ConstantVariables;
import com.example.ranak.vapcode.Ui.listofapplication.Fragment_ListOfAppToLock;
import com.example.ranak.vapcode.R;
import com.example.ranak.vapcode.Ui.settings.Fragment_Settings;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String ApplicationMode=null;
    private boolean doubleBackToExitPressedOnce=false;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
          R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.setDrawerListener(toggle);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_lockapp);


        SharedPreferences AppCurrentMode = getSharedPreferences(ConstantVariables.APPLICATION_MOOD_SHARED_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = AppCurrentMode.edit();

        ApplicationMode = AppCurrentMode.getString(ConstantVariables.APPLICATION_MOOD_KEY_SH,null);

        if(ApplicationMode==null ){  //|| ApplicationMode.matches(ConstantVariables.APPSTATUSFIRSTTIME)
            this.ApplicationMode=ConstantVariables.APPSTATUSFIRSTTIME;
            editor.putString(ConstantVariables.APPLICATION_MOOD_KEY_SH,ConstantVariables.APPSTATUSFIRSTTIME);
        }else {
            this.ApplicationMode=ConstantVariables.APPSTATUSCONSECUTIVE;
            editor.putString(ConstantVariables.APPLICATION_MOOD_KEY_SH,ConstantVariables.APPSTATUSCONSECUTIVE);
        }
        editor.commit();

        Bundle bundle= new Bundle();
        bundle.putCharSequence(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,ApplicationMode);


        Fragment_ListOfAppToLock fragment = new Fragment_ListOfAppToLock();
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.content_main,fragment,ConstantVariables.LIST_OF_APP_FRAGMENT_TAG);

        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(ismainfracmentislive()){
                super.onBackPressed();
            }else {
                goToMainFragment();
            }
//            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String FragmentTag=null;
        Fragment fragment=null;

        if (id == R.id.nav_settings) {

            fragment = new Fragment_Settings();
            FragmentTag=ConstantVariables.SETTINGS_FRAGMENT_TAG;


        } else if (id == R.id.nav_lockapp) {

            fragment = new Fragment_ListOfAppToLock();
            FragmentTag=ConstantVariables.LIST_OF_APP_FRAGMENT_TAG;


        } else if (id == R.id.nav_privacy) {



        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }
        else if (id == R.id.nav_about) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.d("ENtered"," in lockapp");
        drawer.closeDrawer(GravityCompat.START);
        if(fragment!=null){

            Bundle bundle= new Bundle();
            bundle.putCharSequence(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,ApplicationMode);
            fragment.setArguments(bundle);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_main,fragment,FragmentTag);
            transaction.commit();
        }


        return true;
    }

    private boolean ismainfracmentislive(){
        Fragment fragment = getFragmentManager().findFragmentByTag(ConstantVariables.LIST_OF_APP_FRAGMENT_TAG);

            if(fragment!=null && fragment.isVisible()){
                return true;
            }else {
                return false;
            }

    }

    private boolean goToMainFragment(){
        Fragment fragment = new Fragment_ListOfAppToLock();
        String FragmentTag = ConstantVariables.LIST_OF_APP_FRAGMENT_TAG;
        Bundle bundle= new Bundle();
        bundle.putCharSequence(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,ApplicationMode);
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_main,fragment,FragmentTag);
        transaction.commit();
        navigationView.setCheckedItem(R.id.nav_lockapp);
        return true;
    }



}
