/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.jarklee.essential.common.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jarklee.essential.common.method.Invokable;
import com.jarklee.essential.common.method.Mapper;

import java.io.File;

public class FileHelper {

    public static File getCachedFolder(@NonNull final Context context) {
        File cachedFolder = context.getExternalCacheDir();
        if (cachedFolder == null) {
            cachedFolder = context.getCacheDir();
        }
        return cachedFolder;
    }

    public static File getAppFolder(@NonNull final Context context) {
        File appFolder = context.getExternalFilesDir(null);
        if (appFolder == null) {
            appFolder = context.getFilesDir();
        }
        return appFolder;
    }

    public static File getRandomTempFile(@NonNull final Context context) {
        final File cacheDir = getCachedFolder(context);
        String randomFileName = String.format("temp_file_%s.tmp", StringHelper.randomString("temp_file"));
        File tempFile = new File(cacheDir, randomFileName);
        while (tempFile.exists()) {
            randomFileName = String.format("temp_file_%s.tmp", StringHelper.randomString("temp_file"));
            tempFile = new File(cacheDir, randomFileName);
        }
        return tempFile;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void tempFile(@NonNull final Context context,
                                @NonNull final Invokable<File> tempFileInvoker) {
        final File tempFile = getRandomTempFile(context);
        try {
            tempFileInvoker.invoke(tempFile);
        } finally {
            tempFile.delete();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <T> T tempFile(@NonNull final Context context,
                                 @NonNull final Mapper<File, T> tempFileInvoker) {
        final File tempFile = getRandomTempFile(context);
        try {
            return tempFileInvoker.apply(tempFile);
        } finally {
            tempFile.delete();
        }
    }
}
