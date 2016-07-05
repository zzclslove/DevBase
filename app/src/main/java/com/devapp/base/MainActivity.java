package com.devapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.devapp.R;
import com.devapp.category.CategoryFragment;
import com.devapp.findings.FindingsFragment;
import com.devapp.home.HomeFragment;
import com.devapp.order.CartFragment;
import com.devapp.user.UserFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;
    private MyMainViewPager mainPager;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mainPager = (MyMainViewPager)findViewById(R.id.id_main_viewpager);
        mainPager.setScanScroll(false);
        fragmentList = new ArrayList<>();

        Fragment homeFragment = HomeFragment.newInstance("首页");
        Fragment categoryFragment = CategoryFragment.newInstance("类目");
        Fragment findingsFragment = FindingsFragment.newInstance("发现");
        Fragment cartFragment = CartFragment.newInstance("购物车");
        Fragment userFragment = UserFragment.newInstance("用户中心");
        fragmentList.add(homeFragment);
        fragmentList.add(categoryFragment);
        fragmentList.add(findingsFragment);
        fragmentList.add(cartFragment);
        fragmentList.add(userFragment);

        mainPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragmentList));
        mainPager.setCurrentItem(3);//设置当前显示标签页为第一页
        mainPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int select) {
                bottomNavigationBar.selectTab(select);
            }
            @Override
            public void onPageScrolled(int positon, float arg1, int arg2 ) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.mipmap.icon_home, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_cate, "分类"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, "发现"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_cart, "购物车"))
                .addItem(new BottomNavigationItem(R.mipmap.icon_user, "我的"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                mainPager.setCurrentItem(position);
                transaction.commitAllowingStateLoss();
            }
            @Override
            public void onTabUnselected(int position) {}
            @Override
            public void onTabReselected(int position) {}
        });
    }

    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent. KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(bottomNavigationBar.getCurrentSelectedPosition() == 0){
                MainActivity.this.finish();
            }else{
                bottomNavigationBar.selectTab(0);
                mainPager.setCurrentItem(0);
            }
        }
        return false ;
    }

}
