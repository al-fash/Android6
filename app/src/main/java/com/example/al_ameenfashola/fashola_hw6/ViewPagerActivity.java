package com.example.al_ameenfashola.fashola_hw6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by xing on 2/14/2015.
 */
public class ViewPagerActivity extends FragmentActivity implements
        MyRecyclerviewFragment.OnRecyclerViewItemSelectedListener {

   // MyFragmentPagerAdapter myPagerAdapter;
    MyFragmentStatePagerAdapter myPagerAdapter;
    ViewPager mViewPager;
    MovieDataJson movieData;

    private int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_activity_main);
        try {
            movieData=new MovieDataJson(getApplicationContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        myPagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), movieData.getSize());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        customizeViewPager();
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setCurrentItem(5);

    }

    private void customizeViewPager(){
        //mViewPager.setOffscreenPageLimit(4);

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                // Fading out
                //final float normalizedposition = Math.abs(Math.abs(position) - 1);
                //page.setAlpha(normalizedposition);

                // Scaling effect
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);

                // Rotation effect
                //page.setRotationY(position * -30);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }



    public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        int count;

        public MyFragmentStatePagerAdapter (FragmentManager fm, int size) {
            super(fm);
            count = size;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return MyRecyclerviewFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            String name;
            switch (position) {
                case 0:
                    name = "Top Selling Movies";
                    break;
                case 1:
                    name = "New Release Movies";
                    break;
                default:
                    name = "Home";
                    break;
            }
            return name.toUpperCase();
        }
    }

    @Override
    public void onItemSelected(int position,HashMap<String,?> movie){
        /*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_list_container, MyDetailFragment.newInstance(movie))
                .addToBackStack(null)
                .commit();
                */
        Intent intent = new Intent(this, ToolBarActivity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);

    }

}
