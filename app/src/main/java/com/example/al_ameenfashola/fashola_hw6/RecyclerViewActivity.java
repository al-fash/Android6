package com.example.al_ameenfashola.fashola_hw6;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.HashMap;


public class RecyclerViewActivity extends ActionBarActivity implements
        MyRecyclerviewFragment.OnRecyclerViewItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_list_container, MyRecyclerviewFragment.newInstance(0))
                .commit();

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(int position,HashMap<String,?> movie){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_list_container, MyDetailFragment.newInstance(movie))
                    .addToBackStack(null)
                    .commit();

    }


}