//package com.android.tupple.robot.utils.test.agenda;
//
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.samsung.android.app.calendar.R;
//import com.samsung.android.app.cleancalendar.presenter.agenda.AgendaView;
//import com.samsung.android.app.cleancalendar.presenter.agenda.AgendaViewWrapper;
//import com.samsung.android.libcalendar.cleanobjects.CleanObservable;
//import com.samsung.android.libcalendar.cleanobjects.CleanObserver;
//import com.samsung.android.libcalendar.common.data.AgendaData;
//import com.samsung.android.libcalendar.common.data.StickerItem;
//
//class AgendaViewWrapperImpl implements AgendaViewWrapper<AgendaData, StickerItem> {
//
//    private FragmentManager mFragmentManager;
//    private AgendaFragment mAgendaFragment;
//    private CleanObserver<AgendaView<AgendaData, StickerItem>> mViewCreatedObserver;
//
//    AgendaViewWrapperImpl(FragmentManager fragmentManager) {
//        mFragmentManager = fragmentManager;
//    }
//
//    @Override
//    public void show() {
//        createAgendaFragment();
//        setViewCreatedObserverOnFragment();
//    }
//
//    private void createAgendaFragment() {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        mAgendaFragment = (AgendaFragment) mFragmentManager.findFragmentByTag(AgendaFragment.TAG);
//        if (mAgendaFragment == null) {
//            mAgendaFragment = AgendaFragment.newInstance();
//        }
//        fragmentTransaction.replace(R.id.agenda_fragment, mAgendaFragment, AgendaFragment.TAG);
//        fragmentTransaction.commitAllowingStateLoss();
//    }
//
//    private void setViewCreatedObserverOnFragment() {
//        if (mViewCreatedObserver != null) {
//            mAgendaFragment.setViewCreatedObserver(mViewCreatedObserver);
//        }
//    }
//
//    @Override
//    public void hide() {
//        if (mAgendaFragment == null) {
//            throw new IllegalStateException("mAgendaFragment must not null");
//        }
//        mAgendaFragment.hide();
//    }
//
//    @Override
//    public void invalidate() {
//
//    }
//
//    @Override
//    public CleanObservable<AgendaView<AgendaData, StickerItem>> getViewCreatedObservable() {
//        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
//    }
//}
