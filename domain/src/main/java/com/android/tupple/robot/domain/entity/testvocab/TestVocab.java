package com.android.tupple.robot.domain.entity.testvocab;

import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultPresenterImpl;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class TestVocab {

    private final static String TAG = "TestVocab";

    private TestVocabPresenterHolder mTestVocabPresenterHolder;

    private TestVocabPresenter mCurrentLevelPresenter;
    private AnswerResultPresenter mAnswerResultPresenter;
    private TestProgressPresenter mTestProgressPresenter;

    public TestVocab() {
        mTestVocabPresenterHolder = new TestVocabPresenterHolder();
    }

    public void setLevel1Presenter(Level1Presenter level1Presenter) {
        mTestVocabPresenterHolder.setLevel1Presenter(level1Presenter);
        level1Presenter.setOnNextLevelObserver(this::switchLevel);
        level1Presenter.setOnResultAnswerHandler(this::handleResultAnswer);
    }

    public void setLevel2Presenter(Level2Presenter level2Presenter) {
        mTestVocabPresenterHolder.setLevel2Presenter(level2Presenter);
    }

    public void setLevel3Presenter(Level3Presenter level3Presenter) {
        mTestVocabPresenterHolder.setLevel3Presenter(level3Presenter);
        level3Presenter.setOnResultAnswerHandler(this::handleResultAnswer);
    }

    public void setAnswerResultPresenter(AnswerResultPresenterImpl answerResultPresenter) {
        mAnswerResultPresenter = answerResultPresenter;
    }

    public void setTestProgressPresenter(TestProgressPresenter testProgressPresenter) {
        this.mTestProgressPresenter = testProgressPresenter;
    }

    private void handleResultAnswer(boolean isRight, double progress) {
        if (mTestProgressPresenter != null) {
            mTestProgressPresenter.addRightAnswer(isRight, progress);
        }
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
            case LEVEL3_1:
                mCurrentLevelPresenter = mTestVocabPresenterHolder.get(TestVocabLevel.LEVEL3_1);
                break;
        }
    }

    public void init(){
        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.init();
        }
    }

    public void start() {
        if (mCurrentLevelPresenter != null) {
            mCurrentLevelPresenter.start();
        }

        if (mTestProgressPresenter != null) {
            mTestProgressPresenter.start();
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

        if (mAnswerResultPresenter != null) {
            mAnswerResultPresenter.hide();
        }

        mCurrentLevelPresenter = null;
    }

    class TestVocabPresenterHolder {

        public final String TAG = "TestVocabPresenterHolder";

        private Level1Presenter mLevel1Presenter;
        private Level2Presenter mLevel2Presenter;
        private Level3Presenter mLevel3Presenter;

        public TestVocabPresenterHolder() {
        }

        public void setLevel1Presenter(Level1Presenter level1Presenter) {
            this.mLevel1Presenter = level1Presenter;
        }

        public void setLevel2Presenter(Level2Presenter level2Presenter) {
            this.mLevel2Presenter = level2Presenter;
        }

        public void setLevel3Presenter(Level3Presenter level3Presenter) {
            this.mLevel3Presenter = level3Presenter;
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
                    presenter = mLevel3Presenter;
                    break;
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
