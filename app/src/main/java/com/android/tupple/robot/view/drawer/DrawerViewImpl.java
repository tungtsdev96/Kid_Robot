package com.android.tupple.robot.view.drawer;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.MenuItemData;
import com.android.tupple.robot.domain.presenter.drawer.DrawerView;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerViewImpl implements DrawerView<MenuItemData> {

    private Activity mActivity;

    private RecyclerView mRcvDrawer;
    private DrawerAdapter mDrawerAdapter;

    public DrawerViewImpl(Activity activity) {
        this.mActivity = activity;
        initRecycleView();
    }

    private void initRecycleView() {
        mRcvDrawer = mActivity.findViewById(R.id.rcv_menu_item);
        mDrawerAdapter = new DrawerAdapter(mActivity);
        mRcvDrawer.setLayoutManager(new LinearLayoutManager(mActivity));
        mRcvDrawer.setAdapter(mDrawerAdapter);
    }


    @Override
    public void setListMenu(List<MenuItemData> items) {
        items.add(new MenuItemData("Sach Giao Khoa", "ic_alphabet"));
        items.add(new MenuItemData("Chu de", "ic_alphabet"));
        items.add(new MenuItemData("Bao thuc", "ic_alphabet"));
        items.add(new MenuItemData("Gia tri", "ic_alphabet"));
        mDrawerAdapter.setListMenu(items);
    }
}
