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
    String[] aTips;
    String[] bTips;
    String[] cTips;
    String aboutUs;

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

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer ;
    volatile List<HashMap<String,String>> mList;
    volatile List<HashMap<String,String>> fList;
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
        aTips = getResources().getStringArray(R.array.a_tip);
        bTips = getResources().getStringArray(R.array.b_tip);
        cTips = getResources().getStringArray(R.array.c_tip);
        aboutUs = getResources().getString(R.string.about_us);

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

        ArrayList<String> groupItem = new ArrayList<>();

        Collections.addAll(groupItem, menu);

        ArrayList<Object> childItem = new ArrayList<>();

        childItem.add(fList);
        childItem.add(mList);

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
                        TheFoundationFragment cFragment = new TheFoundationFragment();
                        Bundle data = new Bundle();
                        data.putInt("position", position);
                        data.putString("title", foundation[position]);
                        data.putString("aboutUs", aboutUs);
                        cFragment.setArguments(data);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.content_frame, cFragment);
                        ft.commit();
                        break;
                }
                break;
            case 1:
                //Currently selected country
                //mTitle = mCountries[position];
                // Creating a fragment object
                DangerSituationFragment cFragment = new DangerSituationFragment();

                // Creating a Bundle object
                Bundle data = new Bundle();

                // Setting the index of the currently selected item of mDrawerList
                data.putInt("position", position);
                data.putString("title", dangerSituations[position]);
                data.putString("aTip", aTips[position]);
                data.putString("bTip", bTips[position]);
                data.putString("cTip", cTips[position]);

                // Setting the position to the fragment
                cFragment.setArguments(data);

                // Getting reference to the FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a fragment transaction
                FragmentTransaction ft = fragmentManager.beginTransaction();

                // Adding a fragment to the fragment transaction
                ft.replace(R.id.content_frame, cFragment);

                // Committing the transaction
                ft.commit();
                break;
        }
    }
}
