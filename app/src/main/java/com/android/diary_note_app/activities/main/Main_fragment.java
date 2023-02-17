package com.android.diary_note_app.activities.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.diary_note_app.MainActivity;
import com.android.diary_note_app.R;
import com.android.diary_note_app.ViewAdapter;
import com.google.android.material.tabs.TabLayout;

public class Main_fragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    MainActivity Act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Act = (MainActivity) getActivity();
        setTabLayout();
        return inflater.inflate(R.layout.fragment_main_fragment, container, false);
    }


    private void setTabLayout(){
        tabLayout = (TabLayout)Act.findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("최신"));
        tabLayout.addTab(tabLayout.newTab().setText("달력"));
        tabLayout.addTab(tabLayout.newTab().setText("기분"));
        tabLayout.addTab(tabLayout.newTab().setText("사진"));



        viewPager = (ViewPager) Act.findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewAdapter(Act.getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }
}