///*
// * Copyright (C) 2015 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.android.tupple.robot.utils;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.res.Resources;
//import android.text.method.LinkMovementMethod;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.core.content.ContextCompat;
//
//import com.samsung.android.calendarutils.Logger;
//import com.samsung.android.libcalendar.common.R;
//import com.samsung.android.libcalendar.common.constant.PreferenceConstants;
//
//import io.reactivex.annotations.NonNull;
//
//import static com.samsung.android.libcalendar.common.utils.PreferenceUtils.setSharedPreference;
//
//public class PermissionUtils {
//
//    private static final String TAG = "PermissionUtils";
//    private static final String ACTION_CONTACT_BIRTHDAY_EVENT_SYNC = "com.samsung.android.calendar.SYNC_CONTACT_BIRTHDAY_EVENT";
//
//    public static boolean hasPermissions(final Context context, final String... permissions) {
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                Logger.secD(TAG, "hasPermissions return false : " + permission);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isAllPermissionGranted(final int... grantResults) {
//        if (grantResults.length < 1) {
//            return false;
//        }
//        for (int result : grantResults) {
//            if (result == PackageManager.PERMISSION_DENIED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public interface createNetworkAndContactPermissionCallBack {
//        void onDismiss();
//    }
//
//    public static void createNetworkPermissionDialog(final Context context, @NonNull createNetworkAndContactPermissionCallBack callBack) {
//        if (context == null) {
//            return;
//        }
//        View notice = View.inflate(context, R.layout.allow_contact_read_permission, null);
//        TextView message = notice.findViewById(R.id.allow_app_permissions_message);
//        Resources res = context.getResources();
//        StringBuilder allowAppPermissionString = new StringBuilder(1280);
//
//        String str = String.format(res.getString(R.string.allow_calendar_access_the_data), res.getString(R.string.app_label));
//        allowAppPermissionString.append(str);
//        allowAppPermissionString.append("\n\n");
//        allowAppPermissionString.append("·");
//        allowAppPermissionString.append("  ");
//        allowAppPermissionString.append(res.getString(R.string.allow_app_permission_message_string_contact));
//
//        allowAppPermissionString.append("\n\n");
//        allowAppPermissionString.append(res.getString(R.string.allow_transfer_data));
//        allowAppPermissionString.append("\n\n");
//        allowAppPermissionString.append(res.getString(R.string.allow_calendar_agree_to_all_of_the_above));
//
//        message.setText(allowAppPermissionString);
//        message.setMovementMethod(LinkMovementMethod.getInstance());
//
//        AlertDialog.Builder allowPermissionsDialog = new AlertDialog.Builder(context);
//        allowPermissionsDialog
//                .setView(notice)
//                .setCancelable(false)
//                .setPositiveButton(R.string.allow, (dialog, which) -> {
//                    setPreferenceValues(context, true);
//                    setSharedPreference(context, PreferenceConstants.KEY_CHINA_HOLIDAY_AUTO_UPDATE_SETTINGS, true);
//                    closeDialog(dialog);
//                    sendContactBirthdaySyncIntent(context);
//                })
//                .setNegativeButton(R.string.deny, (dialog, which) -> {
//                    setPreferenceValues(context, false);
//                    closeDialog(dialog);
//                });
//        if (callBack != null) {
//            allowPermissionsDialog.setOnDismissListener(dialogInterface -> callBack.onDismiss());
//        }
//        allowPermissionsDialog.show();
//    }
//
//    public static void closeDialog(DialogInterface dialog) {
//        if (dialog != null && ((Dialog) dialog).isShowing()) {
//            dialog.dismiss();
//        }
//    }
//
//    public static void setPreferenceValues(Context context, boolean value) {
//        setSharedPreference(context, PreferenceConstants.PREFERENCES_CREATE_NETWORK_DIALOG, value);
//        setSharedPreference(context, PreferenceConstants.PREFERENCES_AGREE_NETWORK_NOTICE, value);
//        setSharedPreference(context, PreferenceConstants.PREFERENCES_AGREE_LEGAL_NOTICE, value);
//    }
//
//    public static void sendContactBirthdaySyncIntent(Context context) {
//        Intent intent = new Intent();
//        intent.setAction(ACTION_CONTACT_BIRTHDAY_EVENT_SYNC);
//        context.sendBroadcast(intent);
//        Logger.secD(TAG, "Send broadcast : " + ACTION_CONTACT_BIRTHDAY_EVENT_SYNC);
//    }
//
//    public static void createHolidayUpdateLegalDialog(final Context context) {
//        View notice = View.inflate(context, R.layout.allow_holiday_update_legal_permission, null);
//        TextView message = notice.findViewById(R.id.allow_holiday_update_legal_message);
//        Resources res = context.getResources();
//        StringBuilder allowAppPermissionString = new StringBuilder(1280);
//
//        String str = String.format(res.getString(R.string.allow_calendar_use_network_to_connections_follow_data), res.getString(R.string.app_label));
//        allowAppPermissionString.append(str);
//        allowAppPermissionString.append(res.getString(R.string.allow_downloaded_holiday_calendar_update_and_updated_automatically));
//
//        message.setText(allowAppPermissionString);
//        message.setMovementMethod(LinkMovementMethod.getInstance());
//
//        AlertDialog.Builder allowPermissionsDialog = new AlertDialog.Builder(context);
//        allowPermissionsDialog
//                .setView(notice)
//                .setCancelable(false)
//                .setPositiveButton(R.string.allow, (dialog, which) -> {
//                    setPreferenceHolidayUpdateLegalValues(context, true);
//                    closeDialog(dialog);
//                })
//                .setNegativeButton(R.string.deny, (dialog, which) -> {
//                    closeDialog(dialog);
//                });
//        allowPermissionsDialog.show();
//    }
//
//    public static void setPreferenceHolidayUpdateLegalValues(Context context, boolean value) {
//        setSharedPreference(context, PreferenceConstants.PREFERENCES_ALLOW_HOLIDAY_UPDATE, value);
//        setSharedPreference(context, PreferenceConstants.KEY_CHINA_HOLIDAY_AUTO_UPDATE_SETTINGS, value);
//    }
//}
