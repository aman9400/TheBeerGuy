package com.example.thebeerguy.Intro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new Splash1();

            case 1 : return new Splash2();

            case 2 : return new Splash3();

//            case 3 : return new Splash4();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
