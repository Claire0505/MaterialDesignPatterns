package com.admin.claire.materialdesignpatterns;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.admin.claire.materialdesignpatterns.adapter.ViewPagerAdapter;

public class TabLayoutActivity extends AppCompatActivity {
    ViewPager mViewPager;
    Toolbar mToolbar;
    ViewPagerAdapter mViewPagerAdapter;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_tabs);
        setSupportActionBar(mToolbar);

        // add back arrow to toolbar <-
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setViewPager();
    }

    private void setViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
