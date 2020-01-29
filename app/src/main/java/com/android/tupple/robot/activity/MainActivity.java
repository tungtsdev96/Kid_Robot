package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;
import com.android.tupple.robot.data.entity.MenuItemData;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.entity.menumain.MenuMain;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.drawer.DrawerModel;
import com.android.tupple.robot.domain.presenter.drawer.DrawerPresenterImpl;
import com.android.tupple.robot.domain.presenter.drawer.DrawerView;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookViewWrapper;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;
import com.android.tupple.robot.data.model.drawer.DrawerModelFactory;
import com.android.tupple.robot.data.model.english.EnglishModelFactory;
import com.android.tupple.robot.view.drawer.DrawerViewFactory;
import com.android.tupple.robot.view.englishbook.EnglishBookViewWrapperFactory;
import com.android.tupple.robot.view.englishtopic.EnglishTopicViewWrapperFactory;

public class MainActivity extends BaseActivity {

    private MenuMain mMenuMain;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initFirstBatch(savedInstanceState);
        inject(savedInstanceState);

        mMenuMain.init();
    }

    private void initFirstBatch(Bundle bundle) {
        mMenuMain = new MenuMain();
        mActivityLauncher = new ActivityLauncher(this);
    }

    private void inject(Bundle bundle) {
        injectDrawer(bundle);
        injectEnglishBook(bundle);
        injectEnglishTopic(bundle);
    }

    private void injectEnglishTopic(Bundle bundle) {
        EnglishTopicPresenterImpl<Topic> englishTopicPresenter = new EnglishTopicPresenterImpl<>();
        EnglishTopicViewWrapper<Topic> englishTopicViewWrapper = EnglishTopicViewWrapperFactory.newEnglishTopicViewWrapper(getSupportFragmentManager(), bundle);
        EnglishTopicModel<Topic>  englishTopicModel = EnglishModelFactory.newEnglishTopicModel(this);

        englishTopicPresenter.setEnglishTopicViewWrapper(englishTopicViewWrapper);
        englishTopicPresenter.setEnglishBookModel(englishTopicModel);
        // TODO innit Observerable
        mMenuMain.setEnglishTopicPresenter(englishTopicPresenter);
    }

    private void injectEnglishBook(Bundle bundle) {
        EnglishBookPresenterImpl<SchoolBook> englishBookPresenter = new EnglishBookPresenterImpl<>();
        EnglishBookViewWrapper<SchoolBook> englishBookViewWrapper = EnglishBookViewWrapperFactory.newSchoolBookEnglishBookViewWrapper(getSupportFragmentManager(), bundle);
        EnglishBookModel<SchoolBook> englishBookModel = EnglishModelFactory.newEnglishBookModel(this);

        englishBookPresenter.setEnglishTopicViewWrapper(englishBookViewWrapper);
        englishBookPresenter.setEnglishBookModel(englishBookModel);

        // TODO innit Observerable
        englishBookPresenter.setOnItemBookClickedObserver(mActivityLauncher::launchUnitActivity);

        mMenuMain.setCurrentPresenterByMenuType(MenuType.ENGLISH_BOOK);
        mMenuMain.setEnglishBookPresenter(englishBookPresenter);
    }

    private void injectDrawer(Bundle bundle) {
        DrawerPresenterImpl<MenuItemData> drawerPresenter = new DrawerPresenterImpl<>();
        DrawerView<MenuItemData> drawerView = DrawerViewFactory.newDrawerView(this);
        DrawerModel<MenuItemData> drawerModel = DrawerModelFactory.newDrawerModel(this);

        drawerPresenter.setDrawerView(drawerView);
        drawerPresenter.setDrawerModel(drawerModel);
        mMenuMain.setDrawerViewPresenter(drawerPresenter);

        drawerPresenter.setItemMenuSelectedObserver(mMenuMain::changeMenu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuMain.start();
    }

    @Override
    protected void onDestroy() {
        mMenuMain.stop();
        super.onDestroy();
    }
}