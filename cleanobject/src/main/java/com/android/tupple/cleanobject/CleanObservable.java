package com.android.tupple.cleanobject;

public class CleanObservable<T> {

    private ObservableOnSubscribe<T> mObservableOnSubscribe;

    private CleanObservable(ObservableOnSubscribe<T> observableOnSubscribe) {
        mObservableOnSubscribe = observableOnSubscribe;
    }

    public static <T> CleanObservable<T> create(ObservableOnSubscribe<T> observableOnSubscribe) {
        return new CleanObservable<>(observableOnSubscribe);
    }

    public Disposable subscribe(VoidConsumer onNext) {
        if (mObservableOnSubscribe != null) {
            mObservableOnSubscribe.subscribe(new CleanObserver<T>() {
                @Override
                public void onNext() {
                    onNext.accept();
                }

                @Override
                public void onNext(T t) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {
                    onNext.accept();
                }
            });
        }
        return () -> mObservableOnSubscribe = null;
    }

    public Disposable subscribe(Consumer<T> onNext) {
        if (mObservableOnSubscribe != null) {
            mObservableOnSubscribe.subscribe(new CleanObserver<T>() {
                @Override
                public void onNext() {

                }

                @Override
                public void onNext(T t) {
                    onNext.accept(t);
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
        return () -> mObservableOnSubscribe = null;
    }

    public Disposable subscribe(Consumer<T> onNext, Consumer<Throwable> onError) {
        if (mObservableOnSubscribe != null) {
            mObservableOnSubscribe.subscribe(new CleanObserver<T>() {
                @Override
                public void onNext() {

                }

                @Override
                public void onNext(T t) {
                    onNext.accept(t);
                }

                @Override
                public void onError(Throwable t) {
                    onError.accept(t);
                }

                @Override
                public void onComplete() {

                }
            });
        }
        return () -> mObservableOnSubscribe = null;
    }

    public Disposable subscribe(Consumer<T> onNext, Consumer<Throwable> onError, VoidConsumer onComplete) {
        if (mObservableOnSubscribe != null) {
            mObservableOnSubscribe.subscribe(new CleanObserver<T>() {
                @Override
                public void onNext() {

                }

                @Override
                public void onNext(T t) {
                    onNext.accept(t);
                }

                @Override
                public void onError(Throwable t) {
                    onError.accept(t);
                }

                @Override
                public void onComplete() {
                    onComplete.accept();
                }
            });
        }
        return () -> mObservableOnSubscribe = null;
    }

    public interface VoidConsumer {
        void accept();
    }

    public interface Consumer<T> {
        void accept(T t);
    }

    public interface ObservableOnSubscribe<T> {
        void subscribe(CleanObserver<T> cleanObserver);
    }
}
