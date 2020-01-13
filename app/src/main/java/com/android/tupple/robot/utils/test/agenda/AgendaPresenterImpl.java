//package com.android.tupple.robot.utils.test.agenda;
//
//import android.text.TextUtils;
//
//import androidx.annotation.NonNull;
//
//import com.samsung.android.app.cleancalendar.entity.PresenterObserver;
//import com.samsung.android.app.cleancalendar.entity.ReminderAppDetailButtonHandler;
//import com.samsung.android.app.cleancalendar.entity.agenda.AgendaPresenter;
//import com.samsung.android.app.cleancalendar.entity.agenda.AgendaType;
//import com.samsung.android.app.cleancalendar.entity.delete.DeleteParams;
//import com.samsung.android.app.cleancalendar.entity.detail.DetailInfo;
//import com.samsung.android.app.cleancalendar.entity.detail.SubscriptionDetailInfo;
//import com.samsung.android.app.cleancalendar.entity.share.PickParams;
//import com.samsung.android.app.cleancalendar.entity.share.ShareParams;
//import com.samsung.android.app.cleancalendar.entity.share.ShareType;
//import com.samsung.android.app.cleancalendar.presenter.DetailButtonHandler;
//import com.samsung.android.app.cleancalendar.presenter.SubscribedDetailButtonHandler;
//import com.samsung.android.app.cleancalendar.presenter.data.DragSelectParams;
//import com.samsung.android.app.cleancalendar.presenter.data.MultiDragSelectParams;
//import com.samsung.android.libcalendar.cleanobjects.Period;
//import com.samsung.android.libcalendar.cleanobjects.Time;
//import com.samsung.android.libcalendar.cleanobjects.TimeProvider;
//
//import java.util.List;
//import java.util.Optional;
//
//public class AgendaPresenterImpl<A, S> implements AgendaPresenter {
//
//    private AgendaViewWrapper<A, S> mAgendaViewWrapper;
//    private AgendaView<A, S> mAgendaView;
//    private AgendaModel<A, S> mAgendaModel;
//    private TimeProvider mTimeProvider;
//    private PresenterObserver<ShareParams> mShareObserver;
//    private PresenterObserver<DeleteParams> mDeleteObserver;
//    private DetailButtonHandler mDetailButtonHandler;
//    private ReminderAppDetailButtonHandler mReminderAppDetailButtonHandler;
//    private SubscribedDetailButtonHandler mSubscribedDetailButtonHandler;
//
//    private AgendaType mAgendaType;
//    private int mMaxPickCount;
//    private long mSelectedTimeInMillis;
//    private boolean mIsPreSetChecked;
//
//    private Period mPeriod;
//    private String mSearchString = "";
//
//    public AgendaPresenterImpl(AgendaType agendaType, int maxPickCount, long selectedTimeInMillis) {
//        mAgendaType = agendaType;
//        mMaxPickCount = maxPickCount;
//        mSelectedTimeInMillis = selectedTimeInMillis;
//    }
//
//    public void setAgendaViewWrapper(AgendaViewWrapper<A, S> agendaViewWrapper) {
//        mAgendaViewWrapper = agendaViewWrapper;
//        mAgendaViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
//    }
//
//    public void setAgendaModel(AgendaModel<A, S> agendaModel) {
//        mAgendaModel = agendaModel;
//    }
//
//    public void setTimeProvider(TimeProvider timeProvider) {
//        mTimeProvider = timeProvider;
//        setPeriod();
//    }
//
//    private void setPeriod() {
//        // 2018.1.1 00:00:00 ~ (2018.7.1 00:00:00) ~ 2018.12.31 23:59:59
//        mPeriod = new Period(mTimeProvider.now().set(mSelectedTimeInMillis).addMonth(-6).setTimeZoneID(Time.Constant.TIMEZONE_ID_UTC.value()),
//                mTimeProvider.now().set(mSelectedTimeInMillis).addMonth(6).addSecond(-1).setTimeZoneID(Time.Constant.TIMEZONE_ID_UTC.value()));
//    }
//
//    @Override
//    public void init() {
//        mAgendaViewWrapper.show();
//    }
//
//    @Override
//    public void start() {
//        requestAgendaData();
//    }
//
//    @Override
//    public void setShareObserver(PresenterObserver<ShareParams> shareObserver) {
//        mShareObserver = shareObserver;
//    }
//
//    @Override
//    public void shared(boolean isShared) {
//        mAgendaView.showSharedUI();
//        mAgendaView.stopEditMode();
//    }
//
//    @Override
//    public void setDeleteObserver(PresenterObserver<DeleteParams> deleteObserver) {
//        mDeleteObserver = deleteObserver;
//    }
//
//    @Override
//    public void deleted(boolean isSuccess) {
//        if (!isSuccess) {   // Cancel dialog
//            return;
//        }
//        mAgendaView.stopEditMode();
//        requestAgendaData();
//    }
//
//    @Override
//    public void finish() {
//        mAgendaModel.destroy();
//    }
//
//    public void setDetailButtonHandler(DetailButtonHandler detailButtonHandler) {
//        mDetailButtonHandler = detailButtonHandler;
//    }
//
//    private Optional<DetailButtonHandler> getDetailButtonHandler() {
//        return Optional.ofNullable(mDetailButtonHandler);
//    }
//
//    public void setAppEventDetailButtonHandler(ReminderAppDetailButtonHandler reminderAppDetailButtonHandler) {
//        mReminderAppDetailButtonHandler = reminderAppDetailButtonHandler;
//    }
//
//    private Optional<ReminderAppDetailButtonHandler> getAppEventDetailButtonHandler() {
//        return Optional.ofNullable(mReminderAppDetailButtonHandler);
//    }
//
//    public void setSubscribedDetailButtonHandler(SubscribedDetailButtonHandler subscribedDetailButtonHandler) {
//        mSubscribedDetailButtonHandler = subscribedDetailButtonHandler;
//    }
//
//    private Optional<SubscribedDetailButtonHandler> getSubscribedDetailButtonHandler() {
//        return Optional.ofNullable(mSubscribedDetailButtonHandler);
//    }
//
//    private void onViewCreated(AgendaView<A, S> agendaView) {
//        mAgendaView = agendaView;
//        AgendaViewObservables observables = mAgendaView.getObservables();
//        if (observables != null) {
//            initObservers(observables);
//        }
//        mAgendaView.setAgendaType(mAgendaType);
//        mAgendaView.setVoiceSearchIcon();
//        mAgendaView.setMaxPickCount(mMaxPickCount);
//        switch (mAgendaType) {
//            case NORMAL:
//            case SELECT:
//            case SELECT_TEXT:
//                mAgendaView.requestSearchViewFocus();
//                break;
//            case EDIT:
//                mAgendaView.hideSearchView();
//                break;
//            case MULTI_SELECT:
//                mAgendaView.expandActionBar();
//                mAgendaView.startEditMode();
//                break;
//        }
//    }
//
//    private void initObservers(AgendaViewObservables agendaViewObservables) {
//        agendaViewObservables.getEditModeCreatedObservable().subscribe(this::onEditModeCreated);
//        agendaViewObservables.getEditModeDestroyedObservable().subscribe(this::onEditModeDestroyed);
//        agendaViewObservables.getItemClickedObservable().subscribe(this::onItemClicked);
//        agendaViewObservables.getItemLongClickedObservable().subscribe(this::onItemLongClicked);
//        agendaViewObservables.getMonthRangeBackExpandClickedObservable().subscribe(this::onMonthRangeBackExpandClicked);
//        agendaViewObservables.getMonthRangeAfterExpandClickedObservable().subscribe(this::onMonthRangeAfterExpandClicked);
//        agendaViewObservables.getTopScrolledObservable().subscribe(this::onTopScrolled);
//        agendaViewObservables.getBottomScrolledObservable().subscribe(this::onBottomScrolled);
//        agendaViewObservables.getDragSelectedObservable().subscribe(this::onDragSelected);
//        agendaViewObservables.getDragSelectEndedObservable().subscribe(this::onDragSelectEnded);
//        agendaViewObservables.getMultiDragSelectedObservable().subscribe(this::onMultiDragSelected);
//        agendaViewObservables.getSearchStringChangedObservable().subscribe(this::onSearchStringChanged);
//        agendaViewObservables.getContentObserverOnChangeObservable().subscribe(this::onContentObserverOnChanged);
//        agendaViewObservables.getCompleteTaskClickedObservable().subscribe(this::onCompleteTaskClicked);
//        agendaViewObservables.getAllCheckBoxClickedObservable().subscribe(this::onAllCheckBoxClicked);
//        agendaViewObservables.getDeleteAgendaDataClickedObservable().subscribe(this::onDeleteAgendaDataClicked);
//        agendaViewObservables.getShareAgendaDataClickedObservable().subscribe(this::onShareAgendaDataClicked);
//        agendaViewObservables.getStickerRequestedObservable().subscribe(this::requestSticker);
//    }
//
//    private void onEditModeCreated() {
//        switch (mAgendaType) {
//            case NORMAL:
//                mAgendaType = AgendaType.EDIT;
//                mAgendaView.setAgendaType(mAgendaType);
//                mAgendaView.startCheckBoxShowAnimation();
//                mAgendaView.hideSearchView();
//                break;
//        }
//    }
//
//    private void onEditModeDestroyed() {
//        switch (mAgendaType) {
//            case EDIT:
//                mAgendaType = AgendaType.NORMAL;
//                mAgendaView.setAgendaType(mAgendaType);
//                mAgendaView.startCheckBoxHideAnimation();
//                mAgendaView.showSearchView();
//                break;
//            case MULTI_SELECT:
//                mAgendaView.hide();
//                break;
//        }
//    }
//
//    private void onItemClicked(PickParams pickParams) {
//        if (pickParams.isEventOrTask()) {
//            switch (mAgendaType) {
//                case NORMAL:
//                    if (pickParams.launchUri != null) {
//                        getSubscribedDetailButtonHandler().ifPresent(handler -> handler.handleClick(new SubscriptionDetailInfo(pickParams.rowId, pickParams.title, pickParams.launchUri)));
//                    } else {
//                        getDetailButtonHandler().ifPresent(detailButtonHandler ->
//                                detailButtonHandler.handleClick(!pickParams.isTask, new DetailInfo(Math.abs(pickParams.rowId), pickParams.startMillis, pickParams.endMillis)));
//                    }
//                    break;
//                case EDIT:
//                    mAgendaView.setChecked(pickParams);
//                    mAgendaView.refreshEditMode(true);
//                    break;
//                case SELECT:
//                    mShareObserver.onNext(pickParams.toShareParams(ShareType.PICK_ID));
//                    break;
//                case SELECT_TEXT:
//                    mShareObserver.onNext(pickParams.toShareParams(ShareType.PICK_TEXT));
//                    break;
//                case MULTI_SELECT:
//                    mAgendaView.setChecked(pickParams);
//                    mAgendaView.refreshEditMode(true);
//                    break;
//            }
//        } else {
//            switch (mAgendaType) {
//                case NORMAL:
//                    getAppEventDetailButtonHandler().ifPresent(reminderAppDetailButtonHandler ->
//                            reminderAppDetailButtonHandler.handleClick(pickParams.launchUri));
//                    break;
//            }
//        }
//    }
//
//    private void onItemLongClicked(int position) {
//        switch (mAgendaType) {
//            case NORMAL:
//                mAgendaView.startEditMode();
//                mAgendaView.enableDragSelect(position);
//                break;
//            case EDIT:
//                mAgendaView.enableDragSelect(position);
//                break;
//            case MULTI_SELECT:
//                mAgendaView.enableDragSelect(position);
//                break;
//        }
//    }
//
//    private void onMonthRangeBackExpandClicked() {
//        if (isEditMode()) {
//            return;
//        }
//        mPeriod.start.addMonth(-6);
//        requestAgendaData();
//    }
//
//    private void onMonthRangeAfterExpandClicked() {
//        if (isEditMode()) {
//            return;
//        }
//        mPeriod.end.addMonth(6);
//        requestAgendaData();
//    }
//
//    private void onTopScrolled() {
//        onMonthRangeBackExpandClicked();
//    }
//
//    private void onBottomScrolled() {
//        onMonthRangeAfterExpandClicked();
//    }
//
//    private void onDragSelected(DragSelectParams dragSelectParams) {
//        mAgendaView.setChecked(dragSelectParams.itemId, dragSelectParams.isSelected);
//        if (isEditMode()) {
//            mAgendaView.refreshEditMode(false);
//        }
//    }
//
//    private void onDragSelectEnded() {
//        if (isEditMode()) {
//            mAgendaView.refreshEditMode(true);
//        }
//    }
//
//    private void onMultiDragSelected(MultiDragSelectParams multiDragSelectParams) {
//        mAgendaView.setChecked(multiDragSelectParams.startPosition, multiDragSelectParams.endPosition);
//        if (isEditMode()) {
//            mAgendaView.refreshEditMode(true);
//        }
//    }
//
//    private void onSearchStringChanged(String searchString) {
//        mSearchString = searchString;
//        requestAgendaData();
//    }
//
//    private void onContentObserverOnChanged() {
//        if (isEditMode()) {
//            return;
//        }
//        requestAgendaData();
//    }
//
//    private void onCompleteTaskClicked(CompleteTaskParams completeTaskParams) {
//        mAgendaModel.completeTask(completeTaskParams.id, completeTaskParams.isCompleted);
//    }
//
//    private void onAllCheckBoxClicked(boolean isChecked) {
//        mAgendaView.setAllChecked(isChecked);
//        mAgendaView.refreshEditMode(true);
//    }
//
//    private void onDeleteAgendaDataClicked(DeleteParams deleteParams) {
//        mDeleteObserver.onNext(deleteParams);
//    }
//
//    private void onShareAgendaDataClicked(ShareParams shareParams) {
//        mShareObserver.onNext(shareParams);
//    }
//
//    private boolean isEditMode() {
//        return mAgendaType == AgendaType.EDIT || mAgendaType == AgendaType.MULTI_SELECT;
//    }
//
//    private void requestAgendaData() {
//        mAgendaView.showProgress();
//        switch (mAgendaType) {
//            case NORMAL:
//            case EDIT:
//            case SELECT:
//            case SELECT_TEXT:
//                mAgendaModel.getIsAgendaDataEmpty(mAgendaType, getBackPeriod(mPeriod)).subscribe(isBackAgendaDataEmpty ->
//                        mAgendaModel.getIsAgendaDataEmpty(mAgendaType, getAfterPeriod(mPeriod)).subscribe(isAfterAgendaDataEmpty -> {
//                            if (isBackAgendaDataEmpty) {
//                                mPeriod.start.set(getMinTime());
//                            }
//                            if (isAfterAgendaDataEmpty) {
//                                mPeriod.end.set(getMaxTime());
//                            }
//                            mAgendaModel.getAgendaDataList(mAgendaType, mPeriod, mSearchString).subscribe(this::setAgendaData);
//                        }));
//                break;
//            case MULTI_SELECT:
//                mPeriod.start.set(getMinTime());
//                mPeriod.end.set(getMaxTime());
//                mAgendaModel.getAgendaDataList(mAgendaType, mPeriod, mSearchString).subscribe(this::setAgendaData);
//                break;
//        }
//
//    }
//
//    @NonNull
//    private Period getBackPeriod(Period period) {
//        return new Period(getMinTime(), period.getClone().start.addDay(-1));
//    }
//
//    private Time getMinTime() {
//        return mTimeProvider.now().set(-2145963600000L);
//    }
//
//    @NonNull
//    private Period getAfterPeriod(Period period) {
//        return new Period(period.getClone().end.addDay(1), getMaxTime());
//    }
//
//    private Time getMaxTime() {
//        return mTimeProvider.now().set(2114420340000L);
//    }
//
//    private void setAgendaData(List<A> agendaDataList) {
//        mAgendaView.setAgendaDataList(agendaDataList, mPeriod.start.getTimeInMillis(), mPeriod.end.getTimeInMillis(), mSearchString);
//        mAgendaView.restoreInstanceState();
//        preSetChecked(agendaDataList.size());
//        if (isEditMode()) {
//            mAgendaView.refreshEditMode(true);
//        }
//        if (isSelectedTimeScrollEnabled()) {
//            mAgendaView.scrollToSelectedTime(mSelectedTimeInMillis);
//            setSelectedTimeScrollEnabled(false);
//        }
//        mAgendaView.setPenSelectionEnabled(agendaDataList.size() > 0);
//        mAgendaView.hideProgress();
//    }
//
//    private void preSetChecked(int agendaDataListSize) {
//        if (!mIsPreSetChecked && mAgendaType == AgendaType.MULTI_SELECT && agendaDataListSize == 1) {
//            mAgendaView.setAllChecked(true);
//        }
//        mIsPreSetChecked = true;
//    }
//
//    private boolean isSelectedTimeScrollEnabled() {
//        return mSelectedTimeInMillis != Long.MIN_VALUE;
//    }
//
//    private void setSelectedTimeScrollEnabled(boolean enabled) {
//        mSelectedTimeInMillis = enabled ? mTimeProvider.now().getTimeInMillis() : Long.MIN_VALUE;
//    }
//
//    private void requestSticker(String stickerId) {
//        if (TextUtils.isEmpty(stickerId)) {
//            return;
//        }
//        mAgendaModel.getStickerData(stickerId).subscribe(mAgendaView::setSticker);
//    }
//
//    @Override
//    public void setVoiceSearchQuery(String query) {
//        if (mAgendaType == AgendaType.NORMAL) {
//            mAgendaView.setVoiceSearchQuery(query);
//        }
//    }
//
//    @Override
//    public void onKeyDown(int keyCode) {
//        mAgendaView.onKeyDown(keyCode);
//    }
//
//    @Override
//    public void onKeyUp(int keyCode) {
//        mAgendaView.onKeyUp(keyCode);
//    }
//}
