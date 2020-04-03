package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.model.english.EnglishModelFactory;
import com.android.tupple.robot.domain.entity.englishbook.EnglishBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookView;
import com.android.tupple.robot.view.englishbook.EnglishBookViewFactory;

public class EnglishBookActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private EnglishBook englishBook;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_english_book;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        injectFirstBatch();
        inject(bundle);
    }

    private void inject(Bundle bundle)  {
        EnglishBookPresenterImpl<SchoolBook> englishBookPresenter = new EnglishBookPresenterImpl<>();
        EnglishBookView<SchoolBook> englishBookView = EnglishBookViewFactory.newEnglishBookView(this,bundle);
        EnglishBookModel<SchoolBook> englishBookModel = EnglishModelFactory.newEnglishBookModel(this);

        englishBookPresenter.setEnglishBookView(englishBookView);
        englishBookPresenter.setEnglishBookModel(englishBookModel);

        // innit Observerable
        englishBookPresenter.setOnItemBookClickedObserver(activityLauncher::launchLessonActivity);
        englishBookPresenter.setCloseButtonHandler(this::onBackPressed);

        englishBook.setEnglishBookPresenter(englishBookPresenter);
        englishBook.init();
    }

    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        englishBook = new EnglishBook();
    }
}
