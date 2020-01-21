package com.android.tupple.robot.domain.presenter;

/**
 * Created by tungts on 2020-01-17.
 */

public interface PresenterObserver<T> {

    void onComplete(T t);

}
