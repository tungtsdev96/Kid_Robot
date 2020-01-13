//package com.android.tupple.robot.utils.test.agenda;
//
//import com.samsung.android.app.cleancalendar.entity.agenda.AgendaType;
//import com.samsung.android.app.cleancalendar.entity.share.PickParams;
//import com.samsung.android.libcalendar.cleanobjects.View;
//
//import java.util.List;
//
//public interface AgendaView<A, S> extends View {
//
//    void setAgendaType(AgendaType agendaType);
//
//    void setMaxPickCount(int maxPickCount);
//
//    void setAgendaDataList(List<A> agendaDataList, long startMillis, long endMillis, String searchString);
//
//    void setSticker(S stickerItem);
//
//    void expandActionBar();
//
//    void refreshEditMode(boolean isSelectEnded);
//
//    void startEditMode();
//
//    void stopEditMode();
//
//    void setChecked(PickParams pickParams);
//
//    void setChecked(int minPosition, int maxPosition);
//
//    void setChecked(long itemId, boolean isChecked);
//
//    void setAllChecked(boolean isChecked);
//
//    void enableDragSelect(int position);
//
//    void startCheckBoxShowAnimation();
//
//    void startCheckBoxHideAnimation();
//
//    void scrollToSelectedTime(long selectedTimeInMillis);
//
//    AgendaViewObservables getObservables();
//
//    void setVoiceSearchQuery(String query);
//
//    void onKeyDown(int keyCode);
//
//    void onKeyUp(int keyCode);
//
//    void restoreInstanceState();
//
//    void showProgress();
//
//    void hideProgress();
//
//    void showSharedUI();
//
//    void showSearchView();
//
//    void hideSearchView();
//
//    void requestSearchViewFocus();
//
//    void setPenSelectionEnabled(boolean isEnabled);
//
//    void setVoiceSearchIcon();
//}
