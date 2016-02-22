package com.example.slidingtab;

import com.example.slidingtab.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private RemindAdapter mRemindAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        //ActionBarUtil.setup(this, R.string.my_remind);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab);
        mViewPager =(ViewPager)findViewById(R.id.pager);

        // 设置ViewPager属性
        mRemindAdapter = new RemindAdapter(getSupportFragmentManager(), this);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mRemindAdapter);

        // 定义 SlidingTabLayout
        //mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.primary));
        //mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mSlidingTabLayout.setCustomTabView(R.layout.tab, R.id.txt);
        mSlidingTabLayout.setViewPager(mViewPager); // 加载ViewPager

    }

}
