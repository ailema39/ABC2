package com.mycompany.abc;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // Array of strings storing country names
    String[] dangerSituations ;

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
            R.drawable.indundaciones,
            R.drawable.robo,
            R.drawable.secuestro,
            R.drawable.tdc,
            R.drawable.violencia_domestica
    };

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer ;
    volatile List<HashMap<String,String>> mList;
    volatile NewAdapter mAdapter;
    volatile String TEXT = "text";
    volatile String ICON = "icon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_menu_abc);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(R.string.danger_situation);

        setContentView(R.layout.activity_main);

        // Getting an array of danger situations
        dangerSituations = getResources().getStringArray(R.array.category_array);

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

        ArrayList<String> groupItem = new ArrayList<>();
        ArrayList<Object> childItem = new ArrayList<>();

        groupItem.add(getResources().getString(R.string.danger_situation));
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
                Toast.makeText(getApplicationContext(),
                        "Group Clicked",
                        Toast.LENGTH_SHORT).show();
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
                //showFragment(childPosition);
                /*} else { // Show message box for countries : 5 to 9
                    Toast.makeText(getApplicationContext(), mCountries[position], Toast.LENGTH_LONG).show();
                }*/

                Toast.makeText(getApplicationContext(),
                        "Child Clicked ",
                        Toast.LENGTH_LONG).show();
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
}
