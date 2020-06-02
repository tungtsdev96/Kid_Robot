package com.android.tupple.robot.view.englishbook;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookView;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookViewImpl implements EnglishBookView<SchoolBook>, BookAdapter.OnBookFragmentItemClickListener {

    private Activity mActivity;
    private Bundle mBundle;

    private FloatingActionButton mFabClose;
    private SnapRecycleView mRcvListBooks;
    private BookAdapter mBookAdapter;

    private CleanObserver<SchoolBook> mOnItemBookClickedObserver;
    private CleanObserver<SchoolBook> mOnItemBookLongClickedObserver;
    private CleanObserver<SchoolBook> mOnBtnDownloadClickedObserver;
    private CleanObserver mOnBtnCloseClickedObserver;

    public EnglishBookViewImpl(Activity mActivity, Bundle mBundle) {
        this.mActivity = mActivity;
        this.mBundle = mBundle;
    }

    @Override
    public void initLayout() {
        mFabClose = mActivity.findViewById(R.id.btn_close);
        SingleClickUtil.registerListener(mFabClose, v -> {
            if (mOnBtnCloseClickedObserver != null) {
                mOnBtnCloseClickedObserver.onNext();
            }
        });

        mRcvListBooks = mActivity.findViewById(R.id.rcv_english_book);
        mBookAdapter = new BookAdapter(mActivity);
        mRcvListBooks.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false));
        mRcvListBooks.setAdapter(mBookAdapter);
        mBookAdapter.setOnBookFragmentItemClickListener(this);
    }

    @Override
    public void setListData(List<SchoolBook> listBooks) {
        mBookAdapter.setListBooks(listBooks);
    }

    @Override
    public void onItemBookClicked(int position) {
        if (mOnItemBookClickedObserver != null) {
            mOnItemBookClickedObserver.onNext(mBookAdapter.getBookByPosition(position));
        }
    }

    @Override
    public void onItemBookLongClicked(int position) {
        if (mOnItemBookLongClickedObserver != null) {
            mOnItemBookLongClickedObserver.onNext(mBookAdapter.getBookByPosition(position));
        }
    }

    @Override
    public void onButtonDownloadClicked(int position) {
        if (mOnBtnDownloadClickedObserver != null) {
            mOnBtnDownloadClickedObserver.onNext(mBookAdapter.getBookByPosition(position));
        }
    }

    @Override
    public CleanObservable<SchoolBook> getOnItemBookClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnItemBookClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<SchoolBook> getOnItemBookLongClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnItemBookClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<SchoolBook> getOnBtnDownloadClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnBtnDownloadClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnBtnCloseClickedObserver = cleanObserver);
    }

}
