package com.ypf.fastsearch.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ypf.fastsearch.R;
import com.ypf.fastsearch.adapter.CustomViewPagerAdapter;
import com.ypf.fastsearch.fragment.RecordFragment;
import com.ypf.fastsearch.fragment.SearchFragment;
import com.ypf.fastsearch.util.SnackUtil;
import com.ypf.fastsearch.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigation.OnMenuItemSelectionListener, ViewPager.OnPageChangeListener {

    private Toolbar toolbar;
    private CustomViewPager viewPager;
    private it.sephiroth.android.library.bottomnavigation.BottomNavigation bottomNavigation;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private Button edit_btn;
    private List<Fragment> fragments;
    private CustomViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initView();
        initData();
        configView();

    }

    private void configView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawer_layout.addDrawerListener(mActionBarDrawerToggle);
        nav_view.setNavigationItemSelectedListener(this);
        bottomNavigation.setDefaultSelectedIndex(1);
        bottomNavigation.setOnMenuItemClickListener(this);
        viewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setNoScroll(true);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        fragments = new ArrayList<>();
        RecordFragment finishedFragment = new RecordFragment();
        SearchFragment searchFragment = new SearchFragment();
        RecordFragment unfinishedFragment = new RecordFragment();
        fragments.add(finishedFragment);
        fragments.add(searchFragment);
        fragments.add(unfinishedFragment);
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        bottomNavigation = (BottomNavigation) findViewById(R.id.bottomNavigation);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        edit_btn = (Button) nav_view.getHeaderView(0).findViewById(R.id.edit_btn);
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackUtil.show(nav_view, getResources().getString(R.string.username), 0);
            }
        });
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public void onMenuItemSelect(@IdRes int i, int i1) {
        viewPager.setCurrentItem(i1);
        setToobarTitle(i1);
    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setToobarTitle(position);
        viewPager.setCurrentItem(position);
        bottomNavigation.setSelectedIndex(position, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setToobarTitle(int position) {
        switch (position) {
            case 0:
                toolbar.setTitle(R.string.finished);
                break;
            case 1:
                toolbar.setTitle(R.string.search);
                break;
            case 2:
                toolbar.setTitle(R.string.unfinished);
                break;
        }
    }
}
