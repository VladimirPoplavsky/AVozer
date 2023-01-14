package com.firstapp.avozer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.firstapp.avozer.fragments.FeedFragment;
import com.firstapp.avozer.fragments.NotiflicationFragment;
import com.firstapp.avozer.fragments.SettingsFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FeedFragment();

            case 1:
                return new NotiflicationFragment();

            case 2:
                return new SettingsFragment();

            default:
                return new FeedFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
