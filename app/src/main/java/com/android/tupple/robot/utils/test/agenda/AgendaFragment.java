//package com.android.tupple.robot.utils.test.agenda;
//
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.Intent;
//import android.database.ContentObserver;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Parcelable;
//import android.provider.CalendarContract;
//import android.text.InputFilter;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.samsung.android.app.calendar.R;
//import com.samsung.android.app.calendar.salog.view.aganda.SaAgendaFragment;
//import com.samsung.android.app.calendar.utils.ShareViaHelper;
//import com.samsung.android.app.cleancalendar.entity.agenda.AgendaType;
//import com.samsung.android.app.cleancalendar.entity.share.PickParams;
//import com.samsung.android.app.cleancalendar.entity.share.ShareParams;
//import com.samsung.android.app.cleancalendar.entity.share.ShareType;
//import com.samsung.android.app.cleancalendar.presenter.agenda.AgendaView;
//import com.samsung.android.app.cleancalendar.presenter.agenda.AgendaViewObservables;
//import com.samsung.android.app.cleancalendar.presenter.agenda.CompleteTaskParams;
//import com.samsung.android.app.cleancalendar.presenter.data.DragSelectParams;
//import com.samsung.android.calendarutils.ActivityModeUtils;
//import com.samsung.android.calendarutils.StringUtils;
//import com.samsung.android.calendarutils.ToastUtils;
//import com.samsung.android.calendarutils.support.global.android.view.ViewGlobalCompat;
//import com.samsung.android.libcalendar.cleanobjects.CleanObserver;
//import com.samsung.android.libcalendar.common.constant.AgendaConstants;
//import com.samsung.android.libcalendar.common.data.AgendaData;
//import com.samsung.android.libcalendar.common.data.StickerItem;
//import com.samsung.android.libcalendar.common.info.MimeType;
//import com.samsung.android.libcalendar.common.utils.Feature;
//import com.samsung.android.libcalendar.common.utils.ImmUtils;
//import com.samsung.android.libcalendar.common.utils.LimitContract;
//import com.samsung.android.libcalendar.common.utils.RoundCornerUtils;
//import com.samsung.android.libcalendar.common.utils.SingleClickUtil;
//import com.samsung.android.libcalendar.common.utils.ViewUtils;
//import com.samsung.android.libcalendar.platform.permission.PermissionChecker;
//import com.samsung.android.libcalendar.platform.permission.model.calendar.CalendarGroupPermission;
//import com.samsung.android.libcalendar.platform.utils.HoverUtils;
//import com.samsung.android.libcalendar.platform.utils.MenuUtils;
//import com.samsung.android.libcalendar.platform.utils.RecyclerViewUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javax.annotation.Nonnull;
//
//public class AgendaFragment extends SaAgendaFragment implements AgendaView<AgendaData, StickerItem>, OnAgendaFragmentListener {
//
//    public static final String TAG = "AgendaFragment";
//
//    private Context mContext;
//    private Bundle mSavedInstanceState;
//    private AgendaRecyclerView mRecyclerView;
//    private AgendaAdapter mAgendaAdapter;
//    private SearchView mSearchView;
//    private LinearLayout mExpandedActionBarLayout;
//    private int mRecyclerViewHeight = 0;
//    private AgendaType mAgendaType;
//    private boolean mIsNeedToSendSALog = true;
//    private ProgressBar mProgressBar;
//    private int mDefaultGoToTopBottomPadding;
//    private boolean mIsConfirmKeyPressed;
//
//    private AgendaActionMode mAgendaActionMode;
//    private BottomNavigationView mAgendaSearchBottomNavigationView;
//    private BottomNavigationView mAgendaPickBottomNavigationView;
//    private View mAgendaBottomNavigationUpperCorner;
//
//    private final ContentObserver mContentObserver = new ContentObserver(new Handler()) {
//        @Override
//        public boolean deliverSelfNotifications() {
//            return true;
//        }
//
//        @Override
//        public void onChange(boolean selfChange) {
//            AgendaObservers.getInstance(getContext()).getContentObserverOnChangeCleanObserver().ifPresent(CleanObserver::onNext);
//        }
//    };
//
//    private CleanObserver<AgendaView<AgendaData, StickerItem>> mViewCreatedObserver;
//
//    Optional<CleanObserver<AgendaView<AgendaData, StickerItem>>> getViewCreatedObserver() {
//        return Optional.ofNullable(mViewCreatedObserver);
//    }
//
//    void setViewCreatedObserver(CleanObserver<AgendaView<AgendaData, StickerItem>> viewCreatedObserver) {
//        mViewCreatedObserver = viewCreatedObserver;
//    }
//
//    static AgendaFragment newInstance() {
//        return new AgendaFragment();
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mContext = context;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        insertSearchViewFlowSALogging(isPickerMode());
//        PermissionChecker.with(getActivity()).permissions(CalendarGroupPermission.PERMISSIONS)
//                .setListener(granted -> mContext.getContentResolver().registerContentObserver(CalendarContract.Events.CONTENT_URI, true, mContentObserver))
//                .check();
//    }
//
//    @Override
//    public void onPause() {
//        mContext.getContentResolver().unregisterContentObserver(mContentObserver);
//        super.onPause();
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        /* List additional value not saved by RetainedFragment */
//        outState.putString(AgendaConstants.SAVED_INSTANCE_SEARCH_STRING, mSearchView.getQuery().toString());
//        outState.putBoolean(AgendaConstants.SAVED_INSTANCE_ACTION_MODE, mAgendaAdapter.isActionMode());
//        outState.putSerializable(AgendaConstants.SAVED_INSTANCE_CHECKED_ITEM, mAgendaAdapter.getCheckedItemIds());
//        outState.putParcelable(AgendaConstants.SAVED_INSTANCE_LAYOUT_MANAGER, mRecyclerView.getLayoutManager().onSaveInstanceState());
//        outState.putBoolean(AgendaConstants.SAVED_INSTANCE_SIP_SHOWN, ImmUtils.isInputMethodShown(getContext()));
//    }
//
//    @Override
//    public void restoreInstanceState() {
//        if (mSavedInstanceState == null) {
//            return;
//        }
//        boolean isActionMode = mSavedInstanceState.getBoolean(AgendaConstants.SAVED_INSTANCE_ACTION_MODE, false);
//        if (isActionMode) {
//            onActionModeDestroyed();
//            startEditMode();
//            ArrayList<Long> checkedItemIds = (ArrayList<Long>) mSavedInstanceState.getSerializable(AgendaConstants.SAVED_INSTANCE_CHECKED_ITEM);
//            if (checkedItemIds != null && !checkedItemIds.isEmpty()) {
//                if (mAgendaAdapter != null) {
//                    checkedItemIds.forEach(checkedItemId -> mAgendaAdapter.setChecked(checkedItemId, true));
//                    mAgendaAdapter.notifyDataSetChanged();
//                }
//            }
//            if (mAgendaType == AgendaType.MULTI_SELECT) {
//                restoreMultiSelectMode();
//            }
//        }
//        Parcelable layoutManagerInstanceState = mSavedInstanceState.getParcelable(AgendaConstants.SAVED_INSTANCE_LAYOUT_MANAGER);
//        if (layoutManagerInstanceState != null) {
//            mRecyclerView.getLayoutManager().onRestoreInstanceState(layoutManagerInstanceState);
//        }
//        mSavedInstanceState = null;
//    }
//
//    private void restoreMultiSelectMode() {
//        if (mAgendaAdapter != null && mAgendaAdapter.getItemCount() == 1
//                && mAgendaAdapter.getItemViewType(0) == AgendaConstants.AGENDA_ITEM_TYPE.EMPTY) {
//            mAgendaActionMode.setEnabledSelectAll(false);
//        }
//        clearActionBarSearchViewFocus();
//        requestSearchViewFocus();
//    }
//
//    private void clearActionBarSearchViewFocus() {
//        final AppCompatActivity activity = (AppCompatActivity) getActivity();
//        final ActionBar actionBar = (activity == null) ? null : activity.getSupportActionBar();
//        final View customView = (actionBar == null) ? null : actionBar.getCustomView();
//        final SearchView searchView = (customView == null) ? null : customView.findViewById(R.id.search_view);
//        if (searchView != null) {
//            searchView.clearFocus();
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@Nonnull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mSavedInstanceState = savedInstanceState;
//
//        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
//
//        initActionBar();
//        initAdapter();
//        initRecyclerView(view);
//        initActionMode(view);
//        initProgressBar(view);
//
//        getViewCreatedObserver().ifPresent(observer -> observer.onNext(this));
//        return view;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        AgendaObservers.removeInstance(getContext());
//    }
//
//    private void initActionBar() {
//        setHasOptionsMenu(true);
//        final AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if (activity == null) {
//            return;
//        }
//        activity.setTitle(" ");
//        Toolbar toolbar = activity.findViewById(R.id.toolbar);
//        toolbar.setContentInsetsAbsolute(0, 0);
//        activity.setSupportActionBar(toolbar);
//        boolean isMultiplePick = activity.getIntent().getBooleanExtra(AgendaConstants.EXTRA_MULTIPLE_PICK, false);
//        if (isMultiplePick) {
//            return;
//        }
//        ActionBar actionBar = activity.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setCustomView(R.layout.view_agenda_action_bar);
//            mSearchView = actionBar.getCustomView().findViewById(R.id.search_view);
//            if (mSearchView != null) {
//                mSearchView.setOnQueryTextListener(mOnQueryTextListener);
//                setCloseButtonOnClickListener(mSearchView);
//                setVoiceButtonOnTouchListener(mSearchView);
//                limitSearchTextLength(mContext, mSearchView);
//                mSearchView.seslGetUpButton().setVisibility(View.VISIBLE);
//                SingleClickUtil.registerListener(mSearchView.seslGetUpButton(), v -> {
//                    insertSALoggingOnNavigateUpButtonClicked();
//                    if (activity != null && !activity.isFinishing()) {
//                        activity.onBackPressed();
//                    }
//                });
//                mSearchView.post(() -> {
//                    if (mSavedInstanceState != null) {
//                        mSearchView.setQuery(mSavedInstanceState.getString(AgendaConstants.SAVED_INSTANCE_SEARCH_STRING, ""), false);
//                        if (isRestoredHideInputMethod()) {
//                            mSearchView.clearFocus();
//                        }
//                    }
//                });
//                mSearchView.clearFocus();
//            }
//        }
//    }
//
//    private void setCloseButtonOnClickListener(SearchView searchView) {
//        ImageView closeButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
//        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//        if (closeButton != null && searchAutoComplete != null) {
//            closeButton.setOnClickListener(v -> {
//                searchAutoComplete.setText("");
//                searchAutoComplete.requestFocus();
//                ImmUtils.showInputMethod(getContext(), searchAutoComplete);
//                insertSearViewCloseButtonSALogging();
//            });
//        }
//    }
//
//    private void setVoiceButtonOnTouchListener(SearchView searchView) {
//        ImageView voiceButton = searchView.findViewById(androidx.appcompat.R.id.search_voice_btn);
//        if (voiceButton != null) {
//            voiceButton.setOnTouchListener((v, event) -> {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    ImmUtils.forceHideInputMethodIfShown(getActivity());
//                }
//                return false;
//            });
//        }
//    }
//
//    private void limitSearchTextLength(Context context, SearchView searchView) {
//        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//        if (searchAutoComplete != null) {
//            searchAutoComplete.setFilters(new InputFilter[]{new LimitContract.LengthFilter(context, LimitContract.EDIT_MAX_LENGTH)});
//        }
//    }
//
//    @Override
//    public void setVoiceSearchIcon() {
//        if (mSearchView == null || isPickerMode()) {
//            return;
//        }
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        if (searchManager != null) {
//            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        }
//    }
//
//    private SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String s) {
//            ImmUtils.forceHideInputMethodIfShown(getActivity());
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String s) {
//            if (mIsNeedToSendSALog) {
//                insertSALoggingOnQueryTextChange(isPickerMode());
//                mIsNeedToSendSALog = false;
//            }
//            AgendaObservers.getInstance(getContext()).getSearchStringChangedCleanObserver().ifPresent(observer -> observer.onNext(s));
//            return false;
//        }
//    };
//
//    private void initAdapter() {
//        mAgendaAdapter = new AgendaAdapter(mContext, this);
//    }
//
//    private void initRecyclerView(View view) {
//        mRecyclerView = view.findViewById(R.id.agenda_list);
//        mRecyclerView.setAdapter(mAgendaAdapter);
//        mRecyclerView.init();
//        mDefaultGoToTopBottomPadding = mRecyclerView.seslGetGoToTopBottomPadding();
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    AppCompatActivity activity = (AppCompatActivity) getActivity();
//                    if (recyclerView != null && ImmUtils.isInputMethodShown(activity)) {
//                        recyclerView.requestFocus();
//                        if (Feature.isSupportMinimizedSip()) {
//                            ImmUtils.minimizeInputMethodIfShown(activity);
//                        } else {
//                            ImmUtils.forceHideInputMethodIfShown(activity);
//                        }
//                    }
//                }
//                if (!isPickerMode() && newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                    insertSearchViewScrollSALogging(mAgendaType == AgendaType.EDIT);
//                }
//                if (Feature.isSupportHoveringUI(mContext) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    HoverUtils.setHoverPointerIcon(recyclerView, HoverUtils.HOVERING_SPENICON_DEFAULT);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (Feature.isSupportHoveringUI(mContext) && dy != 0) {
//                    boolean mIsUpScroll = dy < 0;
//
//                    HoverUtils.setHoverPointerIcon(recyclerView, mIsUpScroll
//                            ? HoverUtils.HOVERING_SCROLLICON_POINTER_UP
//                            : HoverUtils.HOVERING_SCROLLICON_POINTER_DOWN);
//                }
//            }
//        });
//        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
//            int recyclerViewHeight = mRecyclerView.getHeight();
//            if (recyclerViewHeight != mRecyclerViewHeight) {
//                mRecyclerViewHeight = recyclerViewHeight;
//                mAgendaAdapter.notifyDataSetChanged();
//            }
//        });
//        RoundCornerUtils.setRoundedCorners(mRecyclerView, ViewGlobalCompat.ROUNDED_CORNER_ALL);
//    }
//
//    private void initActionMode(View view) {
//        mAgendaActionMode = new AgendaActionMode(mContext);
//        mAgendaActionMode.setOnAgendaFragmentListener(this);
//        mAgendaSearchBottomNavigationView = view.findViewById(R.id.agenda_search_bottom_navigation);
//        mAgendaSearchBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()) {
//                case R.id.action_delete:
//                    insertSALoggingOnDeleteButtonClicked(mAgendaAdapter.getCheckedItemsCount());
//                    onDeleteButtonClicked();
//                    break;
//                case R.id.action_share:
//                    insertSALoggingOnShareButtonClicked(mAgendaAdapter.getCheckedItemsCount());
//                    onShareButtonClicked();
//                    break;
//                default:
//            }
//            return true;
//        });
//        mAgendaPickBottomNavigationView = view.findViewById(R.id.agenda_pick_bottom_navigation);
//        mAgendaPickBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
//            switch (menuItem.getItemId()) {
//                case R.id.action_done:
//                    insertSALoggingOnDoneButtonClicked();
//                    onDoneButtonClicked();
//                    break;
//                default:
//            }
//            return true;
//        });
//        mAgendaBottomNavigationUpperCorner = view.findViewById(R.id.agenda_bottom_navigation_upper_corner);
//        RoundCornerUtils.setRoundedCorners(mAgendaBottomNavigationUpperCorner,
//                ViewGlobalCompat.ROUNDED_CORNER_BOTTOM_LEFT | ViewGlobalCompat.ROUNDED_CORNER_BOTTOM_RIGHT);
//    }
//
//    private void initProgressBar(View view) {
//        mProgressBar = view.findViewById(R.id.loading_progress);
//    }
//
//    @Override
//    public void onItemClicked(int position) {
//        if (position < 0 || position >= mAgendaAdapter.getItemCount()) {
//            return;
//        }
//        if (mAgendaType == AgendaType.NORMAL) {
//            insertClickItemSALogging();
//        }
//        AgendaObservers.getInstance(getContext()).getItemClickedCleanObserver().ifPresent(observer -> {
//            PickParams pickParams = new PickParams();
//            int itemType = mAgendaAdapter.getItemType(position);
//            long itemId = mAgendaAdapter.getItemId(position);
//            long rowId = mAgendaAdapter.getRowId(position);
//            long startMillis = mAgendaAdapter.getStartMillis(itemId);
//            long endMillis = mAgendaAdapter.getEndMillis(itemId);
//            String launchUri = mAgendaAdapter.getLaunchUri(position);
//            switch (itemType) {
//                case AgendaConstants.AGENDA_ITEM_TYPE.EVENT:
//                    pickParams = new PickParams(itemId, rowId, false, startMillis, endMillis);
//                    break;
//                case AgendaConstants.AGENDA_ITEM_TYPE.TASK:
//                    pickParams = new PickParams(itemId, rowId, true, startMillis, endMillis);
//                    break;
//                case AgendaConstants.AGENDA_ITEM_TYPE.APP_EVENT:
//                    pickParams = new PickParams(itemId, launchUri);
//                    break;
//                case AgendaConstants.AGENDA_ITEM_TYPE.SUBSCRIPTION_EVENT:
//                    if (launchUri != null) {
//                        pickParams = new PickParams(itemId, rowId, mAgendaAdapter.getTitle(position), launchUri);
//                    }
//                    break;
//            }
//            observer.onNext(pickParams);
//        });
//    }
//
//    @Override
//    public boolean onItemLongClicked(int position) {
//        AgendaObservers.getInstance(getContext()).getItemLongClickedCleanObserver().ifPresent(observer -> observer.onNext(position));
//        return true;
//    }
//
//    @Override
//    public void onHeaderClicked() {
//        insertHeaderFooterClickedSALogging(true);
//        AgendaObservers.getInstance(getContext()).getMonthRangeBackExpandClickedCleanObserver().ifPresent(CleanObserver::onNext);
//    }
//
//    @Override
//    public void onFooterClicked() {
//        insertHeaderFooterClickedSALogging(false);
//        AgendaObservers.getInstance(getContext()).getMonthRangeAfterExpandClickedCleanObserver().ifPresent(CleanObserver::onNext);
//    }
//
//    @Override
//    public void onTaskCheckboxClicked(long id, boolean isChecked) {
//        insertTaskCheckboxSALogging(isChecked);
//        CompleteTaskParams completeTaskParams = new CompleteTaskParams(id, isChecked);
//        AgendaObservers.getInstance(getContext()).getCompleteTaskClickedCleanObserver().ifPresent(observer -> observer.onNext(completeTaskParams));
//    }
//
//    @Override
//    public void onActionModeCreated() {
//        AgendaObservers.getInstance(getContext()).getEditModeCreatedCleanObserver().ifPresent(CleanObserver::onNext);
//    }
//
//    @Override
//    public void onActionModeDestroyed() {
//        AgendaObservers.getInstance(getContext()).getEditModeDestroyedCleanObserver().ifPresent(CleanObserver::onNext);
//    }
//
//    @Override
//    public void onAllCheckBoxClicked(boolean isChecked) {
//        AgendaObservers.getInstance(getContext()).getAllCheckBoxClickedCleanObserver().ifPresent(observer -> observer.onNext(isChecked));
//    }
//
//    @Override
//    public void onDeleteButtonClicked() {
//        if (mAgendaAdapter.getCheckedItemsCount() < 1) {
//            return;
//        }
//        AgendaObservers.getInstance(getContext()).getDeleteAgendaDataClickedCleanObserver().ifPresent(observer -> observer.onNext(mAgendaAdapter.getDeleteParams()));
//    }
//
//    @Override
//    public void onShareButtonClicked() {
//        if (mAgendaAdapter.getCheckedItemsCount() < 1) {
//            return;
//        }
//        final Intent intent = getActivity().getIntent();
//        final String mimeType = intent.getType();
//        final boolean isIcsSupportFromMessage = intent.getBooleanExtra("support_ics", false);
//        final String callingPackageName = getActivity().getCallingActivity() != null ? getActivity().getCallingActivity().getPackageName() : StringUtils.EMPTY;
//        ShareType shareType = ShareType.SHARE;
//        if (!TextUtils.isEmpty(mimeType)) {
//            if (mimeType.equals(MimeType.TEXT_PLAIN)) {
//                shareType = ShareType.PICK_TEXT;
//            } else if (mimeType.equals(MimeType.TEXT_IDS)) {
//                shareType = ShareType.PICK_IDS;
//            } else if (ShareViaHelper.isMessageApp(getContext(), callingPackageName)) {
//                shareType = ShareType.PICK_FILE_OR_TEXT;
//            } else {
//                shareType = ShareType.PICK;
//            }
//        }
//        ShareParams shareParams = new ShareParams(shareType,
//                mAgendaAdapter.getCheckedItemEventIds(),
//                mAgendaAdapter.getCheckedItemTaskIds(),
//                mAgendaAdapter.getCheckedItemStartMillis(),
//                callingPackageName,
//                isIcsSupportFromMessage);
//        AgendaObservers.getInstance(getContext()).getShareAgendaDataClickedCleanObserver().ifPresent(observer -> observer.onNext(shareParams));
//    }
//
//    @Override
//    public void onDoneButtonClicked() {
//        onShareButtonClicked();
//    }
//
//    @Override
//    public void onStickerRequested(String stickerId) {
//        AgendaObservers.getInstance(getContext()).getStickerRequestedCleanObserver().ifPresent(observer -> observer.onNext(stickerId));
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void hide() {
//        if (mSavedInstanceState == null) {
//            getActivity().finish();
//        }
//    }
//
//    @Override
//    public void invalidate() {
//
//    }
//
//    private boolean isPickerMode() {
//        return mAgendaType == AgendaType.SELECT || mAgendaType == AgendaType.SELECT_TEXT || mAgendaType == AgendaType.MULTI_SELECT;
//    }
//
//    @Override
//    public void setAgendaType(AgendaType agendaType) {
//        mAgendaType = agendaType;
//        mAgendaAdapter.setAgendaType(agendaType);
//        mAgendaActionMode.setAgendaType(agendaType);
//    }
//
//    @Override
//    public void setMaxPickCount(int maxPickCount) {
//        mAgendaAdapter.setMaxPickCount(maxPickCount);
//        mAgendaActionMode.setMaxPickCount(maxPickCount);
//    }
//
//    @Override
//    public void setAgendaDataList(List<AgendaData> agendaDataList, long startMillis, long endMillis, String searchString) {
//        mAgendaAdapter.setAgendaDataList(agendaDataList, startMillis, endMillis, searchString);
//        if (mAgendaType == AgendaType.MULTI_SELECT) {
//            mAgendaActionMode.setEnabledSelectAll(!agendaDataList.isEmpty());
//        }
//    }
//
//    @Override
//    public void setSticker(@NonNull StickerItem stickerItem) {
//        mAgendaAdapter.setSticker(stickerItem);
//    }
//
//    @Override
//    public void expandActionBar() {
//        mExpandedActionBarLayout = getActivity().findViewById(R.id.expanded_action_bar);
//        mExpandedActionBarLayout.setVisibility(View.VISIBLE);
//        mSearchView = mExpandedActionBarLayout.findViewById(R.id.search_view);
//        if (mSearchView != null) {
//            mSearchView.setOnQueryTextListener(mOnQueryTextListener);
//            limitSearchTextLength(mContext, mSearchView);
//            mSearchView.clearFocus();
//        }
//    }
//
//    @Override
//    public void refreshEditMode(boolean isSelectEnded) {
//        /* Action Mode */
//        boolean isCheckedItemsNotDeletable = mAgendaAdapter.isCheckedItemsNotDeletable();
//        boolean isCheckedItemsNotSharable = mAgendaAdapter.isCheckedItemsNotSharable();
//        mAgendaActionMode.setAllChecked(mAgendaAdapter.isAllChecked());
//        mAgendaActionMode.setCheckedCount(mAgendaAdapter.getCheckedItemsCount());
//        mAgendaActionMode.setIsCheckedItemsNotDeletable(isCheckedItemsNotDeletable);
//        mAgendaActionMode.setIsCheckedItemsNotSharable(isCheckedItemsNotSharable);
//        mAgendaActionMode.invalidate();
//        /* Bottom Navigation */
//        if (mAgendaType == AgendaType.NORMAL) {
//            MenuUtils.startSlideAnimation(mAgendaSearchBottomNavigationView, false);
//            MenuUtils.startSlideAnimation(mAgendaPickBottomNavigationView, false);
//            MenuUtils.startSlideAnimation(mAgendaBottomNavigationUpperCorner, false);
//            mRecyclerView.seslSetGoToTopBottomPadding(mDefaultGoToTopBottomPadding);
//        } else if (mAgendaType == AgendaType.EDIT) {
//            boolean needBottomMenu = (isSelectEnded || mAgendaSearchBottomNavigationView.getVisibility() == View.VISIBLE)
//                    && mAgendaAdapter.getCheckedItemsCount() > 0 && !ActivityModeUtils.isLandscape(getContext());
//            MenuUtils.startSlideAnimation(mAgendaSearchBottomNavigationView, needBottomMenu);
//            MenuUtils.startSlideAnimation(mAgendaBottomNavigationUpperCorner, needBottomMenu);
//            if (needBottomMenu) {
//                MenuUtils.setMenuEnabled(mAgendaSearchBottomNavigationView, R.id.action_delete, !isCheckedItemsNotDeletable);
//                MenuUtils.setMenuVisible(mAgendaSearchBottomNavigationView, R.id.action_share,
//                        !isCheckedItemsNotSharable && mAgendaAdapter.getCheckedItemsCount() <= AgendaConstants.MAX_SHARE_COUNT);
//            }
//            mRecyclerView.seslSetGoToTopBottomPadding(needBottomMenu ? getResources().getDimensionPixelSize(R.dimen.action_bar_height) + mDefaultGoToTopBottomPadding : mDefaultGoToTopBottomPadding);
//        } else if (mAgendaType == AgendaType.MULTI_SELECT) {
//            boolean needBottomMenu = (isSelectEnded || mAgendaSearchBottomNavigationView.getVisibility() == View.VISIBLE)
//                    && mAgendaAdapter.getCheckedItemsCount() > 0 && !ActivityModeUtils.isLandscape(getContext());
//            MenuUtils.startSlideAnimation(mAgendaPickBottomNavigationView, needBottomMenu);
//            MenuUtils.startSlideAnimation(mAgendaBottomNavigationUpperCorner, needBottomMenu);
//            mRecyclerView.seslSetGoToTopBottomPadding(needBottomMenu ? getResources().getDimensionPixelSize(R.dimen.action_bar_height) + mDefaultGoToTopBottomPadding : mDefaultGoToTopBottomPadding);
//        }
//    }
//
//    @Override
//    public void startEditMode() {
//        ((AppCompatActivity) getActivity()).startSupportActionMode(mAgendaActionMode);
//    }
//
//    @Override
//    public void stopEditMode() {
//        mAgendaActionMode.finish();
//    }
//
//    @Override
//    public void setChecked(PickParams pickParams) {
//        long itemId = pickParams.itemId;
//        mAgendaAdapter.setChecked(itemId, !mAgendaAdapter.isChecked(itemId));
//        mAgendaAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void setChecked(int minPosition, int maxPosition) {
//        if (!mAgendaAdapter.isActionMode()) {
//            startEditMode();
//        }
//        for (int position = minPosition; position <= maxPosition; position++) {
//            final long id = mAgendaAdapter.getItemId(position);
//            mAgendaAdapter.setChecked(id, !mAgendaAdapter.isChecked(id));
//        }
//        mAgendaAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void setChecked(long itemId, boolean isChecked) {
//        mAgendaAdapter.setChecked(itemId, isChecked);
//        mAgendaAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void setAllChecked(boolean isChecked) {
//        mAgendaAdapter.setAllChecked(isChecked);
//        mAgendaAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void enableDragSelect(int position) {
//        mRecyclerView.seslStartLongPressMultiSelection();
//        AgendaObservers.getInstance(getContext()).getDragSelectedCleanObserver().ifPresent(observer ->
//                observer.onNext(new DragSelectParams(mAgendaAdapter.getItemId(position), true)));
//        if (mIsConfirmKeyPressed) {
//            AgendaObservers.getInstance(getContext()).getDragSelectEndedCleanObserver().ifPresent(CleanObserver::onNext);
//        }
//    }
//
//    @Override
//    public void startCheckBoxShowAnimation() {
//        RecyclerViewUtils.startCheckBoxShowAnimation(mContext, mAgendaAdapter.getSlideContainerList());
//    }
//
//    @Override
//    public void startCheckBoxHideAnimation() {
//        RecyclerViewUtils.startCheckBoxHideAnimation(mContext, mAgendaAdapter.getSlideContainerList());
//    }
//
//    @Override
//    public void scrollToSelectedTime(long selectedTimeInMillis) {
//        int nearestItemPosition = mAgendaAdapter.getNearestItemPosition(selectedTimeInMillis);
//        if (nearestItemPosition > 0) {
//            mRecyclerView.scrollToPosition(nearestItemPosition);
//        }
//    }
//
//    @Override
//    public AgendaViewObservables getObservables() {
//        return new AgendaViewObservablesImpl(getContext());
//    }
//
//    @Override
//    public void setVoiceSearchQuery(String query) {
//        mSearchView.setQuery(query, false);
//        insertVoiceSearchSALogging(TextUtils.isEmpty(query));
//    }
//
//    @Override
//    public void onKeyDown(int keyCode) {
//        if (ViewUtils.isConfirmKey(keyCode)) {
//            mIsConfirmKeyPressed = true;
//        }
//    }
//
//    @Override
//    public void onKeyUp(int keyCode) {
//        if (ViewUtils.isConfirmKey(keyCode)) {
//            mIsConfirmKeyPressed = false;
//        }
//    }
//
//    @Override
//    public void showProgress() {
//        if (mProgressBar != null) {
//            mProgressBar.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void hideProgress() {
//        if (mProgressBar != null) {
//            mProgressBar.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void showSharedUI() {
//        if (mAgendaAdapter.getCheckedItemTaskIds().size() > 0) {
//            final int checkedItemEventIdsSize = mAgendaAdapter.getCheckedItemEventIds().size();
//            String quantityString = getContext().getResources().getQuantityString(R.plurals.share_via_cant_share_tasks_tpop, checkedItemEventIdsSize);
//            ToastUtils.showToast(getContext(), String.format(quantityString, checkedItemEventIdsSize));
//        }
//    }
//
//    @Override
//    public void showSearchView() {
//        if (mSearchView != null) {
//            mSearchView.setFocusable(true);
//            mSearchView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void hideSearchView() {
//        if (mSearchView != null) {
//            mSearchView.clearFocus();
//            mSearchView.setFocusable(false);
//            mSearchView.setVisibility(View.GONE);
//        }
//        ImmUtils.forceHideInputMethodIfShown(getActivity());
//    }
//
//    @Override
//    public void requestSearchViewFocus() {
//        if (mSearchView == null) {
//            return;
//        }
//
//        if (isRestoredHideInputMethod()) {
//            mSearchView.clearFocus();
//            return;
//        }
//
//        mSearchView.requestFocus();
//        if (!ImmUtils.isInputMethodShown(mContext)) {
//            ImmUtils.forcedShowInputMethod(mContext, mSearchView);
//        }
//    }
//
//    private boolean isRestoredHideInputMethod() {
//        return mSavedInstanceState != null && !mSavedInstanceState.getBoolean(AgendaConstants.SAVED_INSTANCE_SIP_SHOWN, false);
//    }
//
//    @Override
//    public void setPenSelectionEnabled(boolean isEnabled) {
//        mRecyclerView.seslSetPenSelectionEnabled(isEnabled);
//    }
//}
