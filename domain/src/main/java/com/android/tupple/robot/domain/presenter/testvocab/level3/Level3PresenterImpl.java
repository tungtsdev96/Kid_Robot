package com.android.tupple.robot.domain.presenter.testvocab.level3;

import com.android.tupple.robot.domain.entity.testvocab.Level3ItemPresenter;
import com.android.tupple.robot.domain.entity.testvocab.Level3Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.testvocab.TestVocabModel;
import com.android.tupple.robot.domain.presenter.data.LearningVocabModel;
import com.android.tupple.robot.domain.presenter.testvocab.ResultAnswerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3PresenterImpl<LessonData, Topic, Vocabulary> implements Level3Presenter {

    private List<Level3ItemPresenter> mListLevel3ItemPresenter = new ArrayList<>();

    private Level3ViewWrapper<LessonData, Topic, Vocabulary> mLevel3ViewWrapper;
    private Level3View<LessonData, Topic, Vocabulary> mLevel3View;

    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    private ListLearningVocabHandler<Vocabulary> mListLearningVocabHandler;
    private PresenterObserver<TestVocabLevel> mOnNextLevelPresenterObserver;
    private PageChangeListenerHandler mOnPageChangeListenerHandler;

    private List<Vocabulary> mListVocabulary = new ArrayList<>();
    private int mCurrentVocabulary = -1;

    private ResultAnswerHandler mOnResultAnswerHandler;

    public Level3PresenterImpl(){}

    public interface ListLearningVocabHandler<Vocabulary> {
        void onNext(List<Vocabulary> vocabularies);
    }

    public interface PageChangeListenerHandler {
        void onPageChange(int previousPage, int pageSelected);
    }

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = testVocabModel;
    }

    public void setLearningVocabModel(LearningVocabModel<Vocabulary> learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    public void setLevel3ViewWrapper(Level3ViewWrapper<LessonData, Topic, Vocabulary> level3ViewWrapper) {
        this.mLevel3ViewWrapper = level3ViewWrapper;
        this.mLevel3ViewWrapper.getViewCreatedObservable().subscribe(this::setLevel3View);
    }

    public void setLevel3View(Level3View<LessonData, Topic, Vocabulary> level3View) {
        this.mLevel3View = level3View;
        initObservable();
        loadData();
    }

    private void initObservable() {
        mLevel3View.getBtnPreviousClickedObservable().subscribe(this::handleOnPreviousBtnClicked);
        mLevel3View.getBtnNextClickedObservable().subscribe(this::handleOnNextBtnClicked);
        mLevel3View.getPageChangeObservable().subscribe(this::handlePageChangeListener);
    }

    private void handlePageChangeListener(Integer position) {
        if (mCurrentVocabulary == position) {
            return;
        }

        if (getPageChangeListenerHandler() != null) {
           getPageChangeListenerHandler().onPageChange(mCurrentVocabulary, position);
        }

        mListLevel3ItemPresenter.get(mCurrentVocabulary).onPageChange();
        mListLevel3ItemPresenter.get(position).onPageSelected();
        mCurrentVocabulary = position;
    }

    private void handleOnNextBtnClicked() {
        if (mCurrentVocabulary >= mListVocabulary.size() - 1) {
            mLearningVocabModel.updateLearnLessonDone().subscribe(isSuccess -> {
                mOnNextLevelPresenterObserver.onComplete(TestVocabLevel.LEVEL3_1);
            });
        } else {
            boolean isCanNext = mListLevel3ItemPresenter.get(mCurrentVocabulary).checkCanNextVocab();
            if (!isCanNext) {
                return;
            }

            mLevel3View.setCurrentVocab(mCurrentVocabulary + 1);
            if (mOnResultAnswerHandler != null) {
                mOnResultAnswerHandler.onResult(true, 1);
            }
        }
    }

    private void handleOnPreviousBtnClicked() {
        if (mCurrentVocabulary <= 0) {
            return;
        }

        mLevel3View.setCurrentVocab(mCurrentVocabulary - 1);
        if (mOnResultAnswerHandler != null) {
            mOnResultAnswerHandler.onResult(true, -1);
        }
    }

    public void addLevel3ItemPresenter(Level3ItemPresenter item) {
        mListLevel3ItemPresenter.add(item);
    }

    @Override
    public TestVocabLevel getLevel() {
        return TestVocabLevel.LEVEL3_1;
    }

    @Override
    public void nextQuestion() {

    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        this.mOnNextLevelPresenterObserver = onNextLevelObserver;
    }

    @Override
    public void setOnResultAnswerHandler(ResultAnswerHandler onResultAnswerHandler) {
        this.mOnResultAnswerHandler = onResultAnswerHandler;
    }

    @Override
    public void init() {
        mLevel3ViewWrapper.show();
    }

    @Override
    public void start() {
    }

    private void loadData() {
        mListVocabulary.addAll(mLearningVocabModel.getListVocabLearning());
        if (getListLearningVocabHandler() != null) {
            getListLearningVocabHandler().onNext(mListVocabulary);
        }

        mCurrentVocabulary = 0;
        mLevel3View.setListLearningVocab(mListVocabulary);
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

    public ListLearningVocabHandler<Vocabulary> getListLearningVocabHandler() {
        return mListLearningVocabHandler;
    }

    public void setListLearningVocabHandler(ListLearningVocabHandler<Vocabulary> listLearningVocabHandler) {
        this.mListLearningVocabHandler = listLearningVocabHandler;
    }

    public PageChangeListenerHandler getPageChangeListenerHandler() {
        return mOnPageChangeListenerHandler;
    }

    public void setPageChangeListenerHandler(PageChangeListenerHandler pageChangeListenerHandler) {
        this.mOnPageChangeListenerHandler = pageChangeListenerHandler;
    }
}
