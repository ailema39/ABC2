package com.mycompany.abc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // Array of strings storing country names
    String[] dangerSituations;
    String[] menu;
    String[] foundation;
    String[] directory;
    String[] tips;
    String[] aTips;
    String[] bTips;
    String[] cTips;
    String[] tips1;
    String[] tips2;
    String[] tips3;
    String aboutUs;
    String donations;

    // Array of integers points to images stored in /res/drawable
    int[] icDangerSituations = new int[]{
            R.drawable.aeropuerto,
            R.drawable.avion,
            R.drawable.banco,
            R.drawable.bebes,
            R.drawable.cajeros,
            R.drawable.carros,
            R.drawable.casas,
            R.drawable.celular,
            R.drawable.computadora,
            R.drawable.discapacitados,
            R.drawable.incendios,
            R.drawable.inundaciones,
            R.drawable.robo,
            R.drawable.secuestro,
            R.drawable.tdc,
            R.drawable.violencia_domestica
    };

    int[] icFoundation = new int[]{
            R.drawable.info,
            R.drawable.donacion
    };

    int[] icEmergency = new int[]{
            R.drawable.ambulancia,
            R.drawable.bomberos,
            R.drawable.cruz_roja,
            R.drawable.policia,
            R.drawable.sinaproc
    };

    int[] icTips = new int[]{
            R.drawable.victima,
            R.drawable.solos,
            R.drawable.delincuente
    };

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer ;
    volatile List<HashMap<String,String>> mList;
    volatile List<HashMap<String,String>> fList;
    volatile List<HashMap<String,String>> eList;
    volatile List<HashMap<String,String>> tList;
    volatile NewAdapter mAdapter;
    volatile String TEXT = "text";
    volatile String ICON = "icon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_menu_abc);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setTitle(R.string.danger_situation);

        setContentView(R.layout.activity_main);

        // Getting an array of danger situations
        menu = getResources().getStringArray(R.array.menu_array);
        foundation = getResources().getStringArray(R.array.foundation_array);
        dangerSituations = getResources().getStringArray(R.array.category_array);
        directory = getResources().getStringArray(R.array.directory_array);
        tips = getResources().getStringArray(R.array.tips_array);
        aTips = getResources().getStringArray(R.array.a_tip);
        bTips = getResources().getStringArray(R.array.b_tip);
        cTips = getResources().getStringArray(R.array.c_tip);
        tips1 = getResources().getStringArray(R.array.tip_1);
        tips2 = getResources().getStringArray(R.array.tip_2);
        tips3 = getResources().getStringArray(R.array.tip_3);
        aboutUs = getResources().getString(R.string.about_us);
        donations = getResources().getString(R.string.donations);

        // Getting a reference to the drawer listview
        ExpandableListView mDrawerList = (ExpandableListView) findViewById(R.id.drawer_list);

        // Getting a reference to the sidebar drawer ( Title + ListView )
        mDrawer = ( LinearLayout) findViewById(R.id.drawer);

        // Each row in the list stores name and icon of each danger situation
        mList = new ArrayList<>();
        for(int i=0;i<dangerSituations.length;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put(TEXT, dangerSituations[i]);
            hm.put(ICON, Integer.toString(icDangerSituations[i]) );
            mList.add(hm);
        }

        fList = new ArrayList<>();
        for(int i=0;i<foundation.length;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put(TEXT, foundation[i]);
            hm.put(ICON, Integer.toString(icFoundation[i]) );
            fList.add(hm);
        }

        eList = new ArrayList<>();
        for(int i=0;i<directory.length;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put(TEXT, directory[i]);
            hm.put(ICON, Integer.toString(icEmergency[i]));
            eList.add(hm);
        }

        tList = new ArrayList<>();
        for(int i=0;i<tips.length;i++){
            HashMap<String, String> hm = new HashMap<>();
            hm.put(TEXT, tips[i]);
            hm.put(ICON, Integer.toString(icTips[i]));
            tList.add(hm);
        }

        ArrayList<String> groupItem = new ArrayList<>();
        Collections.addAll(groupItem, menu);

        ArrayList<Object> childItem = new ArrayList<>();

        childItem.add(fList);
        childItem.add(mList);
        childItem.add(eList);
        childItem.add(tList);


        // Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
        mAdapter = new NewAdapter(this, groupItem, childItem);

        // Getting reference to DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        // Creating a ToggleButton for NavigationDrawer with drawer event listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){

            /** Called when drawer is closed */
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };

        // Setting event listener for the drawer
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                /*Toast.makeText(getApplicationContext(),
                        "Group Clicked",
                        Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });

        // ItemClick event handler for the drawer items
        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //if (childPosition < 5) { // Show fragment for countries : 0 to 4
                //TryFragment fragment = (TryFragment) SampleActivity.this.getFragmentManager()
                showFragment(groupPosition, childPosition);
                /*} else { // Show message box for countries : 5 to 9
                    Toast.makeText(getApplicationContext(), mCountries[position], Toast.LENGTH_LONG).show();
                }*/

                /*Toast.makeText(getApplicationContext(),
                        "Child Clicked ",
                        Toast.LENGTH_LONG).show();*/
                // Closing the drawer
                mDrawerLayout.closeDrawer(mDrawer);

                return false;
            }
        });

        // Enabling Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Setting the adapter to the listView
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showFragment(int groupPosition, int position){
        Log.d("MyApp", groupPosition + ", " + position);
        switch (groupPosition){
            case 0:
                switch (position){
                    case 0:
                        TheFoundationFragment tfFragment = new TheFoundationFragment();
                        Bundle tfBundle = new Bundle();

                        tfBundle.putInt("position", position);
                        tfBundle.putString("title", foundation[position]);
                        tfBundle.putString("aboutUs", aboutUs);
                        tfFragment.setArguments(tfBundle);

                        FragmentManager tfFragmentManager = getSupportFragmentManager();
                        FragmentTransaction tfFragmentTransaction = tfFragmentManager.beginTransaction();

                        tfFragmentTransaction.replace(R.id.content_frame, tfFragment);
                        tfFragmentTransaction.commit();
                        break;
                    case 1:
                        DonationsFragment dFragment = new DonationsFragment();
                        Bundle bBundle = new Bundle();

                        bBundle.putInt("position", position);
                        bBundle.putString("title", foundation[position]);
                        bBundle.putString("donations", donations);
                        dFragment.setArguments(bBundle);

                        FragmentManager dFragmentManager = getSupportFragmentManager();
                        FragmentTransaction dFragmentTransaction = dFragmentManager.beginTransaction();

                        dFragmentTransaction.replace(R.id.content_frame, dFragment);
                        dFragmentTransaction.commit();
                        break;
                }
                break;
            case 1:
                DangerSituationFragment dsFragment = new DangerSituationFragment();
                Bundle dsBundle = new Bundle();

                // Setting the index of the currently selected item of mDrawerList
                dsBundle.putInt("position", position);
                dsBundle.putString("title", dangerSituations[position]);
                dsBundle.putString("aTip", aTips[position]);
                dsBundle.putString("bTip", bTips[position]);
                dsBundle.putString("cTip", cTips[position]);
                dsFragment.setArguments(dsBundle);

                FragmentManager dsFragmentManager = getSupportFragmentManager();
                FragmentTransaction dsFragmentTransaction = dsFragmentManager.beginTransaction();

                dsFragmentTransaction.replace(R.id.content_frame, dsFragment);
                dsFragmentTransaction.commit();
                break;
            case 2:
                DirectoryFragment dFragment = new DirectoryFragment();
                Bundle dBundle = new Bundle();

                dBundle.putInt("position", position);
                dBundle.putString("title", directory[position]);
                dFragment.setArguments(dBundle);

                FragmentManager dFragmentManager = getSupportFragmentManager();
                FragmentTransaction dFragmentTransaction = dFragmentManager.beginTransaction();

                dFragmentTransaction.replace(R.id.content_frame, dFragment);
                dFragmentTransaction.commit();
                break;
            case 3:
                TipsFragment tFragment = new TipsFragment();
                Bundle tBundle = new Bundle();

                tBundle.putInt("position", position);
                tBundle.putString("title", tips[position]);
                tBundle.putString("tip1", tips1[position]);
                tBundle.putString("tip2", tips2[position]);
                tBundle.putString("tip3", tips3[position]);
                tFragment.setArguments(tBundle);

                FragmentManager tFragmentManager = getSupportFragmentManager();
                FragmentTransaction tFragmentTransaction = tFragmentManager.beginTransaction();

                tFragmentTransaction.replace(R.id.content_frame, tFragment);
                tFragmentTransaction.commit();
                break;
        }
    }
}
