//package com.android.tupple.robot.utils.test.agenda;
//
//import android.content.Context;
//
//import com.samsung.android.app.cleancalendar.entity.delete.DeleteParams;
//import com.samsung.android.app.cleancalendar.entity.share.PickParams;
//import com.samsung.android.app.cleancalendar.entity.share.ShareParams;
//import com.samsung.android.app.cleancalendar.presenter.agenda.AgendaViewObservables;
//import com.samsung.android.app.cleancalendar.presenter.agenda.CompleteTaskParams;
//import com.samsung.android.app.cleancalendar.presenter.data.DragSelectParams;
//import com.samsung.android.app.cleancalendar.presenter.data.MultiDragSelectParams;
//import com.samsung.android.libcalendar.cleanobjects.CleanObservable;
//
//class AgendaViewObservablesImpl implements AgendaViewObservables {
//
//    private Context mContext;
//
//    AgendaViewObservablesImpl(Context context) {
//        mContext = context;
//    }
//
//    @Override
//    public CleanObservable getEditModeCreatedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setEditModeCreatedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getEditModeDestroyedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setEditModeDestroyedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<PickParams> getItemClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setItemClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<Integer> getItemLongClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setItemLongClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getMonthRangeBackExpandClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setMonthRangeBackExpandClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getMonthRangeAfterExpandClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setMonthRangeAfterExpandClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getTopScrolledObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setTopScrolledCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getBottomScrolledObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setBottomScrolledCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<DragSelectParams> getDragSelectedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setDragSelectedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getDragSelectEndedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setDragSelectEndedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<MultiDragSelectParams> getMultiDragSelectedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setMultiDragSelectedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<String> getSearchStringChangedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setSearchStringChangedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable getContentObserverOnChangeObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setContentObserverOnChangeCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<CompleteTaskParams> getCompleteTaskClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setCompleteTaskClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<Boolean> getAllCheckBoxClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setAllCheckBoxClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<DeleteParams> getDeleteAgendaDataClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setDeleteAgendaDataClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<ShareParams> getShareAgendaDataClickedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setShareAgendaDataClickedCleanObserver);
//    }
//
//    @Override
//    public CleanObservable<String> getStickerRequestedObservable() {
//        return CleanObservable.create(AgendaObservers.getInstance(mContext)::setStickerRequestedCleanObserver);
//    }
//}
