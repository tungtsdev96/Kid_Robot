package com.android.tupple.robot.view.englishbook;

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
import com.android.tupple.robot.commondata.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookView;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookFragment extends Fragment implements EnglishBookView<SchoolBook> {

    public static final String TAG = "EnglishBookFragment";

    private Context mContext;
    private CleanObserver<EnglishBookView<SchoolBook>> mViewCreatedObserver;

    public void setViewCreatedObserver(CleanObserver<EnglishBookView<SchoolBook>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    public static EnglishBookFragment newInstance() {
        return new EnglishBookFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_english_book, container, false);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }
}
