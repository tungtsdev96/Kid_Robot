package com.android.tupple.robot.domain.presenter.testvocab.level3;

import com.android.tupple.robot.domain.entity.testvocab.Level3ItemPresenter;
import com.android.tupple.robot.domain.entity.testvocab.Level3Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3PresenterImpl<LessonData, Topic, Vocabulary> implements Level3Presenter {

    public interface ListLearningVocabHandler<Vocabulary> {
        void onNext(List<Vocabulary> vocabularies);
    }

    private List<Level3ItemPresenter> mListLevel3ItemPresenter = new ArrayList<>();

    private Level3ViewWrapper<LessonData, Topic, Vocabulary> mLevel3ViewWrapper;
    private Level3View<LessonData, Topic, Vocabulary> mLevel3View;

    private Level3Model<LessonData, Topic, Vocabulary> mLevel3Model;
    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    private ListLearningVocabHandler<Vocabulary> mListLearningVocabHandler;

    private List<Vocabulary> mListVocabulary = new ArrayList<>();
    private int mCurrentVocabulary = -1;

    public void setLevel3Model(Level3Model<LessonData, Topic, Vocabulary> level3Model) {
        this.mLevel3Model = level3Model;
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

    }

    public void addLevel3ItemPresenter(Level3ItemPresenter item) {
        mListLevel3ItemPresenter.add(item);
    }

    @Override
    public TestVocabLevel getLevel() {
        return TestVocabLevel.LEVEL3_1;
    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        // TODO show dialog Result
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
}
