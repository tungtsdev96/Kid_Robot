package com.android.tupple.robot.domain.log;

import androidx.annotation.NonNull;

/**
 * Created by tung.ts on 1/15/2020.
 */

public interface CLogger {

    void printSecD(@NonNull String tag, @NonNull String text);

    void printD(@NonNull String tag, @NonNull String text);

    void printSecE(@NonNull String tag, @NonNull String text);

    void printE(@NonNull String tag, @NonNull String text);
}