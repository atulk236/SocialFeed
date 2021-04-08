package com.ueniweb.techsuperficial.socialmerevup.views;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ueniweb.techsuperficial.socialmerevup.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity{

    Context mcontext;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    FeedFragment feedFragment;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initVariable();
        setHomeFrame();
        setBottomNavigation();
    }

    private void initVariable() {
        mcontext = HomeActivity.this;
        fragmentManager = getSupportFragmentManager();
        feedFragment = new FeedFragment();
    }

    private void setHomeFrame() {
        fragmentManager.beginTransaction().add(R.id.home_frame, feedFragment).commit();
        activeFragment = feedFragment;

    }

    private void setBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            replaceFragment(item.getItemId());
            return true;
        });
    }

    public void replaceFragment(int itemId) {
        Fragment fragment = new Fragment();
        switch (itemId) {
            case R.id.feed:
                fragment = feedFragment;
                break;
        }
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit();
        activeFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (activeFragment instanceof FeedFragment) {
            finish();
        } else
            replaceFragment(R.id.home);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

}