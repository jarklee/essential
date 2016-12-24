/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.libs.common.helper;

import android.content.Context;

import java.io.File;

public class FileHelper {

    public static File getCachedFolder(Context context) {
        File cachedFolder = context.getExternalCacheDir();
        if (cachedFolder == null) {
            cachedFolder = context.getCacheDir();
        }
        return cachedFolder;
    }

    public static File getAppFolder(Context context) {
        File appFolder = context.getExternalFilesDir(null);
        if (appFolder == null) {
            appFolder = context.getFilesDir();
        }
        return appFolder;
    }
}
