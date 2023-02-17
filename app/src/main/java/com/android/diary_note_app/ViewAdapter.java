package com.android.diary_note_app;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.diary_note_app.activities.main.Page1_frag;
import com.android.diary_note_app.activities.main.Page2_frag;
import com.android.diary_note_app.activities.main.Page3_frag;
import com.android.diary_note_app.activities.main.Page4_frag;

import java.util.ArrayList;
import java.util.List;


public class ViewAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments=new ArrayList<>();

    public ViewAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new Page1_frag());
        fragments.add(new Page2_frag());
        fragments.add(new Page3_frag());
        fragments.add(new Page4_frag());
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}