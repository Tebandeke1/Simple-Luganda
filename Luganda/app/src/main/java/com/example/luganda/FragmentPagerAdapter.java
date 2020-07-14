package com.example.luganda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private String [] subTitles = {"Numbers","Family","Colors","Phrases"};


    public FragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new NumbersFragment();
            case 1:
                return new AbekikaFreagment();
            case 2:
                return new LangiFragment();
            case 3:
                return new EbigamboFragment();
            default:
                return null;
        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return subTitles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
