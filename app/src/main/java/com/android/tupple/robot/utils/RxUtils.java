package com.android.tupple.robot.utils;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private RxUtils() {
        throw new AssertionError();
    }

    public static <T> ObservableTransformer<T, T> async() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> progressDialog(ProgressDialog dialog) {
        return observable -> observable.doOnSubscribe(disposable -> {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }).doFinally(() -> {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        });
    }

    public static <T> SingleTransformer<T, T> cancelableDialog(@NonNull AlertDialog dialog, @NonNull String cancelText) {
        return observable -> observable.doOnSubscribe(disposable -> {
            if (!dialog.isShowing()) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancelText, (dialog1, which) -> {
                    if (disposable.isDisposed()) {
                        return;
                    }
                    disposable.dispose();
                });
                dialog.show();
            }
        }).doFinally(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        });
    }

    public static <T> ObservableTransformer<T, T> cancelableObservableDialog(@NonNull AlertDialog dialog, @NonNull String cancelText) {
        return observable -> observable.doOnSubscribe(disposable -> {
            if (!dialog.isShowing()) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancelText, (dialog1, which) -> {
                    if (disposable.isDisposed()) {
                        return;
                    }
                    disposable.dispose();
                });
                dialog.show();
            }
        }).doFinally(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        });
    }
}
