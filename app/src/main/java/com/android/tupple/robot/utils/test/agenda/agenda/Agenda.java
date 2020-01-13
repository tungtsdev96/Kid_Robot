//package com.android.tupple.robot.utils.test.agenda.agenda;
//
//import com.samsung.android.app.cleancalendar.entity.delete.Deletable;
//import com.samsung.android.app.cleancalendar.entity.delete.DeleteParams;
//import com.samsung.android.app.cleancalendar.entity.delete.DeletePresenter;
//import com.samsung.android.app.cleancalendar.entity.share.Sharable;
//import com.samsung.android.app.cleancalendar.entity.share.ShareParams;
//import com.samsung.android.app.cleancalendar.entity.share.SharePresenter;
//
//public class Agenda implements Sharable, Deletable {
//
//    private AgendaPresenter mAgendaPresenter;
//    private SharePresenter mSharePresenter;
//    private DeletePresenter mDeletePresenter;
//
//    public void setAgendaPresenter(AgendaPresenter agendaPresenter) {
//        mAgendaPresenter = agendaPresenter;
//        if (mAgendaPresenter != null) {
//            mAgendaPresenter.setShareObserver(this::share);
//            mAgendaPresenter.setDeleteObserver(this::delete);
//        }
//    }
//
//    @Override
//    public void setSharePresenter(SharePresenter sharePresenter) {
//        mSharePresenter = sharePresenter;
//        if (mSharePresenter != null) {
//            mSharePresenter.setSharedObserver(this::shared);
//        }
//    }
//
//    @Override
//    public void setDeletePresenter(DeletePresenter deletePresenter) {
//        mDeletePresenter = deletePresenter;
//        if (mDeletePresenter != null) {
//            mDeletePresenter.setDeletedObserver(this::deleted);
//        }
//    }
//
//    public void init() {
//        if (mAgendaPresenter == null || mSharePresenter == null || mDeletePresenter == null) {
//            throw new IllegalStateException("Presenter is not set");
//        }
//        mAgendaPresenter.init();
//    }
//
//    public void start() {
//        if (mAgendaPresenter == null || mSharePresenter == null || mDeletePresenter == null) {
//            throw new IllegalStateException("Presenter is not set");
//        }
//        mAgendaPresenter.start();
//    }
//
//    public void finish() {
//        mAgendaPresenter.finish();
//        mSharePresenter.finish();
//        mDeletePresenter.finish();
//    }
//
//    private void share(ShareParams shareParams) {
//        mSharePresenter.share(shareParams);
//    }
//
//    private void shared(boolean isShared) {
//        mAgendaPresenter.shared(isShared);
//    }
//
//    private void delete(DeleteParams deleteParams) {
//        mDeletePresenter.delete(deleteParams);
//    }
//
//    private void deleted(boolean isSuccess) {
//        mAgendaPresenter.deleted(isSuccess);
//    }
//
//    public void setVoiceSearchQuery(String query) {
//        mAgendaPresenter.setVoiceSearchQuery(query);
//    }
//
//    public void onKeyDown(int keyCode) {
//        mAgendaPresenter.onKeyDown(keyCode);
//    }
//
//    public void onKeyUp(int keyCode) {
//        mAgendaPresenter.onKeyUp(keyCode);
//    }
//}
