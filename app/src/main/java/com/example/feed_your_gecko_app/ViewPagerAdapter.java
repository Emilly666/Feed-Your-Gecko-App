package com.example.feed_your_gecko_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.feed_your_gecko_app.calendar.CalendarFragment;
import com.example.feed_your_gecko_app.reptiles.ReptilesFragment;
import com.example.feed_your_gecko_app.tasks.HomeFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new CalendarFragment();
        } else if (position == 1) {
            return new HomeFragment();
        } else if (position == 2) {
            return new ReptilesFragment();
        }
        else {
            return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
