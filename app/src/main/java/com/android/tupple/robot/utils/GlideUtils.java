package com.android.tupple.robot.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;

public class GlideUtils {

    public static RequestManager getRequestManager(Context context) {
        if (isValidContextForGlide(context)) {
            return Glide.with(context);
        }

        return null;
    }

    private static boolean isValidContextForGlide(Context context) {
        if (context == null) {
            return false;
        }

        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }

    public static void clear(Context context, View targetView) {
        try {
            Glide.with(context).clear(targetView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageFromStorage(Context context, String path, ImageView imageView) {
        RequestManager requestManager = getRequestManager(context);
        if (requestManager == null || path == null) {
            return;
        }

        final long lastModified = FileManager.getLastModified(path);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .signature(new ObjectKey(lastModified));

        File imagePath = new File(path);
        Uri uri = Uri.fromFile(imagePath);
        requestManager
                .load(uri)
                .apply(requestOptions)
                .into(imageView);
    }

//    RequestOptions requestOptions = new RequestOptions()
//            .transforms(new CenterCrop(), new RoundedCorners(mThumbnailRoundRadius))
//            .signature(new ObjectKey(lastModified));
//            GlideUtils.getRequestManager(mContext).ifPresent(requestManager -> requestManager.load(imagePath).apply(requestOptions)
//                    .listener(new RequestListener<Drawable>() {
//        @Override
//        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//            Logger.w(TAG, "onLoadFailed, " + ", model : " + model);
//            if (e != null) {
//                e.printStackTrace();
//            }
//
//            updateContainerInvisible();
//            return false;
//        }
//
//        @Override
//        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource
//        dataSource, boolean isFirstResource) {
//            Logger.d(TAG, "onResourceReady, " + ", dataSource : " + dataSource + ", model = " + model);
//            updateContainerVisible(subIconDrawable);
//            return false;
//        }
//    }).into(thumbnailImage));

//    GlideUtils.clear(mContext, stickerImage);
}
