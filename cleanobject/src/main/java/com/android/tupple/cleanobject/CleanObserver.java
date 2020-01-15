package com.android.tupple.cleanobject;

public interface CleanObserver<T> {
    void onNext();

    void onNext(T t);

    void onError(Throwable t);

    void onComplete();
}
