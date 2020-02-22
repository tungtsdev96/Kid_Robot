package com.android.tupple.robot.view.testvocab.level3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.tupple.robot.view.testvocab.level3.item.Level3ItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-02-21.
 */

public class CheckPronouncePagerAdapter extends FragmentStateAdapter {

    private List<Level3ItemFragment> mItems = new ArrayList<>();

    public CheckPronouncePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(Level3ItemFragment itemFragment) {
        mItems.add(itemFragment);
    }

}
