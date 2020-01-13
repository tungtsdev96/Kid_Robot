//package com.android.tupple.robot.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.text.TextUtils;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.samsung.android.calendarutils.Logger;
//
//import java.util.Optional;
//
//public class PackageUtils {
//    private static final String TAG = "PackageUtils";
//
//    private static final String SAMSUNG_HOME_PACKAGE_NAME = "com.sec.android.app.launcher";
//    public static final String GOOGLE_CALENDAR_PACKAGE_NAME = "com.google.android.calendar";
//
//    // TODO
//    public static boolean isSamsungHome(Context context) {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//
//        PackageManager packageManager = context == null ? null : context.getPackageManager();
//        ResolveInfo resolveInfo = packageManager == null ? null : packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        ActivityInfo activityInfo = resolveInfo == null ? null : resolveInfo.activityInfo;
//        String defaultHomePackage = activityInfo == null ? null : activityInfo.packageName;
//        if (TextUtils.isEmpty(defaultHomePackage)) {
//            return false;
//        }
//        return SAMSUNG_HOME_PACKAGE_NAME.equals(defaultHomePackage);
//    }
//
//    public static boolean isPackageEnabled(Context context, @NonNull final String packageName) {
//        return getPackageInfo(context, packageName)
//                .map(packageInfo -> packageInfo.applicationInfo.enabled)
//                .orElse(false);
//    }
//
//    public static boolean isPackageExist(Context context, @NonNull String targetPackage) {
//        try {
//            context.getPackageManager().getApplicationInfo(targetPackage, 0);
//            return true;
//        } catch (PackageManager.NameNotFoundException nnfe) {
//            Logger.d(TAG, targetPackage + " is not found");
//            return false;
//        }
//    }
//
//    private static Optional<PackageInfo> getPackageInfo(Context context, @NonNull final String packageName) {
//        return getPackageManager(context).map(pm -> getPackageInfo(pm, packageName, 0));
//    }
//
//    @Nullable
//    private static PackageInfo getPackageInfo(PackageManager packageManager, @NonNull final String packageName, final int flag) {
//        try {
//            return packageManager.getPackageInfo(packageName, flag);
//        } catch (PackageManager.NameNotFoundException e) {
//            Logger.e(TAG, "getPackageInfo NameNotFoundException : " + packageName);
//            return null;
//        } catch (RuntimeException e) {
//            Logger.e(TAG, "getPackageInfo Unhandled exception during finding " + packageName + " package on Device, " + e.getMessage());
//            return null;
//        }
//    }
//
//    public static Optional<PackageManager> getPackageManager(Context context) {
//        return Optional.ofNullable(context).map(Context::getPackageManager);
//    }
//}
