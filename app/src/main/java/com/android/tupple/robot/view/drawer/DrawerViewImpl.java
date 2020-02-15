package com.android.tupple.robot.view.drawer;

import android.app.Activity;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.SnappingRecyclerView;
import com.android.tupple.robot.data.entity.MenuItemData;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.drawer.DrawerView;

import java.util.List;
import java.util.Objects;

/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerViewImpl implements DrawerView<MenuItemData> {

    private Activity mActivity;

    private SnappingRecyclerView mRcvDrawer;
    private DrawerAdapter mDrawerAdapter;

    private CleanObserver<MenuType> mItemMenuSelectedObserver;

    public DrawerViewImpl(Activity activity) {
        this.mActivity = activity;
        initRecycleView();
    }

    private void initRecycleView() {
        mRcvDrawer = mActivity.findViewById(R.id.rcv_menu_item);
        mRcvDrawer.enableViewScaling(true);
        mRcvDrawer.setOrientation(SnappingRecyclerView.Orientation.VERTICAL);
        mDrawerAdapter = new DrawerAdapter(mActivity);
        mRcvDrawer.setAdapter(mDrawerAdapter);

        mRcvDrawer.setOnViewSelectedListener((view, position) -> {
            MenuType menuType = MenuType.fromValue(position % 3 + 1);
            if (mItemMenuSelectedObserver != null) {
                mItemMenuSelectedObserver.onNext(menuType);
            }
        });
    }


    @Override
    public void setListMenu(List<MenuItemData> items) {
        items.add(new MenuItemData("Sach Giao Khoa", "ic_school_book"));
        items.add(new MenuItemData("Chu de", "ic_topic"));
        items.add(new MenuItemData("Bao thuc", "ic_alarm"));
//        items.add(new MenuItemData("Giai tri", "ic_garden"));
        mDrawerAdapter.setListMenu(items);
        Objects.requireNonNull(mRcvDrawer.getLayoutManager()).scrollToPosition(299);
    }

    @Override
    public CleanObservable<MenuType> getItemMenuSelectedObservable() {
        return CleanObservable.create(cleanObserver -> mItemMenuSelectedObserver = cleanObserver);
    }
}
