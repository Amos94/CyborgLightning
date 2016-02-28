package com.example.amosmadalinneculau.objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserHomepage extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout testerLayout;

    private static final long RIPPLE_DURATION = 500;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root)
    FrameLayout root;
    @InjectView(R.id.content_hamburger)
    View contentHamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        ButterKnife.inject(this);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter PageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PageAdapter.addFragment(new UserProfileFragment(), "Profile");
        PageAdapter.addFragment(new UserHomepageFragment(), "Discovery");
        PageAdapter.addFragment(new FriendsListFragment(), "Friends");
        PageAdapter.addFragment(new chatRoom(),"ChatRoom");
        viewPager.setAdapter(PageAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> FragmentList = new ArrayList<>();
        private final List<String> FragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentList.get(position);
        }

        @Override
        public int getCount() {
            return FragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            FragmentList.add(fragment);
            FragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentTitleList.get(position);
        }
    }

}
