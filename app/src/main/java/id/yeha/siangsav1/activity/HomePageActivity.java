package id.yeha.siangsav1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.fragment.FragmentLayanan;
import id.yeha.siangsav1.fragment.FragmentPaket;
import id.yeha.siangsav1.fragment.MenuFragment;
import id.yeha.siangsav1.util.Global;


/*
* Home Page Bottom and Left Navigation
*
* */


public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean viewIsAtHome;
    private FragmentPaket fragmentPaket;
    private FragmentLayanan fragmentLayanan;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Fragment fragment;
    private static final String SELECTED_ITEM = "SELECTED_ITEM";
    private BottomNavigationView bottomNavigation;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponentLayout();
        initEventBottomNav(savedInstanceState);
        displayView(Global.NAV_PAKET);
    }

    private void displayView(int viewId) {
        Fragment fragment = null;
        if (viewId == Global.NAV_PAKET) {
            viewIsAtHome = true;
            fragment = new FragmentPaket();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

    }

    private void initComponentLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigation = findViewById(R.id.bottom_navigation);

    }

    private void initEventBottomNav(Bundle savedInstanceState) {

        /*bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectFragment(item);

                return true;
            }
        });*/

        bottomNavigation.setOnSelected

        MenuItem selectedMenuItem;
        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedMenuItem = bottomNavigation.getMenu().findItem(selectedItem);
        } else {
            selectedMenuItem = bottomNavigation.getMenu().getItem(0);
        }
        selectFragment(selectedMenuItem);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, selectedItem);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        MenuItem homeItem = bottomNavigation.getMenu().getItem(0);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (selectedItem != homeItem.getItemId()) {
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }

        //if the current view is not the Halaman Paket
        //displayView(R.id.nav_paket); //display the News fragment

        if (!viewIsAtHome) {
        } else {
            //If view is in News fragment, exit application
            moveTaskToBack(true);
        }


    }


    // Left menu navigation
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case Global.NAV_PAKET:
                viewIsAtHome = true;
                fragment = new FragmentPaket();
                break;

            case Global.NAV_LAYANAN:
                viewIsAtHome = false;
                fragment = new FragmentLayanan();
                break;

            case Global.NAV_TAGIHAN:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(), "Page Tagihan Not Ready", Toast.LENGTH_LONG).show();
                break;

            case Global.NAV_LAPOR:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(), "Page Lapor Not Ready", Toast.LENGTH_LONG).show();
                break;
            case Global.NAV_INFO:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(), "Page Info Not Ready", Toast.LENGTH_LONG).show();
                break;

        }

        commitFragment();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    // Bottom Navigation
    private void selectFragment(MenuItem item) {
        // init corresponding fragment
        int colorProfile = getResources().getColor(R.color.orange_200);
        int colorNotifications = getResources().getColor(R.color.orange_300);
        int colorMessage = getResources().getColor(R.color.orange_400);


        switch (item.getItemId()) {
            case R.id.menu_profile:
                fragment = MenuFragment.newInstance(getString(R.string.btm_menu_profile),
                        colorProfile);
                break;
            case R.id.menu_notifications:
                fragment = MenuFragment.newInstance(getString(R.string.btm_menu_notif), colorNotifications);
                break;
            case R.id.menu_messages:
                fragment = MenuFragment.newInstance(getString(R.string.btm_menu_notif), colorMessage);
                break;

            case R.id.menu_about:
                fragment = MenuFragment.newInstance(getString(R.string.btm_menu_about), colorMessage);
                break;

            case R.id.menu_logout:
                break;
        }

        // update selected item
        selectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i < bottomNavigation.getMenu().size(); i++) {
            MenuItem menuItem = bottomNavigation.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        /*if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }*/
        commitFragment();
    }

    private void commitFragment() {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }
}
