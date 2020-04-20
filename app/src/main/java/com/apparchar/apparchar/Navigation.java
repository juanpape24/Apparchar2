package com.apparchar.apparchar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.apparchar.apparchar.Vista.LoginActivity;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentCategoria.OnFragmentInteractionListener,FragmentEventos.OnFragmentInteractionListener,FragmentRealTime.OnFragmentInteractionListener,FragmentContenedor.OnFragmentInteractionListener  {
    private static String idUser="";
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(Utilidades.validarPantalla){
            Fragment fragment=new FragmentContenedor();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation,fragment).commit();
            Utilidades.validarPantalla=false;
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_actualizar1) {
            finish();
            startActivity(getIntent());
        }
        if(id==R.id.action_cerrar1){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=new FragmentContenedor();
        boolean fragmentSelecionado=false;
         if (id == R.id.nav_gallery) {
            fragment=new FragmentCategoria();
            fragmentSelecionado=true;

        } else if (id == R.id.nav_slideshow) {
            fragment=new FragmentEventos();
            fragmentSelecionado=true;

        } else if (id == R.id.nav_tools) {
            fragment=new FragmentRealTime();
            fragmentSelecionado=true;

        }else if (id==R.id.nav_shares){
             fragment=new FragmentContenedor();
             fragmentSelecionado=true;
         }
        if(fragmentSelecionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigation,fragment).commit();
        }
        if(id==R.id.nav_send){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
