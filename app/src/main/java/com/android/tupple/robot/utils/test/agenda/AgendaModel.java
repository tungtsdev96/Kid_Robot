//package com.android.tupple.robot.utils.test.agenda;
//
//import com.samsung.android.app.cleancalendar.entity.agenda.AgendaType;
//import com.samsung.android.libcalendar.cleanobjects.CleanObservable;
//import com.samsung.android.libcalendar.cleanobjects.Model;
//import com.samsung.android.libcalendar.cleanobjects.Period;
//
//import java.util.List;
//
//public interface AgendaModel<A, S> extends Model {
//
//    CleanObservable<List<A>> getAgendaDataList(AgendaType agendaType, Period period, String searchQuery);
//
//    CleanObservable<Boolean> getIsAgendaDataEmpty(AgendaType agendaType, Period period);
//
//    CleanObservable<S> getStickerData(String stickerId);
//
//    void completeTask(long id, boolean isCompleted);
//}
