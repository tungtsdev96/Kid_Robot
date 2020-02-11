package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;

public interface EntertainmentViewWrapper extends IView {
    CleanObservable<EntertainmentView> getViewCreatedObservable();
}
