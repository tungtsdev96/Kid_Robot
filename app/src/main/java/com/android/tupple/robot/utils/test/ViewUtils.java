//package com.android.tupple.robot.utils;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Outline;
//import android.util.TypedValue;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewOutlineProvider;
//import android.widget.TextView;
//
//import androidx.annotation.LayoutRes;
//import androidx.appcompat.widget.AppCompatButton;
//
//public class ViewUtils {
//
//    private static final int[] LARGER_FONT_SIZE = {
//            29, 31, 33, 35, 37
//    };
//
//    // prevent to instantiate this object.
//    private ViewUtils() {
//    }
//
//    public static final ViewOutlineProvider OVAL_OUTLINE_PROVIDER = new ViewOutlineProvider() {
//        public void getOutline(View view, Outline outline) {
//            outline.setOval(0, 0, view.getWidth(), view.getHeight());
//        }
//    };
//
//    public static View inflate(final Context context, @LayoutRes final int layoutId) {
//        return LayoutInflater.from(context).inflate(layoutId, null);
//    }
//
//    public static View inflate(final Context context, @LayoutRes final int layoutId, final ViewGroup viewGroup) {
//        return LayoutInflater.from(context).inflate(layoutId, viewGroup, true);
//    }
//
//    public static View inflate(final Context context, @LayoutRes final int layoutId, final ViewGroup viewGroup,
//                               final boolean attachToRoot) {
//        return LayoutInflater.from(context).inflate(layoutId, viewGroup, attachToRoot);
//    }
//
//    public static View inflateWithNoAttach(final Context context, @LayoutRes final int layoutId) {
//        return View.inflate(context, layoutId, null);
//    }
//
//    public static void setVisibility(View view, boolean isVisible) {
//        if (view == null) {
//            return;
//        }
//        @Visibility final int originalVisibility = view.getVisibility();
//        @Visibility final int targetVisibility = isVisible ? View.VISIBLE : View.GONE;
//        if (targetVisibility != originalVisibility) {
//            view.setVisibility(targetVisibility);
//        }
//    }
//
//    public static void setVisibility(View view, @Visibility int visibility) {
//        if (view == null) {
//            return;
//        }
//        @Visibility final int originalVisibility = view.getVisibility();
//        if (visibility != originalVisibility) {
//            view.setVisibility(visibility);
//        }
//    }
//
//    public static void setLargeFontOnTextView(Context context, TextView textView) {
//        if (context == null || textView == null) {
//            return;
//        }
//
//        if (SettingUtils.isHugeFont(context)) {
//            int fontSize = SettingUtils.getSettingsFontSize(context) - 6;
//            if (fontSize >= 0 && fontSize < LARGER_FONT_SIZE.length) {
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, LARGER_FONT_SIZE[fontSize]);
//            }
//        }
//    }
//
//    public static void setLargeFontOnTextView(Context context, TextView textView, float defaultSize) {
//        if (context == null || textView == null) {
//            return;
//        }
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * getFontScale(context));
//    }
//
//    public static void setLargeFontOnAppCompactButton(Context context, AppCompatButton button, float defaultSize) {
//        if (context == null || button == null) {
//            return;
//        }
//        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * getFontScale(context));
//    }
//
//    public static float getLargeFontSize(Resources res, int defaultSize) {
//        return defaultSize * getFontScale(res);
//    }
//
//    private static float getFontScale(Context context) {
//        return getFontScale(context.getResources());
//    }
//
//    private static float getFontScale(Resources res) {
//        final float MAX_FONT_SCALE = 1.2f;
//        float fontScale = res.getConfiguration().fontScale;
//        if (fontScale > MAX_FONT_SCALE) {
//            fontScale = MAX_FONT_SCALE;
//        }
//        return fontScale;
//    }
//
//    public static boolean isConfirmKey(int keyCode) {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_DPAD_CENTER:
//            case KeyEvent.KEYCODE_ENTER:
//            case KeyEvent.KEYCODE_SPACE:
//            case KeyEvent.KEYCODE_NUMPAD_ENTER:
//                return true;
//            default:
//                return false;
//        }
//    }
//}
