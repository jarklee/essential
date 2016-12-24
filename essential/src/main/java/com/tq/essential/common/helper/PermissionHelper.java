/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.essential.common.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionHelper {

    public static boolean has(Context context, String... permissions) {
        if (context == null) {
            return false;
        }
        if (permissions == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        boolean granted = true;
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                granted = false;
                break;
            }
        }
        return granted;
    }

    public static void request(Activity activity, int requestID, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        if (activity != null && permissions != null) {
            activity.requestPermissions(permissions, requestID);
        }
    }

    public static boolean isGranted(int[] grantedResults) {
        if (grantedResults == null) {
            return true;
        }
        boolean granted = true;
        for (int grantedResult : grantedResults) {
            if (grantedResult != PackageManager.PERMISSION_GRANTED) {
                granted = false;
                break;
            }
        }
        return granted;
    }
}