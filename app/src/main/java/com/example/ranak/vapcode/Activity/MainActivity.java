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

/**
 *MainActivity
 * @author Md Sayfullah Al Noman Ranak
 * @version 1.0
 * **********Description***************
 *
 *  THis is the MainActivity Where The Application Started
 *
 *  ************Properties (Activities)***************
 *  Handles the navigation Drawer
 *  Handles the appearance of each fragment when pressing each button of navigation drawer
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String ApplicationMode=null;
    NavigationView navigationView;

    /**
     *  ************Properties (Activities)***************
     * Set up the view content in the activity
     * plotting the navigation drawer in the activity
     * setting up the first fragment which will be apear first, and the drawer button which will be auto pressed
        *when the application will be first opened
     *
     * @param savedInstanceState
     */
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

        // Getting and setting the Application mood (whether the application is opened for first time or already
            // opened before)
        SharedPreferences AppCurrentMode = getSharedPreferences(ConstantVariables.APPLICATION_MOOD_SHARED_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = AppCurrentMode.edit();

        ApplicationMode = AppCurrentMode.getString(ConstantVariables.APPLICATION_MOOD_KEY_SH,null);

        if(ApplicationMode==null ){
            this.ApplicationMode=ConstantVariables.APPSTATUSFIRSTTIME;
            editor.putString(ConstantVariables.APPLICATION_MOOD_KEY_SH,ConstantVariables.APPSTATUSFIRSTTIME);
        }else {
            this.ApplicationMode=ConstantVariables.APPSTATUSCONSECUTIVE;
            editor.putString(ConstantVariables.APPLICATION_MOOD_KEY_SH,ConstantVariables.APPSTATUSCONSECUTIVE);
        }
        editor.commit();


        //Activity is empty now, a fragment will be added, and it will be appear when the application will be
            //first launched
        Fragment_ListOfAppToLock fragment = new Fragment_ListOfAppToLock();
        Bundle bundle= new Bundle();

        //sending the Application mood to the next activity
        bundle.putCharSequence(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,ApplicationMode);
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        // List of application is the fragment which will be apear first, when the application is first opened
        transaction.add(R.id.content_main,fragment,ConstantVariables.LIST_OF_APP_FRAGMENT_TAG);
        transaction.commit();
        //"Lock App" is the button from the menu(Buttons) of drawer which will be auto pressed when the
        // application will first launch
        navigationView.setCheckedItem(R.id.nav_lockapp);

    }

    /**
     *Drawer will be closed if it is opened
     * if drawer is opened , then the "Lock App"(Main Fragment)@goToMainFragment fragment will appear,
        * if any other fragment is currently opened
     *
     */
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
        }


    }

    /**
     *  ************Properties (Activities)***************
     * appear fragment according to the menu(button of navigation drawer) item selected
     * which menu is selected will be decided according to the id of the menu (R.id)
     * drawer will be automatically closed after selecting a menu
     * @param item
     * @return true (method executed properly)
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String FragmentTag=null;
        Fragment fragment=null;
        // just initializing which fragment will be shown accoring to id number
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
        // Closing the drawer
        drawer.closeDrawer(GravityCompat.START);

        // finalize the fragment , and make it appear which was selected in the above if condition
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

    /**
     * Check if the mainFragment (Lock App) is alive or not
     * @return -> if alive true, or false
     */
    private boolean ismainfracmentislive(){
        Fragment fragment = getFragmentManager().findFragmentByTag(ConstantVariables.LIST_OF_APP_FRAGMENT_TAG);

            if(fragment!=null && fragment.isVisible()){
                return true;
            }else {
                return false;
            }

    }

    /**
     * Will make the main fragment (Lock App) appear
     * auto select the navigation menu
     * @return
     */
    private boolean goToMainFragment(){
        Fragment fragment = new Fragment_ListOfAppToLock();
        String FragmentTag = ConstantVariables.LIST_OF_APP_FRAGMENT_TAG;
        Bundle bundle= new Bundle();
        // sending application mood to next frgament
        bundle.putCharSequence(ConstantVariables.BUND_MAINACTIVITY_TO_ANY_FRAGMENT_KEY,ApplicationMode);
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // placing the existing fragment with main fragment (Lock App)
        transaction.replace(R.id.content_main,fragment,FragmentTag);
        transaction.commit();
        //Auto select the "Lock App" menu (Button)
        navigationView.setCheckedItem(R.id.nav_lockapp);
        return true;
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


}
