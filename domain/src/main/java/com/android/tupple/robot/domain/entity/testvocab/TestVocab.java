package com.android.tupple.robot.domain.entity.testvocab;

import com.android.tupple.robot.domain.log.CLog;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class TestVocab {

    private final static String TAG = "TestVocab";

    private TestVocabPresenterHolder mTestVocabPresenterHolder;

    private TestVocabPresenter mCurrentLevelPresenter;

    public TestVocab() {
        mTestVocabPresenterHolder = new TestVocabPresenterHolder();
    }

    public void setLevel1Presenter(Level1Presenter level1Presenter) {
        mTestVocabPresenterHolder.setLevel1Presenter(level1Presenter);
        level1Presenter.setOnNextLevelObserver(this::switchLevel);
    }

    public void setLevel2Presenter(Level2Presenter level2Presenter) {
        mTestVocabPresenterHolder.setLevel2Presenter(level2Presenter);
    }

    private void switchLevel(TestVocabLevel testVocabLevel) {
        setCurrentPresenter(testVocabLevel);
        init();
    }

    public void setCurrentPresenter(TestVocabLevel testVocabLevel) {
        switch (testVocabLevel) {
            case LEVEL1_1:
                mCurrentLevelPresenter = mTestVocabPresenterHolder.get(TestVocabLevel.LEVEL1_1);
                break;
            case LEVEL2_1:
                mCurrentLevelPresenter = mTestVocabPresenterHolder.get(TestVocabLevel.LEVEL2_1);
                break;
        }
    }

    public void init(){
        // TODO init HeaderPresenter
        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.init();
        }
    }

    public void start() {
        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.start();
        }
    }

    public void stop() {
        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.stop();
        }
    }

    public void finish() {
        if (mTestVocabPresenterHolder != null) {
            mTestVocabPresenterHolder.clear();
        }

        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.finish();
        }

        mCurrentLevelPresenter = null;
    }

    class TestVocabPresenterHolder {

        public final String TAG = "TestVocabPresenterHolder";

        private Level1Presenter mLevel1Presenter;
        private Level2Presenter mLevel2Presenter;

        public TestVocabPresenterHolder() {
        }

        public void setLevel1Presenter(Level1Presenter level1Presenter) {
            this.mLevel1Presenter = level1Presenter;
        }

        public void setLevel2Presenter(Level2Presenter level2Presenter) {
            this.mLevel2Presenter = level2Presenter;
        }

        TestVocabPresenter get(TestVocabLevel testVocabLevel) {
            TestVocabPresenter presenter;
            switch (testVocabLevel) {
                case LEVEL1_1:
                case LEVEL1_2:
                    presenter = mLevel1Presenter;
                    break;
                case LEVEL2_1:
                    presenter = mLevel2Presenter;
                    break;
                case LEVEL3_1:
                default:
                    presenter = null;
            }

            if (presenter == null) {
                CLog.printSecD(TAG, "get(), presenter is null, level = " + testVocabLevel);
            }

            return presenter;
        }

        void clear() {

            if (mLevel1Presenter != null) {
                mLevel1Presenter.finish();
                mLevel1Presenter = null;
            }

            if (mLevel2Presenter != null) {
                mLevel2Presenter.finish();
                mLevel2Presenter = null;
            }

        }

    }

}
