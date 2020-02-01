package com.android.tupple.robot.view.englishbook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookView;

import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookFragment extends Fragment implements EnglishBookView<SchoolBook>, BookAdapter.OnBookFragmentItemClickListener {

    public static final String TAG = "EnglishBookFragment";

    private Context mContext;
    private CleanObserver<EnglishBookView<SchoolBook>> mViewCreatedObserver;

    private CleanObserver<SchoolBook> mOnItemBookClickedObserver;
    private CleanObserver<SchoolBook> mOnItemBookLongClickedObserver;

    void setViewCreatedObserver(CleanObserver<EnglishBookView<SchoolBook>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    static EnglishBookFragment newInstance() {
        return new EnglishBookFragment();
    }

    private RecyclerView mRcvListBooks;
    private BookAdapter mBookAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_english_book, container, false);

        initView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initView(View rootView) {
        mRcvListBooks = rootView.findViewById(R.id.rcv_english_book);
        mBookAdapter = new BookAdapter(mContext);
        mRcvListBooks.setLayoutManager(new GridLayoutManager(mContext, 4));
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
    public CleanObservable<SchoolBook> getOnItemBookClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnItemBookClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<SchoolBook> getOnItemBookLongClickedObservable() {
        return CleanObservable.create(cleanObserver -> mOnItemBookClickedObserver = cleanObserver);
    }

}
