package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.fragment.FragmentLayanan;
import id.yeha.siangsav1.fragment.FragmentPaket;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean viewIsAtHome;
    private FragmentPaket fragmentPaket;
    private FragmentLayanan fragmentLayanan;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponentLayout();
        displayView(R.id.nav_paket);


    }

    private void displayView(int viewId){
        Fragment fragment = null;
        if (viewId == R.id.nav_paket){
            viewIsAtHome = true;
            fragment = new FragmentPaket();
        }
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

    }

    private void initComponentLayout(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (!viewIsAtHome) { //if the current view is not the Halaman Paket
            //displayView(R.id.nav_paket); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_paket:
                viewIsAtHome = true;
                fragment = new FragmentPaket();
                break;

            case R.id.nav_layanan:
                viewIsAtHome = false;
                fragment = new FragmentLayanan();
                break;

            case R.id.nav_tagihan:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(),"Page Tagihan Not Ready",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_lapor:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(),"Page Lapor Not Ready",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_info:
                viewIsAtHome = false;
                Toast.makeText(getApplicationContext(),"Page Info Not Ready",Toast.LENGTH_LONG).show();
                break;

        }

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
}
