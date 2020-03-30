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

import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookViewImpl implements EnglishBookView<SchoolBook>,BookAdapter.OnBookFragmentItemClickListener {
    private Activity mActivity;
    private Bundle mBundle;
    private CleanObserver<EnglishBookView<SchoolBook>> mViewCreatedObserver;

    private CleanObserver<SchoolBook> mOnItemBookClickedObserver;
    private CleanObserver<SchoolBook> mOnItemBookLongClickedObserver;
    private CleanObserver<SchoolBook> mOnBtnDownloadClickedObserver;
    private SnapRecycleView mRcvListBooks;
    private BookAdapter mBookAdapter;

    public EnglishBookViewImpl(Activity mActivity, Bundle mBundle) {
        this.mActivity = mActivity;
        this.mBundle = mBundle;
    }

    @Override
    public void initLayout() {
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
    public void onItemBookClicked(int position) {
        if (mOnItemBookClickedObserver != null) {
            mOnItemBookClickedObserver.onNext(mBookAdapter.getBookByPosition(position));
        }
    }

    @Override
    public void onItemBookLongClicked(int positon) {
        if (mOnItemBookLongClickedObserver != null) {
            mOnItemBookLongClickedObserver.onNext(mBookAdapter.getBookByPosition(positon));
        }
    }

    @Override
    public void onButtonDownloadClicked(int position) {
        if (mOnBtnDownloadClickedObserver != null) {
            mOnBtnDownloadClickedObserver.onNext(mBookAdapter.getBookByPosition(position));
        }
    }

//    private Bundle mBundle;
//
//    private FragmentManager mFragmentManager;
//    private EnglishBookFragment mEnglishBookFragment;
//    private CleanObserver<EnglishBookView<SchoolBook>> mViewCreatedObserver;
//
//    EnglishBookViewImpl(FragmentManager fragmentManager, Bundle bundle) {
//        mFragmentManager = fragmentManager;
//        this.mBundle = bundle;
//    }
//
//    @Override
//    public CleanObservable<EnglishBookView<SchoolBook>> getViewCreatedObservable() {
//        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
//    }
//
//    @Override
//    public void show() {
//        createEnglishBookFragment();
//        setViewCreatedObserverOnFragment();
//    }
//
//    private void createEnglishBookFragment() {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        mEnglishBookFragment = (EnglishBookFragment) mFragmentManager.findFragmentByTag(EnglishBookFragment.TAG);
//        if (mEnglishBookFragment == null) {
//            mEnglishBookFragment = EnglishBookFragment.newInstance();
//        }
//        fragmentTransaction.replace(R.id.content_menu, mEnglishBookFragment, EnglishBookFragment.TAG);
//        fragmentTransaction.commitAllowingStateLoss();
//    }
//
//    private void setViewCreatedObserverOnFragment() {
//        if (mViewCreatedObserver != null) {
//            mEnglishBookFragment.setViewCreatedObserver(mViewCreatedObserver);
//        }
//    }
//
//    @Override
//    public void hide() {
//        if (mEnglishBookFragment == null) {
//
//        }
//
//        // TODO hide EnglishBookFragment
//    }
//
//    @Override
//    public void invalidate() {
//
//    }
//
//    @Override
//    public void initLayout() {
//
//    }
//
//    @Override
//    public void setListData(List<SchoolBook> listBooks) {
//
//    }
//
//    @Override
//    public CleanObservable<SchoolBook> getOnItemBookClickedObservable() {
//        return null;
//    }
//
//    @Override
//    public CleanObservable<SchoolBook> getOnItemBookLongClickedObservable() {
//        return null;
//    }
//
//    @Override
//    public CleanObservable<SchoolBook> getOnBtnDownloadClickedObservable() {
//        return null;
//    }
}
