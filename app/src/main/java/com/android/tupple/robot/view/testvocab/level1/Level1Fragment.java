package com.android.tupple.robot.view.testvocab.level1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1Fragment extends Fragment {

    public final static String TAG = "Level1Fragment";

    private Context mContext;

    private CleanObserver mViewCreatedObserver;

    public static Level1Fragment newInstance() {
        return new Level1Fragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_vocab_level_1, container, false);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext();
        }
        return rootView;
    }

    public void setViewCreatedObserver(CleanObserver viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }
}
