package com.android.tupple.robot.view.drawer;

import android.app.Activity;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.activity.ActivityLauncher;
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
    private CardView cardSchool, cardTopic, cardEntertainment, cardAlarm;

    private CleanObserver<MenuType> mItemSchoolSelectedObserver;
    private CleanObserver<MenuType> mItemTopicSelectedObserver;
    private CleanObserver<MenuType> mItemEntertainmentSelectedObserver;
    public DrawerViewImpl(Activity activity) {
        this.mActivity = activity;
        initView();
    }

    private void initView() {
        ActivityLauncher activityLauncher = new ActivityLauncher(mActivity);

        cardSchool = mActivity.findViewById(R.id.school_book);
        cardEntertainment = mActivity.findViewById(R.id.entertainment);
        cardTopic = mActivity.findViewById(R.id.topic);
        cardTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemTopicSelectedObserver.onNext(MenuType.ENGLISH_TOPIC);
            }
        });
        cardSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemSchoolSelectedObserver.onNext(MenuType.ENGLISH_BOOK);
            }
        });
        cardEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLauncher.launchEntertainmentActivity();
            }
        });
    }





    @Override
    public void setListMenu(List<MenuItemData> items) {

    }

    @Override
    public CleanObservable<MenuType> getItemMenuSelectedObservable() {
        return null;
    }
}
