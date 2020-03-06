/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.tupple.robot.utils;

import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.VisibleForTesting;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class SingleClickUtil {

    private static final int DEFAULT_SKIP_DURATION = 500;

    private SingleClickUtil() {
    }

    public static Disposable registerListener(View v, View.OnClickListener clickListener) {
        return registerListener(v, clickListener, DEFAULT_SKIP_DURATION);
    }

    public static Disposable registerListener(View v, View.OnClickListener clickListener, final long skipDuration) {
        if (v == null || clickListener == null || skipDuration < 0) {
            return Disposables.empty();
        }
        return singleClickObservable(v, AndroidSchedulers.mainThread(), skipDuration)
                .subscribe(aVoid -> clickListener.onClick(v));
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    /* package */ static Observable<Object> singleClickObservable(View v, Scheduler scheduler, final long skipDuration) {
        return RxView.clicks(v)
                .throttleFirst(skipDuration, TimeUnit.MILLISECONDS, scheduler)
                .observeOn(scheduler);
    }

    public static Disposable registerBlockDoubleClickListener(View v, View.OnClickListener clickListener) {
        return registerBlockDoubleClickListener(v, clickListener, ViewConfiguration.getDoubleTapTimeout());
    }

    public static Disposable registerBlockDoubleClickListener(View v, View.OnClickListener clickListener, final long skipDuration) {
        if (v == null || clickListener == null || skipDuration < 0) {
            return Disposables.empty();
        }
        return doubleClickObservable(v, AndroidSchedulers.mainThread(), skipDuration)
                .subscribe(aVoid -> clickListener.onClick(v));
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    /* package */ static Observable<Object> doubleClickObservable(View v, Scheduler scheduler, final long skipDuration) {
        return RxView.clicks(v)
                .throttleLast(skipDuration, TimeUnit.MILLISECONDS, scheduler)
                .observeOn(scheduler);
    }
}
